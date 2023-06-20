package forms.modules;

import forms.CategoryEdit;
import service.CategoriaService;
import service.DespesaService;
import actiontable.TableActionCellEditor;
import actiontable.TableActionCellRender;
import actiontable.TableActionEvent;
import entities.Categoria;
import entities.Despesa;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModuloDespesas extends javax.swing.JPanel {
        private DespesaService despesaService;
        private CategoriaService categoriaService;

        public ModuloDespesas() {
                this.despesaService = new DespesaService();
                this.categoriaService = new CategoriaService();

                initComponents();
                TableActionEvent event = new TableActionEvent() {
                        @Override
                        public void onDelete(int row) {
                                try {
                                        if (despesaTable.isEditing())
                                                despesaTable.getCellEditor().stopCellEditing();

                                        despesaService.deleteDespesa((int) despesaTable.getValueAt(row, 0));
                                        DefaultTableModel model = (DefaultTableModel) despesaTable.getModel();
                                        model.removeRow(row);
                                } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, "Erro ao deletar despesa");
                                        e.printStackTrace();
                                }
                        }
                };
                despesaTable.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
                despesaTable.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));

                preencherCategorias();
                atualizarTabela();

                btnAdd.setBackground(new Color(0, 0, 0, 0));
        }

        private void initComponents() {

                jPanel6 = new javax.swing.JPanel();
                labelTitle3 = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                cbCategory = new javax.swing.JComboBox<>();
                titleMonthly = new javax.swing.JLabel();
                titleOccasional = new javax.swing.JLabel();
                titleExpense = new javax.swing.JLabel();
                titleCategorias = new javax.swing.JLabel();
                titleData = new javax.swing.JLabel();
                inputDespesa = new javax.swing.JTextField();
                inputMensal = new javax.swing.JTextField();
                inputOcasional = new javax.swing.JTextField();
                btnAddUpdate = new javax.swing.JButton();
                btnAdd = new javax.swing.JButton();
                calendario = new com.toedter.calendar.JDateChooser();
                jScrollPane2 = new javax.swing.JScrollPane();
                despesaTable = new javax.swing.JTable();

                setBackground(new java.awt.Color(255, 255, 255));

                jPanel6.setBackground(new java.awt.Color(25, 75, 255));

                labelTitle3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
                labelTitle3.setForeground(new java.awt.Color(255, 255, 255));
                labelTitle3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                labelTitle3.setText("Módulo de Despesas");

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
                                                                .addContainerGap(82, Short.MAX_VALUE)));
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

                titleExpense.setText("Despesas");

                titleCategorias.setText("Categorias");

                btnAddUpdate.setBackground(new java.awt.Color(255, 0, 51));
                btnAddUpdate.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
                btnAddUpdate.setForeground(new java.awt.Color(255, 255, 255));
                btnAddUpdate.setText("Add/Alterar");
                btnAddUpdate.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAddUpdateActionPerformed(evt);
                        }
                });

                btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
                btnAdd.setBorder(null);
                btnAdd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAddActionPerformed(evt);
                        }
                });

                titleData.setText("Data");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(titleCategorias)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(cbCategory,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(btnAdd,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                35,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(46, 46, 46)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(inputDespesa,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                279,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(titleExpense))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(calendario,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(titleData))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                18,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(inputMensal,
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
                                                                                                .addComponent(inputOcasional,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                150,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                7,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(btnAddUpdate)
                                                                                                .addContainerGap()))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(titleCategorias)
                                                                                                .addComponent(titleExpense))
                                                                                .addComponent(titleMonthly)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(titleOccasional)
                                                                                                .addComponent(titleData)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(inputDespesa,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(inputMensal,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(inputOcasional,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(btnAddUpdate))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(btnAdd)
                                                                                                .addComponent(cbCategory,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(calendario,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(17, 17, 17)));

                despesaTable.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                                "ID", "Data", "Categoria", "Descrição", "Mensal (12x)", "Ocasional",
                                                "Total", ""
                                }));
                despesaTable.setRowHeight(40);
                despesaTable.setSelectionBackground(new java.awt.Color(204, 204, 255));
                jScrollPane2.setViewportView(despesaTable);

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
                                                                                290,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(172, Short.MAX_VALUE)));
        }// </editor-fold>//GEN-END:initComponents

        private void preencherCategorias() {
                try {
                        ArrayList<Categoria> categorias = categoriaService.findAllCategorias();

                        for (Categoria categoria : categorias) {
                                cbCategory.addItem(categoria.getNome());
                        }
                } catch (Exception e) {
                        System.out.println("Erro ao preencher categorias");
                        e.printStackTrace();
                }
        }

        private void btnAddUpdateActionPerformed(java.awt.event.ActionEvent evt) {
                try {
                        if (inputDespesa.getText().isEmpty())
                                throw new Exception("Campo de despesas vazio!");

                        if (calendario.getDate() == null)
                                throw new Exception("Selecione uma data!");

                        if (inputOcasional.getText().isEmpty() && inputMensal.getText().isEmpty())
                                throw new Exception("Preencha valor Mensal ou Ocasional!");

                        if (despesaTable.getSelectedRow() != -1)
                                atualizarDespesa();
                        else
                                adicionarDespesa();
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                } finally {
                        atualizarTabela();
                }
        }

        private void atualizarDespesa() {
                try {
                        if (despesaService.findDespesaByName(inputDespesa.getText()) != null)
                                throw new Exception("Nome Despesa já cadastrada!");

                        Despesa despesa = new Despesa();
                        String nomeCategoria = cbCategory.getSelectedItem().toString();
                        int id = Integer.parseInt(
                                        despesaTable.getValueAt(despesaTable.getSelectedRow(), 0).toString());

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
                        String data = dateFormat.format(calendario.getDate());

                        despesa.setDescricao(inputDespesa.getText());
                        despesa.setValorMensal(inputMensal.getText().isEmpty() ? 0.0
                                        : Double.parseDouble(inputMensal.getText()));
                        despesa.setValorOcasional(inputOcasional.getText().isEmpty() ? 0.0
                                        : Double.parseDouble(inputOcasional.getText()));
                        despesa.setMes(Integer.parseInt(data.split("/")[0]));
                        despesa.setAno(Integer.parseInt(data.split("/")[1]));
                        despesa.setId(id);

                        despesaService.updateDespesa(despesa, nomeCategoria);
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }

        private void adicionarDespesa() {
                try {
                        if (despesaService.findDespesaByName(inputDespesa.getText()) != null) {
                                int opcao = JOptionPane.showConfirmDialog(null,
                                                "Rendimento já existe, deseja criar novo?", "Confirmação",
                                                JOptionPane.YES_NO_OPTION);
                                if (opcao == JOptionPane.NO_OPTION) {
                                        inputDespesa.setText("");
                                        return;
                                }
                        }

                        Despesa despesa = new Despesa();
                        String nomeCategoria = cbCategory.getSelectedItem().toString();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
                        String data = dateFormat.format(calendario.getDate());

                        despesa.setDescricao(inputDespesa.getText());
                        despesa.setValorOcasional(inputOcasional.getText().isEmpty() ? 0.0
                                        : Double.parseDouble(inputOcasional.getText()));
                        despesa.setValorMensal(
                                        inputMensal.getText().isEmpty() ? 0.0
                                                        : Double.parseDouble(inputMensal.getText()));
                        despesa.setMes(Integer.parseInt(data.split("/")[0]));
                        despesa.setAno(Integer.parseInt(data.split("/")[1]));

                        despesaService.createDespesa(despesa, nomeCategoria);
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }

        private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
                CategoryEdit frame = new CategoryEdit();
                frame.setVisible(true);
        }

        private void atualizarTabela() {
                DefaultTableModel model = (DefaultTableModel) despesaTable.getModel();
                model.fireTableDataChanged();
                model.setRowCount(0);

                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                try {
                        ArrayList<Despesa> despesas = despesaService.findAllDespesa();

                        if (despesas != null) {
                                for (Despesa despesa : despesas) {
                                        model.addRow(new Object[] {
                                                        despesa.getId(),
                                                        despesa.getMes() + "/" + despesa.getAno(),
                                                        despesa.getCategoria().getNome(),
                                                        despesa.getDescricao(),
                                                        "R$ " + decimalFormat.format(despesa.getValorMensal()),
                                                        "R$ " + decimalFormat.format(despesa.getValorOcasional()),
                                                        "R$ " + decimalFormat.format(despesa.getValorDespesa())
                                        });
                                }
                        }
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar tabela", "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAdd;
        private javax.swing.JButton btnAddUpdate;
        private javax.swing.JComboBox<String> cbCategory;
        private javax.swing.JTextField inputDespesa;
        private javax.swing.JTextField inputOcasional;
        private javax.swing.JTextField inputMensal;
        private com.toedter.calendar.JDateChooser calendario;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JLabel labelTitle3;
        private javax.swing.JTable despesaTable;
        private javax.swing.JLabel titleCategorias;
        private javax.swing.JLabel titleExpense;
        private javax.swing.JLabel titleData;
        private javax.swing.JLabel titleMonthly;
        private javax.swing.JLabel titleOccasional;
        // End of variables declaration//GEN-END:variables
}
