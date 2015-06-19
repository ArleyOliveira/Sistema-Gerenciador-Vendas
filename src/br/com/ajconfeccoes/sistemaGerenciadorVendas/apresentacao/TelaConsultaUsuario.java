/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.CursorUtil;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.MensagemTela;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ArgumentInvalidExeception;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Usuario;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.UsuarioBO;
import java.awt.Cursor;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author arley
 */
public class TelaConsultaUsuario extends Tela {
    private TelaPrincipal telaPrincipal = null;
    List<Usuario> usuarios = new ArrayList<>();
    
    public TelaConsultaUsuario(TelaPrincipal telaPrincipal) {
        try{         
            initComponents();        
            buscarTodosUsuario();
            this.telaPrincipal = telaPrincipal;
        } finally{
           this.setCursor(CursorUtil.getCursor(0));
        }
        
    }
    
    
    public void buscarTodosUsuario(){
        try{
            
            UsuarioBO usuarioBO = new UsuarioBO();
            usuarios = usuarioBO.buscarTodos();
            carregarDadosTabela();
        }catch(SQLException se){
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido contate com o adminstrador", "Consultar Usuários");
        }finally{
            this.setCursor(CursorUtil.getCursor(3));
        }
    }
    public void carregarDadosTabela(){
        Model modelo  = new Model();
        tblUsuarios.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlUsuarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        lblConsultarUsuarios = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setTitle("Usuários");

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblUsuarios);

        lblConsultarUsuarios.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblConsultarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/group.png"))); // NOI18N
        lblConsultarUsuarios.setText("Usuários do Sistema - Empresa AJ Confeccções ");

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/user_edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(60, 30));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/user_delete.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setPreferredSize(new java.awt.Dimension(60, 30));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addGroup(pnlUsuariosLayout.createSequentialGroup()
                        .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConsultarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConsultarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsuariosLayout.createSequentialGroup()
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
         int linhaSelecionada = this.tblUsuarios.getSelectedRow();
        
        if(linhaSelecionada != -1){
             try {
                 Usuario usuarioSelecionado = this.usuarios.get(linhaSelecionada);
                 
                 TelaEditarDadosUsuario telaEditarDadosUsuario = new TelaEditarDadosUsuario(this, usuarioSelecionado);
                 telaPrincipal.addComponenteTela(telaEditarDadosUsuario);
                 telaEditarDadosUsuario.setMaximum(true);
                 telaEditarDadosUsuario.setVisible(true);
             } catch (PropertyVetoException ex) {
                 Logger.getLogger(TelaConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
             }
        }else{
          String mensagem = "Nenhum usuário selecionado.";
          JOptionPane.showMessageDialog(this, mensagem, "Alterar usuário", JOptionPane.INFORMATION_MESSAGE);  
        }
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        
        int linhaSelecionada = this.tblUsuarios.getSelectedRow();
        if(linhaSelecionada != -1){
            Usuario usuarioSelecionado = this.usuarios.get(linhaSelecionada);

            int resposta;
            String mensagem = "Deseja excluir usuário " + usuarioSelecionado.getNome() + " ?";
            String titulo = "Excluir usuário";
            resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                try {
                    CursorUtil.getCursor(3);
                    UsuarioBO usuarioBO = new UsuarioBO();
                    usuarioBO.deletarUsuario(usuarioSelecionado);
                    this.buscarTodosUsuario();
                    MensagemTela.exibirMensagemSucesso(this, "Dados excluidos com sucesso!", "Excluir usuário");                
                }catch (ArgumentInvalidExeception aie){
                   MensagemTela.exibirMensagemErro(this, aie.getMessage(), "Excluir usuário"); 
                } catch (Exception e) {
                    mensagem = "Erro desconhecido. Entre em contato com o administrador do sistema.";
                    MensagemTela.exibirMensagemErro(this, mensagem, "Excluir usuário");
                }finally{
                    CursorUtil.getCursor(0);
                }
            }
            else{
                MensagemTela.exibirMensagemSucesso(this, "Dados não excluidos", "Excluir usuário");
            }
        }else{
          String mensagem = "Nenhum usuário selecionado.";
          JOptionPane.showMessageDialog(this, mensagem, "Excluir usuário", JOptionPane.INFORMATION_MESSAGE);  
        }
        
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConsultarUsuarios;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JTable tblUsuarios;
    // End of variables declaration//GEN-END:variables
    
    public class Model extends AbstractTableModel{

        @Override
        public int getRowCount() {
            return usuarios.size();
        }

        @Override
        public int getColumnCount() {
            return 7;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            Usuario usuario = usuarios.get(linha);
            if(coluna == 0){
                return usuario.getCpf();
            }else if(coluna == 1){
                return usuario.getRg();
            }else if(coluna == 2){
               return usuario.getNome();
            }else if(coluna == 3){
                return usuario.getIdade();
            }else if(coluna == 4){
                return usuario.getTelefone();
            }else if(coluna == 5){
                return usuario.getEndereco();
            }else{
                if(usuario.getPermissao() == 5){
                    return "Usuário Padrão";
                }else{
                    return "Usuário Administrador";
                }
            }
        }
        
        @Override
        public String getColumnName(int coluna){
            if(coluna == 0){
                return "CPF";
            }else if(coluna == 1){
                return "RG";
            }else if(coluna == 2){
               return "Nome"; 
            }else if(coluna == 3){
                return "Idade";
            }else if(coluna == 4){
                return "Telefone";
            }else if(coluna == 5){
                return "Endereço";
            }else{
                return "Permissão";
            }
        }
    
    }

}


