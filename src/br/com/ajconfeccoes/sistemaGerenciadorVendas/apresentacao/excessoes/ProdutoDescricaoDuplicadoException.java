/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes;

/**
 *
 * @author arley
 */
public class ProdutoDescricaoDuplicadoException extends RuntimeException{
    
    public ProdutoDescricaoDuplicadoException(String mensagem){
        super(mensagem);
    }
}
