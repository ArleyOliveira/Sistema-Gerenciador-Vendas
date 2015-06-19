/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.CursorUtil;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.MensagemTela;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.CampoVazioException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ProdutoDescricaoDuplicadoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.QuantidadeProdutoInvalidoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ValorProdutoInvalidoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Produto;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.ProdutoBO;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arley
 */
public class TelaEditarProduto extends Tela {
    Produto produto;
    TelaConsultaProduto telaConsultaProduto = null;
    
    public TelaEditarProduto(TelaConsultaProduto telaConsultaProduto, Produto produto) {
        initComponents();
        this.produto = produto;
        this.setValoresCampos();
        this.telaConsultaProduto = telaConsultaProduto;
    }
    
    public void setValoresCampos(){
        txtCodigo.setText(String.valueOf(produto.getCodigo()));
        txtDescricao.setText(produto.getDescricao());
        txtQuantidade.setText(String.valueOf(produto.getEstoque()));
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
        txtValorUnitario.setText(formatador.format(produto.getValorUnitario()));
        txtQuantidadeMin.setText(String.valueOf(produto.getQuantidadeMinima()));
    }
    
    private void lerDados(){
        String descricao = txtDescricao.getText().trim();
        String strQuantidade = txtQuantidade.getText().trim();
        String strValorUnitario = txtValorUnitario.getText().trim();
        String strQuantidadeMin = txtQuantidadeMin.getText().trim();
        
        String mensagem = "";
        
        if(descricao.isEmpty()){
            mensagem = "Campo descricao é obrigatório.";
        }
        
        int quantidade = 0;
        if(strQuantidade.isEmpty()){
            mensagem = mensagem + "\nCampo Quantidade é obrigatório.";
        }else{
            try{
                quantidade = Integer.parseInt(strQuantidade);
            } catch(NumberFormatException nfe){
                mensagem = mensagem + "\nQuantidade Invalida.";
            }
        }
        
        double valorUnitario = 0;
        if(strValorUnitario.isEmpty()){
            mensagem = mensagem + "\nCampo Valor Unitário é obrigatório.";
        }else{
            try {
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                valorUnitario = formatador.parse(strValorUnitario).doubleValue();
            } catch (ParseException ex) {
                mensagem = mensagem + "\nValor Unitário Invalido";
            }
        }
        
        int quantidadeMin = 0;
        if(!strQuantidadeMin.isEmpty()){
            try{
                quantidadeMin = Integer.parseInt(strQuantidadeMin);
            } catch(NumberFormatException nfe){
                mensagem = mensagem + "\nQuantidade Minima Invalida.";
            }
        }
        
        if(mensagem == "" || mensagem == null){
            produto.setDescricao(descricao);
            produto.setEstoque(quantidade);
            produto.setQuantidadeMinima(quantidadeMin);
            produto.setValorUnitario(valorUnitario);
        }else{
            throw new CampoVazioException(mensagem);
        }
    }
    
    public void limparDados(){
        txtDescricao.setText("");
        txtQuantidade.setText("");
        txtValorUnitario.setText("");
        txtQuantidadeMin.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNovoProdutoEstoque = new javax.swing.JLabel();
        pnlCadastroProduto = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblQuantidade = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JFormattedTextField();
        lblValorUnitario = new javax.swing.JLabel();
        txtValorUnitario = new javax.swing.JFormattedTextField();
        lblQuantidadeMin = new javax.swing.JLabel();
        txtQuantidadeMin = new javax.swing.JFormattedTextField();
        btnSalvarAlteracoes = new javax.swing.JButton();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JFormattedTextField();

        setTitle("Cadastro de Produto");
        setOpaque(false);

        lblNovoProdutoEstoque.setBackground(new java.awt.Color(184, 179, 175));
        lblNovoProdutoEstoque.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblNovoProdutoEstoque.setForeground(new java.awt.Color(1, 1, 1));
        lblNovoProdutoEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/pencil.png"))); // NOI18N
        lblNovoProdutoEstoque.setText("Alterar dados produto em estoque");

        lblDescricao.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblDescricao.setText("Descricao (*):");

        lblQuantidade.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblQuantidade.setText("Quantidade (*):");

        txtQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtQuantidade.setEnabled(false);

        lblValorUnitario.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblValorUnitario.setText("Valor Unitário (*):");

        txtValorUnitario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        lblQuantidadeMin.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblQuantidadeMin.setText("Quantidade Min:");

        txtQuantidadeMin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        btnSalvarAlteracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/disk.png"))); // NOI18N
        btnSalvarAlteracoes.setText("Salvar Alterações");
        btnSalvarAlteracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarAlteracoesActionPerformed(evt);
            }
        });

        lblCodigo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblCodigo.setText("Código:");

        txtCodigo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCodigo.setEnabled(false);

        javax.swing.GroupLayout pnlCadastroProdutoLayout = new javax.swing.GroupLayout(pnlCadastroProduto);
        pnlCadastroProduto.setLayout(pnlCadastroProdutoLayout);
        pnlCadastroProdutoLayout.setHorizontalGroup(
            pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblQuantidadeMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblValorUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroProdutoLayout.createSequentialGroup()
                        .addGroup(pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtQuantidade)
                            .addComponent(txtValorUnitario)
                            .addComponent(txtQuantidadeMin, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlCadastroProdutoLayout.createSequentialGroup()
                        .addGroup(pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                            .addGroup(pnlCadastroProdutoLayout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastroProdutoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCadastroProdutoLayout.setVerticalGroup(
            pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantidadeMin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidadeMin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(btnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCadastroProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNovoProdutoEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNovoProdutoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCadastroProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarAlteracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAlteracoesActionPerformed
        // TODO add your handling code here:
        try{
            this.setCursor(CursorUtil.getCursor(3));
            lerDados();
            ProdutoBO produtoBO = new ProdutoBO();
            produtoBO.atualizarDadosProduto(produto);
            telaConsultaProduto.buscarTodosProdutos();
            MensagemTela.exibirMensagemSucesso(this, "Produto alterado com sucesso!", "Alterar produto");
            this.dispose();
        } catch(CampoVazioException cve){
            MensagemTela.exibirMensagemErro(this, cve.getMessage(), "Alterar produto");
        } catch(QuantidadeProdutoInvalidoException qpie){
            MensagemTela.exibirMensagemErro(this, qpie.getMessage(),  "Alterar produto");
        } catch(ValorProdutoInvalidoException vpie){
            MensagemTela.exibirMensagemErro(this, vpie.getMessage(),  "Alterar produto");
        } catch(SQLException se){
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido. Contate com o adminstrador do sistema",  "Alterar produto");
        } finally{
            this.setCursor(CursorUtil.getCursor(0));
        }
    }//GEN-LAST:event_btnSalvarAlteracoesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvarAlteracoes;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblNovoProdutoEstoque;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblQuantidadeMin;
    private javax.swing.JLabel lblValorUnitario;
    private javax.swing.JPanel pnlCadastroProduto;
    private javax.swing.JFormattedTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JFormattedTextField txtQuantidade;
    private javax.swing.JFormattedTextField txtQuantidadeMin;
    private javax.swing.JFormattedTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables
}