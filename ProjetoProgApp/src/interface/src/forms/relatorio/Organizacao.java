package forms.relatorio;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import entities.Categoria;
import entities.Despesa;
import entities.Rendimento;
import service.CategoriaService;
import service.DespesaService;
import service.RendimentoService;
import utils.FileHandler;

public class Organizacao extends javax.swing.JPanel {
        private CategoriaService categoriaService;
        private String selectedCategoria;

        public Organizacao() {
                this.categoriaService = new CategoriaService();

                initComponents();
                preencherCategorias();

                gridCards.setBackground(new Color(0, 0, 0, 0));
                btnDownload.setBackground(new Color(0, 0, 0, 0));
        }

        private void getLancamentoAllCadastrar() {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.fireTableDataChanged();
                model.setRowCount(0);

                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                int itens = 0;
                double valorTotal = 0;

                try {
                        int categoriaNumber = new CategoriaService().findCategoriaByName(selectedCategoria).getId();

                        ArrayList<Rendimento> rendimentos = new RendimentoService()
                                        .findAllRendimentosbyCategoria(categoriaNumber);
                        ArrayList<Despesa> despesas = new DespesaService().findAllDespesasbyCategoria(categoriaNumber);

                        for (Rendimento rendimento : rendimentos) {
                                itens++;
                                valorTotal += rendimento.getValorMensal() * 12
                                                + rendimento.getValorOcasional();
                                model.addRow(new Object[] {
                                                rendimento.getDescricao(),
                                                "R$ " + decimalFormat.format(rendimento.getValorMensal() * 12),
                                                "R$ " + decimalFormat.format(rendimento.getValorOcasional()),
                                                "R$ " + decimalFormat.format(rendimento.getValorMensal() * 12
                                                                + rendimento.getValorOcasional())
                                });

                        }

                        for (Despesa despesa : despesas) {
                                itens++;
                                valorTotal += despesa.getValorMensal() * 12
                                                + despesa.getValorOcasional();
                                model.addRow(new Object[] {
                                                despesa.getDescricao(),
                                                "R$ " + decimalFormat.format(despesa.getValorMensal() * 12),
                                                "R$ " + decimalFormat.format(despesa.getValorOcasional()),
                                                "R$ " + decimalFormat.format(despesa.getValorMensal() * 12
                                                                + despesa.getValorOcasional())
                                });
                        }

                        cardItens.setValue(itens + (itens != 1 ? " itens" : " item"));
                        cardTotal.setValue("R$ " + decimalFormat.format(valorTotal));
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao preencher tabela: " + e.getMessage(), "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        private void initComponents() {
                jScrollPane1 = new javax.swing.JScrollPane();
                table = new javax.swing.JTable();
                cbCategoria = new javax.swing.JComboBox<>();
                gridCards = new javax.swing.JPanel();
                cardItens = new forms.Cards();
                cardTotal = new forms.Cards();
                jPanel13 = new javax.swing.JPanel();
                labelTitle10 = new javax.swing.JLabel();
                btnDownload = new javax.swing.JButton();

                setBackground(new java.awt.Color(255, 255, 255));

                table.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
                table.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                                "Descrição", "Mensal (12x)", "Ocasional", "Total"
                                }));
                jScrollPane1.setViewportView(table);

                cbCategoria.addItem("categoria");
                cbCategoria.addActionListener(e -> cbCategoriaActionPerformed(e));

                gridCards.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

                cardItens.setForeground(new java.awt.Color(255, 255, 255));
                cardItens.setColor1(new java.awt.Color(25, 75, 255));
                cardItens.setColor2(new java.awt.Color(0, 18, 83));
                cardItens.setTitle("Itens");
                cardItens.setValue("0 itens");
                gridCards.add(cardItens);

                cardTotal.setColor1(new java.awt.Color(235, 173, 0));
                cardTotal.setColor2(new java.awt.Color(160, 121, 20));
                cardTotal.setTitle("Total");
                cardTotal.setValue("R$ 0,00");
                gridCards.add(cardTotal);

                jPanel13.setBackground(new java.awt.Color(25, 75, 255));

                labelTitle10.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
                labelTitle10.setForeground(new java.awt.Color(255, 255, 255));
                labelTitle10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                labelTitle10.setText("Organização");

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(
                                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(labelTitle10,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                1036,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel13Layout.setVerticalGroup(
                                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(16, Short.MAX_VALUE)
                                                                .addComponent(labelTitle10,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                76,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(14, 14, 14)));

                btnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/download.png"))); // NOI18N
                btnDownload.addActionListener(
                                e -> baixarTabela());

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(gridCards,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                500,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGroup(layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                                .addComponent(cbCategoria,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                150,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                .addComponent(btnDownload))
                                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                999,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addGap(55, 55, 55))))
                                                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel13,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(gridCards,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                113,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(cbCategoria,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnDownload,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                40,
                                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                347,
                                                                                Short.MAX_VALUE)));
        }// </editor-fold>//GEN-END:initComponents

        private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
                if (cbCategoria.getSelectedIndex() > 0) {
                        selectedCategoria = cbCategoria.getSelectedItem().toString();
                        getLancamentoAllCadastrar();
                }
        }// GEN-LAST:event_jComboBox1ActionPerformed

        private void preencherCategorias() {
                try {
                        ArrayList<Categoria> categorias = categoriaService.findAllCategorias();

                        for (Categoria categoria : categorias) {
                                cbCategoria.addItem(categoria.getNome());
                        }
                } catch (Exception e) {
                        System.out.println("Erro ao preencher categorias");
                        e.printStackTrace();
                }
        }

        private void baixarTabela() {
                FileHandler.downloadFile(FileHandler.ORGANIZACIONAL,
                                table, cbCategoria.getSelectedItem().toString());
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnDownload;
        private forms.Cards cardItens;
        private forms.Cards cardTotal;
        private javax.swing.JPanel gridCards;
        private javax.swing.JComboBox<String> cbCategoria;
        private javax.swing.JPanel jPanel13;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable table;
        private javax.swing.JLabel labelTitle10;
        // End of variables declaration//GEN-END:variables
}
