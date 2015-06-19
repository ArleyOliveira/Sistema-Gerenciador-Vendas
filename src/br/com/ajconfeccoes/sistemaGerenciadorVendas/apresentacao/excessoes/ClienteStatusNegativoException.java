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
public class ClienteStatusNegativoException extends RuntimeException{
    
    public ClienteStatusNegativoException(String mensagem){
        super(mensagem);
    }
}
