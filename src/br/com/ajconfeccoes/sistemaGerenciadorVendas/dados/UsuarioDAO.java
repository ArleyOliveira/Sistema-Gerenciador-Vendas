/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.dados;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Usuario;
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
public class UsuarioDAO {
    private static final String SQL_INSERT = "INSERT INTO USUARIO(CPF, RG, NOME, IDADE, TELEFONE, CELULAR, ENDERECO, LOGIN, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_CPF = "SELECT * FROM USUARIO WHERE CPF = ? AND F = TRUE";
    private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM USUARIO WHERE LOGIN = ?";
    private static final String SQL_SELECT_TODOS = "SELECT * FROM USUARIO WHERE F = TRUE";
    private static final String SQL_UPDATE_DADOS = "UPDATE USUARIO SET RG = ?, NOME = ?, IDADE = ?, TELEFONE = ?, CELULAR = ?, ENDERECO = ? WHERE CPF = ?";
    private static final String SQL_DESATIVAR_POR_CPF = "UPDATE USUARIO SET F = FALSE WHERE CPF = ?";
    private static final String SQL_SELECT_BY_LOGIN_AND_SENHA = "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = ? AND F = TRUE";
    
    public void criar(Usuario usuario) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, usuario.getCpf());
            comando.setString(2, usuario.getRg());
            comando.setString(3, usuario.getNome());
            comando.setInt(4, usuario.getIdade());
            comando.setString(5, usuario.getTelefone());
            comando.setString(6, usuario.getCelular());
            comando.setString(7, usuario.getEndereco());
            comando.setString(8, usuario.getLogin());
            comando.setString(9, usuario.getSenha());
            
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
    
    public Usuario buscarByCPF(String cpf) throws SQLException{
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_CPF);
            comando.setString(1, cpf);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setCpf(resultado.getString(1));
                usuario.setRg(resultado.getString(2));
                usuario.setNome(resultado.getString(3));
                usuario.setIdade(resultado.getInt(4));
                usuario.setTelefone(resultado.getString(5));
                usuario.setCelular(resultado.getString(6));
                usuario.setEndereco(resultado.getString(7));
                usuario.setPermissao(resultado.getInt(10));
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
        
        return usuario;
    }
    
    public Usuario buscarByLogin(String login) throws SQLException{
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_LOGIN);
            comando.setString(1, login);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setCpf(resultado.getString(1));
                usuario.setRg(resultado.getString(2));
                usuario.setNome(resultado.getString(3));
                usuario.setIdade(resultado.getInt(4));
                usuario.setTelefone(resultado.getString(5));
                usuario.setCelular(resultado.getString(6));
                usuario.setEndereco(resultado.getString(7));
                usuario.setPermissao(resultado.getInt(10));
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
        return usuario;
    }
    
    
    public List<Usuario> buscarTodos() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_TODOS);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setCpf(resultado.getString(1));
                usuario.setRg(resultado.getString(2));
                usuario.setNome(resultado.getString(3));
                usuario.setIdade(resultado.getInt(4));
                usuario.setTelefone(resultado.getString(5));
                usuario.setCelular(resultado.getString(6));
                usuario.setEndereco(resultado.getString(7));
                usuario.setPermissao(resultado.getInt(10));
                usuarios.add(usuario);
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
        return usuarios;
    }
    
    public void atualizarDados(Usuario usuario) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_DADOS);
            comando.setString(7, usuario.getCpf());
            comando.setString(1, usuario.getRg());
            comando.setString(2, usuario.getNome());
            comando.setInt(3, usuario.getIdade());
            comando.setString(4, usuario.getTelefone());
            comando.setString(5, usuario.getCelular());
            comando.setString(6, usuario.getEndereco());
            
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
    
    public void desativarUsuario(Usuario usuario) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DESATIVAR_POR_CPF);
            comando.setString(1, usuario.getCpf());
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
    
    public Usuario buscarByLoginAndSenha(String login, String senha) throws SQLException{
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_BY_LOGIN_AND_SENHA);
            comando.setString(1, login);
            comando.setString(2, senha);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setCpf(resultado.getString(1));
                usuario.setRg(resultado.getString(2));
                usuario.setNome(resultado.getString(3));
                usuario.setIdade(resultado.getInt(4));
                usuario.setTelefone(resultado.getString(5));
                usuario.setCelular(resultado.getString(6));
                usuario.setEndereco(resultado.getString(7));
                usuario.setPermissao(resultado.getInt(10));
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
        return usuario;
    }
}
