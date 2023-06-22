package forms.relatorio;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import entities.Despesa;
import entities.Fundo;
import entities.Investimento;
import entities.Rendimento;
import service.DespesaService;
import service.FundoService;
import service.InvestimentoService;
import service.RendimentoService;
import utils.FileHandler;

public class RelatorioAnual extends javax.swing.JPanel {
        public RelatorioAnual() {
                initComponents();
                gridCards.setBackground(new Color(0, 0, 0, 0));
                btnDownload.setBackground(new Color(0, 0, 0, 0));
        }

        private void getLancametoAllYear(int year) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.fireTableDataChanged();
                model.setRowCount(0);

                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                double rendimentoTotal = 0;
                double despesaTotal = 0;
                double investimentoTotal = 0;
                double fundoTotal = 0;

                try {
                        ArrayList<Rendimento> rendimentos = new RendimentoService().findAllRendimentosByYear(year);
                        ArrayList<Despesa> despesas = new DespesaService().findAllDespesasByYear(year);
                        ArrayList<Investimento> investimentos = new InvestimentoService()
                                        .findAllInvestimentosByYear(year);
                        ArrayList<Fundo> fundos = new FundoService().findAllFundosByYear(year);

                        for (Rendimento rendimento : rendimentos) {
                                model.addRow(new Object[] {
                                                "Rendimento",
                                                rendimento.getDescricao(),
                                                "R$ " + decimalFormat.format(rendimento.getValorMensal() * 12),
                                                "R$ " + decimalFormat.format(rendimento.getValorOcasional()),
                                                "R$ " + decimalFormat.format(rendimento.getValorMensal() * 12
                                                                + rendimento.getValorOcasional())
                                });
                                rendimentoTotal += rendimento.getValorMensal() * 12 + rendimento.getValorOcasional();
                        }

                        for (Despesa despesa : despesas) {
                                model.addRow(new Object[] {
                                                "Despesa",
                                                despesa.getDescricao(),
                                                "R$ " + decimalFormat.format(despesa.getValorMensal() * 12),
                                                "R$ " + decimalFormat.format(despesa.getValorOcasional()),
                                                "R$ " + decimalFormat.format(despesa.getValorMensal() * 12
                                                                + despesa.getValorOcasional())
                                });
                                despesaTotal += despesa.getValorMensal() * 12
                                                + despesa.getValorOcasional();
                        }

                        for (Investimento investimento : investimentos) {
                                model.addRow(new Object[] {
                                                "Investimento",
                                                investimento.getDescricao(),
                                                "R$ " + decimalFormat.format(investimento.getValorMensal() * 12),
                                                "R$ " + decimalFormat.format(investimento.getValorOcasional()),
                                                "R$ " + decimalFormat
                                                                .format(investimento.getValorMensal() * 12
                                                                                + investimento.getValorOcasional())
                                });
                                investimentoTotal += investimento.getValorMensal() * 12
                                                + investimento.getValorOcasional();
                        }

                        for (Fundo fundo : fundos) {
                                model.addRow(new Object[] {
                                                "Fundo",
                                                fundo.getDescricao(),
                                                "R$ " + decimalFormat.format(fundo.getValorMensal() * 12),
                                                "R$ " + decimalFormat.format(fundo.getValorOcasional()),
                                                "R$ " + decimalFormat.format(
                                                                fundo.getValorMensal() * 12 + fundo.getValorOcasional())
                                });
                                fundoTotal += fundo.getValorMensal() * 12 + fundo.getValorOcasional();
                        }

                        cardRendimento.setValue("R$" + decimalFormat.format(rendimentoTotal));
                        cardDespesa.setValue("R$" + decimalFormat.format(despesaTotal));
                        cardInvestimento.setValue("R$" + decimalFormat.format(investimentoTotal));
                        cardFundo.setValue("R$" + decimalFormat.format(fundoTotal));
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao preencher tabela: " + e.getMessage(), "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        private void baixarTabela() {
                String year = cbAno.getSelectedItem().toString();
                FileHandler.downloadFile(FileHandler.RELATORIO_ANUAL,
                                table, year);
        }

        private void initComponents() {
                jScrollPane1 = new javax.swing.JScrollPane();
                table = new javax.swing.JTable();
                gridCards = new javax.swing.JPanel();
                cardRendimento = new forms.Cards();
                cardDespesa = new forms.Cards();
                cardInvestimento = new forms.Cards();
                cardFundo = new forms.Cards();
                jPanel6 = new javax.swing.JPanel();
                labelTitle3 = new javax.swing.JLabel();
                cbAno = new javax.swing.JComboBox<>();
                btnDownload = new javax.swing.JButton();

                setBackground(new java.awt.Color(255, 255, 255));

                table.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
                table.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                                "Tipo Lancamento", "Descricao", "Mensal (12x)", "Ocasional", "Total"
                                }));
                jScrollPane1.setViewportView(table);

                gridCards.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

                cardRendimento.setForeground(new java.awt.Color(255, 255, 255));
                cardRendimento.setColor1(new java.awt.Color(25, 75, 255));
                cardRendimento.setColor2(new java.awt.Color(0, 18, 83));
                cardRendimento.setTitle("Rendimentos");
                cardRendimento.setValue("R$ 0,00");
                gridCards.add(cardRendimento);

                cardDespesa.setColor1(new java.awt.Color(235, 173, 0));
                cardDespesa.setColor2(new java.awt.Color(160, 121, 20));
                cardDespesa.setTitle("Despesas");
                cardDespesa.setValue("R$ 0,00");
                gridCards.add(cardDespesa);

                cardInvestimento.setColor1(new java.awt.Color(25, 75, 255));
                cardInvestimento.setColor2(new java.awt.Color(0, 18, 83));
                cardInvestimento.setTitle("Investimentos");
                cardInvestimento.setValue("R$ 0,00");
                gridCards.add(cardInvestimento);

                cardFundo.setColor1(new java.awt.Color(235, 173, 0));
                cardFundo.setColor2(new java.awt.Color(160, 121, 20));
                cardFundo.setTitle("Fundos");
                cardFundo.setValue("R$ 0,00");
                gridCards.add(cardFundo);

                jPanel6.setBackground(new java.awt.Color(25, 75, 255));

                labelTitle3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
                labelTitle3.setForeground(new java.awt.Color(255, 255, 255));
                labelTitle3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                labelTitle3.setText("Relatório Mensal");

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(labelTitle3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                1036,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(18, Short.MAX_VALUE)));
                jPanel6Layout.setVerticalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(16, Short.MAX_VALUE)
                                                                .addComponent(labelTitle3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                76,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(14, 14, 14)));

                cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Ano", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016",
                                                "2015" }));
                cbAno.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                getLancametoAllYear(Integer.parseInt(cbAno.getSelectedItem().toString()));
                        }
                });

                btnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/download.png"))); // NOI18N
                btnDownload.addActionListener(e -> baixarTabela());

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(gridCards,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(cbAno,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(btnDownload))
                                                                                .addComponent(jScrollPane1))
                                                                .addContainerGap()));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(gridCards,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                113,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(55, 55, 55)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(cbAno,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnDownload))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                303, Short.MAX_VALUE)
                                                                .addContainerGap()));
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnDownload;
        private forms.Cards cardRendimento;
        private forms.Cards cardDespesa;
        private forms.Cards cardInvestimento;
        private forms.Cards cardFundo;
        private javax.swing.JPanel gridCards;
        private javax.swing.JComboBox<String> cbAno;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable table;
        private javax.swing.JLabel labelTitle3;
        // End of variables declaration//GEN-END:variables
}
