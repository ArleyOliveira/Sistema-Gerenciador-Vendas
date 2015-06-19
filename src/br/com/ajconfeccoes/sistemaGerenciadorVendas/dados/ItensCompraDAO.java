/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.dados;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.ItensCompra;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author arley
 */
public class ItensCompraDAO {

    private static String SQL_INSERT = "INSERT INTO ITENSCOMPRA (PRODUTOCODIGO, DESCRICAO, VALORUNITARIO, VALORTOTAL, VENDA) VALUES(?, ?, ?, ?, ?)";

    public void criar(Connection conexao, int venda, ItensCompra item) throws SQLException {
        PreparedStatement comando = null;
        comando = conexao.prepareStatement(SQL_INSERT);
        comando.setInt(1, item.getProduto().getCodigo());
        comando.setString(2, item.getProduto().getDescricao());
        comando.setDouble(3, item.getValorUnitario());
        comando.setDouble(4, item.getValorTotal());
        comando.setInt(5, venda);
        comando.execute();
        retirarProdutoEstoque(conexao, item.getProduto(), item.getQuantidade());
    }
    
    public void retirarProdutoEstoque(Connection conexao, Produto produto, int quantidade) throws SQLException{
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.retirarProdutoEstoque(conexao, produto, quantidade);
    }
}
