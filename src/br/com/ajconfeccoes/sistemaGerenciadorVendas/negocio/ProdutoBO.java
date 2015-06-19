/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ProdutoDescricaoDuplicadoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.QuantidadeProdutoInvalidoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ValorProdutoInvalidoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.ProdutoDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Produto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author arley
 */
public class ProdutoBO {

    public void criar(Produto produto) throws ProdutoDescricaoDuplicadoException, QuantidadeProdutoInvalidoException, ValorProdutoInvalidoException, SQLException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produtoExistente = produtoDAO.buscarByDescricao(produto.getDescricao());
        produtoExistente = produtoDAO.buscarByDescricao(produto.getDescricao());
        if (produtoExistente == null) {
            verificarQuantidade(produto);
            verificarValor(produto);
            produtoDAO.criar(produto);
        }else{
            throw new ProdutoDescricaoDuplicadoException("Existem um produto com esta mesma descriçao cadastrado.");
        }

    }

    public void verificarQuantidade(Produto produto) {
        if (produto.getEstoque() <= 0) {
            throw new QuantidadeProdutoInvalidoException("Quantidade Invalida.\nO produto a ser cadastrado deve ter no minimo um item");
        } else if (produto.getQuantidadeMinima() > produto.getEstoque()) {
            throw new QuantidadeProdutoInvalidoException("Quantidade Invalida.\nA quantidade mínima não pode ser maior do que a quantidade de itens a ser estocada.");
        }
    }
    
    public void verificarQuantidade(int quantidade) {
        if ( quantidade <= 0) {
            throw new QuantidadeProdutoInvalidoException("Quantidade Invalida.\nPara atualizar o estoque deve haver pelo menos um item");
        } 
    }

    public void verificarValor(Produto produto) {
        if (produto.getValorUnitario() <= 0) {
            throw new ValorProdutoInvalidoException("Valor Invalido.\n O valor do produto deve ser maior que zero");
        }
    }

    public void atualizarEstoque(Produto produto, int quantidade) throws QuantidadeProdutoInvalidoException, SQLException {
        verificarQuantidade(quantidade);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.atualizarEstoque(produto, quantidade);
    }

    public void excluirProduto(Produto produto) throws SQLException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.deletarProduto(produto);
    }

    public void atualizarDadosProduto(Produto produto) throws QuantidadeProdutoInvalidoException, ValorProdutoInvalidoException, SQLException {
        verificarQuantidade(produto);
        verificarValor(produto);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.atualizarDados(produto);
    }
    
    public List<Produto> buscarTodos() throws SQLException{
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.buscarTodos();
    }
    
    public Produto buscarByCodigo(int codigo) throws SQLException{
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.buscarByCodigo(codigo);
    }
}
