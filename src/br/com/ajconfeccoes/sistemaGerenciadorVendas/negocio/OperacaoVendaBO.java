/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ArgumentInvalidExeception;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ClienteInvalidoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ClienteStatusNegativoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.QuantidadeDeItensCompraInvalidoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.VendaInvalidaException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.ContaDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.LiquidacaoDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.OperacaoVendaDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Cliente;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Conta;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.ItensCompra;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.OperacaoVenda;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author arley
 */
public class OperacaoVendaBO {

    public void criarVenda(OperacaoVenda operacaoVenda) throws ClienteInvalidoException, ArgumentInvalidExeception, QuantidadeDeItensCompraInvalidoException, ClienteStatusNegativoException, SQLException {
        if(operacaoVenda == null){
            throw new VendaInvalidaException("Venda invalida");
        }
        this.verificarClienteExistente(operacaoVenda.getCliente());
        if(!operacaoVenda.getCliente().getCpf().equals("000.000.000-00")){           
            this.verificarUsuarioExiste(operacaoVenda.getUsuario());
            this.verificarQuantidadeItens(operacaoVenda.getItensCompra());
            this.verificarStatusCliente(operacaoVenda.getCliente());
            OperacaoVendaDAO operacaoVendaDAO = new OperacaoVendaDAO();
            operacaoVendaDAO.criar(operacaoVenda);
        }else{
            throw new ClienteInvalidoException("Não é possivel realizar a venda. Cliente Inválido");
        }
    }
    
 
    public void criarVendaAVista(OperacaoVenda operacaoVenda) throws ClienteInvalidoException, ArgumentInvalidExeception, QuantidadeDeItensCompraInvalidoException, ClienteStatusNegativoException, SQLException {
        if(operacaoVenda == null){
            throw new VendaInvalidaException("Venda invalida");
        }
        
        this.verificarClienteExistente(operacaoVenda.getCliente());
        this.verificarUsuarioExiste(operacaoVenda.getUsuario());
        this.verificarQuantidadeItens(operacaoVenda.getItensCompra());
        this.verificarStatusCliente(operacaoVenda.getCliente());
        OperacaoVendaDAO operacaoVendaDAO = new OperacaoVendaDAO();
        operacaoVendaDAO.criar(operacaoVenda);
    }
    
    public void verificarClienteExistente(Cliente cliente) {
        if (cliente == null || cliente.getCpf() == null) {
            throw new ClienteInvalidoException("Venda invalida.\n Não há cliente");
        }
    }

    public void verificarQuantidadeItens(List<ItensCompra> itens) {
        if (itens == null || itens.size() <= 0) {
            throw new QuantidadeDeItensCompraInvalidoException("Esta venda nao possui nenhum produto\nAdicione os produtos pertencentes a esta venda.");
        }
    }
    
    public void verificaClienteAVista(Cliente cliente){
        String cpf = "000.000.000-00";
        if(cpf.equals(cliente.getCpf())){
            
        }
    }

    public void verificarStatusCliente(Cliente cliente) {
        if (cliente.isStatus() == false) {
            throw new ClienteStatusNegativoException("Cliente status negativo");
        }
    }

    public void verificarUsuarioExiste(Usuario usuario) {
        if (usuario == null) {
            throw new ArgumentInvalidExeception("Não usuário para está venda");
        }
    }

    public void criarVendaELiquidar(OperacaoVenda operacaoVenda) throws ArgumentInvalidExeception, ClienteInvalidoException, QuantidadeDeItensCompraInvalidoException, SQLException {
        if(operacaoVenda == null){
            throw new VendaInvalidaException("Venda invalida");
        }
        this.verificarUsuarioExiste(operacaoVenda.getUsuario());
        this.verificarClienteExistente(operacaoVenda.getCliente());
        this.verificarQuantidadeItens(operacaoVenda.getItensCompra());
        OperacaoVendaDAO operacaoVendaDAO = new OperacaoVendaDAO();
        operacaoVendaDAO.criar(operacaoVenda);       
        ContaDAO contaDAO = new ContaDAO();
        Conta conta = contaDAO.buscarPorVenda(operacaoVenda.getCodigo());
        LiquidacaoBO liquidacaoBO = new LiquidacaoBO();
        liquidacaoBO.criar(conta);
    }
    
}
