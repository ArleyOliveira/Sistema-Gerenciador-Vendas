/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ArgumentInvalidExeception;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.CaixaAbertoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.CaixaDiarioDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.CaixaDiario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author arley
 */
public class CaixaDiarioBO {
  
    public void criar(CaixaDiario caixaDiario) throws CaixaAbertoException, SQLException {
        try{
            verificaCaixaAberto();
        }catch(CaixaAbertoException cae){
            if(!verificaSeCaixaFechado()){
                reabrirCaixa(caixaDiario.getCodigo());
                throw  new CaixaAbertoException("O Caixa encontrava-se em estado finalizado e então foi iniciado");
            }
        }
        CaixaDiarioDAO caixaDiarioDAO = new CaixaDiarioDAO();
        caixaDiarioDAO.criar(caixaDiario);
    }
    
    public void verificaCaixaAberto() throws CaixaAbertoException, SQLException{
        CaixaDiarioDAO caixaDiarioDAO = new CaixaDiarioDAO();
        CaixaDiario caixaDiario = null;
        caixaDiario = caixaDiarioDAO.buscarCaixaAtual();
        if(caixaDiario != null){
            throw new CaixaAbertoException("O caixa já se encontra em aberto!");
        }
    }
    
    public CaixaDiario buscarDadosCaixaAtual() throws SQLException{
        CaixaDiarioDAO caixaDiarioDAO = new CaixaDiarioDAO();
        CaixaDiario caixaDiario = null;
        caixaDiario = caixaDiarioDAO.buscarCaixaAtual();
        if(caixaDiario == null){
            throw new CaixaAbertoException("O caixa de hoje não foi aberto");
        }
        return caixaDiario;
    }
    
    public boolean verificaSeCaixaFechado() throws SQLException{
       CaixaDiarioDAO caixaDiarioDAO = new CaixaDiarioDAO();
       CaixaDiario caixaDiario = null;
       caixaDiario = caixaDiarioDAO.buscarCaixaAtual();
       if(caixaDiario == null){
           return true;
       }else{
           return caixaDiario.isStatus();
       }
    }
    
    public void reabrirCaixa(int codigo) throws SQLException{
       CaixaDiarioDAO caixaDiarioDAO = new CaixaDiarioDAO();
       caixaDiarioDAO.reabrirCaixa(codigo);
    }
    
    public List<CaixaDiario> buscarCaixasByMes() throws SQLException{
        CaixaDiarioDAO caixaDiarioDAO = new CaixaDiarioDAO();
        List<CaixaDiario> caixas = null;
        caixas = caixaDiarioDAO.buscarTodosByMesAtual();
        if(caixas.size() <= 0){
            throw new ArgumentInvalidExeception("Não há nenhum registro de caixa para este mês");
        }
        return caixas;
    }
    
    public void finalizarCaixa(CaixaDiario caixaDiario) throws SQLException{
       CaixaDiarioDAO caixaDiarioDAO = new CaixaDiarioDAO();
       caixaDiarioDAO.reabrirCaixa(caixaDiario.getCodigo());
    }
    
}
