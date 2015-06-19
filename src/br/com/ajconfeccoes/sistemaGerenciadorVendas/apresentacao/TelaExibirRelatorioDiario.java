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
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.DadosRelatorioDiario;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Liquidacao;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.CaixaDiarioBO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.LiquidacaoBO;
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
public class TelaExibirRelatorioDiario extends Tela {

    List<Liquidacao> liquidacoes = new ArrayList<>();
    CaixaDiario caixaDiario = null;
    
    public TelaExibirRelatorioDiario() {       
        initComponents();
        this.carregarDadosNaTela();
        if(liquidacoes.size() <= 0){
            desabilitarComponentes();
        }
    }
    public void desabilitarComponentes(){
        btnImprimir.disable();
        btnSalvarPdf.disable();
    }
    public void carregarDadosTabela(){
        Model model = new Model();
        this.tblContasRecebidas.setModel(model);
    }
    
    public void carregarDadosNaTela(){
        try{
            this.buscarDadosCaixaAtual();
            this.buscarDadosDeLiquidacoesConta();
            this.setValoresComponentesTela();
            this.buscarDadosDeLiquidacoesConta();
        } catch(CaixaAbertoException cae){
            this.carregarDadosTabela();
            MensagemTela.exibirMensagemInformacao(this, cae.getMessage(), "Relatório diário");
            this.dispose();
            this.setVisible(false);
        } catch(SQLException se){
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido. Contate com o administrador.", "Relatório diário");
        }
    }
    
    public void setValoresComponentesTela(){
        lblValorIniciado.setText(caixaDiario.getValorInicialFormatado());
        lblValorAtual.setText(caixaDiario.getValorFinalFormatado());
        lblQuantidadeDeContas.setText(String.valueOf(liquidacoes.size()));
    }
    
    public void buscarDadosCaixaAtual() throws SQLException{
        CaixaDiarioBO caixaDiarioBO = new CaixaDiarioBO();
        caixaDiario = caixaDiarioBO.buscarDadosCaixaAtual();
    }
    public void buscarDadosDeLiquidacoesConta(){
        try{
            this.setCursor(CursorUtil.getCursor(3));
            LiquidacaoBO liquidacaoBO = new LiquidacaoBO();
            liquidacoes = liquidacaoBO.liquidacoesDiarias();
            this.carregarDadosTabela();
        } catch (SQLException ex) {
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido. Contate com o adminstrador", "Consultar liquidações");
        } finally{
            this.setCursor(CursorUtil.getCursor(0));
        }
    }
    
    public List<DadosRelatorioDiario> getDadosRelatorio(){
        List<DadosRelatorioDiario> dados = new ArrayList<>();
        DadosRelatorioDiario dadosRelatorioDiario = null;
        for (Liquidacao liquidacao : liquidacoes) {
            dadosRelatorioDiario = new DadosRelatorioDiario();
            dadosRelatorioDiario.setCodigo(liquidacao.getConta().getCodigo());
            dadosRelatorioDiario.setValor(liquidacao.getValorPago());
            dadosRelatorioDiario.setCliente(liquidacao.getConta().getOperacaoVenda().getCliente().getNome());
            dadosRelatorioDiario.setUsuario(liquidacao.getConta().getOperacaoVenda().getUsuario().getNome());
            dados.add(dadosRelatorioDiario);
        }
        return dados;
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
        pnlRelatorioDiario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContasRecebidas = new javax.swing.JTable();
        pnlDadosCaixa = new javax.swing.JPanel();
        lblCaixaIniciado = new javax.swing.JLabel();
        lblValorIniciado = new javax.swing.JLabel();
        lblCaixaAtual = new javax.swing.JLabel();
        lblValorAtual = new javax.swing.JLabel();
        lblNumeroDeContas = new javax.swing.JLabel();
        lblQuantidadeDeContas = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        btnSalvarPdf = new javax.swing.JButton();

        setTitle("Relatório Diário");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/icon-folha.png"))); // NOI18N
        jLabel1.setText("Relátorio de contas liquidadas hoje");

        pnlRelatorioDiario.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatório diário"));

        tblContasRecebidas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblContasRecebidas);

        pnlDadosCaixa.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados caixa"));

        lblCaixaIniciado.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblCaixaIniciado.setForeground(new java.awt.Color(228, 73, 65));
        lblCaixaIniciado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCaixaIniciado.setText("Caixa Inicado em :");

        lblValorIniciado.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblValorIniciado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lblCaixaAtual.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblCaixaAtual.setForeground(new java.awt.Color(228, 73, 65));
        lblCaixaAtual.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCaixaAtual.setText("Caixa Atual em :");

        lblValorAtual.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblValorAtual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        lblNumeroDeContas.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblNumeroDeContas.setForeground(new java.awt.Color(228, 73, 65));
        lblNumeroDeContas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumeroDeContas.setText("Quantidade de contas pagas: ");

        lblQuantidadeDeContas.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblQuantidadeDeContas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout pnlDadosCaixaLayout = new javax.swing.GroupLayout(pnlDadosCaixa);
        pnlDadosCaixa.setLayout(pnlDadosCaixaLayout);
        pnlDadosCaixaLayout.setHorizontalGroup(
            pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblCaixaIniciado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNumeroDeContas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCaixaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblValorIniciado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblValorAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblQuantidadeDeContas, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDadosCaixaLayout.setVerticalGroup(
            pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosCaixaLayout.createSequentialGroup()
                .addGap(6, 12, Short.MAX_VALUE)
                .addGroup(pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuantidadeDeContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNumeroDeContas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblValorIniciado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCaixaIniciado, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCaixaAtual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorAtual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlRelatorioDiarioLayout = new javax.swing.GroupLayout(pnlRelatorioDiario);
        pnlRelatorioDiario.setLayout(pnlRelatorioDiarioLayout);
        pnlRelatorioDiarioLayout.setHorizontalGroup(
            pnlRelatorioDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRelatorioDiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRelatorioDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                    .addComponent(pnlDadosCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlRelatorioDiarioLayout.setVerticalGroup(
            pnlRelatorioDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRelatorioDiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDadosCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/printer.png"))); // NOI18N
        btnImprimir.setText("Imprimir");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRelatorioDiario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvarPdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRelatorioDiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnSalvarPdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPdfActionPerformed
        // TODO add your handling code here:
        
        List<DadosRelatorioDiario> dadosGerados = getDadosRelatorio();
        if(dadosGerados.size() > 0){
            try{
                this.setCursor(CursorUtil.getCursor(3));
                String arquivoRelatorio = System.getProperty("user.dir")
                       + "/relatorios/RelatorioDiario.jasper";

                Map<String,Object> paramentros = new HashMap<>();
                paramentros.put("valorInicial", caixaDiario.getValorInicial());
                JRBeanCollectionDataSource fonteDados 
                          = new JRBeanCollectionDataSource(dadosGerados);

                JasperPrint relatorioGerado = JasperFillManager.fillReport(arquivoRelatorio, paramentros, fonteDados);

                JasperViewer telaExibicaoRelatorio 
                           = new JasperViewer(relatorioGerado, false);
                telaExibicaoRelatorio.setTitle("Relatório de diário");
                telaExibicaoRelatorio.setVisible(true);
            } catch(JRException ex){            
                JOptionPane.showMessageDialog(this, "Erro ao exibir relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
            }finally{
                this.setCursor(CursorUtil.getCursor(0));
            }
        }else{
            JOptionPane.showMessageDialog(this, "Não liquidações hoje.", "Erro ao carregar PDF", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarPdfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalvarPdf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCaixaAtual;
    private javax.swing.JLabel lblCaixaIniciado;
    private javax.swing.JLabel lblNumeroDeContas;
    private javax.swing.JLabel lblQuantidadeDeContas;
    private javax.swing.JLabel lblValorAtual;
    private javax.swing.JLabel lblValorIniciado;
    private javax.swing.JPanel pnlDadosCaixa;
    private javax.swing.JPanel pnlRelatorioDiario;
    private javax.swing.JTable tblContasRecebidas;
    // End of variables declaration//GEN-END:variables

    public class Model extends AbstractTableModel{

        @Override
        public int getRowCount() {
            return liquidacoes.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            Liquidacao liquidacao = liquidacoes.get(linha);
            if(coluna == 0){
                return liquidacao.getConta().getCodigo();
            }else if(coluna == 1){
                return liquidacao.getConta().getOperacaoVenda().getCliente().getNome();
            }else if(coluna == 2){
                return liquidacao.getConta().getOperacaoVenda().getUsuario().getNome();
            }else {
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                return formatador.format(liquidacao.getValorPago());
            }
        }
        
        @Override
        public String getColumnName(int coluna){
            if(coluna == 0){
                return "Nº Conta";
            }else if(coluna == 1){
                return "Cliente";
            }else if(coluna == 2){
               return "Usuário"; 
            }else{
                return "Valor Pago";
            }
        }
    
    }
}
