package forms.modules;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import entities.ResumoAnual;
import service.ResumoService;

public class ModuloResumoAnual extends javax.swing.JPanel {
    private ResumoService resumoAnualService;

    public ModuloResumoAnual() {
        resumoAnualService = new ResumoService();

        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        resumoTable = new javax.swing.JTable();
        cbAno = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        resumoTable.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        resumoTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                },
                new String[] {
                        "Descrição", "Mensal (12x)", "Ocasional", "Total Anual"
                }));
        jScrollPane1.setViewportView(resumoTable);

        cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ano", "2023", "2022", "2021", "2020",
                "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010" }));
        cbAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarTabela();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 821,
                                                        Short.MAX_VALUE)
                                                .addGap(55, 55, 55)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void atualizarTabela() {
        try {
            int ano = Integer.parseInt(cbAno.getSelectedItem().toString());

            ResumoAnual resumoPorMes = resumoAnualService.getResumoAnualMensal(ano);
            ResumoAnual resumoOcasional = resumoAnualService.getResumoAnualOcasional(ano);

            preencherTabela(resumoPorMes, resumoOcasional);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void preencherTabela(ResumoAnual resumoByMes, ResumoAnual resumoOcasional) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double somaTotalAnual = 0;

        resumoTable.setValueAt("Rendimento", 0, 0);
        somaTotalAnual = resumoByMes.getValorRendimento() + resumoOcasional.getValorRendimento();
        resumoTable.setValueAt("R$ " + decimalFormat.format(resumoByMes.getValorRendimento()), 0, 1);
        resumoTable.setValueAt("R$ " + decimalFormat.format(resumoOcasional.getValorRendimento()), 0, 2);
        resumoTable.setValueAt("R$ " + decimalFormat.format(somaTotalAnual), 0, 3);

        resumoTable.setValueAt("Investimento a Longo Prazo", 1, 0);
        somaTotalAnual = resumoByMes.getValorInvestimento() + resumoOcasional.getValorInvestimento();
        resumoTable.setValueAt("R$ " + decimalFormat.format(resumoByMes.getValorInvestimento()), 1, 1);
        resumoTable.setValueAt("R$ " + decimalFormat.format(resumoOcasional.getValorInvestimento()), 1, 2);
        resumoTable.setValueAt("R$ " + decimalFormat.format(somaTotalAnual), 1, 3);

        resumoTable.setValueAt("Fundo para Despesas Ocasionais", 2, 0);
        somaTotalAnual = resumoByMes.getFundoDespesasOcasionais() + resumoOcasional.getFundoDespesasOcasionais();
        resumoTable.setValueAt("R$ " + decimalFormat.format(resumoByMes.getFundoDespesasOcasionais()), 2, 1);
        resumoTable.setValueAt("R$ " + decimalFormat.format(somaTotalAnual), 2, 3);

        resumoTable.setValueAt("Total Disponível para Despesas Durante o Ano", 3, 0);
        somaTotalAnual = resumoByMes.getTotalDisponivel() + resumoOcasional.getTotalDisponivel();
        resumoTable.setValueAt("R$ " + decimalFormat.format(somaTotalAnual), 3, 3);

        resumoTable.setValueAt("Total Despesas Mensais Orçadas (12 meses)", 4, 0);
        resumoTable.setValueAt("R$ " + decimalFormat.format(resumoByMes.getValorDespesas()), 4, 3);

        resumoTable.setValueAt("Total Despesas Ocasionadas para o Ano", 5, 0);
        resumoTable.setValueAt("R$ " + decimalFormat.format(resumoOcasional.getValorDespesas()), 5, 3);

        resumoTable.setValueAt("Valor Total Restante ao Final do Ano", 6, 0);
        somaTotalAnual = resumoByMes.getTotalDisponivel() + resumoOcasional.getTotalDisponivel();
        resumoTable.setValueAt("R$ " + decimalFormat.format(resumoByMes.getTotalDisponivel()), 6, 3);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbAno;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable resumoTable;
    // End of variables declaration//GEN-END:variables
}
