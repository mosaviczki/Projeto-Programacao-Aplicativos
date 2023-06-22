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
import utils.MonthEnum;

public class RelatorioMensal extends javax.swing.JPanel {
    private String selectedMonth;

    public RelatorioMensal() {
        initComponents();
        gridCards.setBackground(new Color(0, 0, 0, 0));
        btnDownload.setBackground(new Color(0, 0, 0, 0));

    }

    private void getLancamentoAllMonth() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.fireTableDataChanged();
        model.setRowCount(0);

        double totalRendimentos=0.0, totalDespesa=0.0, totalInvestimentos=0.0, totalFundos=0.0;

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        int monthNumber = MonthEnum.getEnum(selectedMonth.toUpperCase());

        try {
            ArrayList<Rendimento> rendimentos = new RendimentoService().findAllRendimentosByMonth(monthNumber);
            ArrayList<Despesa> despesas = new DespesaService().findAllDespesasByMonth(monthNumber);
            ArrayList<Investimento> investimentos = new InvestimentoService().findAllInvestimentosByMonth(monthNumber);
            ArrayList<Fundo> fundos = new FundoService().findAllFundosByMonth(monthNumber);

            for (Rendimento rendimento : rendimentos) {
                if (rendimento.getMes() == monthNumber){
                    model.addRow(new Object[] {
                            "Rendimento",
                            rendimento.getDescricao(),
                            "R$ " + decimalFormat.format(rendimento.getValorMensal() + rendimento.getValorOcasional())
                    });
                        totalRendimentos += rendimento.getValorMensal() + rendimento.getValorOcasional();
                }
                else if (rendimento.getMes() < monthNumber && rendimento.getValorMensal() > 0){
                    model.addRow(new Object[] {
                            "Rendimento",
                            rendimento.getDescricao(),
                            "R$ " + decimalFormat.format(rendimento.getValorMensal())
                    });
                        totalRendimentos += rendimento.getValorMensal();
                }
            }

            for (Despesa despesa : despesas) {
                if (despesa.getMes() == monthNumber){
                    model.addRow(new Object[] {
                            "Despesa",
                            despesa.getDescricao(),
                            "R$ " + decimalFormat.format(despesa.getValorMensal() + despesa.getValorOcasional())
                    });
                totalDespesa += despesa.getValorMensal() + despesa.getValorOcasional();
                }
                else if (despesa.getMes() < monthNumber && despesa.getValorMensal() > 0){
                    model.addRow(new Object[] {
                            "Despesa",
                            despesa.getDescricao(),
                            "R$ " + decimalFormat.format(despesa.getValorMensal())
                    });
                totalDespesa += despesa.getValorMensal();
                }
            }

            for (Investimento investimento : investimentos) {
                if (investimento.getMes() == monthNumber){
                    model.addRow(new Object[] {
                            "Investimento",
                            investimento.getDescricao(),
                            "R$ " + decimalFormat
                                    .format(investimento.getValorMensal() + investimento.getValorOcasional())
                    });
                totalInvestimentos += investimento.getValorMensal() + investimento.getValorOcasional();
                }
                else if (investimento.getMes() < monthNumber && investimento.getValorMensal() > 0){
                    model.addRow(new Object[] {
                            "Investimento",
                            investimento.getDescricao(),
                            "R$ " + decimalFormat.format(investimento.getValorMensal())
                    });
                totalInvestimentos += investimento.getValorMensal();
                }
            }

            for (Fundo fundo : fundos) {
                if (fundo.getMes() == monthNumber){
                    model.addRow(new Object[] {
                            "Fundo",
                            fundo.getDescricao(),
                            "R$ " + decimalFormat.format(fundo.getValorMensal() + fundo.getValorOcasional())
                    });
                totalFundos += fundo.getValorMensal() + fundo.getValorOcasional();
                }
                else if (fundo.getMes() < monthNumber && fundo.getValorMensal() > 0){
                    model.addRow(new Object[] {
                            "Fundo",
                            fundo.getDescricao(),
                            "R$ " + decimalFormat.format(fundo.getValorMensal())
                    });
                totalFundos += fundo.getValorMensal();
                }
            }
        cards.setValue("R$ " + decimalFormat.format(totalRendimentos));
        cards2.setValue("R$ " + decimalFormat.format(totalDespesa));
        cards3.setValue("R$ " + decimalFormat.format(totalInvestimentos));
        cards4.setValue("R$ " + decimalFormat.format(totalFundos));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher tabela: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void baixarTabela() {
        FileHandler.downloadFile(FileHandler.RELATORIO_MENSAL, table, selectedMonth);
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        cbMes = new javax.swing.JComboBox<>();
        gridCards = new javax.swing.JPanel();
        cards = new forms.Cards();
        cards2 = new forms.Cards();
        cards3 = new forms.Cards();
        cards4 = new forms.Cards();
        jPanel6 = new javax.swing.JPanel();
        labelTitle3 = new javax.swing.JLabel();
        btnDownload = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        table.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Tipo Lancamento", "Descricao", "Total"
                }));
        jScrollPane1.setViewportView(table);

        cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mês", "Janeiro", "Fevereiro",
                "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        cbMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedMonth = cbMes.getSelectedItem().toString();
                getLancamentoAllMonth();
            }
        });

        gridCards.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cards.setForeground(new java.awt.Color(255, 255, 255));
        cards.setColor1(new java.awt.Color(25, 75, 255));
        cards.setColor2(new java.awt.Color(0, 18, 83));
        cards.setTitle("Rendimentos");
        cards.setValue("R$ 0,00");
        gridCards.add(cards);

        cards2.setColor1(new java.awt.Color(235, 173, 0));
        cards2.setColor2(new java.awt.Color(160, 121, 20));
        cards2.setTitle("Despesas");
        cards2.setValue("R$ 0,00");
        gridCards.add(cards2);

        cards3.setColor1(new java.awt.Color(25, 75, 255));
        cards3.setColor2(new java.awt.Color(0, 18, 83));
        cards3.setTitle("Investimentos");
        cards3.setValue("R$ 0,00");
        gridCards.add(cards3);

        cards4.setColor1(new java.awt.Color(235, 173, 0));
        cards4.setColor2(new java.awt.Color(160, 121, 20));
        cards4.setTitle("Fundos");
        cards4.setValue("R$ 0,00");
        gridCards.add(cards4);

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
                                .addComponent(labelTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 1036,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE)));
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap(16, Short.MAX_VALUE)
                                .addComponent(labelTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)));

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(gridCards, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnDownload, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(gridCards, javax.swing.GroupLayout.PREFERRED_SIZE, 113,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnDownload, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58,
                                                        Short.MAX_VALUE)
                                                .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDownload;
    private forms.Cards cards;
    private forms.Cards cards2;
    private forms.Cards cards3;
    private forms.Cards cards4;
    private javax.swing.JPanel gridCards;
    private javax.swing.JComboBox<String> cbMes;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JLabel labelTitle3;
    // End of variables declaration//GEN-END:variables
}
