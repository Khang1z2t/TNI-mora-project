/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.LuongDAO;
import dao.NhanVienDAO;
import entities.Luong;
import entities.NhanVien;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import ui.Main;
import utils.DialogHelper;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class NhanVienForm extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienForm
     */
    DefaultComboBoxModel cboModel;  
    DefaultTableModel tblModel;
    int index = -1;
    List<NhanVien> listNV = new ArrayList<>();
    String path = null;

    public NhanVienForm() {
        initComponents();
        initTable();
        DBFillToList();
        setSelected(0);
        if (!utils.Auth.isManager()) {
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(false);
        }

        if (txtMaNV.getText().trim().length() == 0) {
            txtMaNV.requestFocus();
        }
        initCBXLuong();
    }

    
    private void initCBXLuong() {
        cboModel = new DefaultComboBoxModel();
        cboModel.removeAllElements();
        List<Luong> lst = new LuongDAO().SelectAll();
        for (Luong l : lst) {
            cboModel.addElement(l);
        }
        cbxLevel.setModel(cboModel);
    }
    
    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{
            "Mã Nhân Viên",
            "Họ Và Tên",
            "Email",
            "Cấp NV",
            "Vai Trò",});
        tblList.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblList.getTableHeader().setOpaque(false);
        tblList.getTableHeader().setBackground(new Color(32, 136, 203));
        tblList.getTableHeader().setForeground(new Color(255, 255, 255));
        tblList.setRowHeight(25);
        tblList.setModel(tblModel);
    }

    void showDetail(int index) {
        txtMaNV.setEditable(false);
        NhanVien nv = listNV.get(index);
        txtMaNV.setText(nv.getMaNhanVien());
        txtMatKhau.setText(nv.getMatKhau());
        Luong lg = (Luong) cbxLevel.getSelectedItem();
        nv.setCap(lg.getCap());
        txtEmail.setText(nv.getEmail());
        txtTenNV.setText(nv.getHoVaTen());
        if (nv.isVaiTro()) {
            rdoQuanLy.setSelected(true);
        } else {
            rdoNhanVien.setSelected(true);
        }

        if (txtMaNV.getText().equals(utils.Auth.user.getMaNhanVien())) {
            btnDelete.setEnabled(false);
        } else {
            btnDelete.setEnabled(true);
        }

        if (!utils.Auth.isManager()) {
            btnDelete.setEnabled(false);
        }
    }

    void previous() {
        try {
            if (index == 0) {
                index = listNV.size();
            }
            index--;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    void next() {
        try {
            if (index == listNV.size() - 1) {
                index = -1;
            }
            index++;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    void first() {
        try {
            index = 0;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    void last() {
        try {
            index = listNV.size() - 1;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    private void fillToTable(List<NhanVien> lst) {
        tblModel.setRowCount(0);
        for (NhanVien nv : lst) {
            tblModel.addRow(new Object[]{
                nv.getMaNhanVien(),
                nv.getHoVaTen(),
                nv.getEmail(),
                nv.getCap(),
                nv.isVaiTro() ? "Quản Lý" : "Thủ thư",});
        }
    }

    private void DBFillToList() {
        NhanVienDAO nvd = new NhanVienDAO();
        listNV = nvd.selectAll();
        fillToTable(listNV);
    }

    private void setSelected(int index) {
        try {
            tblList.setRowSelectionInterval(index, index);
            showDetail(index);
        } catch (Exception e) {

        }
    }

    private void clearForm() {
        txtMaNV.setText("");
        txtMatKhau.setText("");
        txtEmail.setText("");
        txtTenNV.setText("");
        txtMaNV.setEditable(true);
        txtMaNV.requestFocus();
    }

    public static boolean isValidEmail(String email) {
        boolean isValid = true;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException e) {
            isValid = false;
        }
        return isValid;
    }

    public NhanVien readForm() {
        NhanVien nv = new NhanVien();
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String maNhanVien = txtMaNV.getText();
        String matKhau = String.valueOf(txtMatKhau.getPassword());
        String email = txtEmail.getText();
        String hoVaTen = txtTenNV.getText();
        Luong lg = (Luong) cbxLevel.getSelectedItem();
        String cap = lg.getCap();

        if (maNhanVien.trim().length() == 0 || matKhau.trim().length() == 0
                || hoVaTen.trim().length() == 0 || email.trim().length() == 0) {
            utils.DialogHelper.alert(this, "Vui lòng nhập đầy đủ thông tin!");
            return null;
        }

        if (!txtEmail.getText().trim().matches(regex)) {
            DialogHelper.alert(this, "Email không đúng định dạng!");
            return null;
        }

        if (matKhau.trim().length() < 3) {
            DialogHelper.alert(this, "Mật khẩu phải có ít nhất 3 kí tự!");
            return null;
        }

        String xacNhan = DialogHelper.prompt(this, "Xác nhận mật khẩu!");
        if (!xacNhan.equals(matKhau)) {
            DialogHelper.alert(this, "Mật khẩu không trùng khớp!");
            return null;
        }

        nv.setMaNhanVien(maNhanVien);
        nv.setMatKhau(matKhau);
        nv.setHoVaTen(hoVaTen);
        nv.setEmail(email);
        nv.setCap(cap);
        nv.setVaiTro(rdoQuanLy.isSelected());
        return nv;
    }

    private void addNhanVien() {
        NhanVien nv = readForm();
        NhanVienDAO nvd = new NhanVienDAO();
        if (nv != null) {
            for (NhanVien n : listNV) {
                if (nv.getMaNhanVien().equals((n.getMaNhanVien()))) {
                    utils.DialogHelper.alert(this, "Mã nhân viên đã tồn tại!");
                    return;
                }
            }
            listNV.add(nv);
            nvd.insert(nv);
            DBFillToList();
            clearForm();
            utils.DialogHelper.alert(this, "Thêm nhân viên mới thành công!");
        }
    }

    private void deleteNhanVien(NhanVien nv) {
        NhanVienDAO nvd = new NhanVienDAO();
        nvd.delete(nv.getMaNhanVien());
        listNV.remove(nv);
        DBFillToList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpRole = new javax.swing.ButtonGroup();
        panelBorder1 = new swing.PanelBorder();
        tabNV = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        rdoQuanLy = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        txtMaNV = new swing.TextFieldSuggestion();
        txtTenNV = new swing.TextFieldSuggestion();
        txtEmail = new swing.TextFieldSuggestion();
        txtMatKhau = new swing.PasswordFieldSuggestion();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNextPage = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxLevel = new swing.ComboBoxSuggestion();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new swing.Table();

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(229, 229, 229));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(153, 153, 255))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("VAI TRÒ");

        grpRole.add(rdoQuanLy);
        rdoQuanLy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoQuanLy.setForeground(new java.awt.Color(153, 153, 255));
        rdoQuanLy.setText("QUẢN LÝ");

        grpRole.add(rdoNhanVien);
        rdoNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoNhanVien.setForeground(new java.awt.Color(153, 153, 255));
        rdoNhanVien.setText("Thủ thư");
        rdoNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNhanVienActionPerformed(evt);
            }
        });

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtTenNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Mã Nhân Viên");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tên Nhân Viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Gmail");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mật khẩu");

        lblNextPage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNextPage.setForeground(new java.awt.Color(0, 0, 0));
        lblNextPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/nextBtn.png"))); // NOI18N
        lblNextPage.setText("CHI TIẾT");
        lblNextPage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNextPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNextPageMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Cấp NV");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(lblNextPage))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdoQuanLy)
                                .addGap(26, 26, 26)
                                .addComponent(rdoNhanVien))
                            .addComponent(cbxLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblNextPage))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdoQuanLy)
                    .addComponent(rdoNhanVien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbxLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(32, 136, 203));
        btnAdd.setText("THÊM");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 90, 40));

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(32, 136, 203));
        btnDelete.setText("XÓA");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, 80, 40));

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(32, 136, 203));
        btnUpdate.setText("SỬA");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, 90, 40));

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(32, 136, 203));
        btnClear.setText("MỚI");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel2.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 90, 40));

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel2.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, 60, 40));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel2.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 500, 60, 40));

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pre.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrevious, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 60, 40));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/first.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        jPanel2.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 60, 40));

        tabNV.addTab("Cập nhật", jPanel2);

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 123, Short.MAX_VALUE))
        );

        tabNV.addTab("Danh Sách", jPanel3);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabNV, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabNV)
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

    private void rdoNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNhanVienActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        addNhanVien();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            for (NhanVien nv : listNV) {
                if (txtMaNV.getText().equalsIgnoreCase(nv.getMaNhanVien())) {

                    boolean choose = utils.DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa nhân viên '" + nv.getHoVaTen() + "' không?");
                    if (choose) {
                        deleteNhanVien(nv);
                        setSelected(0);
                        utils.DialogHelper.alert(this, "Xóa thông tin nhân viên thành công!");
                        if (listNV.isEmpty()) {
                            clearForm();
                        }
                    } else {
                        return;
                    }
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        for (NhanVien nv : listNV) {
            if (txtMaNV.getText().equals(nv.getMaNhanVien())) {
                NhanVienDAO nvd = new NhanVienDAO();
                Luong lg = (Luong) cbxLevel.getSelectedItem();
                String cap = lg.getCap();
                String matKhau = String.valueOf(txtMatKhau.getPassword());
                String xacNhan = DialogHelper.prompt(this, "Xác nhận mật khẩu");
                if (!matKhau.equals(xacNhan)) {
                    utils.DialogHelper.alert(this, "Mật khẩu không trùng khớp!");
                    return;
                }
                nv.setMatKhau(matKhau);
                if (isValidEmail(txtEmail.getText())) {
                    nv.setEmail(txtEmail.getText());
                } else {
                    DialogHelper.alert(this, "Email không đúng định dạng!");
                }
                nv.setHoVaTen(txtTenNV.getText());
                nv.setCap(cap);
                nv.setVaiTro(rdoQuanLy.isSelected());
                nvd.update(nv);
                DBFillToList();
                utils.DialogHelper.alert(this, "Cập nhật thông tin nhân viên thành công");
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        previous();
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        try {
            index = tblList.getSelectedRow();
            String ma = (String) tblList.getValueAt(index, 0);
            NhanVien nv = new NhanVienDAO().selectById(ma);
            txtMaNV.setText(nv.getMaNhanVien());
            txtTenNV.setText(nv.getHoVaTen());
            txtMatKhau.setText(nv.getMatKhau());
            txtEmail.setText(nv.getEmail());
            if (nv.isVaiTro()) {
                rdoNhanVien.setSelected(true);
            } else {
                rdoQuanLy.setSelected(true);
            }
            tabNV.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblListMouseClicked

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void lblNextPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextPageMouseClicked
        // TODO add your handling code here:
        Main.Instance.setForm(new NguoiDungForm());
    }//GEN-LAST:event_lblNextPageMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdate;
    private swing.ComboBoxSuggestion cbxLevel;
    private javax.swing.ButtonGroup grpRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNextPage;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JTabbedPane tabNV;
    private swing.Table tblList;
    private swing.TextFieldSuggestion txtEmail;
    private swing.TextFieldSuggestion txtMaNV;
    private swing.PasswordFieldSuggestion txtMatKhau;
    private swing.TextFieldSuggestion txtTenNV;
    // End of variables declaration//GEN-END:variables
}
