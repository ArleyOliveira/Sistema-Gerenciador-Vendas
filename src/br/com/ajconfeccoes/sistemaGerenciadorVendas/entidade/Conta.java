/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author arley
 */
public class Conta {
    private int codigo;
    private Date data;
    private double valorTotal;
    private boolean status;
    private OperacaoVenda operacaoVenda = null;

    public OperacaoVenda getOperacaoVenda() {
        return operacaoVenda;
    }

    public void setOperacaoVenda(OperacaoVenda operacaoVenda) {
        this.operacaoVenda = operacaoVenda;
    }

    public boolean isStatus() {
        return status;
    }
    
    public String getDataFormatada(){
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        return dt1.format(this.data);
    }
    

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
}
