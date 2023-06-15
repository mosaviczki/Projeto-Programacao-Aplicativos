package Form;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;

public class ModuloResumo extends javax.swing.JPanel {

    public ModuloResumo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jRadioAnual = new javax.swing.JRadioButton();
        jRadioMensal = new javax.swing.JRadioButton();
        panelResumo = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Módulo de resumo");

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

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioAnual)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioMensal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(panelResumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioAnual)
                    .addComponent(jRadioMensal)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelResumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(544, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioAnual;
    private javax.swing.JRadioButton jRadioMensal;
    private javax.swing.JPanel panelResumo;
    // End of variables declaration//GEN-END:variables
}
