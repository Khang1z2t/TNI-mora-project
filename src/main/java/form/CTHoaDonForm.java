
package form;

import dao.ChiTietHoaDonDAO;
import dao.HoadonDAO;
import dao.SachDAO;
import entities.ChiTietHoaDon;
import entities.Hoadon;
import entities.Sach;
import ui.Main;
import ui.ScrollBar;
import utils.MoneyFormat;
import utils.PrintReport;
import utils.XDate;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ngocd
 */
public class CTHoaDonForm extends javax.swing.JPanel {
    SachDAO sachDAO = new SachDAO();
    HoadonDAO hdDAO = new HoadonDAO();
    ChiTietHoaDonDAO cthdDao = new ChiTietHoaDonDAO();
    List<ChiTietHoaDon> listCTHD = new ArrayList<>();

    public CTHoaDonForm(Hoadon hd) {
        initComponents();
        setForm(hd);
    }

    private void setForm(Hoadon hd) {
        txtMaHD.setText(hd.getMaHoaDon());
        txtMaTV.setText(String.valueOf(hd.getMaTV()));
        txtNguoiTao.setText(hd.getMaNV());
        txtNgayTao.setText(XDate.toString(hd.getNgayTao(), "dd/MM/yyyy hh:mm:ss"));
        listCTHD = cthdDao.selectById(hd.getMaHoaDon());
        fillTableCTHD();
    }

    private void fillTableCTHD() {
        DefaultTableModel model = (DefaultTableModel) tblCTHD.getModel();
        model.setColumnIdentifiers(new Object[]{
                "STT", "Mã Sách", "Tên Sách", "Số Lượng", "Giá", "Thành Tiền"
        });
        model.setRowCount(0);
        double tongTien = 0;
        int stt = 1;
        for (ChiTietHoaDon cthd : listCTHD) {
            Sach sach = sachDAO.selectById(cthd.getMaSach());
            double thanhTien = cthd.getSoLuong() * cthd.getDonGia();
            tongTien += thanhTien;
            model.addRow(new Object[]{
                    stt++,
                    sach.getMaSach(),
                    sach.getTenSach(),
                    cthd.getSoLuong(),
                    MoneyFormat.format(cthd.getDonGia()),
                    MoneyFormat.format(thanhTien)
            });
            lblTongTien.setText(MoneyFormat.format(tongTien));
            lblTongTien.setToolTipText(String.valueOf(tongTien));
            spTable.setVerticalScrollBar(new ScrollBar());
            spTable.getVerticalScrollBar().setBackground(Color.white);
            spTable.getViewport().setBackground(Color.white);
        }
    }

    private void printFilePDF() {
        String maPN = txtMaHD.getText();
        Hoadon hd = hdDAO.selectById(maPN);
        PrintReport.printHoaDonPDF(
                tblCTHD,
                hd.getMaHoaDon(),
                hd.getNgayTao(),
                hd.getMaNV(),
                hd.getMaTV(),
                Double.parseDouble(lblTongTien.getToolTipText())
        );

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBack = new javax.swing.JLabel();
        lblBack1 = new javax.swing.JLabel();
        txtMaHD = new swing.TextFieldSuggestion();
        lblBack2 = new javax.swing.JLabel();
        lblBack3 = new javax.swing.JLabel();
        txtNguoiTao = new swing.TextFieldSuggestion();
        lblBack4 = new javax.swing.JLabel();
        txtMaTV = new swing.TextFieldSuggestion();
        lblBack5 = new javax.swing.JLabel();
        txtNgayTao = new swing.TextFieldSuggestion();
        spTable = new javax.swing.JScrollPane();
        tblCTHD = new swing.Table();
        btnPrintPDF = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

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
        lblBack1.setText("Xem Thông Tin Chi Tiết Phiếu Nhập");

        txtMaHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblBack2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack2.setForeground(new java.awt.Color(0, 0, 0));
        lblBack2.setText("Mã Hóa Đơn");

        lblBack3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack3.setForeground(new java.awt.Color(0, 0, 0));
        lblBack3.setText("Người tạo");

        txtNguoiTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblBack4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack4.setForeground(new java.awt.Color(0, 0, 0));
        lblBack4.setText("Mã Thành Viên");

        txtMaTV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblBack5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack5.setForeground(new java.awt.Color(0, 0, 0));
        lblBack5.setText("Ngày tạo");

        txtNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblCTHD.setForeground(new java.awt.Color(255, 255, 255));
        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
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
        spTable.setViewportView(tblCTHD);

        btnPrintPDF.setBackground(new java.awt.Color(153, 153, 255));
        btnPrintPDF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrintPDF.setForeground(new java.awt.Color(0, 0, 0));
        btnPrintPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pdf.png"))); // NOI18N
        btnPrintPDF.setText("In PDF");
        btnPrintPDF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrintPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintPDFActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tổng Tiền:");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(0, 0, 0));
        lblTongTien.setText("000.000.000.đ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnPrintPDF))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblBack1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblBack))
                                        .addComponent(spTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(lblBack4)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblBack2)
                                                                .addGap(23, 23, 23)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                                        .addComponent(txtMaTV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblBack5)
                                                        .addComponent(lblBack3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblBack)
                                        .addComponent(lblBack1))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblBack2)
                                        .addComponent(lblBack3)
                                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblBack4)
                                        .addComponent(txtMaTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblBack5)
                                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnPrintPDF)
                                        .addComponent(jLabel1)
                                        .addComponent(lblTongTien))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        Main.Instance.setForm(new GioHangForm());
    }//GEN-LAST:event_lblBackMouseClicked

    private void btnPrintPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintPDFActionPerformed
        // TODO add your handling code here:
        printFilePDF();
    }//GEN-LAST:event_btnPrintPDFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrintPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBack1;
    private javax.swing.JLabel lblBack2;
    private javax.swing.JLabel lblBack3;
    private javax.swing.JLabel lblBack4;
    private javax.swing.JLabel lblBack5;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JScrollPane spTable;
    private swing.Table tblCTHD;
    private swing.TextFieldSuggestion txtMaHD;
    private swing.TextFieldSuggestion txtMaTV;
    private swing.TextFieldSuggestion txtNgayTao;
    private swing.TextFieldSuggestion txtNguoiTao;
    // End of variables declaration//GEN-END:variables
}
