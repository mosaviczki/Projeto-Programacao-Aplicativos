package forms.modules;

import forms.CategoryEdit;
import forms.Variaveis;
import service.CategoriaService;
import service.RendimentoService;
import actiontable.TableActionCellEditor;
import actiontable.TableActionCellRender;
import actiontable.TableActionEvent;
import entities.Categoria;
import entities.Rendimento;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModuloRendimento extends javax.swing.JPanel {
        private RendimentoService rendimentoService;
        private CategoriaService categoriaService;

        public ModuloRendimento() {
                this.rendimentoService = new RendimentoService();
                this.categoriaService = new CategoriaService();

                initComponents();
                TableActionEvent event = new TableActionEvent() {
                        @Override
                        public void onDelete(int row) {
                                try {
                                        if (rendimentoTable.isEditing())
                                                rendimentoTable.getCellEditor().stopCellEditing();
                                        if (JOptionPane.showConfirmDialog(null,
                                                        "Tem certeza que deseja deletar este rendimento?",
                                                        "Deletar rendimento",
                                                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                                rendimentoService.deleteRendimento(
                                                                (int) rendimentoTable.getValueAt(row, 0));
                                                JOptionPane.showMessageDialog(null, "Rendimento deletado com sucesso!",
                                                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                                DefaultTableModel model = (DefaultTableModel) rendimentoTable
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
                rendimentoTable.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
                rendimentoTable.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));

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
                inputRendimento = new javax.swing.JTextField();
                inputMensal = new javax.swing.JTextField();
                inputOcasional = new javax.swing.JTextField();
                btnAddRendimento = new javax.swing.JButton();
                btnAdd = new javax.swing.JButton();
                calendario = new com.toedter.calendar.JDateChooser();
                titleCalendar = new javax.swing.JLabel();
                jScrollPane2 = new javax.swing.JScrollPane();
                rendimentoTable = new javax.swing.JTable();

                setBackground(new java.awt.Color(255, 255, 255));

                jPanel6.setBackground(new java.awt.Color(25, 75, 255));

                labelTitle3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
                labelTitle3.setForeground(new java.awt.Color(255, 255, 255));
                labelTitle3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                labelTitle3.setText("Módulo de Rendimento");

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

                titleExpense.setText("Rendimentos");

                titleCategorias.setText("Categorias");

                btnAddRendimento.setBackground(new java.awt.Color(255, 0, 51));
                btnAddRendimento.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
                btnAddRendimento.setForeground(new java.awt.Color(255, 255, 255));
                btnAddRendimento.setText("Add/Alterar");
                btnAddRendimento.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                buttonAddRendimentoActionPerformed(evt);
                        }
                });

                btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
                btnAdd.setBorder(null);
                btnAdd.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAddActionPerformed(evt);
                        }
                });

                titleCalendar.setText("Data");

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
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(inputRendimento,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                334,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(titleExpense))
                                                                .addGap(18, 18, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(calendario,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(titleCalendar))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(inputMensal,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                144,
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
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(inputOcasional,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                144,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                7,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(btnAddRendimento)
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
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(titleMonthly)
                                                                                                .addComponent(titleOccasional)
                                                                                                .addComponent(titleCalendar)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(calendario,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(inputMensal,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(inputOcasional,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(btnAddRendimento))
                                                                                .addComponent(cbCategory,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnAdd)
                                                                                .addComponent(inputRendimento,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(17, 17, 17)));

                rendimentoTable.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                                "ID", "Data", "Categoria", "Descrição", "Mensal",
                                                "Ocasional",
                                                "Total", ""
                                }));
                rendimentoTable.setRowHeight(40);
                rendimentoTable.setSelectionBackground(new java.awt.Color(204, 204, 255));
                jScrollPane2.setViewportView(rendimentoTable);

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
                                                                .addContainerGap(35, Short.MAX_VALUE)));
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

        // Botão para adicionar rendimento
        private void buttonAddRendimentoActionPerformed(java.awt.event.ActionEvent evt) {
                try {
                        if (inputRendimento.getText().isEmpty())
                                throw new Exception("Campo de rendimentos vazio!");

                        if (calendario.getDate() == null)
                                throw new Exception("Selecione uma data!");

                        if (inputOcasional.getText().isEmpty() && inputMensal.getText().isEmpty())
                                throw new Exception("Preencha valor Mensal ou Ocasional!");

                        if (rendimentoTable.getSelectedRow() != -1)
                                atualizarRendimento();
                        else
                                adicionarRendimento();
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                } finally {
                        atualizarTabela();
                }
        }

        private void atualizarRendimento() {
                try {
                        if (rendimentoService.findRendimentoByName(inputRendimento.getText()) != null)
                                throw new Exception("Nome de Rendimento já cadastrado!");

                        Rendimento rendimento = new Rendimento();
                        String nomeCategoria = cbCategory.getSelectedItem().toString();
                        int id = Integer.parseInt(
                                        rendimentoTable.getValueAt(rendimentoTable.getSelectedRow(), 0).toString());

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
                        String data = dateFormat.format(calendario.getDate());

                        rendimento.setDescricao(inputRendimento.getText());
                        rendimento.setValorMensal(inputMensal.getText().isEmpty() ? 0
                                        : Double.parseDouble(inputMensal.getText()));
                        rendimento.setValorOcasional(inputOcasional.getText().isEmpty() ? 0
                                        : Double.parseDouble(inputOcasional.getText()));
                        rendimento.setMes(Integer.parseInt(data.split("/")[0]));
                        rendimento.setAno(Integer.parseInt(data.split("/")[1]));
                        rendimento.setId(id);

                        rendimentoService.updateRendimento(rendimento, nomeCategoria);
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }

        private void adicionarRendimento() {
                try {
                        if (rendimentoService.findRendimentoByName(inputRendimento.getText()) != null) {
                                int opcao = JOptionPane.showConfirmDialog(null,
                                                "Rendimento já existe, deseja criar novo?", "Confirmação",
                                                JOptionPane.YES_NO_OPTION);
                                if (opcao == JOptionPane.NO_OPTION) {
                                        inputRendimento.setText("");
                                        return;
                                }
                        }

                        Rendimento rendimento = new Rendimento();
                        String nomeCategoria = cbCategory.getSelectedItem().toString();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
                        String data = dateFormat.format(calendario.getDate());

                        rendimento.setDescricao(inputRendimento.getText());
                        rendimento.setValorMensal(inputMensal.getText().isEmpty() ? 0
                                        : Double.parseDouble(inputMensal.getText()));
                        rendimento.setValorOcasional(inputOcasional.getText().isEmpty() ? 0
                                        : Double.parseDouble(inputOcasional.getText()));
                        rendimento.setMes(Integer.parseInt(data.split("/")[0]));
                        rendimento.setAno(Integer.parseInt(data.split("/")[1]));

                        rendimentoService.createRendimento(rendimento, nomeCategoria);
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }

        // Botão para adicionar categoria
        private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddActionPerformed
                CategoryEdit frame = new CategoryEdit();
                frame.setVisible(true);
        }// GEN-LAST:event_btnAddActionPerformed

        public void Category(Variaveis val) {
                String nome = (val.getNome());
                System.out.println(nome);
        }

        private void atualizarTabela() {
                DefaultTableModel model = (DefaultTableModel) rendimentoTable.getModel();
                model.fireTableDataChanged();
                model.setRowCount(0);

                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                try {
                        ArrayList<Rendimento> rendimentos = rendimentoService.findAllRendimentos();

                        if (rendimentos != null) {
                                for (Rendimento rendimento : rendimentos) {
                                        model.addRow(new Object[] {
                                                        rendimento.getId(),
                                                        rendimento.getMes() + "/" + rendimento.getAno(),
                                                        rendimento.getCategoria().getNome(),
                                                        rendimento.getDescricao(),
                                                        "R$ " + decimalFormat.format(rendimento.getValorMensal()),
                                                        "R$ " + decimalFormat.format(rendimento.getValorOcasional()),
                                                        "R$ " + decimalFormat.format(rendimento.getValorRendimento())
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
        private javax.swing.JButton btnAddRendimento;
        private javax.swing.JComboBox<String> cbCategory;
        private javax.swing.JTextField inputRendimento;
        private javax.swing.JTextField inputOcasional;
        private javax.swing.JTextField inputMensal;
        private com.toedter.calendar.JDateChooser calendario;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JLabel labelTitle3;
        private javax.swing.JTable rendimentoTable;
        private javax.swing.JLabel titleCategorias;
        private javax.swing.JLabel titleExpense;
        private javax.swing.JLabel titleCalendar;
        private javax.swing.JLabel titleMonthly;
        private javax.swing.JLabel titleOccasional;
        // End of variables declaration//GEN-END:variables
}
