package form;

import dao.NhaCungCapDAO;
import entities.NhaCungCap;
import ui.ScrollBar;
import utils.DialogHelper;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * @author ngocd
 */
public class NhaCungCapForm extends javax.swing.JPanel {

    NhaCungCapDAO nccDAO = new NhaCungCapDAO();
    int index = -1;

    public NhaCungCapForm() {
        initComponents();
        init();
    }

    private void init() {
        fillTable();
        tabs.setSelectedIndex(1);
    }

    private void addNCC() {
        String maNCC = txtMaNCC.getText();
        if (nccDAO.selectById(maNCC) != null) {
            DialogHelper.alert(this, "Mã NCC đã tồn tại");
        } else {
            NhaCungCap ncc = getForm();
            try {
                nccDAO.insert(ncc);
                fillTable();
                clearForm();
                DialogHelper.alert(this, "Thêm mới thành công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Thêm mới thất bại");
                throw new RuntimeException(e);
            }
        }

    }

    private void updateNCC() {
        NhaCungCap ncc = getForm();
        try {
            nccDAO.update(ncc);
            fillTable();
            DialogHelper.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại");
            throw new RuntimeException(e);
        }
    }

    private void deleteNCC() {
        if (DialogHelper.confirm(this, "Bạn có muốn xóa nhà cung cấp này không?")) {
            String maNCC;
            if (tabs.getSelectedIndex() == 1) {
                maNCC = (String) tblNhaCungCap.getValueAt(index, 0);
            } else {
                maNCC = txtMaNCC.getText();
            }
            try {
                nccDAO.delete(maNCC);
                fillTable();
                clearForm();
                DialogHelper.alert(this, "Xóa thành công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại");
                throw new RuntimeException(e);
            }
        }
    }

    private void findNCC() {
        this.fillTable();
        this.clearForm();
        this.index = -1;
        this.updateStatus();
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhaCungCap.getModel();
        model.setColumnIdentifiers(new String[]{
            "Mã NCC", "Tên NCC", "Số điện thoại", "Địa chỉ"
        });
        model.setRowCount(0);
        try {
            String keyword = txtFindNCC.getText();
            List<NhaCungCap> list = nccDAO.selectByKey(keyword);
            for (NhaCungCap ncc : list) {
                model.addRow(new Object[]{
                    ncc.getMaNhaCC(),
                    ncc.getTenNhaCC(),
                    ncc.getSDT(),
                    ncc.getDiaChi()
                });
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
            throw new RuntimeException(e);
        }
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.white);
        spTable.getViewport().setBackground(Color.white);
    }

    private void setForm(NhaCungCap ncc) {
        txtMaNCC.setText(ncc.getMaNhaCC());
        txtTenNCC.setText(ncc.getTenNhaCC());
        txtSDT.setText(ncc.getSDT());
        txtDiaChi.setText(ncc.getDiaChi());
    }

    private NhaCungCap getForm() {
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNhaCC(txtMaNCC.getText());
        ncc.setTenNhaCC(txtTenNCC.getText());
        ncc.setSDT(txtSDT.getText());
        ncc.setDiaChi(txtDiaChi.getText());
        return ncc;
    }

    private void clearForm() {
        NhaCungCap ncc = new NhaCungCap();
        this.setForm(ncc);
        this.index = -1;
        this.updateStatus();
    }

    private void first() {
        this.index = 0;
        this.edit();
    }

    private void previous() {
        if (this.index > 0) {
            this.index--;
            this.edit();
        }
    }

    private void next() {
        if (this.index < tblNhaCungCap.getRowCount() - 1) {
            this.index++;
            this.edit();
        }
    }

    private void last() {
        this.index = tblNhaCungCap.getRowCount() - 1;
        this.edit();
    }

    private void edit() {
        String id = (String) tblNhaCungCap.getValueAt(this.index, 0);
        NhaCungCap ncc = nccDAO.selectById(id);
        this.setForm(ncc);
        tabs.setSelectedIndex(0);
        this.updateStatus();
    }

    private void updateStatus() {
        boolean edit = (this.index >= 0);
        boolean first = (this.index == 0);
        boolean last = (this.index == tblNhaCungCap.getRowCount() - 1);

        //form state
        txtMaNCC.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

        //Directional state
        btnPre.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnFirst.setEnabled(edit && !first);
        btnLast.setEnabled(edit && !last);

    }

    private void showPopup(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = tblNhaCungCap.rowAtPoint(e.getPoint());
            int column = tblNhaCungCap.columnAtPoint(e.getPoint());
            tblNhaCungCap.getSelectionModel().setSelectionInterval(row, row);
            popupMenu.show(tblNhaCungCap, e.getX(), e.getY());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        pAdd = new javax.swing.JMenuItem();
        pUpdate = new javax.swing.JMenuItem();
        pDelete = new javax.swing.JMenuItem();
        panelBorder1 = new swing.PanelBorder();
        tabs = new javax.swing.JTabbedPane();
        tabThongTin = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        txtMaNCC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        tabDanhSach = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblNhaCungCap = new swing.Table();
        jPanel1 = new javax.swing.JPanel();
        txtFindNCC = new javax.swing.JTextField();

        popupMenu.setBackground(new java.awt.Color(242, 242, 242));
        popupMenu.setOpaque(false);

        pAdd.setBackground(new java.awt.Color(242, 242, 242));
        pAdd.setText("Thêm");
        pAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pAddActionPerformed(evt);
            }
        });
        popupMenu.add(pAdd);

        pUpdate.setBackground(new java.awt.Color(242, 242, 242));
        pUpdate.setText("Sửa");
        pUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pUpdateActionPerformed(evt);
            }
        });
        popupMenu.add(pUpdate);

        pDelete.setBackground(new java.awt.Color(242, 242, 242));
        pDelete.setText("Xóa");
        pDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pDeleteActionPerformed(evt);
            }
        });
        popupMenu.add(pDelete);

        setBackground(new java.awt.Color(229, 229, 229));

        panelBorder1.setBackground(new java.awt.Color(229, 229, 229));

        tabThongTin.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã Nhà Cung Cấp");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên Nhà Cung Cấp");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Địa Chỉ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Số điện thoại");

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setMaximumSize(new java.awt.Dimension(88, 26));
        btnThem.setMinimumSize(new java.awt.Dimension(88, 26));
        btnThem.setPreferredSize(new java.awt.Dimension(88, 26));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setMaximumSize(new java.awt.Dimension(88, 26));
        btnXoa.setMinimumSize(new java.awt.Dimension(88, 26));
        btnXoa.setPreferredSize(new java.awt.Dimension(88, 26));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setMaximumSize(new java.awt.Dimension(88, 26));
        btnSua.setMinimumSize(new java.awt.Dimension(88, 26));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMoi.setText("Làm mới");
        btnMoi.setPreferredSize(new java.awt.Dimension(75, 26));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
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

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        javax.swing.GroupLayout tabThongTinLayout = new javax.swing.GroupLayout(tabThongTin);
        tabThongTin.setLayout(tabThongTinLayout);
        tabThongTinLayout.setHorizontalGroup(
            tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabThongTinLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabThongTinLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabThongTinLayout.createSequentialGroup()
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSDT)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(tabThongTinLayout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(txtTenNCC)
                            .addComponent(txtMaNCC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 37, Short.MAX_VALUE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(31, Short.MAX_VALUE))))
        );
        tabThongTinLayout.setVerticalGroup(
            tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabThongTinLayout.createSequentialGroup()
                .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabThongTinLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLast, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFirst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(tabThongTinLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(btnMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        tabs.addTab("CẬP NHẬT", tabThongTin);

        tabDanhSach.setBackground(new java.awt.Color(229, 229, 229));

        tblNhaCungCap.setBackground(new java.awt.Color(229, 229, 229));
        tblNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblNhaCungCapMouseReleased(evt);
            }
        });
        spTable.setViewportView(tblNhaCungCap);

        jPanel1.setBackground(new java.awt.Color(229, 229, 229));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtFindNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindNCCKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindNCC)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtFindNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabDanhSachLayout = new javax.swing.GroupLayout(tabDanhSach);
        tabDanhSach.setLayout(tabDanhSachLayout);
        tabDanhSachLayout.setHorizontalGroup(
            tabDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabDanhSachLayout.setVerticalGroup(
            tabDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("DANH SÁCH", tabDanhSach);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteNCC();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateNCC();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        previous();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addNCC();
    }//GEN-LAST:event_btnThemActionPerformed

    private void pAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pAddActionPerformed
        // TODO add your handling code here:
        tabs.setSelectedIndex(0);
        clearForm();
    }//GEN-LAST:event_pAddActionPerformed

    private void pUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pUpdateActionPerformed
        // TODO add your handling code here:
        this.index = tblNhaCungCap.getSelectedRow();
        if (this.index >= 0) {
            this.edit();
        }
    }//GEN-LAST:event_pUpdateActionPerformed

    private void pDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pDeleteActionPerformed
        // TODO add your handling code here:
        this.index = tblNhaCungCap.getSelectedRow();
        if (this.index >= 0) {
            deleteNCC();
        }
    }//GEN-LAST:event_pDeleteActionPerformed

    private void tblNhaCungCapMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhaCungCapMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            showPopup(evt);
        }
    }//GEN-LAST:event_tblNhaCungCapMouseReleased

    private void txtFindNCCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindNCCKeyReleased
        // TODO add your handling code here:
        findNCC();
    }//GEN-LAST:event_txtFindNCCKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem pAdd;
    private javax.swing.JMenuItem pDelete;
    private javax.swing.JMenuItem pUpdate;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JPanel tabDanhSach;
    private javax.swing.JPanel tabThongTin;
    private javax.swing.JTabbedPane tabs;
    private swing.Table tblNhaCungCap;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtFindNCC;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNCC;
    // End of variables declaration//GEN-END:variables

}
