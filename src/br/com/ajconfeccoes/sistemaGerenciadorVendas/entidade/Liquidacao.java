/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade;

import java.util.Date;

/**
 *
 * @author arley
 */
public class Liquidacao {
    private Conta conta;
    private Date data;
    private double desconto;
    private double valorPago;

    public CaixaDiario getCaixaDiario() {
        return caixaDiario;
    }

    public void setCaixaDiario(CaixaDiario caixaDiario) {
        this.caixaDiario = caixaDiario;
    }
    private CaixaDiario caixaDiario;

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
    
    
}
