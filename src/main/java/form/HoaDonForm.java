/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.GiohangDAO;
import dao.SachDAO;
import dao.ThanhVienDAO;
import entities.Giohang;
import entities.Sach;
import entities.ThanhVien;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
     ArrayList<ThanhVien> ist = new ArrayList<>();
     
    int index = -1;
    DefaultTableModel tblModel;
    DefaultComboBoxModel cbxModel;
    public HoaDonForm() {
        initComponents();
        initCBX();
        time();
        DBToListGH();
        txtNV.setText(utils.Auth.user.getMaNhanVien()+ "-" + utils.Auth.user.getHoVaTen());
        lblUser.setText(utils.Auth.user.getMaNhanVien()+ "-" + utils.Auth.user.getHoVaTen());
        initTable();
        if(lblTien.getText().equals("NULL")){
                lblTien.setText("0");
        }
        txtNV.enableInputMethods(false);
        
        //TAB 2 - Thanh Toan
        initCBXGioHang();
        initPTTT();
        initSetTextTab2();
        DBtoListTV();
    }
    private void time(){
        Date currentTime = new Date();
        
        // Định dạng thời gian
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        
        // Hiển thị thời gian theo định dạng AM/PM
        String time = formatter.format(currentTime);
        lblTime.setText(time);
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
    
    private void DBToListGH(){
        GiohangDAO ghd = new GiohangDAO();
        list = (ArrayList<Giohang>) ghd.SelectAll();
    }
    public Giohang readForm() {
        ThanhVien tv = new ThanhVien();
        Sach sa = (Sach) cbxSach.getSelectedItem();
        Giohang gh = new Giohang();

        try{
        String maTV = txtMaTV.getText();
        int soluong = Integer.parseInt(txtSoLuong.getText());

        
        if(soluong<=0){
            utils.DialogHelper.alert(this, "Số lượng phải lớn hơn hoặc bằng 1");
            return null;
        }
        if(maTV.trim().isEmpty()){
           gh.setMaTV(00000000000);
        }
        gh.setSoluong(soluong);
        gh.setMaTV(Integer.parseInt(maTV));
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
        tab = new swing.MaterialTabbed();
        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        txtNV = new swing.TextField();
        btnThem = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        lblTien = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        txtSoLuong = new swing.TextField();
        cbxSach = new swing.ComboBoxSuggestion();
        lblThongBao = new javax.swing.JLabel();
        txtMaTV = new swing.TextField();
        spPanel = new javax.swing.JScrollPane();
        tblList = new swing.Table();
        btnThanhToan = new javax.swing.JButton();
        panelBorder3 = new swing.PanelBorder();
        panelBorder4 = new swing.PanelBorder();
        lblUser = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTongTien = new swing.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        txtThanhVien = new swing.TextFieldSuggestion();
        jLabel4 = new javax.swing.JLabel();
        cbxHang = new swing.ComboBoxSuggestion();
        jLabel5 = new javax.swing.JLabel();
        cbxThanhToan = new swing.ComboBoxSuggestion();
        jLabel6 = new javax.swing.JLabel();
        txtTongGiamGia1 = new swing.TextFieldSuggestion();

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setMaximumSize(new java.awt.Dimension(698, 594));
        panelBorder1.setMinimumSize(new java.awt.Dimension(698, 594));
        panelBorder1.setPreferredSize(new java.awt.Dimension(698, 594));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tab.setForeground(new java.awt.Color(0, 0, 0));
        tab.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        tab.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("HÀNG");

        txtNV.setBackground(new java.awt.Color(255, 255, 255));
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
        lblTongTien.setForeground(new java.awt.Color(0, 0, 0));
        lblTongTien.setText("TỔNG TIỀN :");

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

        txtSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoLuong.setLabelText("Số lượng");

        lblThongBao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtMaTV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaTV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMaTV.setLabelText("Mã thành viên");
        txtMaTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTVActionPerformed(evt);
            }
        });

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spPanel.setViewportView(tblList);

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(cbxSach, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(btnThem))
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(btnXoa))
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(txtMaTV, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(spPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGap(436, 436, 436)
                            .addComponent(lblTongTien)
                            .addGap(6, 6, 6)
                            .addComponent(lblTien)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(472, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(jLabel1))
                        .addComponent(cbxSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(btnThem)))
                    .addGap(6, 6, 6)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa))
                    .addGap(6, 6, 6)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(txtMaTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(spPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGap(205, 205, 205)
                            .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(29, 29, 29)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTongTien)
                        .addComponent(lblTien))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tab.addTab("Giỏ hàng", new javax.swing.ImageIcon(getClass().getResource("/icon/cart.png")), panelBorder2); // NOI18N

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBorder4.setBackground(new java.awt.Color(228, 228, 228));

        lblUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(0, 0, 0));
        lblUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avt.png"))); // NOI18N
        lblUser.setText("ma-ten");

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(0, 0, 0));
        lblTime.setText("hh:mm:ss");

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
            .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblUser)
                    .addGap(42, 42, 42)
                    .addComponent(lblTime)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblUser)
                        .addGroup(panelBorder4Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblTime)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelBorder3.add(panelBorder4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("BẢNG GIÁ VÀ SẢN PHẨM");
        panelBorder3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        panelBorder3.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 280, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("PHƯƠNG THỨC THANH TOÁN");
        panelBorder3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));
        panelBorder3.add(txtThanhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 280, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("KHÁCH HÀNG");
        panelBorder3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        cbxHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxHangActionPerformed(evt);
            }
        });
        panelBorder3.add(cbxHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 240, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("TỔNG TIỀN HÀNG");
        panelBorder3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        panelBorder3.add(cbxThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 210, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("TỔNG GIẢM GIÁ");
        panelBorder3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));
        panelBorder3.add(txtTongGiamGia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 280, -1));

        tab.addTab("Thanh Toán", new javax.swing.ImageIcon(getClass().getResource("/icon/buy.png")), panelBorder3); // NOI18N

        panelBorder1.add(tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 520));

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
        thanhvien();
        tab.setSelectedIndex(1);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtMaTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTVActionPerformed

    private void cbxHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxHangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private swing.ComboBoxSuggestion cbxHang;
    private swing.ComboBoxSuggestion cbxSach;
    private swing.ComboBoxSuggestion cbxThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblTien;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblUser;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private swing.PanelBorder panelBorder3;
    private swing.PanelBorder panelBorder4;
    private javax.swing.JScrollPane spPanel;
    private swing.MaterialTabbed tab;
    private swing.Table tblList;
    private swing.TextField txtMaTV;
    private swing.TextField txtNV;
    private swing.TextField txtSoLuong;
    private swing.TextFieldSuggestion txtThanhVien;
    private swing.TextFieldSuggestion txtTongGiamGia1;
    private swing.TextFieldSuggestion txtTongTien;
    // End of variables declaration//GEN-END:variables

    //TAB 2 - THANH TOAN

    private void initCBXGioHang(){
        cbxModel = new DefaultComboBoxModel();
        cbxModel.removeAllElements();
        List<Giohang> lst = new GiohangDAO().SelectAll();
        for (Giohang gh : lst) {
            cbxModel.addElement(gh);
        }
        cbxHang.setModel(cbxModel);
    }
    
    private void initPTTT(){
        String[] phuongThucThanhToan = {"Tiền mặt", "Thẻ", "Momo", "VNPay"};
        cbxThanhToan.setModel(new DefaultComboBoxModel(phuongThucThanhToan));
    }
    
    private void initSetTextTab2(){
        int tong =0;
        for(Giohang it : list){
            tong += it.getGia() * it.getSoluong();
        }
        txtTongTien.setText(String.valueOf(tong));
    }
    
    private void DBtoListTV(){
        ThanhVienDAO tvd = new ThanhVienDAO();
        ist = (ArrayList<ThanhVien>) tvd.SelectAll();
    }
    private void thanhvien(){
        Giohang gh = new Giohang();
        int ma = Integer.parseInt(txtMaTV.getText());
        for(ThanhVien tv : ist){
            if(ma==tv.getMaTV()){
                txtThanhVien.setText(tv.getTenTV());
                gh.setMaTV(ma);
            }
        }
    }
}
