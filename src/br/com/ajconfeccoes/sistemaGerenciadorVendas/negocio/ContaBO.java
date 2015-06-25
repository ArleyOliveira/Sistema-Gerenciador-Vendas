/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ArgumentInvalidExeception;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.ContaDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Conta;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.DadosGraficoVendasMensal;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author arley
 */
public class ContaBO {
    
    public List<Conta> buscarTodos() throws SQLException{
        ContaDAO contaDAO = new ContaDAO();
        return contaDAO.buscarTodos();
    }
    
    public List<DadosGraficoVendasMensal> buscarByMesAtualGerarGrafico() throws SQLException{
        ContaDAO contaDAO = new ContaDAO();
        List<DadosGraficoVendasMensal> vendasMensais = null;    
        vendasMensais = contaDAO.buscarByMesGerarGrafico();
        if(vendasMensais == null || vendasMensais.size() <= 0){
            throw new ArgumentInvalidExeception("Não há existe vendas para este mês.");
        }
        return vendasMensais;
    }
}
