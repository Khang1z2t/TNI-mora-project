/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.TheLoaiDAO;
import entities.TheLoai;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class TheLoaiForm extends javax.swing.JPanel {
    List<TheLoai> listTL = new ArrayList<>();
    DefaultTableModel tblModel;
    int index = 0;
    /**
     * Creates new form FormTemplate
     */
    public TheLoaiForm() {
        initComponents();
       initTable();
        setSelected(0);
        DBFillToList();
    }
    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{
            "Mã Thể loại",
            "Tên thể loại",
        });
        tblList.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblList.getTableHeader().setOpaque(false);
        tblList.getTableHeader().setBackground(new Color(32, 136, 203));
        tblList.getTableHeader().setForeground(new Color(255, 255, 255));
        tblList.setRowHeight(25);
        tblList.setModel(tblModel);
    }
    void previous() {
        try {
            if (index == 0) {
                index = listTL.size();
            }
            index--;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    void next() {
        try {
            if (index == listTL.size() - 1) {
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
            index = listTL.size() - 1;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    void showDetail(int index) {
        txtMaTheLoai.setEditable(false);
        TheLoai tl = listTL.get(index);
        txtMaTheLoai.setText(tl.getMaTheLoai());
        txtTenTheLoai.setText(tl.getTenTheLoai());
    }
    private void fillToTable(List<TheLoai> lst) {
        tblModel.setRowCount(0);
        for (TheLoai st : lst) {
            tblModel.addRow(new Object[]{
                st.getMaTheLoai(),
                st.getTenTheLoai(),
            });
        }
    }

    private void DBFillToList() {
        TheLoaiDAO std = new TheLoaiDAO();
        listTL = std.SelectAll();
        fillToTable(listTL);
    }

    private void setSelected(int index) {
        try {
            tblList.setRowSelectionInterval(index, index);
            showDetail(index);
        } catch (Exception e) {

        }
    }

    private void clearForm() {
        txtMaTheLoai.setEditable(true);
        txtMaTheLoai.setText("");
        txtTenTheLoai.setText("");
   }
    public TheLoai readForm(){
        TheLoai tl = new TheLoai();
        String ma = txtMaTheLoai.getText();
        String ten = txtTenTheLoai.getText();
        
        if(ma.equals("") || ten.equalsIgnoreCase("")){
            utils.DialogHelper.alert(this, "VUI LÒNG NHẬP ĐẦY ĐỦ THÔNG TIN!");
            return null;
        }
        
        tl.setMaTheLoai(ma);
        tl.setTenTheLoai(ten);
        return tl;
    }
    private void addTL() {
        TheLoai tl = readForm();
        TheLoaiDAO tld = new TheLoaiDAO();
        if (tl != null) {
            for (TheLoai t : listTL) {
                if (t.getMaTheLoai().equals(tl.getMaTheLoai())) {
                    utils.DialogHelper.alert(this, "Thể loại đã tồn tại!");
                    return;
                }
            }
            listTL.add(tl);
            tld.insert(tl);
            DBFillToList();
            clearForm();
            utils.DialogHelper.alert(this, "Thêm mới thành công!");
        }
    }
    private void delete(TheLoai tl) {
        TheLoaiDAO tld = new TheLoaiDAO();
        tld.delete(tl);
        listTL.remove(tl);
        index = 0;
        DBFillToList();
    }

    private void update(TheLoai tl) {
        TheLoaiDAO tld = new TheLoaiDAO();
        tl.setTenTheLoai(txtTenTheLoai.getText());
        tld.update(tl);
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

        panelBorder1 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        txtMaTheLoai = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenTheLoai = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new swing.Table();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBorder1.setBackground(new java.awt.Color(229, 229, 229));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MÃ THỂ LOẠI");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("THỂ LOẠI");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(32, 136, 203));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(32, 136, 203));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(32, 136, 203));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(32, 136, 203));
        btnMoi.setText("Làm mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMoi)
                .addContainerGap(34, Short.MAX_VALUE))
        );

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

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pre.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/first.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblList);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenTheLoai)
                                    .addComponent(jLabel3)
                                    .addComponent(txtMaTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        add(panelBorder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addTL();        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            for (TheLoai tl : listTL) {
                if (txtMaTheLoai.getText().equalsIgnoreCase(tl.getMaTheLoai())) {
                    boolean choose = utils.DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa thể loại '" + tl.getTenTheLoai() + "' không?");
                    if (choose) {
                        delete(tl);
                        setSelected(0);
                        utils.DialogHelper.alert(this, "Xóa thông tin thành công!");
                        if (listTL.isEmpty()) {
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
        for (TheLoai tl : listTL) {
            if (txtMaTheLoai.getText().equals(tl.getMaTheLoai())) {
                update(tl);
                utils.DialogHelper.alert(this, "Cập nhật thông tin thành công!");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
        txtMaTheLoai.requestFocus();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        last();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:\
        previous();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.PanelBorder panelBorder1;
    private swing.Table tblList;
    private javax.swing.JTextField txtMaTheLoai;
    private javax.swing.JTextField txtTenTheLoai;
    // End of variables declaration//GEN-END:variables
}
