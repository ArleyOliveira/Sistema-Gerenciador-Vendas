/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao;

import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.CursorUtil;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.classesUtil.MensagemTela;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ArgumentInvalidExeception;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.CaixaAbertoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.CampoVazioException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ClienteInvalidoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ClienteStatusNegativoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.ProdutoExistenteNaLista;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.apresentacao.excessoes.QuantidadeDeItensCompraInvalidoException;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Cliente;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.ItensCompra;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.OperacaoVenda;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Produto;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.entidade.Usuario;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.ClienteBO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.OperacaoVendaBO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.ProdutoBO;
import br.com.ajconfeccoes.sistemaGerenciadorVendas.negocio.UsuarioBO;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
public class TelaOperacoesVenda extends Tela {

    Cliente cliente = null;
    List<ItensCompra> itensCompra = null;
    Usuario usuario = null;
    Produto produtoAtual = null;
    OperacaoVenda operacaoVenda = null;

    public TelaOperacoesVenda() {
        initComponents();
        this.configurarComponestesTela();
        this.itensCompra = new ArrayList<>();
        carregarDadosTabelaItens();
        operacaoVenda = new OperacaoVenda();
        this.setDadosVenda();
        this.buscarUsuario();
    }

    public void atualizarDadosTelaCompleta() {
        try {
            this.configurarComponestesTela();
            this.itensCompra = new ArrayList<>();
            carregarDadosTabelaItens();
            cliente = null;
            operacaoVenda = null;
            operacaoVenda = new OperacaoVenda();
            this.setDadosVenda();
            this.buscarUsuario();
            this.limparDadosClienteTela();
            this.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TelaOperacoesVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addTodosDadosVenda() {
        this.operacaoVenda.setCliente(cliente);
        this.operacaoVenda.setUsuario(usuario);
        this.operacaoVenda.setItensCompra(itensCompra);
    }

    public void buscarUsuario() {
        try {
            UsuarioBO usuarioBO = new UsuarioBO();
            usuario = usuarioBO.buscarTodos().get(0);
        } catch (SQLException ex) {
            MensagemTela.exibirMensagemErro(this, "Não foi possivel recuperar usuario", "Usuario");
        }
    }

    public void setDadosVenda() {
        lblDataVendaData.setText(operacaoVenda.getDataFormatada());
        lblValorTotalVendaValor.setText("R$ " + operacaoVenda.getValorTotalFormatado());
    }

    public void setDadosProdutoTela(Produto produto) {
        txtDescricao.setText(produto.getDescricao());
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
        txtValorUnitarioProduto.setText(formatador.format(produto.getValorUnitario()));
    }

    private void configurarComponestesTela() {
        lblNomeCliente.setVisible(false);
        lblClienteAtivo.setVisible(false);
    }

    private void setNomeClienteTela() {
        lblNomeCliente.setVisible(true);
        lblClienteAtivo.setText(cliente.getNome());
        lblClienteAtivo.setVisible(true);
    }

    private String lerCpfCliente() {
        String cpf = txtCpf.getText().trim();

        String mensagem = "";

        if (cpf.length() < 11) {
            mensagem = "CPF inválido.";
        }

        if (!(mensagem == "" || mensagem == null)) {
            throw new CampoVazioException(mensagem);
        }

        return cpf;
    }

    private int lerCodigoProduto() {
        String strCodigo = txtCodigoProduto.getText().trim();

        String mensagem = "";
        int codigo = -1;
        if (strCodigo.isEmpty()) {
            mensagem = "Informe o código do produto";
        } else {
            try {
                codigo = Integer.parseInt(strCodigo);
            } catch (NumberFormatException nfe) {
                mensagem = "Código invalido";
            }
        }

        if (!(mensagem == "" || mensagem == null)) {
            throw new CampoVazioException(mensagem);
        }

        return codigo;
    }

    private void limparDadosClienteTela() {
        txtCpf.setText("");
        lblNomeCliente.setText("");
        lblClienteAtivo.setText("");
        configurarComponestesTela();
    }

    private void limparDadosProdutoInserido() {
        txtCodigoProduto.setText("");
        txtDescricao.setText("");
        txtQuantidadeProduto.setText("");
        txtValorUnitarioProduto.setText("");
    }

    private int lerQuantidadeProduto() {
        String strQuantidade = txtQuantidadeProduto.getText().trim();

        String mensagem = "";
        int quantidade = -1;
        if (strQuantidade.isEmpty()) {
            mensagem = "Informe a quantidade do produto";
        } else {
            try {
                quantidade = Integer.parseInt(strQuantidade);
            } catch (NumberFormatException nfe) {
                mensagem = "Quantidade invalida";
            }
        }
        if (quantidade <= 0) {
            mensagem = "Quantidade invalida";
        }
        if (!(mensagem == "" || mensagem == null)) {
            throw new CampoVazioException(mensagem);
        }

        return quantidade;
    }

    public void carregarDadosTabelaItens() {
        Model model = new Model();
        this.tblItensCompra.setModel(model);
    }

    public void verificaProdutoJaInseridoNaLista(int codigo) {
        for (ItensCompra item : itensCompra) {
            if (item.getProduto().getCodigo() == codigo) {
                throw new ProdutoExistenteNaLista("Produto ja adicionado na lista.\nPara alterar selecione o produto em clique no botão alterar");
            }
        }
    }

    public void existeProdutoParaAdicionar() {
        if (produtoAtual == null) {
            throw new ArgumentInvalidExeception(("Não há item para adicionar.\nPor favor consulte o produto desejado."));
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

        lblOperacoesVenda = new javax.swing.JLabel();
        pnlCliente = new javax.swing.JPanel();
        lblCPF = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        lblConsultarCliente = new javax.swing.JButton();
        lblNomeCliente = new javax.swing.JLabel();
        lblClienteAtivo = new javax.swing.JLabel();
        pnlDadosVenda = new javax.swing.JPanel();
        lblDataVenda = new javax.swing.JLabel();
        lblValorTotalVenda = new javax.swing.JLabel();
        lblValorTotalVendaValor = new javax.swing.JLabel();
        lblDataVendaData = new javax.swing.JLabel();
        pnlProduto = new javax.swing.JPanel();
        lblCodigoProduto = new javax.swing.JLabel();
        txtCodigoProduto = new javax.swing.JFormattedTextField();
        lblConsultarProduto = new javax.swing.JButton();
        lblDescricaoProduto = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblQuantidadeProduto = new javax.swing.JLabel();
        txtQuantidadeProduto = new javax.swing.JFormattedTextField();
        lblValorUnitarioProduto = new javax.swing.JLabel();
        txtValorUnitarioProduto = new javax.swing.JFormattedTextField();
        btnAdicionarProduto = new javax.swing.JButton();
        pnlItensVenda = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItensCompra = new javax.swing.JTable();
        btnEditarProduto = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAddContas = new javax.swing.JButton();
        btnLiquidar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setTitle("Operações Venda");

        lblOperacoesVenda.setBackground(new java.awt.Color(108, 184, 239));
        lblOperacoesVenda.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblOperacoesVenda.setForeground(new java.awt.Color(210, 37, 37));
        lblOperacoesVenda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOperacoesVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/computer.png"))); // NOI18N
        lblOperacoesVenda.setText("Operações de Vendas - AJ CONFECÇÕES");
        lblOperacoesVenda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblOperacoesVenda.setOpaque(true);

        pnlCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        lblCPF.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblCPF.setText("CPF (*):");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblConsultarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/zoom.png"))); // NOI18N
        lblConsultarCliente.setText("Consultar");
        lblConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblConsultarClienteActionPerformed(evt);
            }
        });

        lblNomeCliente.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblNomeCliente.setText("Nome: ");

        lblClienteAtivo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblClienteAtivo.setForeground(new java.awt.Color(72, 131, 228));

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClienteAtivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblConsultarCliente)
                        .addGap(0, 12, Short.MAX_VALUE))))
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlClienteLayout.createSequentialGroup()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConsultarCliente))
                .addGap(18, 18, 18)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lblClienteAtivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        pnlDadosVenda.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Venda"));

        lblDataVenda.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblDataVenda.setForeground(new java.awt.Color(88, 152, 235));
        lblDataVenda.setText("Data :");

        lblValorTotalVenda.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblValorTotalVenda.setForeground(new java.awt.Color(88, 152, 235));
        lblValorTotalVenda.setText("Valor Total :");

        lblValorTotalVendaValor.setFont(new java.awt.Font("Ubuntu", 1, 25)); // NOI18N
        lblValorTotalVendaValor.setForeground(new java.awt.Color(199, 69, 69));
        lblValorTotalVendaValor.setText("R$ 0,00");

        lblDataVendaData.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N

        javax.swing.GroupLayout pnlDadosVendaLayout = new javax.swing.GroupLayout(pnlDadosVenda);
        pnlDadosVenda.setLayout(pnlDadosVendaLayout);
        pnlDadosVendaLayout.setHorizontalGroup(
            pnlDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDataVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblValorTotalVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblValorTotalVendaValor, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(lblDataVendaData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDadosVendaLayout.setVerticalGroup(
            pnlDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosVendaLayout.createSequentialGroup()
                .addGroup(pnlDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataVendaData, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblValorTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDadosVendaLayout.createSequentialGroup()
                        .addComponent(lblValorTotalVendaValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addGap(8, 8, 8))
        );

        pnlProduto.setBorder(javax.swing.BorderFactory.createTitledBorder("Produto"));
        pnlProduto.setToolTipText("");

        lblCodigoProduto.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblCodigoProduto.setText("Código :");

        txtCodigoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lblConsultarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/zoom.png"))); // NOI18N
        lblConsultarProduto.setText("Consultar");
        lblConsultarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblConsultarProdutoActionPerformed(evt);
            }
        });

        lblDescricaoProduto.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblDescricaoProduto.setText("Descrição :");

        txtDescricao.setEditable(false);
        txtDescricao.setBackground(new java.awt.Color(136, 192, 234));
        txtDescricao.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        lblQuantidadeProduto.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblQuantidadeProduto.setText("Quantidade:");

        txtQuantidadeProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lblValorUnitarioProduto.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblValorUnitarioProduto.setText("Valor Unit R$:");

        txtValorUnitarioProduto.setEditable(false);
        txtValorUnitarioProduto.setBackground(new java.awt.Color(136, 192, 234));
        txtValorUnitarioProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        btnAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/add.png"))); // NOI18N
        btnAdicionarProduto.setText("Adicionar");
        btnAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProdutoLayout = new javax.swing.GroupLayout(pnlProduto);
        pnlProduto.setLayout(pnlProdutoLayout);
        pnlProdutoLayout.setHorizontalGroup(
            pnlProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdutoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnlProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDescricaoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCodigoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProdutoLayout.createSequentialGroup()
                        .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblConsultarProduto)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlProdutoLayout.createSequentialGroup()
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblValorUnitarioProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorUnitarioProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlProdutoLayout.setVerticalGroup(
            pnlProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdutoLayout.createSequentialGroup()
                .addGroup(pnlProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConsultarProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorUnitarioProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorUnitarioProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 27, Short.MAX_VALUE))
        );

        pnlItensVenda.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens Venda"));

        tblItensCompra.setModel(new javax.swing.table.DefaultTableModel(
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
        tblItensCompra.setFocusable(false);
        jScrollPane1.setViewportView(tblItensCompra);

        btnEditarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/pencil.png"))); // NOI18N
        btnEditarProduto.setText("Editar");
        btnEditarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProdutoActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/package_delete.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlItensVendaLayout = new javax.swing.GroupLayout(pnlItensVenda);
        pnlItensVenda.setLayout(pnlItensVendaLayout);
        pnlItensVendaLayout.setHorizontalGroup(
            pnlItensVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItensVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItensVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlItensVendaLayout.createSequentialGroup()
                        .addComponent(btnEditarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlItensVendaLayout.setVerticalGroup(
            pnlItensVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItensVendaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItensVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btnAddContas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/contas16x16.png"))); // NOI18N
        btnAddContas.setText("Add Contas");
        btnAddContas.setPreferredSize(null);
        btnAddContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddContasActionPerformed(evt);
            }
        });

        btnLiquidar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/money.png"))); // NOI18N
        btnLiquidar.setText("Liquidar");
        btnLiquidar.setPreferredSize(null);
        btnLiquidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiquidarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ajconfeccoes/sistemaGerenciadorVendas/apresentacao/imagens/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(null);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOperacoesVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDadosVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlItensVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLiquidar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddContas, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblOperacoesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDadosVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlItensVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLiquidar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddContas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblConsultarClienteActionPerformed
        // TODO add your handling code here:
        try {
            this.setCursor(CursorUtil.getCursor(3));
            ClienteBO clienteBO = new ClienteBO();
            cliente = clienteBO.buscarByCpf(lerCpfCliente());
            this.setNomeClienteTela();
        } catch (CampoVazioException cve) {
            MensagemTela.exibirMensagemErro(this, cve.getMessage(), "Consultar Cliente");
        } catch (ArgumentInvalidExeception aie) {
            MensagemTela.exibirMensagemErro(this, aie.getMessage(), "Consultar Cliente");
        } catch (SQLException ex) {
            MensagemTela.exibirMensagemErro(this, "Não foi possivel consultar o cliente\nTente novamente", "Consultar Cliente");
        } finally {
            this.setCursor(CursorUtil.getCursor(0));
        }
    }//GEN-LAST:event_lblConsultarClienteActionPerformed

    private void lblConsultarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblConsultarProdutoActionPerformed
        // TODO add your handling code here:
        try {
            this.setCursor(CursorUtil.getCursor(3));
            ProdutoBO produtoBO = new ProdutoBO();
            produtoAtual = produtoBO.buscarByCodigo(lerCodigoProduto());
            if (produtoAtual == null) {
                MensagemTela.exibirMensagemErro(this, "Produto não encontrado", "Consultar produto");
            } else {
                this.setDadosProdutoTela(produtoAtual);
            }
        } catch (CampoVazioException cve) {
            MensagemTela.exibirMensagemErro(this, cve.getMessage(), "Consultar produto");
        } catch (SQLException ex) {
            MensagemTela.exibirMensagemErro(this, "Não foi possivel consultar o produto\nTente novamente", "Consultar produto");
        } finally {
            this.setCursor(CursorUtil.getCursor(0));
        }
    }//GEN-LAST:event_lblConsultarProdutoActionPerformed

    private void btnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoActionPerformed
        // TODO add your handling code here:
        try {
            this.setCursor(CursorUtil.getCursor(3));
            existeProdutoParaAdicionar();
            ItensCompra item = new ItensCompra();
            int quantidade = -1;
            quantidade = lerQuantidadeProduto();
            verificaProdutoJaInseridoNaLista(produtoAtual.getCodigo());
            if (quantidade <= produtoAtual.getEstoque()) {
                item.setProduto(produtoAtual);
                item.setQuantidade(quantidade);
                item.setValorUnitario(produtoAtual.getValorUnitario());
                item.setValorTotal(quantidade * produtoAtual.getValorUnitario());
                this.itensCompra.add(item);
                operacaoVenda.setValorTotal(operacaoVenda.getValorTotal() + (quantidade * produtoAtual.getValorUnitario()));
                this.setDadosVenda();
                this.carregarDadosTabelaItens();
                limparDadosProdutoInserido();
                produtoAtual = null;
            } else {
                MensagemTela.exibirMensagemErro(this, "Quantidade indisponivel para este produto", "Adicionar produto");
            }
        } catch (ArgumentInvalidExeception aie) {
            MensagemTela.exibirMensagemErro(this, aie.getMessage(), "Adicionar produto");
        } catch (ProdutoExistenteNaLista penl) {
            MensagemTela.exibirMensagemErro(this, penl.getMessage(), "Adicionar produto");
            limparDadosProdutoInserido();
            produtoAtual = null;
        } catch (CampoVazioException cve) {
            MensagemTela.exibirMensagemErro(this, cve.getMessage(), "Adicionar produto");
        } finally {
            this.setCursor(CursorUtil.getCursor(0));
        }
    }//GEN-LAST:event_btnAdicionarProdutoActionPerformed

    private void btnEditarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProdutoActionPerformed
        // TODO add your handling code here:
        this.setCursor(CursorUtil.getCursor(3));
        int linhaSelecionada = this.tblItensCompra.getSelectedRow();

        if (linhaSelecionada != -1) {
            try {
                ItensCompra item = this.itensCompra.get(linhaSelecionada);
                produtoAtual = item.getProduto();
                txtCodigoProduto.setText(String.valueOf(produtoAtual.getCodigo()));
                txtQuantidadeProduto.setText(String.valueOf(item.getQuantidade()));
                setDadosProdutoTela(produtoAtual);
                operacaoVenda.setValorTotal(operacaoVenda.getValorTotal() - item.getValorTotal());
                this.itensCompra.remove(item);
                carregarDadosTabelaItens();
            } finally {
                this.setCursor(CursorUtil.getCursor(0));
            }
        } else {
            this.setCursor(CursorUtil.getCursor(0));
            String mensagem = "Nenhum produto selecionado.";
            JOptionPane.showMessageDialog(this, mensagem, "Alterar produto", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarProdutoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        this.setCursor(CursorUtil.getCursor(3));
        int linhaSelecionada = this.tblItensCompra.getSelectedRow();

        if (linhaSelecionada != -1) {
            try {
                ItensCompra item = this.itensCompra.get(linhaSelecionada);
                operacaoVenda.setValorTotal(operacaoVenda.getValorTotal() - item.getValorTotal());
                this.itensCompra.remove(item);
                setDadosVenda();
                carregarDadosTabelaItens();
            } finally {
                this.setCursor(CursorUtil.getCursor(0));
            }
        } else {
            this.setCursor(CursorUtil.getCursor(0));
            String mensagem = "Nenhum produto selecionado.";
            JOptionPane.showMessageDialog(this, mensagem, "Remover produto", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAddContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContasActionPerformed
        // TODO add your handling code here
        try {
            this.setCursor(CursorUtil.getCursor(3));
            this.addTodosDadosVenda();
            OperacaoVendaBO vendaBO = new OperacaoVendaBO();
            vendaBO.criarVenda(operacaoVenda);
            this.atualizarDadosTelaCompleta();
            MensagemTela.exibirMensagemSucesso(this, "A venda foi registrar com sucesso!", "Operação Venda");
        } catch (ClienteInvalidoException cie) {
            MensagemTela.exibirMensagemErro(this, cie.getMessage(), "Operação Venda");
        } catch (ArgumentInvalidExeception aie) {
            MensagemTela.exibirMensagemErro(this, aie.getMessage(), "Operação Venda");
        } catch (QuantidadeDeItensCompraInvalidoException qicie) {
            MensagemTela.exibirMensagemErro(this, qicie.getMessage(), "Operação Venda");
        } catch (ClienteStatusNegativoException csne) {
            MensagemTela.exibirMensagemErro(this, csne.getMessage(), "Operação Venda");
        } catch (SQLException se) {
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido Contate com o adminstrador", "Operação Venda");
        } finally {
            this.setCursor(CursorUtil.getCursor(0));
        }
    }//GEN-LAST:event_btnAddContasActionPerformed

    private void btnLiquidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiquidarActionPerformed
        // TODO add your handling code here:
        try {
            this.setCursor(CursorUtil.getCursor(3));
            this.addTodosDadosVenda();
            OperacaoVendaBO vendaBO = new OperacaoVendaBO();
            vendaBO.criarVendaELiquidar(operacaoVenda);
            this.atualizarDadosTelaCompleta();
            MensagemTela.exibirMensagemSucesso(this, "A venda foi registrar com sucesso!\nE o valor foi adicionado no caixa", "Operação Venda");
        } catch (ArgumentInvalidExeception aie) {
            MensagemTela.exibirMensagemErro(this, aie.getMessage(), "Operação Venda");
        } catch (ClienteInvalidoException cie) {
            MensagemTela.exibirMensagemErro(this, cie.getMessage(), "Operação Venda");
        } catch (QuantidadeDeItensCompraInvalidoException qicie) {
            MensagemTela.exibirMensagemErro(this, qicie.getMessage(), "Operação Venda");
        } catch(CaixaAbertoException cae){
            MensagemTela.exibirMensagemErro(this, cae.getMessage(), "Operação Venda");
        } catch (SQLException ex) {
            MensagemTela.exibirMensagemErro(this, "Erro desconhecido Contate com o adminstrador", "Operação Venda");
        } finally {
            this.setCursor(CursorUtil.getCursor(0));
        }
    }//GEN-LAST:event_btnLiquidarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int resposta;
        String mensagem = "Deseja cancelar esta venda ?";
        String titulo = "Cancelar venda";
        resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            this.atualizarDadosTelaCompleta();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddContas;
    private javax.swing.JButton btnAdicionarProduto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditarProduto;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLiquidar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblClienteAtivo;
    private javax.swing.JLabel lblCodigoProduto;
    private javax.swing.JButton lblConsultarCliente;
    private javax.swing.JButton lblConsultarProduto;
    private javax.swing.JLabel lblDataVenda;
    private javax.swing.JLabel lblDataVendaData;
    private javax.swing.JLabel lblDescricaoProduto;
    private javax.swing.JLabel lblNomeCliente;
    private javax.swing.JLabel lblOperacoesVenda;
    private javax.swing.JLabel lblQuantidadeProduto;
    private javax.swing.JLabel lblValorTotalVenda;
    private javax.swing.JLabel lblValorTotalVendaValor;
    private javax.swing.JLabel lblValorUnitarioProduto;
    private javax.swing.JPanel pnlCliente;
    private javax.swing.JPanel pnlDadosVenda;
    private javax.swing.JPanel pnlItensVenda;
    private javax.swing.JPanel pnlProduto;
    private javax.swing.JTable tblItensCompra;
    private javax.swing.JFormattedTextField txtCodigoProduto;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JFormattedTextField txtQuantidadeProduto;
    private javax.swing.JFormattedTextField txtValorUnitarioProduto;
    // End of variables declaration//GEN-END:variables

    public class Model extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return itensCompra.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            ItensCompra item = itensCompra.get(linha);
            if (coluna == 0) {
                return item.getProduto().getCodigo();
            } else if (coluna == 1) {
                return item.getProduto().getDescricao();
            } else if (coluna == 2) {
                return item.getQuantidade();
            } else if (coluna == 3) {
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                return formatador.format(item.getValorUnitario());
            } else {
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                return formatador.format(item.getValorTotal());
            }
        }

        @Override
        public String getColumnName(int coluna) {
            if (coluna == 0) {
                return "CODIGO";
            } else if (coluna == 1) {
                return "DESCRIÇÃO";
            } else if (coluna == 2) {
                return "QUANTIDADE";
            } else if (coluna == 3) {
                return "VALOR UNIT";
            } else {
                return "VALOR TOTAL";
            }
        }

    }
}
