package forms.modules;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import entities.ResumoMensal;
import service.ResumoService;
import utils.MonthEnum;

public class ModuloResumoMensal extends javax.swing.JPanel {
        private ResumoService resumoMensalService;

        public ModuloResumoMensal() {
                resumoMensalService = new ResumoService();
                initComponents();
        }

        private void initComponents() {
                jScrollPane1 = new javax.swing.JScrollPane();
                resumoTable = new javax.swing.JTable();
                cbMes = new javax.swing.JComboBox<>();
                cbAno = new javax.swing.JComboBox<>();

                setBackground(new java.awt.Color(255, 255, 255));

                resumoTable.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
                resumoTable.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null },
                                                { null, null },
                                                { null, null },
                                                { null, null },
                                                { null, null },
                                                { null, null },
                                },
                                new String[] {
                                                "Descrição", "Total Mensal"
                                }));
                jScrollPane1.setViewportView(resumoTable);

                cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Mês", "Janeiro", "Fevereiro", "Março",
                                                "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro",
                                                "Novembro", "Dezembro" }));
                cbMes.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbMesActionPerformed(evt);
                        }
                });

                cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ano", "2023", "2022", "2021",
                                "2020",
                                "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010" }));
                cbAno.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbAnoActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(cbMes,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                150,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(cbAno,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                150,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                855,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGap(55, 55, 55)))));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(cbMes,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(cbAno,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                351,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(22, Short.MAX_VALUE)));
        }// </editor-fold>//GEN-END:initComponents

        private void cbMesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
                if (cbAno.getSelectedItem().toString().equals("Ano")
                                || cbMes.getSelectedItem().toString().equals("Mês"))
                        return;
                atualizarTabela();
        }// GEN-LAST:event_jComboBox1ActionPerformed

        private void cbAnoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox2ActionPerformed
                if (cbAno.getSelectedItem().toString().equals("Ano")
                                || cbMes.getSelectedItem().toString().equals("Mês"))
                        return;
                atualizarTabela();
        }// GEN-LAST:event_jComboBox2ActionPerformed

        private void atualizarTabela() {
                try {
                        int mes = MonthEnum.getEnum(cbMes.getSelectedItem().toString().toUpperCase());
                        int ano = Integer.parseInt(cbAno.getSelectedItem().toString());

                        ResumoMensal resumo = resumoMensalService.getResumoMensal(mes, ano);

                        preencherTabela(resumo);
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }

        private void preencherTabela(ResumoMensal resumo) {
                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                resumoTable.setValueAt("Rendimento", 0, 0);
                resumoTable.setValueAt("R$ " + decimalFormat.format(resumo.getValorRendimento()), 0, 1);

                resumoTable.setValueAt("Investimento a Longo Prazo", 1, 0);
                resumoTable.setValueAt("R$ " + decimalFormat.format(resumo.getValorInvestimento()), 1, 1);

                resumoTable.setValueAt("Fundo para Despesas Ocasionais", 2, 0);
                resumoTable.setValueAt("R$ " + decimalFormat.format(resumo.getFundoDespesasOcasionais()), 2, 1);

                resumoTable.setValueAt("Valor Total Disponível por Mês para Despesas", 3, 0);
                resumoTable.setValueAt("R$ " + decimalFormat.format(resumo.getTotalDisponivel()), 3, 1);

                resumoTable.setValueAt("Valor Total das Despesas Orçadas para o Mês", 4, 0);
                resumoTable.setValueAt("R$ " + decimalFormat.format(resumo.getValorDespesas()), 4, 1);

                resumoTable.setValueAt("Valor Total", 5, 0);
                resumoTable.setValueAt("R$ " + decimalFormat.format(resumo.getValorTotal()), 5, 1);
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JComboBox<String> cbMes;
        private javax.swing.JComboBox<String> cbAno;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable resumoTable;
        // End of variables declaration//GEN-END:variables
}
