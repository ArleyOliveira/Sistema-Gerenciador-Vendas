/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ArgumentInvalidExeception;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.CaixaAbertoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.CaixaDiarioDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.ClienteDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.ContaDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.LiquidacaoDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.UsuarioDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.CaixaDiario;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Cliente;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Conta;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Liquidacao;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author arley
 */
public class LiquidacaoBO {
    
    public void criar(Conta conta) throws CaixaAbertoException, SQLException{
        if(!conta.isStatus()){
            throw new ArgumentInvalidExeception("Esta conta já está liquidada.");
        }
        Liquidacao liquidacao = new Liquidacao();
        liquidacao.setConta(conta);
        liquidacao.setValorPago(conta.getValorTotal());
        liquidacao.setCaixaDiario(getCaixaAtual());
        //criar
        LiquidacaoDAO liquidacaoDAO = new LiquidacaoDAO();
        liquidacaoDAO.criar(liquidacao);
        this.adicionarValorCaixa(liquidacao.getCaixaDiario().getCodigo(),liquidacao.getValorPago());
        mudarStatusConta(conta);
    }
    
    public void mudarStatusConta(Conta conta) throws SQLException{
        ContaDAO contaDAO = new ContaDAO();
        contaDAO.atualizarStatusContaPaga(conta.getCodigo());
    }
    public void adicionarValorCaixa(int codigo, double valor) throws SQLException{
        CaixaDiarioDAO caixaDiarioDAO = new CaixaDiarioDAO();
        caixaDiarioDAO.adicionarValor(codigo, valor);
    }
    public List<Liquidacao> liquidacoesDiarias() throws SQLException{
        List<Liquidacao> liquidacoes = null;
        LiquidacaoDAO liquidacaoDAO = new LiquidacaoDAO();
        liquidacoes = liquidacaoDAO.buscarLiquidacoesCaixaDiario(getCaixaAtual().getCodigo());
        return liquidacoes;
    }
    
    public CaixaDiario getCaixaAtual() throws CaixaAbertoException, SQLException{
        CaixaDiarioDAO caixaDiarioDAO = new CaixaDiarioDAO();
        if(caixaDiarioDAO.buscarCaixaAtual() == null){
            throw new CaixaAbertoException("Caixa não foi aberto.\nPor favor inicie o caixa.");
        }else{
           return caixaDiarioDAO.buscarCaixaAtual(); 
        }     
    }    
}
