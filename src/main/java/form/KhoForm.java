package form;

import dao.SachDAO;
import entities.Sach;
import ui.ScrollBar;
import utils.DialogHelper;
import utils.MoneyFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * @author ngocd
 */
public class KhoForm extends javax.swing.JPanel {
    SachDAO sachDAO = new SachDAO();

    public KhoForm() {
        initComponents();
        init();
    }

    private void init() {
        fillTableSach();
    }

    private void fillTableSach() {
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Mã Sách", "Tên Sách", "Năm Xuất Bản", "Nhà Xuất Bản", "Giá", "Số Lượng", "Tác Giả", "Thể Loại", "Ghi Chú"
        });
        model.setRowCount(0);
        try {
            String keyword = txtFindSach.getText();
            List<Sach> list = sachDAO.selectByKeyword(keyword);
            for (Sach sach : list) {
                model.addRow(new Object[]{
                        sach.getMaSach(),
                        sach.getTenSach(),
                        sach.getNamXB(),
                        sach.getNhaXB(),
                        MoneyFormat.format(sach.getGia()),
                        sach.getSoLuong(),
                        sach.getTentacgia(),
                        sach.getTheloai(),
                        sach.getGhiChu()
                });
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            throw new RuntimeException(e);
        }
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.white);
        spTable.getViewport().setBackground(Color.white);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFindSach = new javax.swing.JTextField();
        spTable = new javax.swing.JScrollPane();
        tblSach = new swing.Table();
        btnNhap = new javax.swing.JButton();
        btnPhieuNhap = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();

        setBackground(new java.awt.Color(229, 229, 229));

        jPanel1.setBackground(new java.awt.Color(229, 229, 229));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtFindSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindSachKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtFindSach)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtFindSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSach.setBackground(new java.awt.Color(229, 229, 229));
        tblSach.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        spTable.setViewportView(tblSach);

        btnNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhap.setText("Nhập Hàng");

        btnPhieuNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPhieuNhap.setText("Phiếu Nhập");

        btnExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExcel.setText("Xuất Excel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(btnNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNhap)
                                        .addComponent(btnPhieuNhap)
                                        .addComponent(btnExcel))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtFindSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindSachKeyReleased
        // TODO add your handling code here:
        fillTableSach();
    }//GEN-LAST:event_txtFindSachKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnNhap;
    private javax.swing.JButton btnPhieuNhap;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane spTable;
    private swing.Table tblSach;
    private javax.swing.JTextField txtFindSach;
    // End of variables declaration//GEN-END:variables
}
