/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.dados;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.CaixaDiario;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Produto;
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
public class CaixaDiarioDAO {
    
    private static String SQL_INSERT = "INSERT INTO CAIXADIARIO(VALORINICIAL, VALORFINAL) VALUES(?, ?)";
    private static String SQL_UPDATE_VALOR_CAIXA = "UPDATE CAIXADIARIO SET VALORFINAL = VALORFINAL + ? WHERE CODIGO = ?";
    private static String SQL_SELECT_CAIXA_ATUAL = "SELECT * FROM CAIXADIARIO WHERE DATA = CURRENT_DATE";
    private static String SQL_UPDATE_STATUS_CAIXA = "UPDATE CAIXADIARIO SET STATUS = true WHARE CODIGO = ?";
    private static String SQL_SELECT_BY_MES_ATUAL = "SELECT * FROM CAIXADIARIO WHERE Month(data) = MONTH(CURRENT_DATE)";
    private static String SQL_UPDATE_FINALIZAR_CAIXA = "UPDATE CAIXADIARIO SET STATUS = FALSE WHERE CODIGO = ?";
    
    public void criar(CaixaDiario caixaDiario) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setDouble(1, caixaDiario.getValorInicial());
            comando.setDouble(2, caixaDiario.getValorInicial());
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
    
    public void adicionarValor(int codigo, double valor) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_VALOR_CAIXA);
            comando.setDouble(1, valor);
            comando.setInt(2, codigo);
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
    public void reabrirCaixa(int codigo) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();        
            comando = conexao.prepareStatement(SQL_UPDATE_STATUS_CAIXA);
            comando.setInt(1, codigo);
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
    
    public CaixaDiario buscarCaixaAtual() throws SQLException {
        CaixaDiario caixaDiario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_CAIXA_ATUAL);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                caixaDiario = new CaixaDiario();
                caixaDiario.setCodigo(resultado.getInt(1));
                caixaDiario.setData(resultado.getDate(2));
                caixaDiario.setStatus(resultado.getBoolean(3));
                caixaDiario.setValorInicial(resultado.getDouble(4));
                caixaDiario.setValorFinal(resultado.getDouble(5));
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

        return caixaDiario;
    }
    
    public List<CaixaDiario> buscarTodosByMesAtual() throws SQLException {
        List<CaixaDiario> caixas = new ArrayList<>();
        CaixaDiario caixaDiario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_MES_ATUAL);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                caixaDiario = new CaixaDiario();
                caixaDiario.setCodigo(resultado.getInt(1));
                caixaDiario.setData(resultado.getDate(2));
                caixaDiario.setStatus(resultado.getBoolean(3));
                caixaDiario.setValorInicial(resultado.getDouble(4));
                caixaDiario.setValorFinal(resultado.getDouble(5));
                caixas.add(caixaDiario);
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
        return caixas;
    }
    
    public void finalizarCaixa(int codigo) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();        
            comando = conexao.prepareStatement(SQL_UPDATE_FINALIZAR_CAIXA);
            comando.setInt(1, codigo);
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
