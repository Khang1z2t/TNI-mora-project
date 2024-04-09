/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.ThanhVienDAO;
import entities.ThanhVien;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import ui.ScrollBar;
import utils.DialogHelper;
import utils.XDate;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class ThanhVienForm extends javax.swing.JPanel {
    DefaultTableModel tblModel;
    ArrayList<ThanhVien> list = new ArrayList<>();
    int index = -1;

    /**
     * Creates new form FormTemplate
     */
    public ThanhVienForm() {
        initComponents();
        initTable();
        DBFillToList();
    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{
                "Tên thành viên",
                "Số điện thoại",
                "Ngày sinh",
                "Giới tính",
                "Điểm tích lũy",
        });
        tblList.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblList.getTableHeader().setOpaque(false);
        tblList.getTableHeader().setBackground(new Color(32, 136, 203));
        tblList.getTableHeader().setForeground(new Color(255, 255, 255));
        tblList.setRowHeight(25);
        tblList.setModel(tblModel);

        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.white);
        spTable.getViewport().setBackground(Color.white);
    }

    void showDetail(int index) {
        txtMaTV.setEditable(false);
        ThanhVien tv = list.get(index);
        txtMaTV.setText(String.valueOf(tv.getMaTV()));
        txtTenTV.setText(tv.getTenTV());
        txtNgaySinh.setText(XDate.toString(tv.getNgaysinh(), "dd-MM-yyyy"));
        if (tv.isGioitinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
    }

    private void setSelected(int index) {
        try {
            tblList.setRowSelectionInterval(index, index);
            showDetail(index);
        } catch (Exception e) {

        }
    }

    private void fillToTable(List<ThanhVien> lst) {
        tblModel.setRowCount(0);

        for (ThanhVien tv : lst) {
            tblModel.addRow(new Object[]{
                    tv.getTenTV(),
                    tv.getMaTV(),
                    utils.XDate.toString(tv.getNgaysinh(), "dd-MM-yyyy"),
                    tv.isGioitinh() ? "Nam" : "Nữ",
                    tv.getDiem(),
            });
        }
    }

    private void DBFillToList() {
        ThanhVienDAO tvd = new ThanhVienDAO();
        list = (ArrayList<ThanhVien>) tvd.SelectAll();
        fillToTable(list);
    }

    public static boolean isValidDate(String inputDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false); // Không cho phép chấp nhận các giá trị ngày tháng không hợp lệ

        try {
            sdf.parse(inputDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public ThanhVien readForm() {
        ThanhVien tv = new ThanhVien();
//        XDate.toDate(txtNgaySinh.getText(), "dd-MM-yyyy");
        try {

            String ma = txtMaTV.getText();
            String ten = txtTenTV.getText();
            String ngaysinh = txtNgaySinh.getText();
            boolean gioitinh = true;

            boolean isValid = isValidDate(ngaysinh, "dd-MM-yyyy");

            int diem = 0;
            if (ma.trim().length() < 10 || ma.trim().isEmpty() || ten.trim().isEmpty() || ngaysinh.trim().isEmpty()) {
                DialogHelper.alert(this, "Vui lòng nhập đầy đủ thông tin!");
                return null;
            }

            if (!isValid) {
                DialogHelper.alert(this, "Ngày sinh sai định dạng");
                return null;
            }

            if (rdoNu.isSelected()) {
                gioitinh = false;
            }

            tv.setMaTV(Integer.parseInt(ma));
            tv.setTenTV(ten);
            tv.setNgaysinh(XDate.toDate(ngaysinh, "dd-MM-yyyy"));
            tv.setGioitinh(gioitinh);
            tv.setDiem(diem);

        } catch (NumberFormatException e) {

        }
        return tv;
    }

    private void clearForm() {
        txtTenTV.setText("");
        txtMaTV.setText("");
        txtNgaySinh.setText("");
    }

    private void AddTV() {
        ThanhVien tv = readForm();
        ThanhVienDAO tvd = new ThanhVienDAO();
        if (tv != null) {
            for (ThanhVien n : list) {
                if (tv.getMaTV() == n.getMaTV()) {
                    utils.DialogHelper.alert(this, "Số điện thoại đã tồn tại!");
                    return;
                }
            }
            list.add(tv);
            tvd.insert(tv);
            DBFillToList();
            clearForm();
            utils.DialogHelper.alert(this, "Thêm thành viên mới thành công!");
        }
    }

    private void delete(ThanhVien tv) {
        ThanhVienDAO tvd = new ThanhVienDAO();
        tvd.delete(tv.getMaTV());
        list.remove(tv);
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

        grpGender = new javax.swing.ButtonGroup();
        panelBorder1 = new swing.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        txtTenTV = new swing.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        txtMaTV = new swing.TextFieldSuggestion();
        jLabel4 = new javax.swing.JLabel();
        txtNgaySinh = new swing.TextFieldSuggestion();
        jLabel5 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        panelBorder2 = new swing.PanelBorder();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        spTable = new javax.swing.JScrollPane();
        tblList = new swing.Table();

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tên thành viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Số điện thoại/Mã thành viên");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Ngày sinh");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Giới tính");

        grpGender.add(rdoNam);
        rdoNam.setText("Nam");

        grpGender.add(rdoNu);
        rdoNu.setText("Nữ");

        panelBorder2.setBackground(new java.awt.Color(224, 224, 224));

        btnThem.setBackground(new java.awt.Color(153, 204, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 0, 0));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(153, 204, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 0, 0));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(153, 204, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 0, 0));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(153, 204, 255));
        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(0, 0, 0));
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
                panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
                panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(28, Short.MAX_VALUE))
        );

        tblList.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        spTable.setViewportView(tblList);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel2)
                                                        .addComponent(txtTenTV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtMaTV, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                                .addGap(6, 6, 6)
                                                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel5)
                                                                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                                                .addComponent(rdoNam)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(rdoNu)))))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(33, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTenTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtMaTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(rdoNam)
                                                                        .addComponent(rdoNu))))
                                                .addGap(92, 92, 92))
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(76, Short.MAX_VALUE))
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
        AddTV();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            for (ThanhVien tv : list) {
                if (Integer.parseInt(txtMaTV.getText()) == tv.getMaTV()) {
                    boolean choose = utils.DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa thành viên " + tv.getTenTV() + "' không?");
                    if (choose) {
                        delete(tv);
                        setSelected(0);
                        utils.DialogHelper.alert(this, "Xóa thành viên thành công!");
                        if (list.isEmpty()) {
                            clearForm();
                        }
                    } else {
                        return;
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        boolean gioitinh = true;
        for (ThanhVien tv : list) {
            if (Integer.parseInt(txtMaTV.getText()) == tv.getMaTV()) {
                ThanhVienDAO tvd = new ThanhVienDAO();
                tv.setTenTV(txtTenTV.getText());
                tv.setNgaysinh(XDate.toDate(txtNgaySinh.getText(), "dd-MM-yyyy"));
                if (rdoNu.isSelected()) {
                    gioitinh = false;
                }
                tv.setGioitinh(gioitinh);
                tvd.update(tv);
                DBFillToList();
                DialogHelper.alert(this, "Cập nhật thông tin thành công");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        try {
            index = tblList.getSelectedRow();
            int ma = (int) tblList.getValueAt(index, 1);
            ThanhVien tv = new ThanhVienDAO().selectById(ma);
            txtTenTV.setText(tv.getTenTV());
            txtMaTV.setText(String.valueOf(tv.getMaTV()));
            txtNgaySinh.setText(XDate.toString(tv.getNgaysinh(), "dd-MM-yyyy"));
            if (tv.isGioitinh()) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup grpGender;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JScrollPane spTable;
    private swing.Table tblList;
    private swing.TextFieldSuggestion txtMaTV;
    private swing.TextFieldSuggestion txtNgaySinh;
    private swing.TextFieldSuggestion txtTenTV;
    // End of variables declaration//GEN-END:variables
}
