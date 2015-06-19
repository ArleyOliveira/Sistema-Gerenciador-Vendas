/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.Tela;
import javax.swing.JOptionPane;

/**
 *
 * @author arley
 */
public class MensagemTela {
    
    public static void exibirMensagemErro(Tela tela, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(tela, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirMensagemSucesso(Tela tela, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(tela, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    public static void exibirMensagemInformacao(Tela tela, String mensagem, String titulo) {
        JOptionPane.showMessageDialog(tela, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE); 
    }
     
}
