/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.NhanVienDAO;
import entities.NhanVien;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ui.NguoiDungJDialog;
import utils.DialogHelper;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class NhanVienForm extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienForm
     */
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
    }
    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{
            "Mã Nhân Viên",
            "Họ Và Tên",
            "Email",
            "Vai Trò",
        });
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
                nv.isVaiTro() ? "Quản Lý" : "Thủ thư",
            });
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
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        rdoQuanLy = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        lblQRCode = new javax.swing.JLabel();
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(153, 153, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("MÃ NHÂN VIÊN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("TÊN NHÂN VIÊN");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("MẬT KHẨU");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
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

        lblQRCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQRCode.setForeground(new java.awt.Color(153, 153, 255));
        lblQRCode.setText("QR CODE ");
        lblQRCode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblQRCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQRCodeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtMatKhau)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMaNV)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(rdoQuanLy)
                        .addGap(26, 26, 26)
                        .addComponent(rdoNhanVien))
                    .addComponent(lblQRCode))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdoQuanLy)
                    .addComponent(rdoNhanVien))
                .addGap(18, 18, 18)
                .addComponent(lblQRCode)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(32, 136, 203));
        btnAdd.setText("THÊM");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(32, 136, 203));
        btnDelete.setText("XÓA");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(32, 136, 203));
        btnUpdate.setText("SỬA");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(32, 136, 203));
        btnClear.setText("MỚI");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pre.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/first.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear)
                .addGap(18, 18, 18)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
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

    private void lblQRCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQRCodeMouseClicked
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        JOptionPane.showConfirmDialog(this, icon, "QRCode", JOptionPane.CANCEL_OPTION);
    }//GEN-LAST:event_lblQRCodeMouseClicked

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
                String matKhau = txtMatKhau.getText();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup grpRole;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblQRCode;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JTabbedPane tabNV;
    private swing.Table tblList;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
