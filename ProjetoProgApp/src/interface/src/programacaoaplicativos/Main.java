package programacaoaplicativos;

import forms.modules.ModuloFundo;
import forms.modules.ModuloDespesas;
import forms.modules.ModuloInvestimento;
import forms.modules.ModuloRendimento;
import forms.modules.ModuloResumo;
import forms.relatorio.Organizacao;
import forms.relatorio.RelatorioAnual;
import forms.relatorio.RelatorioMensal;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import sidebar.MenuItem;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.menus.setBackground(Color.decode("#001253"));
        setForm(new RelatorioMensal());
        execute();
    }

    private void execute() {
        ImageIcon iconHome = new ImageIcon(getClass().getResource("/img/home.png"));
        ImageIcon iconModule = new ImageIcon(getClass().getResource("/img/modulo.png"));
        ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/img/circle.png"));
        // menu itens
        MenuItem menuHome1 = new MenuItem(iconSubMenu, "Relatório Mensal", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setForm(new RelatorioMensal());
            }
        });
        MenuItem menuHome2 = new MenuItem(iconSubMenu, "Relatório Anual", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setForm(new RelatorioAnual());
            }
        });
        MenuItem menuHome3 = new MenuItem(iconSubMenu, "Organização", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setForm(new Organizacao());
            }
        });

        MenuItem menuModulo1 = new MenuItem(iconSubMenu, "Rendimento", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setForm(new ModuloRendimento());
            }
        });
        MenuItem menuModulo2 = new MenuItem(iconSubMenu, "Despesas", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setForm(new ModuloDespesas());
            }
        });
        MenuItem menuModulo3 = new MenuItem(iconSubMenu, "Investimento a longo prazo", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setForm(new ModuloInvestimento());
            }
        });
        MenuItem menuModulo4 = new MenuItem(iconSubMenu, "Fundo de despesas ocasionais", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setForm(new ModuloFundo());
            }
        });
        MenuItem menuModulo5 = new MenuItem(iconSubMenu, "Resumo", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setForm(new ModuloResumo());
            }
        });

        MenuItem menuHome = new MenuItem(iconHome, "Home", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setForm(new RelatorioMensal());
            }
        }, menuHome1, menuHome2, menuHome3);
        MenuItem menuModule = new MenuItem(iconModule, "Modulo", null, menuModulo1, menuModulo2, menuModulo3,
                menuModulo4, menuModulo5);

        addMenu(menuHome, menuModule);
    }

    private void setForm(JComponent com) {
        panelBody.removeAll();
        panelBody.add(com);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        menus.repaint();
        menus.revalidate();
    }

    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        sidebar = new sidebar.Sidebar();
        jScrollPane = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        panelBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GMG System");

        jPanel.setBackground(new java.awt.Color(255, 255, 255));
        jPanel.setMaximumSize(new java.awt.Dimension(210, 32767));
        jPanel.setLayout(new javax.swing.BoxLayout(jPanel, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane.setBorder(null);

        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane.setViewportView(menus);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
                sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(sidebarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(sidebarLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        sidebarLayout.setVerticalGroup(
                sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                                .addContainerGap()));

        jPanel.add(sidebar);

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.setMinimumSize(new java.awt.Dimension(1100, 600));
        panelBody.setLayout(new java.awt.BorderLayout());
        jPanel.add(panelBody);

        getContentPane().add(jPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private sidebar.Sidebar sidebar;
    // End of variables declaration//GEN-END:variables
}
