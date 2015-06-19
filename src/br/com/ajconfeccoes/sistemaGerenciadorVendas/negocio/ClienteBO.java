/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ArgumentInvalidExeception;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.UsuarioCpfExistenteException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.ClienteDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.UsuarioDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Cliente;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author arley
 */
public class ClienteBO {
    
    public void criar(Cliente cliente) throws SQLException{
      ClienteDAO clienteDAO = new ClienteDAO();  
      clienteDAO.criar(cliente);
    }
    
    public void verificaUsuarioExistente(Cliente cliente) throws SQLException{
        Cliente clienteExistente = null;
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteExistente = clienteDAO.buscarByCPF(cliente.getCpf());
        if(clienteExistente != null){
            throw new UsuarioCpfExistenteException("Existe um cliente cadastrado com esse CPF.");
        }
    }
    
    public List<Cliente> buscarTodos() throws SQLException{
        List<Cliente> clientes = null;
        ClienteDAO clienteDAO = new ClienteDAO();
        clientes = clienteDAO.buscarTodos();
        return clientes;
    }
    
    public void atualizarDados(Cliente cliente) throws SQLException{
      ClienteDAO clienteDAO = new ClienteDAO();  
      clienteDAO.atualizarDados(cliente);
    }
    
     public void deletarCliente(Cliente cliente) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente clienteExiste = null;
        clienteExiste = clienteDAO.buscarByCPF(cliente.getCpf());
        if (clienteExiste != null) {
            clienteDAO.desativarCliente(cliente);
        } else {
            throw new ArgumentInvalidExeception("Impossível excluir cliente. Este não se encontra em nossa base de dados");
        }

    }
    
    public Cliente buscarByCpf(String cpf) throws SQLException{
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = null;
        cliente = clienteDAO.buscarByCPF(cpf);
        if(cliente == null){
            throw new ArgumentInvalidExeception("Cliente não encontrado");
        }
        return cliente;
    }
}
