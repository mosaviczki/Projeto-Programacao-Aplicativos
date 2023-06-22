package forms;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Cards extends javax.swing.JPanel {

    private Color color1, color2;
    
    public Cards() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
    }
    
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        value = new javax.swing.JLabel();

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("");

        title.setFont(new java.awt.Font("Ubuntu Light", 1, 14)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Title");

        value.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        value.setForeground(new java.awt.Color(255, 255, 255));
        value.setText("Value");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(value)
                    .addComponent(title)
                    .addComponent(jLabel1))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(value)
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) { 
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }
    
    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setValue(String value) {
        this.value.setText(value);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel title;
    protected javax.swing.JLabel value;
    // End of variables declaration//GEN-END:variables
}
