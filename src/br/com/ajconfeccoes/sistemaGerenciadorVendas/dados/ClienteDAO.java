/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.dados;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arley
 */
public class ClienteDAO {
    
    private static final String SQL_INSERT = "INSERT INTO CLIENTE(CPF, RG, NOME, IDADE, TELEFONE, CELULAR, ENDERECO) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_CPF = "SELECT * FROM CLIENTE WHERE CPF = ? AND F = TRUE";
    private static final String SQL_SELECT_BY_CPF_CONTA = "SELECT * FROM CLIENTE WHERE CPF = ?";
    private static final String SQL_SELECT_TODOS = "SELECT * FROM CLIENTE WHERE F = TRUE";
    private static final String SQL_UPDATE_DADOS = "UPDATE CLIENTE SET RG = ?, NOME = ?, IDADE = ?, TELEFONE = ?, CELULAR = ?, ENDERECO = ?, STATUS = ? WHERE CPF = ? AND F = TRUE";
    private static final String SQL_DESATIVAR_POR_CPF = "UPDATE CLIENTE SET F = FALSE WHERE CPF = ?";
    public void criar(Cliente cliente) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, cliente.getCpf());
            comando.setString(2, cliente.getRg());
            comando.setString(3, cliente.getNome());
            comando.setInt(4, cliente.getIdade());
            comando.setString(5, cliente.getTelefone());
            comando.setString(6, cliente.getCelular());
            comando.setString(7, cliente.getEndereco());      
            
            comando.execute();
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException();
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
    
     public Cliente buscarByCPF(String cpf) throws SQLException{
        Cliente cliente = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_CPF);
            comando.setString(1, cpf);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                cliente = new Cliente();
                cliente.setCpf(resultado.getString(1));
                cliente.setRg(resultado.getString(2));
                cliente.setNome(resultado.getString(3));
                cliente.setIdade(resultado.getInt(4));
                cliente.setTelefone(resultado.getString(5));
                cliente.setCelular(resultado.getString(6));
                cliente.setEndereco(resultado.getString(7));
                cliente.setStatus(resultado.getBoolean("STATUS"));
            }
            
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException();
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        
        return cliente;
    }
     
    public List<Cliente> buscarTodos() throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_TODOS);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                cliente = new Cliente();
                cliente.setCpf(resultado.getString(1));
                cliente.setRg(resultado.getString(2));
                cliente.setNome(resultado.getString(3));
                cliente.setIdade(resultado.getInt(4));
                cliente.setTelefone(resultado.getString(5));
                cliente.setCelular(resultado.getString(6));
                cliente.setEndereco(resultado.getString(7));
                cliente.setStatus(resultado.getBoolean("STATUS"));
                clientes.add(cliente);
            }
            
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException();
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        return clientes;
    }
    
    public void atualizarDados(Cliente cliente) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_DADOS);
            comando.setString(8, cliente.getCpf());
            comando.setString(1, cliente.getRg());
            comando.setString(2, cliente.getNome());
            comando.setInt(3, cliente.getIdade());
            comando.setString(4, cliente.getTelefone());
            comando.setString(5, cliente.getCelular());
            comando.setString(6, cliente.getEndereco());
            comando.setBoolean(7, cliente.isStatus());
            
            comando.execute();
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException();
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
    
    public void desativarCliente(Cliente cliente) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DESATIVAR_POR_CPF);
            comando.setString(1, cliente.getCpf());
            comando.execute();
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
    
    public Cliente buscarByCPFConta(String cpf) throws SQLException{
        Cliente cliente = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_CPF_CONTA);
            comando.setString(1, cpf);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                cliente = new Cliente();
                cliente.setCpf(resultado.getString(1));
                cliente.setRg(resultado.getString(2));
                cliente.setNome(resultado.getString(3));
                cliente.setIdade(resultado.getInt(4));
                cliente.setTelefone(resultado.getString(5));
                cliente.setCelular(resultado.getString(6));
                cliente.setEndereco(resultado.getString(7));
                cliente.setStatus(resultado.getBoolean("STATUS"));
            }
            
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException();
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        
        return cliente;
    }
}
