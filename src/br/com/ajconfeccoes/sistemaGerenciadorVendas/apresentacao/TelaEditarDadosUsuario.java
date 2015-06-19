/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.MensagemTela;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.CampoVazioException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.UsuarioCpfExistenteException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Usuario;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.UsuarioBO;
import java.awt.Cursor;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arley
 */
public class TelaEditarDadosUsuario extends Tela {
    
    Usuario usuario = null;
    TelaConsultaUsuario telaConsultaUsuario = null;
    
    public TelaEditarDadosUsuario(TelaConsultaUsuario telaConsultaUsuario, Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        this.setValoresCampos();
        this.telaConsultaUsuario = telaConsultaUsuario;
    }
    
     public void lerDadosTela(){
        String rg = txtRg.getText().trim();
        String nome = txtNome.getText().trim();
        String stringIdade = txtIdade.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String celular = txtCelular.getText().trim();
        String endereco = txtEndereco.getText().trim();
        
        String mensagem = "";
         
        if(rg.isEmpty()){
            mensagem = "\nCampo RG é obrigatório.";
        }
        
        if(nome.isEmpty()){
            mensagem = mensagem + "\nCampo nome é obrigatório.";
        }
        
        int idade = 0;
        
        if(stringIdade.isEmpty()){
            mensagem = mensagem + "\nCampo idade é obrigatório";       
        }else{
             try{
                idade = Integer.parseInt(stringIdade);
            }catch(NumberFormatException nfe){
                mensagem = mensagem + "\nIdade invalida.";
            }
        }
        
       if(mensagem == "" || mensagem == null){
           usuario.setRg(rg);
           usuario.setNome(nome);
           usuario.setIdade(idade);
           usuario.setTelefone(telefone);
           usuario.setCelular(celular);
           usuario.setEndereco(endereco);
       }else{
           throw new CampoVazioException(mensagem);
       }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPainelCadastro = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblCpf = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        lblRg = new javax.swing.JLabel();
        txtRg = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        lblCelular = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        lblIdade = new javax.swing.JLabel();
        txtIdade = new javax.swing.JFormattedTextField();
        btnSalvarAlteracao = new javax.swing.JButton();
        lblAlterarDadosUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlPainelCadastro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 241, 240)));

        lblNome.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblNome.setText("Nome (*):");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        lblCpf.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblCpf.setText("CPF (*): ");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setEnabled(false);

        lblRg.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblRg.setText("RG (*) : ");

        txtRg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRgActionPerformed(evt);
            }
        });

        lblTelefone.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblTelefone.setText("Telefone: ");

        lblCelular.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblCelular.setText("Celular:");

        lblEndereco.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblEndereco.setText("Endereço: ");

        lblIdade.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblIdade.setText("Idade : ");

        txtIdade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        btnSalvarAlteracao.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnSalvarAlteracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/disk.png"))); // NOI18N
        btnSalvarAlteracao.setText("Salvar Alterações");
        btnSalvarAlteracao.setToolTipText("Proximo");
        btnSalvarAlteracao.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalvarAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarAlteracaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPainelCadastroLayout = new javax.swing.GroupLayout(pnlPainelCadastro);
        pnlPainelCadastro.setLayout(pnlPainelCadastroLayout);
        pnlPainelCadastroLayout.setHorizontalGroup(
            pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPainelCadastroLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlPainelCadastroLayout.createSequentialGroup()
                            .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlPainelCadastroLayout.createSequentialGroup()
                            .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblIdade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCelular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlPainelCadastroLayout.createSequentialGroup()
                                    .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTelefone, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCpf, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(lblRg))
                                .addComponent(txtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnSalvarAlteracao, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPainelCadastroLayout.setVerticalGroup(
            pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPainelCadastroLayout.createSequentialGroup()
                .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPainelCadastroLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPainelCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRg, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPainelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnSalvarAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        lblAlterarDadosUsuario.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblAlterarDadosUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/user_edit.png"))); // NOI18N
        lblAlterarDadosUsuario.setText("Editar dados usuário");
        lblAlterarDadosUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPainelCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblAlterarDadosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAlterarDadosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPainelCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAlteracaoActionPerformed
       Cursor cursor = new Cursor(3);
        this.setCursor(cursor);
        try {
            // TODO add your handling code here
            lerDadosTela();
            UsuarioBO usuarioBO = new UsuarioBO();    
            usuarioBO.atualizarDados(usuario);
            this.dispose();
            this.telaConsultaUsuario.buscarTodosUsuario();
            MensagemTela.exibirMensagemSucesso(this, "Dados Alterados com sucesso!", "Alterar dados Usuário");
           
        } catch (CampoVazioException cve){
            MensagemTela.exibirMensagemErro(this, cve.getMessage(), "Cadastrar Usuário");
        } catch (SQLException ex){
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido. Contate com o adminstrador", "Cadastrar Usuário");
        }finally{
           cursor = new Cursor(0);
           this.setCursor(cursor);  
        }
    }//GEN-LAST:event_btnSalvarAlteracaoActionPerformed

    private void txtRgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRgActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    public void setValoresCampos(){
        txtNome.setText(usuario.getNome());
        txtCpf.setText(usuario.getCpf());
        txtRg.setText(usuario.getRg());
        txtIdade.setText(String.valueOf(usuario.getIdade()));
        txtTelefone.setText(usuario.getTelefone());
        txtCelular.setText(usuario.getCelular());
        txtEndereco.setText(usuario.getEndereco());
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvarAlteracao;
    private javax.swing.JLabel lblAlterarDadosUsuario;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblIdade;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblRg;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JPanel pnlPainelCadastro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JFormattedTextField txtIdade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRg;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
