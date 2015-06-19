/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.CursorUtil;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.MensagemTela;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ArgumentInvalidExeception;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Cliente;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.DadosRelatorioDiario;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Produto;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.ClienteBO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.ProdutoBO;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author arley
 */
public class TelaConsultaProduto extends Tela {
    
    TelaPrincipal telaPrincipal = null;
    List<Produto> produtos = null;
    
    public TelaConsultaProduto(TelaPrincipal telaPrincipal) {
        initComponents();
        produtos = new ArrayList<>();
        this.telaPrincipal = telaPrincipal;
        buscarTodosProdutos();
    }
    
    public void buscarTodosProdutos(){
        try{
            this.setCursor(CursorUtil.getCursor(3));
            ProdutoBO produtoBO = new ProdutoBO();
            this.produtos = produtoBO.buscarTodos();
            carregarDadosTabela();
        } catch (SQLException ex) {
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido. Contate com adminstrador", "Consultar produtos");
        } finally{
            this.setCursor(CursorUtil.getCursor(0));
        }
    }
    
    public void carregarDadosTabela(){
        Model model = new Model();
        this.tblProdutos.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblProdutosCadastrados = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnAtualizarEstoque = new javax.swing.JButton();
        btnSalvarPdf = new javax.swing.JButton();

        setTitle("Consultar Produtos");

        lblProdutosCadastrados.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblProdutosCadastrados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/package.png"))); // NOI18N
        lblProdutosCadastrados.setText("Produtos cadastrados");

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblProdutos);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/pencil.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/package_delete.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAtualizar.setBackground(new java.awt.Color(250, 253, 254));
        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/arrow_refresh.png"))); // NOI18N
        btnAtualizar.setOpaque(true);
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnAtualizarEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/arrow_refresh.png"))); // NOI18N
        btnAtualizarEstoque.setText("Atualizar Estoque");
        btnAtualizarEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarEstoqueActionPerformed(evt);
            }
        });

        btnSalvarPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/page_white_acrobat.png"))); // NOI18N
        btnSalvarPdf.setText("Salvar PDF");
        btnSalvarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarPdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizarEstoque)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvarPdf)
                        .addGap(18, 18, 18)
                        .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblProdutosCadastrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProdutosCadastrados, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditar)
                        .addComponent(btnAtualizarEstoque)
                        .addComponent(btnExcluir)
                        .addComponent(btnSalvarPdf))
                    .addComponent(btnAtualizar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        int linhaSelecionada = this.tblProdutos.getSelectedRow();
        if(linhaSelecionada != -1){
            Produto produtoSelecionado = this.produtos.get(linhaSelecionada);

            int resposta;
            String mensagem = "Deseja excluir produto " + produtoSelecionado.getDescricao()+ " ?";
            String titulo = "Excluir produto";
            resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                try {
                    CursorUtil.getCursor(3);
                    ProdutoBO produtoBO = new ProdutoBO();
                    produtoBO.excluirProduto(produtoSelecionado);
                    this.buscarTodosProdutos();
                    MensagemTela.exibirMensagemSucesso(this, "Dados excluidos com sucesso!", "Excluir produto");                
                }catch (ArgumentInvalidExeception aie){
                   MensagemTela.exibirMensagemErro(this, aie.getMessage(), "Excluir produto"); 
                } catch (Exception e) {
                    mensagem = "Erro desconhecido. Entre em contato com o administrador do sistema.";
                    MensagemTela.exibirMensagemErro(this, mensagem, "Excluir produto");
                }finally{
                    CursorUtil.getCursor(0);
                }
            }
            else{
                MensagemTela.exibirMensagemSucesso(this, "Dados não excluidos", "Excluir produto");
            }
        }else{
          String mensagem = "Nenhum produto selecionado.";
          JOptionPane.showMessageDialog(this, mensagem, "Excluir produto", JOptionPane.INFORMATION_MESSAGE);  
        }
        
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        this.setCursor(CursorUtil.getCursor(3));
        int linhaSelecionada = this.tblProdutos.getSelectedRow();
        
        if(linhaSelecionada != -1){
             try {
                 Produto produtoSelecionado = this.produtos.get(linhaSelecionada);  
                 TelaEditarProduto telaEditarDadosProduto = new TelaEditarProduto(this, produtoSelecionado);
                 telaPrincipal.addComponenteTela(telaEditarDadosProduto);
                 telaEditarDadosProduto.setMaximum(true);
                 telaEditarDadosProduto.setVisible(true);
             } catch (PropertyVetoException ex) {
                 Logger.getLogger(TelaConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
             }finally{
                 this.setCursor(CursorUtil.getCursor(0));
             }
        }else{
          this.setCursor(CursorUtil.getCursor(0));
          String mensagem = "Nenhum produto selecionado.";
          JOptionPane.showMessageDialog(this, mensagem, "Alterar produto", JOptionPane.INFORMATION_MESSAGE);  
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // TODO add your handling code here:
        this.buscarTodosProdutos();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnAtualizarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarEstoqueActionPerformed
        // TODO add your handling code here:
        this.setCursor(CursorUtil.getCursor(3));
        int linhaSelecionada = this.tblProdutos.getSelectedRow();
        
        if(linhaSelecionada != -1){
             try {
                 Produto produtoSelecionado = this.produtos.get(linhaSelecionada);  
                 TelaAtualizarEstoqueProduto telaAtualizarEstoque = new TelaAtualizarEstoqueProduto(this, produtoSelecionado);
                 telaPrincipal.addComponenteTela(telaAtualizarEstoque);
                 telaAtualizarEstoque.setMaximum(true);
                 telaAtualizarEstoque.setVisible(true);
             } catch (PropertyVetoException ex) {
                 Logger.getLogger(TelaConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
             }finally{
                 this.setCursor(CursorUtil.getCursor(0));
             }
        }else{
          this.setCursor(CursorUtil.getCursor(0));
          String mensagem = "Nenhum produto selecionado.";
          JOptionPane.showMessageDialog(this, mensagem, "Atualizar estoque", JOptionPane.INFORMATION_MESSAGE);  
        }
    }//GEN-LAST:event_btnAtualizarEstoqueActionPerformed

    private void btnSalvarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPdfActionPerformed
        // TODO add your handling code here        
        try{
            this.setCursor(CursorUtil.getCursor(3));  
            String arquivoRelatorio = System.getProperty("user.dir")
                   + "/relatorios/RelatorioEstoque.jasper";
            
            Map<String, Object> paramentros = new HashMap<String, Object>();
            
            JRBeanCollectionDataSource fonteDados 
                      = new JRBeanCollectionDataSource(produtos);
            
            JasperPrint relatorioGerado = JasperFillManager.fillReport(arquivoRelatorio, paramentros, fonteDados);
            
            JasperViewer telaExibicaoRelatorio 
                       = new JasperViewer(relatorioGerado, false);
            telaExibicaoRelatorio.setTitle("Relatório de Estoque");
            telaExibicaoRelatorio.setVisible(true);
        } catch(JRException ex){            
            JOptionPane.showMessageDialog(this, "Erro ao exibir relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally{
            this.setCursor(CursorUtil.getCursor(0));
        }
    }//GEN-LAST:event_btnSalvarPdfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnAtualizarEstoque;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvarPdf;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblProdutosCadastrados;
    private javax.swing.JTable tblProdutos;
    // End of variables declaration//GEN-END:variables

    public class Model extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return produtos.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            Produto produto = produtos.get(linha);
            if (coluna == 0) {
                return String.valueOf(produto.getCodigo());
            } else if (coluna == 1) {
                return produto.getDescricao();
            } else if (coluna == 2) {
                return String.valueOf(produto.getEstoque());
            } else if (coluna == 3) {
                return String.valueOf(produto.getQuantidadeMinima());
            } else {
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                return formatador.format(produto.getValorUnitario());
            }
        }

        @Override
        public String getColumnName(int coluna) {
            if (coluna == 0) {
                return "Código";
            } else if (coluna == 1) {
                return "Descrição";
            } else if (coluna == 2) {
                return "Estoque";
            } else if (coluna == 3) {
                return "Quantidade Minima";
            } else {
                return "Valor Unitário";
            }
        }

    }
}