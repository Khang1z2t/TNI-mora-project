/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.Luong;
import form.*;
import event.EventMenuSelected;
import form.Form_Home;
import form.NhanVienForm;
import form.SachForm;
import form.TacGiaForm;
import utils.Auth;
import utils.XImage;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class Main extends javax.swing.JFrame {

    public static Main Instance;

    ArrayList<Luong> ist = new ArrayList<>();
    //sap xep theo tt form
    //ton hang
    //doanh thu
    //tao hoa don
    //dang xuat

    public Main() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setTitle("Phần mềm quản lý cửa hàng sách");
        setIconImage(XImage.getAppIcon());
        //khai bao form
        menu1.initMoving(Main.this);
        menu1.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    setForm(new Form_Home());
                } else if (index == 1) {
                    if (Auth.isManager()) {
                        setForm(new NhanVienForm());
                    }else{
                        utils.DialogHelper.alert(Main.this, "Không đủ quyền truy cập!");
                    }
                } else if (index == 2) {
                    setForm(new SachForm());
                } else if (index == 3) {
                    setForm(new TheLoaiForm());
                } else if (index == 4) {
                    setForm(new TacGiaForm());
                } else if (index == 5) {
                    setForm(new NhaCungCapForm());
                } else if (index == 6) {
                    setForm(new KhoForm());
                } else if (index == 7) {
                    setForm(new ThanhVienForm());
                } else if (index == 8) {

                } else if (index == 9) {

                } else if (index == 10) {
                    setForm(new DoiMKForm());
                } else if (index == 11) {
                    setForm(new GioHangForm());
                } else if (index == 12) {
                    if(Auth.isManager()) {
                        setForm(new ThongKeForm());
                    } else {
                        JOptionPane.showMessageDialog(Main.this, "Không đủ quyền truy cập");
                    }
                } else if (index == 13) {
                    Auth.clear();
                    Main.this.dispose();
                    new DangNhapJDialog((Frame) getOwner(), true).setVisible(true);
                }
            }
        });
        //  set when system open start with home form
        setForm(new Form_Home());
        Instance = this;
        //Day data luong vao list
    }

    public void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new swing.PanelBorder();
        header2 = new component.Header();
        mainPanel = new javax.swing.JPanel();
        menu1 = new component.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(231, 231, 231));

        header2.setBackground(new java.awt.Color(255, 255, 255));

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Header header2;
    private javax.swing.JPanel mainPanel;
    private component.Menu menu1;
    private swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
