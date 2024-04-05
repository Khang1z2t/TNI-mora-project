/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.GiohangDAO;
import dao.SachDAO;
import entities.Giohang;
import entities.Sach;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import ui.ThanhToanJFrame;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class HoaDonForm extends javax.swing.JPanel {
     ArrayList<Giohang> list = new ArrayList<>();
    int index = -1;
    DefaultTableModel tblModel;
    DefaultComboBoxModel cbxModel;
    public HoaDonForm() {
        initComponents();
        initCBX();
        txtNV.setText(utils.Auth.user.getMaNhanVien()+ "-" + utils.Auth.user.getHoVaTen());
        initTable();
        if(lblTien.getText().equals("NULL")){
                lblTien.setText("0");
        }
        txtNV.enableInputMethods(false);
    }
//    private void initGia(){
//        
//        lblTien.setText(Integer.sum(, ABORT));
//    }
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
    
    private void initCBX() {
        cbxModel = new DefaultComboBoxModel();
        cbxModel.removeAllElements();
        List<Sach> lst = new SachDAO().SelectAll();
        for (Sach sach : lst) {
            cbxModel.addElement(sach);
        }
        cbxSach.setModel(cbxModel);
    }
    
    public Giohang readForm() {
        Sach sa = (Sach) cbxSach.getSelectedItem();
        Giohang gh = new Giohang();
        try{
        int soluong = Integer.parseInt(txtSoLuong.getText());
        if(soluong<=0){
            utils.DialogHelper.alert(this, "Số lượng phải lớn hơn hoặc bằng 1");
        }
        gh.setSoluong(soluong);
        }catch(NumberFormatException e){
            lblThongBao.setText("Vui lòng nhập số lượng là số!");
            lblThongBao.setForeground(Color.RED);
        }
        gh.setMasach(sa.getMaSach());
        gh.setTensach(sa.getTenSach());
        gh.setGia(sa.getGia());
        gh.setManv(utils.Auth.user.getMaNhanVien());
        return gh;
}
    
    private void fillToTable() {
        tblModel.setRowCount(0);
        for (int i=0;i<list.size();i++){
            Giohang gh = list.get(i);
            tblModel.addRow(new Object[]{
                i+1,
                gh.getTensach(),
                gh.getSoluong(),
                gh.getGia(),
            });
        }
    }
    
    //thuat thuc tinh tong tien qua java:test 21/3/24
    private void tinhTien(){
        int tong =0;
        for(Giohang it : list){
            tong += it.getGia() * it.getSoluong();
        }
        lblTien.setText(String.valueOf(tong+" VND"));
    }
    
    private void addCart() {
        Giohang gh = readForm();
        GiohangDAO ghd = new GiohangDAO();
        if (gh != null) {
            //Neu them hang ma trung ten thi cap nhat so luong
            boolean ex =false;
            for(Giohang it : list){
                if(it.getMasach().equalsIgnoreCase(gh.getMasach())){
                    int newSL = it.getSoluong() + gh.getSoluong() ;
                    it.setSoluong(newSL);
                    ghd.update(it);
                    ex=true;
                    break;
                    //end
                }
            }
            if(!ex){
                list.add(gh);
                ghd.insert(gh);
            }
            fillToTable();
            //cap nhat hien thi
            tinhTien();
            //tinh tong tien,setText
        }
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
        jLabel1 = new javax.swing.JLabel();
        txtNV = new swing.TextField();
        btnThem = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        lblTien = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        txtSoLuong = new swing.TextField();
        cbxSach = new model.ComboBoxSuggestion();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        lblThongBao = new javax.swing.JLabel();

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(229, 229, 229));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HÀNG");

        txtNV.setBackground(new java.awt.Color(229, 229, 229));
        txtNV.setEnabled(false);
        txtNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNV.setLabelText("Nhân viên thực hiện giao dịch");

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 255, 255));
        lblTongTien.setText("TỔNG TIỀN :");

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lblTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTien.setForeground(new java.awt.Color(255, 153, 102));
        lblTien.setText("NULL");

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtSoLuong.setBackground(new java.awt.Color(229, 229, 229));
        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoLuong.setLabelText("Số lượng");

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblList);

        lblThongBao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTongTien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTien)
                        .addGap(108, 108, 108))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                    .addComponent(txtNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbxSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(42, 42, 42)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(43, Short.MAX_VALUE))))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnThem)
                    .addComponent(cbxSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnXoa))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTongTien)
                        .addComponent(lblTien))
                    .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addCart();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            int selectedRow = tblList.getSelectedRow();
            if (selectedRow != -1) {
                Giohang gh = list.get(selectedRow);
                int magiohang = gh.getMagiohang(); // Lấy mã giỏ hàng của mục được chọn
                GiohangDAO ghd = new GiohangDAO();
                ghd.delete(magiohang); // Xóa mục từ cơ sở dữ liệu
                list.remove(selectedRow); // Xóa mục từ danh sách
                fillToTable(); // Cập nhật lại bảng hiển thị
                lblThongBao.setText("Xóa thành công!");
                lblThongBao.setForeground(Color.GREEN);
                tinhTien();
            } else {
                lblThongBao.setText("Vui lòng chọn mục để xóa!");
                lblThongBao.setForeground(Color.RED);
            }
        } catch (Exception e) {
            utils.DialogHelper.alert(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        new ThanhToanJFrame().setVisible(true);
    }//GEN-LAST:event_btnThanhToanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private model.ComboBoxSuggestion cbxSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblTien;
    private javax.swing.JLabel lblTongTien;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JTable tblList;
    private swing.TextField txtNV;
    private swing.TextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
