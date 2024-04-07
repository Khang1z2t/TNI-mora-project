package form;

import dao.ChiTietPhieuNhapDAO;
import dao.NhaCungCapDAO;
import dao.PhieuNhapDAO;
import dao.SachDAO;
import entities.ChiTietPhieuNhap;
import entities.PhieuNhap;
import entities.Sach;
import swing.FileChooser;
import ui.Main;
import ui.ScrollBar;
import utils.DialogHelper;
import utils.MoneyFormat;
import utils.PrintReport;
import utils.XDate;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author ngocd
 */
public class PhieuNhapForm extends javax.swing.JPanel {

    SachDAO sachDAO = new SachDAO();
    PhieuNhapDAO pnDAO = new PhieuNhapDAO();
    ChiTietPhieuNhapDAO ctpnDAO = new ChiTietPhieuNhapDAO();
    NhaCungCapDAO nccDAO = new NhaCungCapDAO();

    public PhieuNhapForm() {
        initComponents();
        init();
    }

    private void init() {
        fillTableSach();
    }

    private void editPN() {
        String maPN = (String) tblPhieuNhap.getValueAt(tblPhieuNhap.getSelectedRow(), 1);
        PhieuNhap pn = pnDAO.selectById(maPN);
        Main.Instance.setForm(new SuaCTPhieuNhapForm(pn));
    }

    // ko đc đụng
    private void deletePN() {
        String maPN = (String) tblPhieuNhap.getValueAt(tblPhieuNhap.getSelectedRow(), 1);
        PhieuNhap pn = pnDAO.selectById(maPN);
        List<ChiTietPhieuNhap> ctpnList = ctpnDAO.selectById(maPN);

        if (DialogHelper.confirm(null, "Bạn có chắc chắn muốn xoá phiếu nhập này không?")) {
            // Phân vân đoạn này
            for (ChiTietPhieuNhap ctpn : ctpnList) {
                Sach sach = sachDAO.selectById(ctpn.getMasach());
                sach.setSoLuong(sach.getSoLuong() - ctpn.getSoluong());
                sachDAO.update(sach);
            }
            ctpnDAO.delete(pn.getMaNhap());
            pnDAO.delete(pn.getMaNhap());
            DialogHelper.alert(null, "Xoá phiếu nhập thành công!");
            fillTableSach();
        }
    }

    private void viewPN() {
        String maPN = (String) tblPhieuNhap.getValueAt(tblPhieuNhap.getSelectedRow(), 1);
        PhieuNhap pn = pnDAO.selectById(maPN);
        Main.Instance.setForm(new XemCTPhieuNhap(pn));
    }

    private void fillTableSach() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblPhieuNhap.getModel();
            model.setColumnIdentifiers(new Object[]{
                "STT",
                "Mã Phiếu Nhập",
                "Nhà Cung Cấp",
                "Người tạo",
                "Thời gian tạo",
                "Tổng Tiền"
            });
            model.setRowCount(0);

            int stt = 1;
            String keyword = txtFindPhieuNhap.getText();
            String formDate = getDate(txtTuNgay.getDate());
            String toDate = getDate(txtDenNgay.getDate());
            String minPrice = txtTuGia.getText();
            String maxPrice = txtDenGia.getText();
            List<PhieuNhap> list = pnDAO.selectByKeyword(keyword, formDate, toDate, minPrice, maxPrice);
            for (PhieuNhap pn : list) {
                model.addRow(new Object[]{
                    stt++,
                    pn.getMaNhap(),
                    nccDAO.selectById(pn.getMaNhaCC()).getTenNhaCC(),
                    pn.getMaNV(),
                    XDate.toString(pn.getNgayNhap(), "dd/MM/yyyy HH:mm:ss"),
                    MoneyFormat.format(pn.getTongTien())
                });
            }
        } catch (Exception e) {
            DialogHelper.alert(null, "Lỗi truy vấn dữ liệu!");
            throw new RuntimeException(e);
        }
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.white);
        spTable.getViewport().setBackground(Color.white);
    }

    private String getDate(Date date) {
        if (date == null) {
            return "";
        }
        return XDate.toString(date, "yyyy-MM-dd");
    }

    private void printReport() {
        FileChooser chooser = new FileChooser("data/excel/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("EXCEL FILES", ".xls", ".xlsx", ".xln");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Save As");

        int value = chooser.showSaveDialog(null);
        if (value == FileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            PrintReport.printExcel(tblPhieuNhap, file);
        }
    }

    private void showPopupNhap(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = tblPhieuNhap.rowAtPoint(e.getPoint());
            int column = tblPhieuNhap.columnAtPoint(e.getPoint());
            tblPhieuNhap.getSelectionModel().setSelectionInterval(row, row);
            popupMenuPNhap.show(tblPhieuNhap, e.getX(), e.getY());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuPNhap = new javax.swing.JPopupMenu();
        pEdit = new javax.swing.JMenuItem();
        pView = new javax.swing.JMenuItem();
        lblBack = new javax.swing.JLabel();
        lblBack1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtFindPhieuNhap = new javax.swing.JTextField();
        spTable = new javax.swing.JScrollPane();
        tblPhieuNhap = new swing.Table();
        btnExcel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtTuNgay = new com.toedter.calendar.JDateChooser();
        txtDenNgay = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        txtTuGia = new javax.swing.JTextField();
        txtDenGia = new javax.swing.JTextField();

        pEdit.setText("Sửa Phiếu Nhập");
        pEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pEditActionPerformed(evt);
            }
        });
        popupMenuPNhap.add(pEdit);

        pView.setText("Xem Chi Tiết");
        pView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pViewActionPerformed(evt);
            }
        });
        popupMenuPNhap.add(pView);

        setBackground(new java.awt.Color(229, 229, 229));

        lblBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack.setForeground(new java.awt.Color(0, 0, 0));
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/prevBtn.png"))); // NOI18N
        lblBack.setText("Trở lại");
        lblBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });

        lblBack1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBack1.setForeground(new java.awt.Color(0, 0, 0));
        lblBack1.setText("Phiếu Nhập");

        jPanel3.setBackground(new java.awt.Color(229, 229, 229));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtFindPhieuNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindPhieuNhapKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindPhieuNhap)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtFindPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

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
        spTable.setViewportView(tblPhieuNhap);

        btnExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExcel.setText("Xuất Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(229, 229, 229));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Theo ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtTuNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTuNgayPropertyChange(evt);
            }
        });

        txtDenNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDenNgayPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(229, 229, 229));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Theo giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtTuGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTuGiaKeyReleased(evt);
            }
        });

        txtDenGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDenGiaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTuGia, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDenGia, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTuGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDenGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBack1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBack))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spTable)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBack)
                    .addComponent(lblBack1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        Main.Instance.setForm(new KhoForm());
    }//GEN-LAST:event_lblBackMouseClicked

    private void txtFindPhieuNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindPhieuNhapKeyReleased
        // TODO add your handling code here:
        fillTableSach();
    }//GEN-LAST:event_txtFindPhieuNhapKeyReleased

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:
        printReport();
    }//GEN-LAST:event_btnExcelActionPerformed

    private void pEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pEditActionPerformed
        // TODO add your handling code here:
        editPN();
    }//GEN-LAST:event_pEditActionPerformed

    private void pViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pViewActionPerformed
        // TODO add your handling code here:
        viewPN();
    }//GEN-LAST:event_pViewActionPerformed

    private void tblPhieuNhapMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            showPopupNhap(evt);
        }
    }//GEN-LAST:event_tblPhieuNhapMouseReleased

    private void txtTuGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuGiaKeyReleased
        // TODO add your handling code here:
        fillTableSach();
    }//GEN-LAST:event_txtTuGiaKeyReleased

    private void txtDenGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenGiaKeyReleased
        // TODO add your handling code here:
        fillTableSach();
    }//GEN-LAST:event_txtDenGiaKeyReleased

    private void txtTuNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTuNgayPropertyChange
        // TODO add your handling code here:
        fillTableSach();

    }//GEN-LAST:event_txtTuNgayPropertyChange

    private void txtDenNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDenNgayPropertyChange
        // TODO add your handling code here:
        fillTableSach();

    }//GEN-LAST:event_txtDenNgayPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBack1;
    private javax.swing.JMenuItem pEdit;
    private javax.swing.JMenuItem pView;
    private javax.swing.JPopupMenu popupMenuPNhap;
    private javax.swing.JScrollPane spTable;
    private swing.Table tblPhieuNhap;
    private javax.swing.JTextField txtDenGia;
    private com.toedter.calendar.JDateChooser txtDenNgay;
    private javax.swing.JTextField txtFindPhieuNhap;
    private javax.swing.JTextField txtTuGia;
    private com.toedter.calendar.JDateChooser txtTuNgay;
    // End of variables declaration//GEN-END:variables
}
