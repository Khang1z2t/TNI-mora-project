/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;

import dao.ChiTietPhieuNhapDAO;
import dao.PhieuNhapDAO;
import dao.SachDAO;
import entities.ChiTietPhieuNhap;
import entities.PhieuNhap;
import entities.Sach;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import ui.Main;
import ui.ScrollBar;
import utils.DialogHelper;
import utils.MoneyFormat;

/**
 *
 * @author ngocd
 */
public class NhapHangForm extends javax.swing.JPanel {

    SachDAO sachDAO = new SachDAO();
    PhieuNhapDAO pnDAO = new PhieuNhapDAO();
    ChiTietPhieuNhapDAO ctpnDAO = new ChiTietPhieuNhapDAO();
    private List<PhieuNhap> listPN = new ArrayList<>();

    public NhapHangForm() {
        initComponents();
        init();
    }

    private void init() {
        fillTableSach();
    }
    
    private void addPhieuNhap() {
        
    }

    private void fillTableSach() {
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setColumnIdentifiers(new Object[]{
            "Mã Sách", "Tên Sách", "Giá", "Số Lượng",});
        model.setRowCount(0);
        try {
            String keyword = txtFindSach.getText();
            List<Sach> list = sachDAO.selectByKeyword(keyword);
            for (Sach sach : list) {
                model.addRow(new Object[]{
                    sach.getMaSach(),
                    sach.getTenSach(),
                    MoneyFormat.format(sach.getGia()),
                    sach.getSoLuong(),});
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            throw new RuntimeException(e);
        }
        spTblSach.setVerticalScrollBar(new ScrollBar());
        spTblSach.getVerticalScrollBar().setBackground(Color.white);
        spTblSach.getViewport().setBackground(Color.white);
    }
    


    private void showPopupSach(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = tblSach.rowAtPoint(e.getPoint());
            int column = tblSach.columnAtPoint(e.getPoint());
            tblSach.getSelectionModel().setSelectionInterval(row, row);
            popupMenuSach.show(tblSach, e.getX(), e.getY());
        }
    }

    private void showPopupNhap(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = tblPhieuNhap.rowAtPoint(e.getPoint());
            int column = tblPhieuNhap.columnAtPoint(e.getPoint());
            tblPhieuNhap.getSelectionModel().setSelectionInterval(row, row);
            popupMenuNhap.show(tblPhieuNhap, e.getX(), e.getY());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuSach = new javax.swing.JPopupMenu();
        pAdd = new javax.swing.JMenuItem();
        popupMenuNhap = new javax.swing.JPopupMenu();
        pEdit = new javax.swing.JMenuItem();
        pDelete = new javax.swing.JMenuItem();
        lblBack = new javax.swing.JLabel();
        lblBack1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtFindSach = new javax.swing.JTextField();
        spTblSach = new javax.swing.JScrollPane();
        tblSach = new swing.Table();
        jPanel1 = new javax.swing.JPanel();
        lblBack2 = new javax.swing.JLabel();
        txtMaNhap = new javax.swing.JTextField();
        lblBack3 = new javax.swing.JLabel();
        lblBack4 = new javax.swing.JLabel();
        cboNcc = new model.ComboBoxSuggestion();
        txtMaNV = new javax.swing.JTextField();
        spTblNhap = new javax.swing.JScrollPane();
        tblPhieuNhap = new swing.Table();
        jLabel1 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        pAdd.setText("Thêm");
        popupMenuSach.add(pAdd);

        pEdit.setText("Sửa");
        pEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pEditActionPerformed(evt);
            }
        });
        popupMenuNhap.add(pEdit);

        pDelete.setText("Xoá");
        popupMenuNhap.add(pDelete);

        setBackground(new java.awt.Color(229, 229, 229));

        lblBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });

        lblBack1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBack1.setText("Nhập Hàng");

        jPanel2.setBackground(new java.awt.Color(229, 229, 229));

        jPanel3.setBackground(new java.awt.Color(229, 229, 229));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtFindSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindSachKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindSach)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSach.setBackground(new java.awt.Color(229, 229, 229));
        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSachMouseReleased(evt);
            }
        });
        spTblSach.setViewportView(tblSach);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spTblSach, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTblSach, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("THÊM", jPanel2);

        jPanel1.setBackground(new java.awt.Color(229, 229, 229));

        lblBack2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack2.setText("Mã Phiếu Nhập");

        txtMaNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblBack3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack3.setText("Nhà Cung Cấp");

        lblBack4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack4.setText("Người Tạo Phiếu");

        cboNcc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblPhieuNhap.setBackground(new java.awt.Color(229, 229, 229));
        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseReleased(evt);
            }
        });
        spTblNhap.setViewportView(tblPhieuNhap);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tổng Tiền:");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTien.setText("000.000.0000 đ");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Nhập Hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTblNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblBack4)
                        .addGap(9, 9, 9)
                        .addComponent(txtMaNV))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBack2)
                            .addComponent(lblBack3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNhap)
                            .addComponent(cboNcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBack2)
                    .addComponent(txtMaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBack3)
                    .addComponent(cboNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBack4)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTblNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTongTien)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("NHẬP HÀNG", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBack)
                .addContainerGap())
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBack1)
                    .addComponent(lblBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        Main.Instance.setForm(new KhoForm());
    }//GEN-LAST:event_lblBackMouseClicked

    private void txtFindSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindSachKeyReleased
        // TODO add your handling code here:
        fillTableSach();
    }//GEN-LAST:event_txtFindSachKeyReleased

    private void tblPhieuNhapMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            showPopupNhap(evt);
        }
    }//GEN-LAST:event_tblPhieuNhapMouseReleased

    private void tblSachMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            showPopupSach(evt);
        }
    }//GEN-LAST:event_tblSachMouseReleased

    private void pEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private model.ComboBoxSuggestion cboNcc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBack1;
    private javax.swing.JLabel lblBack2;
    private javax.swing.JLabel lblBack3;
    private javax.swing.JLabel lblBack4;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JMenuItem pAdd;
    private javax.swing.JMenuItem pDelete;
    private javax.swing.JMenuItem pEdit;
    private javax.swing.JPopupMenu popupMenuNhap;
    private javax.swing.JPopupMenu popupMenuSach;
    private javax.swing.JScrollPane spTblNhap;
    private javax.swing.JScrollPane spTblSach;
    private javax.swing.JTabbedPane tabs;
    private swing.Table tblPhieuNhap;
    private swing.Table tblSach;
    private javax.swing.JTextField txtFindSach;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaNhap;
    // End of variables declaration//GEN-END:variables
}
