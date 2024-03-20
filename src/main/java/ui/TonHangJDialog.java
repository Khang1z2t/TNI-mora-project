/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.SachDAO;
import entities.Sach;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import utils.XDate;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class TonHangJDialog extends javax.swing.JDialog {
//    DefaultTableModel tblModel;
//    DefaultComboBoxModel cbxModel;
//    int index = 0;
//    ArrayList <Sach> temp = new ArrayList<>();
    /**
     * Creates new form TonKhoJDialog
     */
    public TonHangJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
//        initTable();
//        initComboBox();
//        DBFillToList();
    }
//    private void chooseCBX(){
//        Sach sach = (Sach) cbxNgayton.getSelectedItem();
//        txtMaSach.setText(sach.getMaSach());
//        txtTenSach.setText(sach.getTenSach());
//        txtSoLuong.setText(String.valueOf(sach.getSoluong()));
//        txtDate.setText(XDate.toString(sach.getNgayton(), "dd-MM-yyyy"));
//    }
//    private void initComboBox() {
//        cbxModel = new DefaultComboBoxModel();
//        cbxModel.removeAllElements();
//        List<Sach> lst = new SachDAO().SelectAll();
//        for (Sach sach : lst) {
//            cbxModel.addElement(sach);
//        }
//        cbxSach.setModel(cbxModel);
//    }
//        private void DBFillToList() {
//        SachDAO cdd = new SachDAO();
//        temp = (ArrayList<Sach>) cdd.SelectAll();
//    }
//private void initCBXDates() {
//    cbxModel = new DefaultComboBoxModel();
//    
//    Date today = new Date();
//    cbxModel.addElement(XDate.toString(today, "dd-MM-yyyy"));
//    
//    Date yesterday = XDate.addDays(today, -1);
//    cbxModel.addElement(XDate.toString(yesterday, "dd-MM-yyyy"));
//    
//    List<Date> listDate = new SachDAO().selectDate();
//    if (listDate != null && !listDate.isEmpty()) {
//        for (Date date : listDate) {
//            if (!date.equals(today) && !date.equals(yesterday)) {
//                cbxModel.addElement(date);
//            }
//        }
//    }
//    cbxDate.setModel(cbxModel);
//}
//    
//    void showDetail(int index) {
//        Sach sach = (Sach) cbxSach.getSelectedItem();
//        txtNgayton.setText(XDate.toString(sach.getNgayton(), "dd-MM-yyyy"));
//        txtSoluong.setText(String.valueOf(sach.getSoluong()));
//        txtSoluong.requestFocus();
//    }
//    private void initTable() {
//        tblModel = new DefaultTableModel();
//        tblModel.setColumnIdentifiers(new String[]{
//            "STT",
//            "Mã sách",
//            "Tên sách",
//            "Số lượng",
//            "Ngày tồn",
//        });
//        tblTon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
//        tblTon.getTableHeader().setOpaque(false);
//        tblTon.getTableHeader().setBackground(new Color(240, 183, 28));
//        tblTon.getTableHeader().setForeground(new Color(255, 255, 255));
//        tblTon.setRowHeight(25);
//        tblTon.setModel(tblModel);
//    }
//
//public void addHang() {
//    
//        try {
//            // Lấy dữ liệu từ các trường nhập liệu
//            Sach sach =  (Sach) cbxSach.getSelectedItem();
//            int soLuong = 0;
//            sach.setSoluong(soLuong);
//            // Thêm hàng vào bảng
//            temp.add(sach);
//            new SachDAO().update(sach);
//            fillToTable();
//        } catch (NumberFormatException e) {
//            utils.DialogHelper.alert(this, "Số lượng không hợp lệ!");
//        }
//    }
//
//public void removeHang() {
//        DefaultTableModel model = (DefaultTableModel) tblTon.getModel();
//        int selectedRow = tblTon.getSelectedRow();
//        if (selectedRow != -1) {
//            if (utils.DialogHelper.confirm(this, "Bạn muốn xóa mục hàng đã được chọn?")) {
//                for (int i = 0; i < model.getRowCount(); i++) {
//                    String masach = (String) tblTon.getValueAt(i,0);
//                    Sach sach = new SachDAO().selectById(masach);
//                    sach.setSoluong(0);
//                    new SachDAO().update(sach);
////                    model.setValueAt(i + 1, i, 0);
//                }
//                temp.remove(selectedRow);
//                model.removeRow(selectedRow);
//            }
//        } else {
//            utils.DialogHelper.alert(this, "Vui lòng chọn một hàng để xóa.");
//        }
//    }
//
//    public void fillToTable() {
//        DefaultTableModel model = (DefaultTableModel) tblTon.getModel();
//        model.setRowCount(0);
//            try {
//                   for(int i=0;i<temp.size();i++){
//                       Object[] row = {
//                           i+1,
//                           temp.get(i).getMaSach(),
//                           temp.get(i).getTenSach(),
//                           temp.get(i).getSoluong(),
//                           XDate.toString(temp.get(i).getNgayton(),"dd-MM-yyyy"),
//                       };
//                       model.addRow(row);
//                   }
//            } catch (Exception e) {
//        }
//    }
//    public void updateSoLuong() {
//        try {
//            Sach sachh = (Sach) cbxSach.getSelectedItem();
//            for (int i = 0; i < tblTon.getRowCount(); i++) {
//                String ma = (String) tblTon.getValueAt(i, 1);
//                if(ma.equalsIgnoreCase(sachh.getMaSach())){
//
//                    // Sử dụng ArrayList để chứa kết quả từ selectById
//                    Sach sach = new SachDAO().selectById(ma);
//
//                    // Kiểm tra xem danh sách có phần tử không trước khi thực hiện các thao tác
//                    if (sach != null) {
//                        sach.setSoluong(Integer.parseInt(String.valueOf(tblTon.getValueAt(i, 3))));
//                        new SachDAO().updateSL(sach);
//                        fillToTable();
//                    }
//                }else {
//                    // Xử lý khi không tìm thấy đối tượng với mã tương ứng
//                    System.out.println("Không tìm thấy đối tượng với mã: " + ma);
//                }
//                
//            }
//            utils.DialogHelper.alert(this, "Cập nhật thành công!");
//        } catch (NumberFormatException e) {
//            utils.DialogHelper.alert(this, "Số lượng không hợp lệ!");
//        }
//    }

   /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMasach = new javax.swing.JTextField();
        txtTensach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNgayton = new javax.swing.JTextField();
        txtAdd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTon = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        cbxSach = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("THỰC HIỆN KIỂM KÊ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã sách");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên sách");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Số lượng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Ngày tồn");

        txtAdd.setText("Thêm số lượng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtMasach, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtTensach, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtNgayton, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(txtAdd)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMasach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTensach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addComponent(txtAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("THÔNG TIN", jPanel1);

        tblTon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblTon);

        btnUpdate.setText("CẬP NHẬT");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa kiểm kê");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(57, 57, 57))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnUpdate))
                .addGap(22, 22, 22))
        );

        tab.addTab("DANH SÁCH", jPanel2);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(32, 136, 203))); // NOI18N

        cbxSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxSach, 0, 473, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxSach, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSachActionPerformed
        // TODO add your handling code here:
//        DBFillToList();
    }//GEN-LAST:event_cbxSachActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
//        removeHang();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
//        updateSoLuong();
    }//GEN-LAST:event_btnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(TonHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TonHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TonHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TonHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TonHangJDialog dialog = new TonHangJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tblTon;
    private javax.swing.JButton txtAdd;
    private javax.swing.JTextField txtMasach;
    private javax.swing.JTextField txtNgayton;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTensach;
    // End of variables declaration//GEN-END:variables
}
