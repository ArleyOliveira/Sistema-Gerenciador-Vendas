/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.CursorUtil;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.MensagemTela;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.CaixaAbertoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.CaixaDiario;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.CaixaDiarioBO;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arley
 */
public class TelaIniciarCaixa extends Tela{

    CaixaDiario caixaDiario= new CaixaDiario();
    public TelaIniciarCaixa() {
        try{
            verificaCaixaAberto();
        }catch(CaixaAbertoException cae){
            MensagemTela.exibirMensagemInformacao(this, cae.getMessage(),"Abrir caixa");
            this.dispose();
        }catch(SQLException se){
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido. Contate com o adminstrador.","Abrir caixa");
            this.dispose();
        }
        initComponents();
        setDataTela();
    }
    
    public void setDataTela(){
        lblDataDataAtual.setText(caixaDiario.getDataFormatada());
    }
    
    public void verificaCaixaAberto() throws CaixaAbertoException, SQLException{
        CaixaDiarioBO caixaDiarioBO = new CaixaDiarioBO();
        caixaDiarioBO.verificaCaixaAberto();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlDadosCaixa = new javax.swing.JPanel();
        lblValorInicial = new javax.swing.JLabel();
        txtValorInicial = new javax.swing.JFormattedTextField();
        btnIniciar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblDataDataAtual = new javax.swing.JLabel();

        setTitle("Iniciar Caixa");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/computer.png"))); // NOI18N
        jLabel1.setText("Iniciar Caixa");

        pnlDadosCaixa.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Caixa"));
        pnlDadosCaixa.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        lblValorInicial.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblValorInicial.setText("Valor Inicial R$:");

        txtValorInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtValorInicial.setText("0,00");

        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/monitor_go.png"))); // NOI18N
        btnIniciar.setText("Iniciar ");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setText("Data:");

        lblDataDataAtual.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblDataDataAtual.setForeground(new java.awt.Color(76, 148, 231));

        javax.swing.GroupLayout pnlDadosCaixaLayout = new javax.swing.GroupLayout(pnlDadosCaixa);
        pnlDadosCaixa.setLayout(pnlDadosCaixaLayout);
        pnlDadosCaixaLayout.setHorizontalGroup(
            pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDadosCaixaLayout.createSequentialGroup()
                        .addComponent(txtValorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDataDataAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(313, Short.MAX_VALUE))
        );
        pnlDadosCaixaLayout.setVerticalGroup(
            pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosCaixaLayout.createSequentialGroup()
                .addGroup(pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(lblDataDataAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDadosCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDadosCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        try{
            this.setCursor(CursorUtil.getCursor(3));
            lerValorCaixaInicial();
            CaixaDiarioBO caixaDiarioBO = new CaixaDiarioBO();
            caixaDiarioBO.criar(caixaDiario);
            MensagemTela.exibirMensagemSucesso(this, "Caixa iniciado com sucesso", "Abrir caixa");
            this.dispose();
        } catch(ParseException pe){
            MensagemTela.exibirMensagemErro(this, "Valor inválido.", "Abrir caixa");
        } catch(CaixaAbertoException cbe){
            MensagemTela.exibirMensagemInformacao(this, cbe.getMessage(),"Abrir caixa");
        } catch (SQLException ex) {
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido. Contate com o adminstrador.","Abrir caixa");
        } finally{
            this.setCursor(CursorUtil.getCursor(0));
        }
    }//GEN-LAST:event_btnIniciarActionPerformed
    public void lerValorCaixaInicial() throws ParseException{
        String strValor = txtValorInicial.getText().trim();
        double valor = -1;
        if(strValor.isEmpty()){
            valor = 0;
        }else{
            DecimalFormat formatador = new DecimalFormat("#,##0.00");        
            valor = formatador.parse(strValor).doubleValue();
            caixaDiario.setValorInicial(valor);
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblDataDataAtual;
    private javax.swing.JLabel lblValorInicial;
    private javax.swing.JPanel pnlDadosCaixa;
    private javax.swing.JFormattedTextField txtValorInicial;
    // End of variables declaration//GEN-END:variables
}
