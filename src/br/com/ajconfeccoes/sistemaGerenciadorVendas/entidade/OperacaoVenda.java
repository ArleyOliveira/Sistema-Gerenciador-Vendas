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
public class OperacaoVenda {
    private double valorTotal;
    private Date data;
    private int codigo;
    private Cliente cliente;
    private Usuario usuario;
    private List<ItensCompra> itensCompra = new ArrayList<>();

    public OperacaoVenda(){
        this.data = new Date(System.currentTimeMillis());
        this.valorTotal = 0;
    }
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setItensCompra(List<ItensCompra> itensCompra) {
        this.itensCompra = itensCompra;
    }

    public Date getData() {
        return data;
    }
    
    public String getValorTotalFormatado(){
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
         return formatador.format(this.valorTotal);
    }
    public String getDataFormatada(){
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        return dt1.format(this.data);
    }
    
    public String getDataFormatadaBanco(){
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        return dt1.format(this.data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItensCompra> getItensCompra() {
        return itensCompra;
    }

    public void addItem(ItensCompra itemCompra) {
        this.itensCompra.add(itemCompra);
        this.valorTotal = this.valorTotal + itemCompra.getValorTotal();
    }
    
    public void removeItem(int index){
        ItensCompra item = this.itensCompra.get(index);
        this.valorTotal = this.valorTotal = item.getValorTotal();
        this.itensCompra.remove(index);
    }
}
