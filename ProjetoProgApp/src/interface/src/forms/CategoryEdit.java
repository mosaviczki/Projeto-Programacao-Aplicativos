package forms;

import service.CategoriaService;

import java.awt.Color;
import java.util.ArrayList;

import entities.Categoria;

public class CategoryEdit extends javax.swing.JFrame {
        private CategoriaService categoriaService;

        public CategoryEdit() {
                this.categoriaService = new CategoriaService();

                initComponents();
                preencherCategorias();

                btnDeleteCategory.setBackground(new Color(0, 0, 0, 0));
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                cbCategoria = new javax.swing.JComboBox<>();
                jLabel2 = new javax.swing.JLabel();
                inputCategoria = new javax.swing.JTextField();
                btnAddUpdate = new javax.swing.JButton();
                btnDeleteCategory = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Categoria");
                setBackground(new java.awt.Color(102, 153, 255));

                jPanel1.setBackground(new java.awt.Color(25, 75, 255));

                jLabel1.setBackground(new java.awt.Color(255, 255, 255));
                jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("Categorias");

                jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                jLabel2.setText("Categoria");

                btnAddUpdate.setBackground(new java.awt.Color(0, 51, 153));
                btnAddUpdate.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
                btnAddUpdate.setForeground(new java.awt.Color(255, 255, 255));
                btnAddUpdate.setText("Add/Alterar");
                btnAddUpdate.setBorder(null);
                btnAddUpdate.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAddUpdateActionPerformed(evt);
                        }
                });

                btnDeleteCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
                btnDeleteCategory.setBorder(null);
                btnDeleteCategory.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDeleteCategoryActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel1,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                169,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(cbCategoria,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(26, 26, 26)
                                                                                                .addComponent(jLabel2)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(inputCategoria,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                177,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(btnAddUpdate,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                78,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(btnDeleteCategory)
                                                                                                .addGap(14, 14, 14)))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                48,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(btnDeleteCategory,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                23,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(cbCategoria,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel2)
                                                                                                .addComponent(inputCategoria,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(btnAddUpdate,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                23,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(38, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        private void btnAddUpdateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
                try {
                        if (cbCategoria.getSelectedItem().toString().equals("nova")) {
                                categoriaService.createCategoria(inputCategoria.getText().toLowerCase());
                                return;
                        } else {
                                String novoNomeCategoria = inputCategoria.getText().toLowerCase();
                                String nomeCategoria = cbCategoria.getSelectedItem().toString();
                                categoriaService.updateCategoria(nomeCategoria, novoNomeCategoria);
                        }
                } catch (Exception e) {
                        System.out.println("Erro ao criar/alterar categoria");
                        e.printStackTrace();
                }
        }// GEN-LAST:event_jButton1ActionPerformed

        private void btnDeleteCategoryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeleteCategoryActionPerformed
                try {
                        if (cbCategoria.getSelectedItem().toString().equals("nova")) {
                                return;
                        }
                        categoriaService.deleteCategoria(cbCategoria.getSelectedItem().toString());
                } catch (Exception e) {
                        System.out.println("Erro ao deletar categoria");
                        e.printStackTrace();
                }
        }// GEN-LAST:event_btnDeleteCategoryActionPerformed

        private void preencherCategorias() {
                try {
                        ArrayList<Categoria> categorias = categoriaService.findAllCategorias();

                        cbCategoria.addItem("nova");
                        for (Categoria categoria : categorias) {
                                cbCategoria.addItem(categoria.getNome());
                        }
                } catch (Exception e) {
                        System.out.println("Erro ao preencher categorias");
                        e.printStackTrace();
                }
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnDeleteCategory;
        private javax.swing.JButton btnAddUpdate;
        private javax.swing.JComboBox<String> cbCategoria;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JTextField inputCategoria;
        // End of variables declaration//GEN-END:variables
}
