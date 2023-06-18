package forms.relatorio;

import java.awt.Color;

public class Organizacao extends javax.swing.JPanel {

    public Organizacao() {
        initComponents();
        gridCards.setBackground(new Color(0, 0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        gridCards = new javax.swing.JPanel();
        cards = new forms.Cards();
        cards2 = new forms.Cards();
        jPanel13 = new javax.swing.JPanel();
        labelTitle10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null }
                },
                new String[] {
                        "Categoria", "Descrição", "Total"
                }));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Categoria", "Casa", "Alimentação", "Carro", "Educação" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        gridCards.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cards.setForeground(new java.awt.Color(255, 255, 255));
        cards.setColor1(new java.awt.Color(25, 75, 255));
        cards.setColor2(new java.awt.Color(0, 18, 83));
        gridCards.add(cards);

        cards2.setColor1(new java.awt.Color(235, 173, 0));
        cards2.setColor2(new java.awt.Color(160, 121, 20));
        gridCards.add(cards2);

        jPanel13.setBackground(new java.awt.Color(25, 75, 255));

        labelTitle10.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        labelTitle10.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTitle10.setText("Organização");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTitle10, javax.swing.GroupLayout.PREFERRED_SIZE, 1036,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addContainerGap(16, Short.MAX_VALUE)
                                .addComponent(labelTitle10, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 999,
                                                        Short.MAX_VALUE)
                                                .addGap(55, 55, 55))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(gridCards, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jComboBox1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(gridCards, javax.swing.GroupLayout.PREFERRED_SIZE, 113,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347,
                                        Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private forms.Cards cards;
    private forms.Cards cards2;
    private javax.swing.JPanel gridCards;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelTitle10;
    private javax.swing.JLabel labelTitle3;
    private javax.swing.JLabel labelTitle4;
    private javax.swing.JLabel labelTitle5;
    private javax.swing.JLabel labelTitle6;
    private javax.swing.JLabel labelTitle7;
    private javax.swing.JLabel labelTitle8;
    private javax.swing.JLabel labelTitle9;
    // End of variables declaration//GEN-END:variables
}
