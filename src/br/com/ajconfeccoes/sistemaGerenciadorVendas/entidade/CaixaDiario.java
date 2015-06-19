/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author arley
 */
public class CaixaDiario {
    private int codigo;
    private boolean status;
    private double valorInicial;
    private double valorFinal;
    private Date data;
    private List<Liquidacao> contasPagas = new ArrayList<>();

    public CaixaDiario(){
        this.data = new Date(System.currentTimeMillis());
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Liquidacao> getContas() {
        return contasPagas;
    }
    
    
    public void addConta(Liquidacao conta){
        this.contasPagas.add(conta);
        this.valorFinal = this.valorFinal + conta.getValorPago();
    }
    
    public String getValorFinalFormatado(){
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
        return formatador.format(this.valorFinal);
    }
    
    public String getValorInicialFormatado(){
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
        return formatador.format(this.valorInicial);
    }
    public String getDataFormatada(){
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        return dt1.format(this.data);
    }
    
    public String getDataFormatadaBanco(){
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        return dt1.format(this.data);
    }
}
