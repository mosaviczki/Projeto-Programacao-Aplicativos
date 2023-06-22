package forms.modules;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import actiontable.TableActionCellEditor;
import actiontable.TableActionCellRender;
import actiontable.TableActionEvent;
import entities.Investimento;
import service.InvestimentoService;

public class ModuloInvestimento extends javax.swing.JPanel {

        public ModuloInvestimento() {
                initComponents();
                TableActionEvent event = new TableActionEvent() {
                        InvestimentoService investimentoService = new InvestimentoService();

                        @Override
                        public void onDelete(int row) {
                                try {
                                        if (table.isEditing())
                                                table.getCellEditor().stopCellEditing();
                                        if (JOptionPane.showConfirmDialog(null,
                                                        "Tem certeza que deseja deletar este investimento?",
                                                        "Deletar investimento",
                                                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                                investimentoService.deleteInvestimento(
                                                                (int) table.getValueAt(row, 0));
                                                JOptionPane.showMessageDialog(null,
                                                                "Investimento deletado com sucesso!",
                                                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                                DefaultTableModel model = (DefaultTableModel) table
                                                                .getModel();
                                                model.removeRow(row);
                                        }
                                } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
                                                        JOptionPane.ERROR_MESSAGE);
                                        e.printStackTrace();
                                }
                        }
                };
                table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
                table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));

                try {
                        this.findInvestimentos();
                } catch (SQLException sqle) {
                        JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados", "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }

        private void findInvestimentos() throws SQLException, IOException {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.fireTableDataChanged();
                model.setRowCount(0);
                InvestimentoService investimentoService = new InvestimentoService();

                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                List<Investimento> investimentos = investimentoService.findAllInvestimento();

                for (Investimento investimento : investimentos) {
                        model.addRow(new Object[] {
                                        investimento.getId(),
                                        investimento.getMes() + "/" + investimento.getAno(),
                                        investimento.getDescricao(),
                                        "R$ " + decimalFormat.format(investimento.getValorMensal()),
                                        "R$ " + decimalFormat.format(investimento.getValorOcasional()),
                                        "R$ " + decimalFormat.format(investimento.getValorMensal() * 12
                                                        + investimento.getValorOcasional())
                        });

                }
        }

        private void initComponents() {

                jPanel6 = new javax.swing.JPanel();
                labelTitle3 = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                titleMonthly = new javax.swing.JLabel();
                titleOccasional = new javax.swing.JLabel();
                titleExpense = new javax.swing.JLabel();
                inputDespesas = new javax.swing.JTextField();
                inputMensal1 = new javax.swing.JTextField();
                inputMensal = new javax.swing.JTextField();
                buttonAddExpense = new javax.swing.JButton();
                titleExpense1 = new javax.swing.JLabel();
                jDateChooser1 = new com.toedter.calendar.JDateChooser();
                jScrollPane2 = new javax.swing.JScrollPane();
                table = new javax.swing.JTable();

                setBackground(new java.awt.Color(255, 255, 255));

                jPanel6.setBackground(new java.awt.Color(25, 75, 255));

                labelTitle3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
                labelTitle3.setForeground(new java.awt.Color(255, 255, 255));
                labelTitle3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                labelTitle3.setText("Módulo de Investimento a Longo Prazo");

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
                                                                .addContainerGap(62, Short.MAX_VALUE)));
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

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

                titleMonthly.setText("Ocasional");

                titleOccasional.setText("Mensal");

                titleExpense.setText("Descrição");

                inputDespesas.setText("");

                inputMensal.setText("");

                inputMensal1.setText("");

                buttonAddExpense.setBackground(new java.awt.Color(255, 0, 51));
                buttonAddExpense.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
                buttonAddExpense.setForeground(new java.awt.Color(255, 255, 255));
                buttonAddExpense.setText("Add/Alterar");
                buttonAddExpense.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        buttonAddExpenseActionPerformed(evt);
                                        findInvestimentos();
                                } catch (NullPointerException npe) {
                                        JOptionPane.showMessageDialog(null,
                                                        "Descricao, data e, ao minimo, um valor sao obrigatorios",
                                                        "Valor invalido", JOptionPane.INFORMATION_MESSAGE);
                                } catch (NumberFormatException nfe) {
                                        JOptionPane.showMessageDialog(null, "O valor informado necessita ser um numero",
                                                        "Valor invalido",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                } catch (SQLException e) {
                                        JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados",
                                                        "Erro",
                                                        JOptionPane.ERROR_MESSAGE);
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                        }

                });

                titleExpense1.setText("Data");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(inputDespesas,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                469,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(titleExpense)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jDateChooser1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(titleExpense1))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(inputMensal1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                150,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(titleOccasional))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(titleMonthly)
                                                                                                .addGap(142, 142, 142))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(inputMensal,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                150,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                7,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(buttonAddExpense)
                                                                                                .addContainerGap()))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(0, 6, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(titleMonthly)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(titleOccasional)
                                                                                                .addComponent(titleExpense)
                                                                                                .addComponent(titleExpense1)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(inputDespesas,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(inputMensal1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(inputMensal,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(buttonAddExpense)
                                                                                .addComponent(jDateChooser1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(17, 17, 17)));

                table.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null, null, null, null }
                                },
                                new String[] {
                                                "ID", "Data", "Fundo Ocasional", "Mensal ", "Ocasional", "Total Anual",
                                                ""
                                }));
                table.setRowHeight(40);
                table.setSelectionBackground(new java.awt.Color(204, 204, 255));
                jScrollPane2.setViewportView(table);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(jScrollPane2)
                                                                                .addComponent(jPanel1,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(29, Short.MAX_VALUE)));
        }// </editor-fold>//GEN-END:initComponents

        private void buttonAddExpenseActionPerformed(java.awt.event.ActionEvent evt)
                        throws SQLException, IOException, NumberFormatException, NullPointerException {// GEN-FIRST:event_buttonAddExpenseActionPerformed
                String descricao = inputDespesas.getText();
                String ocasionalS = inputMensal.getText();
                String mensalS = inputMensal1.getText();
                Double ocasional, mensal;
                Date data = jDateChooser1.getDate();

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");

                String formattedDate = dateFormat.format(data);

                String[] trechoSeparado = formattedDate.split("/");
                int mes = Integer.parseInt(trechoSeparado[0]);
                int ano = Integer.parseInt(trechoSeparado[1]);

                if (!descricao.equals("") && (!ocasionalS.equals("") || !mensalS.equals(""))) {
                        if (!ocasionalS.equals("")) {
                                ocasional = Double.parseDouble(inputMensal.getText());
                        } else {
                                ocasional = 0.0;
                        }
                        if (!mensalS.equals("")) {
                                mensal = Double.parseDouble(inputMensal1.getText());
                        } else {
                                mensal = 0.0;
                        }
                        Investimento investimento = new Investimento(0, descricao, mensal, ocasional, mes, ano);
                        InvestimentoService investimentoService = new InvestimentoService();

                        if (table.isRowSelected(table.getSelectedRow())) {
                                System.out.println("selecionado");
                                investimento.setId((int) table.getValueAt(table.getSelectedRow(), 0));
                                investimentoService.updateInvestimento(investimento);

                        } else {
                                System.out.println("Nao selecionado");
                                investimentoService.createInvestimento(investimento);
                        }
                } else {
                        JOptionPane.showMessageDialog(null, "Descricao, data e, ao minimo, um valor sao obrigatorios",
                                        "Valor invalido", JOptionPane.INFORMATION_MESSAGE);
                }
        }// GEN-LAST:event_buttonAddExpenseActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton buttonAddExpense;
        private javax.swing.JTextField inputDespesas;
        private javax.swing.JTextField inputMensal;
        private javax.swing.JTextField inputMensal1;
        private com.toedter.calendar.JDateChooser jDateChooser1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JLabel labelTitle3;
        private javax.swing.JTable table;
        private javax.swing.JLabel titleExpense;
        private javax.swing.JLabel titleExpense1;
        private javax.swing.JLabel titleMonthly;
        private javax.swing.JLabel titleOccasional;
        // End of variables declaration//GEN-END:variables
}
