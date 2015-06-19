/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.dados;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Cliente;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Conta;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.ItensCompra;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.OperacaoVenda;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Produto;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arley
 */
public class ContaDAO {

    private static String SQL_INSERT = "INSERT INTO CONTA(VALORTOTAL, VENDA) VALUES (?, ?)";
    private static String SQL_SELECT_TODOS = "SELECT C.CODIGO, C.STATUS, CLI.NOME, CLI.CPF, C.VALORTOTAL, C.DATA FROM CONTA C JOIN VENDA V ON C.VENDA = V.CODIGO JOIN CLIENTE CLI ON V.CLIENTE = CLI.CPF";
    private static String SQL_SELECT_BY_CODIGO = "SELECT * FROM CONTAS WHERE CODIGO = ?";
    private static String SQL_SELECT_BY_VENDA = "SELECT * FROM CONTA WHERE VENDA = ?";
    private static String SQL_UPDATE_STATUS_CONTA = "UPDATE CONTA SET STATUS = FALSE WHERE CODIGO = ?";
    
    public void criar(Connection conexao, OperacaoVenda venda) throws SQLException {
        PreparedStatement comando = null;
        comando = conexao.prepareStatement(SQL_INSERT);
        comando.setDouble(1, venda.getValorTotal());
        comando.setInt(2, venda.getCodigo());
        comando.execute();

    }
    
    public Conta buscarPorVenda(int codigoVenda) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Conta conta = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_VENDA);
            comando.setInt(1, codigoVenda);
            resultado = comando.executeQuery();
            while(resultado.next()){
                conta = new Conta();
                conta.setCodigo(resultado.getInt("CODIGO"));
                conta.setData(resultado.getDate("DATA"));
                conta.setStatus(resultado.getBoolean("STATUS"));
                conta.setValorTotal(resultado.getDouble("VALORTOTAL"));
            }
                
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
        return conta;
    }

    public List<Conta> buscarTodos() throws SQLException {
        Conta conta = null;
        Cliente cliente = null;
        List<Conta> contas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_TODOS);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                //Conta
                conta = new Conta();
                conta.setCodigo(resultado.getInt(1));
                conta.setStatus(resultado.getBoolean(2));
                conta.setValorTotal(resultado.getDouble(5));
                conta.setData(resultado.getDate(6));

                //Cliente
                cliente = new Cliente();
                cliente.setCpf(resultado.getString(4));
                cliente.setNome(resultado.getString(3));

                //Adiciona cliente nas operações venda
                OperacaoVenda ov = new OperacaoVenda();
                ov.setCliente(cliente);

                //Adciona operacao venda em conta
                conta.setOperacaoVenda(ov);

                //Adiciona conta na lista de contas
                contas.add(conta);
            }

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
        return contas;
    }

    public Conta buscarByCodigo(Connection conexao, int codigo) throws SQLException {
        Conta conta = null;
        OperacaoVenda operacaoVenda = new OperacaoVenda();
        Cliente cliente = null;
        Usuario usuario = null;
        
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        conexao = BancoDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_SELECT_BY_CODIGO);
        comando.setInt(1, codigo);
        resultado = comando.executeQuery();

        while (resultado.next()) {
           conta = new Conta();
           conta.setCodigo(resultado.getInt(1));
           conta.setStatus(resultado.getBoolean(2));
           conta.setValorTotal(resultado.getDouble(5));
           conta.setData(resultado.getDate(8));
           
           cliente = new Cliente();
           cliente.setCpf(resultado.getString("CLIENTECPF"));
           cliente.setNome(resultado.getString("CLIENTENOME"));
           
           usuario = new Usuario();
           usuario.setCpf(resultado.getString("USUARIOCPF"));
           usuario.setNome(resultado.getString("USUARIONOME"));
           
           operacaoVenda.setCliente(cliente);
           operacaoVenda.setUsuario(usuario);
           
           conta.setOperacaoVenda(operacaoVenda);
        }
        return conta;
    }
    
    public void atualizarStatusContaPaga(int codigo) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_STATUS_CONTA);
            comando.setInt(1, codigo);
            comando.execute();
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException(e);
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
