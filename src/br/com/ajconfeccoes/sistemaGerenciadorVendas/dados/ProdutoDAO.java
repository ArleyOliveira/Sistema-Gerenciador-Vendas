/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.dados;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Cliente;
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
public class ProdutoDAO {

    private static String SQL_INSERT = "INSERT INTO PRODUTO (DESCRICAO, ESTOQUE, VALORUNITARIO, QUANTIDADEMINIMA) VALUES(?, ?, ?, ?)";
    private static String SQL_UPDATE_ESTOQUE = "UPDATE PRODUTO SET ESTOQUE = ESTOQUE + ? WHERE CODIGO = ?";
    private static String SQL_RETIRAR_QUANTIDADE_ESTOQUE = "UPDATE PRODUTO SET ESTOQUE = ESTOQUE - ? WHERE CODIGO = ?";
    private static String SQL_UPDATE_PRODUTO = "UPDATE PRODUTO SET DESCRICAO = ?, ESTOQUE = ?, VALORUNITARIO = ?, QUANTIDADEMINIMA = ? WHERE CODIGO = ?";
    private static String SQL_SELECT_TODOS = "SELECT * FROM PRODUTO";
    private static String SQL_SELECT_BY_CODIGO = "SELECT * FROM PRODUTO WHERE CODIGO = ?";
    private static String SQL_DELETE_BY_CODIGO = "DELETE FROM PRODUTO WHERE CODIGO = ?";
    private static String SQL_SELECT_BY_DESCRICAO = "SELECT * FROM PRODUTO WHERE DESCRICAO = ?";

    public void criar(Produto produto) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            comando.setString(1, produto.getDescricao());
            comando.setInt(2, produto.getEstoque());
            comando.setDouble(3, produto.getValorUnitario());
            comando.setInt(4, produto.getQuantidadeMinima());
            comando.execute();
            resultado = comando.getGeneratedKeys();
            while (resultado.next()) {
                produto.setCodigo(resultado.getInt(1));
            }
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

    public Produto buscarByCodigo(int codigo) throws SQLException {
        Produto produto = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_CODIGO);
            comando.setInt(1, codigo);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                produto = new Produto();
                produto.setCodigo(resultado.getInt(1));
                produto.setDescricao(resultado.getString(2));
                produto.setEstoque(resultado.getInt(3));
                produto.setValorUnitario(resultado.getDouble(4));
                produto.setQuantidadeMinima(resultado.getInt(5));
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

        return produto;
    }

    public List<Produto> buscarTodos() throws SQLException {
        Produto produto = null;
        List<Produto> produtos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_TODOS);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                produto = new Produto();
                produto.setCodigo(resultado.getInt(1));
                produto.setDescricao(resultado.getString(2));
                produto.setEstoque(resultado.getInt(3));
                produto.setValorUnitario(resultado.getDouble(4));
                produto.setQuantidadeMinima(resultado.getInt(5));
                produtos.add(produto);
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
        return produtos;
    }

    public void atualizarEstoque(Produto produto, int quantidade) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_ESTOQUE);
            comando.setInt(1, quantidade);
            comando.setInt(2, produto.getCodigo());
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

    public void atualizarDados(Produto produto) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_PRODUTO);
            comando.setString(1, produto.getDescricao());
            comando.setInt(2, produto.getEstoque());
            comando.setDouble(3, produto.getValorUnitario());
            comando.setInt(4, produto.getEstoque());
            comando.setInt(5, produto.getCodigo());
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

    public void deletarProduto(Produto produto) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETE_BY_CODIGO);
            comando.setInt(1, produto.getCodigo());
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

    public Produto buscarByDescricao(String descricao) throws SQLException {
        Produto produto = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_DESCRICAO);
            comando.setString(1, descricao);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                produto = new Produto();
                produto.setCodigo(resultado.getInt(1));
                produto.setDescricao(resultado.getString(2));
                produto.setEstoque(resultado.getInt(3));
                produto.setValorUnitario(resultado.getDouble(4));
                produto.setQuantidadeMinima(resultado.getInt(5));
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

        return produto;
    }

    public void retirarProdutoEstoque(Connection conexao, Produto produto, int quantidade) throws SQLException {
        PreparedStatement comando = null;
        comando = conexao.prepareStatement(SQL_RETIRAR_QUANTIDADE_ESTOQUE);
        comando.setInt(1, quantidade);
        comando.setInt(2, produto.getCodigo());
        comando.execute();
    }
}