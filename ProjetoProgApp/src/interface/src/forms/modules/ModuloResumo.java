package forms.modules;

import javax.swing.JComponent;

public class ModuloResumo extends javax.swing.JPanel {

    public ModuloResumo() {
        initComponents();
    }

    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioAnual = new javax.swing.JRadioButton();
        jRadioMensal = new javax.swing.JRadioButton();
        panelResumo = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        labelTitle4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(jRadioAnual);
        jRadioAnual.setText("Anual");
        jRadioAnual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAnualActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioMensal);
        jRadioMensal.setText("Mensal");

        panelResumo.setBackground(new java.awt.Color(255, 255, 255));
        panelResumo.setLayout(new java.awt.BorderLayout());

        jButton1.setBackground(new java.awt.Color(25, 75, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(25, 75, 255));

        labelTitle4.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        labelTitle4.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTitle4.setText("Módulo de Resumo");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(labelTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioAnual)
                        .addGap(37, 37, 37)
                        .addComponent(jRadioMensal)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelResumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioAnual)
                    .addComponent(jRadioMensal)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelResumo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioAnualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAnualActionPerformed
        if(jRadioAnual.isSelected() == false){
            setForm(new ModuloResumoAnual());
        }else if(jRadioMensal.isSelected() == true){
            setForm(new ModuloResumoMensal());
        }
    }//GEN-LAST:event_jRadioAnualActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jRadioAnual.isSelected() == true){
            setForm(new ModuloResumoAnual());
        }else if(jRadioMensal.isSelected() == true){
            setForm(new ModuloResumoMensal());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void setForm(JComponent com){
        panelResumo.removeAll();
        panelResumo.add(com);
        panelResumo.repaint();
        panelResumo.revalidate();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioAnual;
    private javax.swing.JRadioButton jRadioMensal;
    private javax.swing.JLabel labelTitle4;
    private javax.swing.JPanel panelResumo;
    // End of variables declaration//GEN-END:variables
}
