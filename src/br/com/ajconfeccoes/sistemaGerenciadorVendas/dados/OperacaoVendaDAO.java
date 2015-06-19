/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.dados;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.ItensCompra;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.OperacaoVenda;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author arley
 */
public class OperacaoVendaDAO {
    private static String SQL_INSERT = "INSERT INTO VENDA(DATA, VALORTOTAL, USUARIO, CLIENTE)  VALUES(?, ?, ?, ?)";
    private static String SQL_SELECT_TODOS = "SELECT * FROM VENDA";
    private static String SQL_SELECT_BY_CODIGO = "SELECT * FROM VENDA WHERE CODIGO = ?";
    private static String SQL_SELECT_BY_DATA = "SELECT * FROM VENDA WHERE DATA = ?";
    
    public void criar(OperacaoVenda operacaoVenda) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            comando.setString(1, operacaoVenda.getDataFormatadaBanco());
            comando.setDouble(2, operacaoVenda.getValorTotal());
            comando.setString(3, operacaoVenda.getUsuario().getCpf());
            //comando.setString(4, operacaoVenda.getUsuario().getNome());
            comando.setString(4, operacaoVenda.getCliente().getCpf());
            //comando.setString(6, operacaoVenda.getCliente().getNome());
            comando.execute();
            resultado = comando.getGeneratedKeys();
            while (resultado.next()) {
                operacaoVenda.setCodigo(resultado.getInt(1));
            }
            
            ItensCompraDAO itensCompraDAO = new ItensCompraDAO();
            for (ItensCompra item : operacaoVenda.getItensCompra()) {
                itensCompraDAO.criar(conexao, operacaoVenda.getCodigo(), item);
            }
            
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.criar(conexao, operacaoVenda);
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException();
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
    
}
