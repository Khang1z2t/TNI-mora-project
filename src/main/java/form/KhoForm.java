package form;

import dao.SachDAO;
import entities.Sach;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ui.ScrollBar;
import utils.DialogHelper;
import utils.MoneyFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import swing.FileChooser;
import ui.Main;

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
    
    private void printReport(JTable table, File file) {
        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Report");
//            tạo header của cột
            Row r = sheet.createRow(0);
            TableModel model = table.getModel();
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = r.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }

//            tạo dữ liẹt của bảng
            for (int i = 0; i < model.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(model.getValueAt(i, j).toString());
                }
            }
            FileOutputStream fos = new FileOutputStream(file + ".xlsx");
            wb.write(fos);
            fos.close();
            wb.close();

            if (DialogHelper.confirm(this, "Lưu thàng công. Bạn có muốn mở file lên không?")) {
                Desktop.getDesktop().open(new File(file + ".xlsx"));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        spTable.setViewportView(tblSach);

        btnNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhap.setText("Nhập Hàng");
        btnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapActionPerformed(evt);
            }
        });

        btnPhieuNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPhieuNhap.setText("Phiếu Nhập");
        btnPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuNhapActionPerformed(evt);
            }
        });

        btnExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExcel.setText("Xuất Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

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
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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

    private void btnNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapActionPerformed
        // TODO add your handling code here:
        Main.Instance.setForm(new NhapHangForm());
    }//GEN-LAST:event_btnNhapActionPerformed

    private void btnPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuNhapActionPerformed
        // TODO add your handling code here:
        Main.Instance.setForm(new PhieuNhapForm());
    }//GEN-LAST:event_btnPhieuNhapActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:
        FileChooser chooser = new FileChooser(System.getProperty("user.home") + "/Desktop");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("EXCEL FILES", ".xls", ".xlsx", ".xln");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Save As");

        int value = chooser.showSaveDialog(null);
        if (value == FileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            printReport(tblSach, file);
        }
    }//GEN-LAST:event_btnExcelActionPerformed


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
