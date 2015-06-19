/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ArgumentInvalidExeception;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.UsuarioCpfExistenteException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.UsuarioLoginExistenteException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.dados.UsuarioDAO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author arley
 */
public class UsuarioBO {

    public void criar(Usuario usuario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.criar(usuario);
    }

    public void verificaUsuarioExistente(Usuario usuario) throws SQLException {
        Usuario usuarioExistente = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioExistente = usuarioDAO.buscarByCPF(usuario.getCpf());
        if (usuarioExistente != null) {
            throw new UsuarioCpfExistenteException("Existe um usuario cadastrado com esse CPF.");
        }
    }

    public void verificaUsuarioLogin(Usuario usuario) throws SQLException {
        Usuario usuarioExistente = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioExistente = usuarioDAO.buscarByLogin(usuario.getLogin());
        if (usuarioExistente != null) {
            throw new UsuarioLoginExistenteException("Existe um usuario cadastrado com esse login.");
        }
    }

    public List<Usuario> buscarTodos() throws SQLException {
        List<Usuario> usuarios = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.buscarTodos();
        return usuarios;
    }

    public void atualizarDados(Usuario usuario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizarDados(usuario);
    }

    public void deletarUsuario(Usuario usuario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioExiste = null;
        usuarioExiste = usuarioDAO.buscarByCPF(usuario.getCpf());
        if (usuarioExiste != null) {
            usuarioDAO.desativarUsuario(usuario);
        } else {
            throw new ArgumentInvalidExeception("Impossível excluir usuário. Este não se encontra em nossa base de dados");
        }
    }
    
    public Usuario login(String login, String senha) throws SQLException {
        Usuario usuarioExistente = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioExistente = usuarioDAO.buscarByLoginAndSenha(login, senha);
        if (usuarioExistente != null) {
            return usuarioExistente;
        }else{
            throw new ArgumentInvalidExeception("Login ou inválidos.\n Tente novamento efetuar o login.");
        }
    }
}
