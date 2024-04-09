/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.Giohang;
import dao.*;
import form.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class ThanhToanJFrame extends javax.swing.JFrame {

    private ThanhToanThanhCongForm success;

    ArrayList<Giohang> list = new ArrayList<>();

    int index = -1;
    DefaultTableModel tblModel;

    public ThanhToanJFrame() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        initTable();


        //dua cac du lieu gio hang duoc them vao truoc do
        DBtoList();
        tinhTien();
        fillToTable();
    }

    private void tinhTien() {
        int tong = 0;
        for (Giohang it : list) {
            tong += it.getGia() * it.getSoluong();
        }
        lblTongTien.setText(String.valueOf("Tổng tiền: " + tong + " VND"));
    }

    private void successForm() {
        list.clear();
        GiohangDAO ghd = new GiohangDAO();
        ghd.reset();
        Timer timer = new Timer(10000, (ActionEvent e) -> {
            // Create an instance of HoaDonForm
            lblThongBao.setText("THANH TOÁN THÀNH CÔNG!");
            lblThongBao.setForeground(Color.green);
            this.dispose();

            // Replace the content of mainPanel with HoaDonForm
            Main.Instance.setForm(new HoaDonForm());

        });
        timer.setRepeats(false); // Set the timer to only fire once
        timer.start();
    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{
                "STT",
                "Tên sách",
                "Số lượng",
                "Giá",
        });
        tblList.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblList.getTableHeader().setOpaque(false);
        tblList.getTableHeader().setBackground(new Color(32, 136, 203));
        tblList.getTableHeader().setForeground(new Color(255, 255, 255));
        tblList.setRowHeight(25);
        tblList.setModel(tblModel);
    }

    private void DBtoList() {
        list.clear();
        list.addAll(new GiohangDAO().SelectAll());

    }

    private void fillToTable() {
        tblModel.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            Giohang gh = list.get(i);
            tblModel.addRow(new Object[]{
                    i + 1,
                    gh.getTensach(),
                    gh.getSoluong(),
                    gh.getGia(),
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        Cash = new javax.swing.JLabel();
        CreditCard = new javax.swing.JLabel();
        VNPAY = new javax.swing.JLabel();
        MOMO = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblThongBao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("THANH TOÁN BẰNG HÌNH THỨC");

        sp.setBorder(null);

        tblList.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        sp.setViewportView(tblList);

        Cash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Cash.png"))); // NOI18N
        Cash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CashMouseClicked(evt);
            }
        });

        CreditCard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/credit.png"))); // NOI18N
        CreditCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreditCardMouseClicked(evt);
            }
        });

        VNPAY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vnpay.png"))); // NOI18N
        VNPAY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VNPAYMouseClicked(evt);
            }
        });

        MOMO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/momo.png"))); // NOI18N
        MOMO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MOMOMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("SẢN PHẨM TRONG GIỎ HÀNG");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(0, 0, 0));

        lblThongBao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGap(144, 144, 144)
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                                .addComponent(Cash)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(CreditCard)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(VNPAY)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(MOMO))
                                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                                .addGap(43, 43, 43)
                                                                .addComponent(jLabel1))))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                                                        .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblThongBao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(49, Short.MAX_VALUE))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                        .addContainerGap(218, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addGap(197, 197, 197)))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Cash)
                                        .addComponent(CreditCard)
                                        .addComponent(VNPAY)
                                        .addComponent(MOMO))
                                .addGap(21, 21, 21))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel2)
                                        .addContainerGap(405, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MOMOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MOMOMouseClicked
        // TODO add your handling code here:
        successForm();
    }//GEN-LAST:event_MOMOMouseClicked

    private void VNPAYMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VNPAYMouseClicked
        // TODO add your handling code here:
        successForm();
    }//GEN-LAST:event_VNPAYMouseClicked

    private void CreditCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreditCardMouseClicked
        // TODO add your handling code here:
        successForm();
    }//GEN-LAST:event_CreditCardMouseClicked

    private void CashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CashMouseClicked
        // TODO add your handling code here:
        successForm();
    }//GEN-LAST:event_CashMouseClicked

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
            java.util.logging.Logger.getLogger(ThanhToanJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThanhToanJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThanhToanJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThanhToanJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThanhToanJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cash;
    private javax.swing.JLabel CreditCard;
    private javax.swing.JLabel MOMO;
    private javax.swing.JLabel VNPAY;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblTongTien;
    private swing.PanelBorder mainPanel;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTable tblList;
    // End of variables declaration//GEN-END:variables
}
