package app.tela;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import app.produto.Produto;
import app.produto.ProdutoDAO;

/**
 *
 * @author Ulysses Werlich
 */
@SuppressWarnings("serial")
public class Tela extends javax.swing.JFrame {

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtProdutos;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtQuant;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Tela() {
        initComponents();
        
        DefaultTableModel modelo = (DefaultTableModel) jtProdutos.getModel();
        jtProdutos.setRowSorter(new TableRowSorter(modelo));
        
    }

    private void atualizarTela() {
		DefaultTableModel dtmProdutos = (DefaultTableModel) jtProdutos.getModel();

		dtmProdutos.setNumRows(0);
		
		ProdutoDAO dao = new ProdutoDAO();
		for (Produto produto : dao.getListaProduto()) {
			Object[] Dados = { produto.getId(), produto.getDescricao(),
					produto.getQtd(), produto.getPreco() };
			dtmProdutos.addRow(Dados);
		}
    	
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	
		try {
			Produto produto = new Produto();

			produto.setDescricao(txtDesc.getText());
			produto.setQtd(Integer.parseInt(txtQuant.getText()));
			produto.setPreco(Double.parseDouble(txtPreco.getText()));
			ProdutoDAO dao = new ProdutoDAO();
			dao.adiciona(produto);
			atualizarTela();

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor inválido nos campos 'Quantidade' e 'Preço'");
		}

		txtDesc.setText("");
		txtPreco.setText("");
		txtQuant.setText("");
                   
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) { // Botao EXCLUIR
		Produto produto = new Produto();

		try {
			produto.setId(Integer.parseInt(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 0).toString()));
			produto.setDescricao(txtDesc.getText());
			produto.setQtd(Integer.parseInt(txtQuant.getText()));
			produto.setPreco(Double.parseDouble(txtPreco.getText()));
			ProdutoDAO dao = new ProdutoDAO();
			dao.remove(produto);
			atualizarTela();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Valor inválido nos campos 'Quantidade' e 'Preço'");
		}

		txtDesc.setText(null);
		txtPreco.setText(null);
		txtQuant.setText(null);
    	
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) { // Botao ATUALIZAR

		Produto produto = new Produto();

		try {
			produto.setId(Integer.parseInt(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 0).toString()));
			produto.setDescricao(txtDesc.getText());
			produto.setQtd(Integer.parseInt(txtQuant.getText()));
			produto.setPreco(Double.parseDouble(txtPreco.getText()));
			ProdutoDAO dao = new ProdutoDAO();
			dao.altera(produto);
			atualizarTela();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Valor inválido nos campos 'Quantidade' e 'Preço'");
		}

		txtDesc.setText(null);
		txtPreco.setText(null);
		txtQuant.setText(null);
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    	
		DefaultTableModel dtmProdutos = (DefaultTableModel) jtProdutos.getModel();
		dtmProdutos.setNumRows(0);
		
        ProdutoDAO dao = new ProdutoDAO();
        
		for (Produto produto : dao.getListaProdutoPorDescricao(txtDesc.getText())) {
			Object[] Dados = { produto.getId(), produto.getDescricao(),
					produto.getQtd(), produto.getPreco() };
			dtmProdutos.addRow(Dados);
		}
    }

    private void jtProdutosMouseClicked(java.awt.event.MouseEvent evt) {
        
        if(jtProdutos.getSelectedRow() != -1 ){
            txtDesc.setText(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 1).toString());
            txtQuant.setText(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 2).toString());
            txtPreco.setText(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 3 ).toString());
        }
    }

    private void jtProdutosKeyReleased(java.awt.event.KeyEvent evt) {
    	
         if(jtProdutos.getSelectedRow() != -1 ){
            txtDesc.setText(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 1).toString());
            txtQuant.setText(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 2).toString());
            txtPreco.setText(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 3).toString());
            
         }
    }
      
    //Configuração de tela gerada automaticamente pelo NetBeans
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDesc = new javax.swing.JTextField();
        txtQuant = new javax.swing.JTextField();
        txtPreco = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PRODUTO");

        jLabel2.setText("QUANTIDADE");

        jLabel3.setText("PREÇO");

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excluir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Editar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Consultar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuant, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        jtProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PRODUTO", "QUANTIDADE", "PREÇO "
            }
        ) {
            @SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            @SuppressWarnings("rawtypes")
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtProdutosMouseClicked(evt);
            }
        });
        jtProdutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtProdutosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtProdutos);
        if (jtProdutos.getColumnModel().getColumnCount() > 0) {
            jtProdutos.getColumnModel().getColumn(0).setResizable(false);
            jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(4);
            jtProdutos.getColumnModel().getColumn(2).setResizable(false);
            jtProdutos.getColumnModel().getColumn(2).setPreferredWidth(4);
            jtProdutos.getColumnModel().getColumn(3).setResizable(false);
            jtProdutos.getColumnModel().getColumn(3).setPreferredWidth(4);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
}
