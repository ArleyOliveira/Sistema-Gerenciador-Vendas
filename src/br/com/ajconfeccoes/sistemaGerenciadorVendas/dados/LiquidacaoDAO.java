/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.dados;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.CaixaDiario;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Conta;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Liquidacao;
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
public class LiquidacaoDAO {
    private static String SQL_INSERT = "INSERT INTO LIQUIDACAO(CONTA, CAIXADIARIO, VALORPAGO) VALUES(?, ?, ?)";
    private static String SQL_SELECT_BY_CAIXA_DIARIO = "SELECT * FROM LIQUIDACAO WHERE CAIXADIARIO = ?";
    
    public void criar(Liquidacao liquidacao) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setInt(1, liquidacao.getConta().getCodigo());
            comando.setInt(2, liquidacao.getCaixaDiario().getCodigo());
            comando.setDouble(3, liquidacao.getValorPago());
            comando.execute();
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

    public List<Liquidacao> buscarLiquidacoesCaixaDiario(int codigo) throws SQLException {
        Liquidacao liquidacao = null;
        Conta conta = null;
        
        List<Liquidacao> liquidacoes = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_CAIXA_DIARIO);
            comando.setInt(1, codigo);
            resultado = comando.executeQuery();
            
            while (resultado.next()) {
                liquidacao = new Liquidacao();
                Conta c = new Conta();
                c.setCodigo(resultado.getInt(1));
                liquidacao.setConta(c);
                CaixaDiario cd = new CaixaDiario();
                cd.setCodigo(resultado.getInt(2));
                liquidacao.setCaixaDiario(cd);
                liquidacao.setValorPago(resultado.getDouble(3));               
                ContaDAO contaDAO = new ContaDAO();
                liquidacao.setConta(contaDAO.buscarByCodigo(conexao,resultado.getInt("CONTA")));
                liquidacoes.add(liquidacao);
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
        return liquidacoes;
    }

    
}
