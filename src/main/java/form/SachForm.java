/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.SachDAO;
import dao.TacGiaDAO;
import dao.TheLoaiDAO;
import entities.Sach;
import entities.TacGia;
import entities.TheLoai;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import swing.FileChooser;
import utils.XImage;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class SachForm extends javax.swing.JPanel {
    ArrayList<Sach> list = new ArrayList<>();
    DefaultComboBoxModel cboModel;
    int index = -1;
    DefaultTableModel tblModel;
    /**
     * Creates new form FormTemplate
     */
    public SachForm() {
        initComponents();
        initTable();
        DBFillToList();
        setSelected(0);
        initComboBoxTG();
        initComboBoxTL();
        
    }
    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{
            "Mã sách",
            "Tên sách",
            "Năm xb",
            "Nhà Xuất bản",
            "Giá",
            "Tên tác giả",
            "Thể loại",
            "Ghi chú",
            "Hình",
        });
        tblList.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblList.getTableHeader().setOpaque(false);
        tblList.getTableHeader().setBackground(new Color(32, 136, 203));
        tblList.getTableHeader().setForeground(new Color(255, 255, 255));
        tblList.setRowHeight(25);
        tblList.setModel(tblModel);
    }
    private void initComboBoxTL() {
        cboModel = new DefaultComboBoxModel();
        cboModel.removeAllElements();
        List<TheLoai> lst = new TheLoaiDAO().SelectAll();
        for (TheLoai tl : lst) {
            cboModel.addElement(tl);
        }
        cboTheLoai.setModel(cboModel);
    }
    private void initComboBoxTG() {
        cboModel = new DefaultComboBoxModel();
        cboModel.removeAllElements();
        List<TacGia> lst = new TacGiaDAO().SelectAll();
        for (TacGia tg : lst) {
            cboModel.addElement(tg);
        }
        cboTacgia.setModel(cboModel);
    }
    void showDetail(int index) {
        txtMasach.setEditable(false);
        Sach sa = list.get(index);
        txtMasach.setText(sa.getMaSach());
        txtTensach.setText(sa.getTenSach());
        txtNamXB.setText(String.valueOf(sa.getNamXB()));
        txtNhaXB.setText(sa.getNhaXB());
        txtGiasach.setText(String.valueOf(sa.getGia()));
        TacGia selectedTacGia = (TacGia) cboTacgia.getSelectedItem();
        TheLoai selectedTheLoai = (TheLoai) cboTheLoai.getSelectedItem();
        selectedTacGia.setTentg(sa.getTentacgia());
        selectedTheLoai.setTenTheLoai(sa.getTheloai());
        cboTacgia.setSelectedItem(selectedTacGia.getTentg());
        cboTheLoai.setSelectedItem(selectedTheLoai.getTenTheLoai());
        txtGhiChu.setText(sa.getGhiChu());
        if (!sa.getHinh().equals("")) {
            lblHinh.setIcon(XImage.read(sa.getHinh(), lblHinh.getWidth(), lblHinh.getHeight()));
            lblHinh.setToolTipText(sa.getHinh());
        }
    }

    void previous() {
        try {
            if (index == 0) {
                index = list.size();
            }
            index--;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    void next() {
        try {
            if (index == list.size() - 1) {
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
            index = list.size() - 1;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    private void fillToTable(List<Sach> list) {
        tblModel.setRowCount(0);
        for (Sach s : list) {
            tblModel.addRow(new Object[]{
                s.getMaSach(),
                s.getTenSach(),
                s.getNamXB(),
                s.getNhaXB(),
                s.getGia(),
                s.getTentacgia(),
                s.getTheloai(),
                s.getGhiChu(),
                s.getHinh(),
            });
        }
    }
    private void clearform(){
        txtMasach.setText("");
        txtTensach.setText("");
        txtNamXB.setText("");
        txtNhaXB.setText("");
        txtGiasach.setText("");
        cboTheLoai.setSelectedIndex(0);
        cboTheLoai.setSelectedIndex(0);
        txtGhiChu.setText("");
        
        txtMasach.setEditable(true);
        txtMasach.requestFocus();
    }
    public Sach readForm(){
        Sach sa = new Sach();
        String ma = txtMasach.getText();
        String ten = txtTensach.getText();
        int namxb = Integer.parseInt(txtNamXB.getText());
        String nhaxb = txtNhaXB.getText();
        String gia = txtGiasach.getText();
        String tentg = String.valueOf(cboTacgia.getSelectedItem());
        String theloai = String.valueOf(cboTheLoai.getSelectedItem());
        String ghichu = txtGhiChu.getText();
   
        //valid
        if(ma.equalsIgnoreCase("") || ten.equalsIgnoreCase("") || namxb == 0 || gia.equals(0) || ghichu.equalsIgnoreCase("") || tentg.equals("") || theloai.equals("")){
            utils.DialogHelper.alert(this, "Vui lòng nhập đủ thông tin!");
        }
        //
        sa.setMaSach(ma);
        sa.setTenSach(ten);
        sa.setNamXB(namxb);
        sa.setNhaXB(nhaxb);
        sa.setGia(Integer.parseInt(gia));
        sa.setTentacgia(tentg);
        sa.setTheloai(theloai);
        sa.setGhiChu(ghichu);
        sa.setHinh(lblHinh.getToolTipText());
        return sa;
    }
    private void addSach(){
        Sach sa = readForm();
        SachDAO sad = new SachDAO();
        if (sa != null) {
            for (Sach n : list) {
                if (sa.getMaSach().equals((n.getMaSach()))) {
                    utils.DialogHelper.alert(this, "Mã Sách đã tồn tại!");
                    return;
                }
            }
            list.add(sa);
            sad.insert(sa);
            DBFillToList();
            clearform();
            utils.DialogHelper.alert(this, "Thêm Sách mới thành công!");
        }
    }
    private void DBFillToList() {
        SachDAO nvd = new SachDAO();
        list = (ArrayList<Sach>) nvd.SelectAll();
        fillToTable(list);
    }
    private void setSelected(int index) {
        try {
            tblList.setRowSelectionInterval(index, index);
            showDetail(index);
        } catch (Exception e) {

        }
    }

    private void delete(Sach sa) {
        SachDAO sad = new SachDAO();
        sad.delete(sa.getMaSach());
        list.remove(sa);
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
        tabSach = new javax.swing.JTabbedPane();
        tabThongTin = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMasach = new javax.swing.JTextField();
        txtTensach = new javax.swing.JTextField();
        txtGiasach = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNamXB = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNhaXB = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        cboTheLoai = new model.ComboBoxSuggestion();
        cboTacgia = new model.ComboBoxSuggestion();
        tabDanhSach = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(229, 229, 229));

        tabThongTin.setBackground(new java.awt.Color(255, 255, 255));
        tabThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã sách");
        tabThongTin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên sách");
        tabThongTin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 69, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Thể loại");
        tabThongTin.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 128, -1, -1));
        tabThongTin.add(txtMasach, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 171, 33));
        tabThongTin.add(txtTensach, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 95, 171, 27));

        txtGiasach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tabThongTin.add(txtGiasach, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 95, 171, 27));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tác giả");
        tabThongTin.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 128, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Năm xuất bản");
        tabThongTin.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 10, -1, -1));
        tabThongTin.add(txtNamXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 31, 171, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Giá sách");
        tabThongTin.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 69, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 102));
        jLabel8.setText("Ghi Chú");
        tabThongTin.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        tabThongTin.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 286, 360, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bìa Sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 153, 255))); // NOI18N

        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabThongTin.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 6, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Nhà xuất bản");
        tabThongTin.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 191, -1, -1));
        tabThongTin.add(txtNhaXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 217, 171, 31));

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
        tabThongTin.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, 38));

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
        tabThongTin.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 80, 38));

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setMaximumSize(new java.awt.Dimension(88, 26));
        btnSua.setMinimumSize(new java.awt.Dimension(88, 26));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        tabThongTin.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 390, 80, 38));

        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMoi.setText("Làm mới");
        btnMoi.setPreferredSize(new java.awt.Dimension(75, 26));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        tabThongTin.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 89, 38));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/last.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        tabThongTin.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 40, 30));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        tabThongTin.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 47, 30));

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pre.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        tabThongTin.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, 47, 30));

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/first.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        tabThongTin.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, 42, 30));
        tabThongTin.add(cboTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 170, -1));
        tabThongTin.add(cboTacgia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 170, -1));

        tabSach.addTab("CẬP NHẬT", tabThongTin);

        tblList.setModel(new javax.swing.table.DefaultTableModel(
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
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        tabDanhSach.setViewportView(tblList);

        tabSach.addTab("DANH SÁCH", tabDanhSach);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabSach, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addSach();        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            for (Sach sa : list) {
                if (txtMasach.getText().equalsIgnoreCase(sa.getMaSach())) {
                    boolean choose = utils.DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa sách " + sa.getTenSach() + "' không?");
                    if (choose) {
                        delete(sa);
                        setSelected(0);
                        utils.DialogHelper.alert(this, "Xóa thông tin sách thành công!");
                        if (list.isEmpty()) {
                            clearform();
                        }
                    } else {
                        return;
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearform();
        txtMasach.requestFocus();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        for (Sach sa : list) {
            if (txtMasach.getText().equals(sa.getMaSach())) {
                SachDAO sad = new SachDAO();
                sa.setTenSach(txtTensach.getText());
                sa.setGia(Integer.parseInt(txtGiasach.getText()));
                sa.setNamXB(Integer.parseInt(txtGiasach.getText()));
                sa.setNhaXB(txtNhaXB.getText());
                sa.setTentacgia(String.valueOf(cboTacgia.getSelectedItem()));
                sa.setTheloai(String.valueOf(cboTheLoai.getSelectedItem()));
                sa.setGhiChu(txtGhiChu.getText());
                sa.setHinh(lblHinh.getToolTipText());
                sad.update(sa);
                DBFillToList();
                utils.DialogHelper.alert(this, "Thông tin sách đã sửa thành công");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        previous();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        first();        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        FileChooser fileChooser = new FileChooser();
        int res = fileChooser.showOpenDialog(this);
        if (res == FileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            XImage.save(selectedFile);
            ImageIcon icon = XImage.read(selectedFile.getName(), lblHinh.getWidth(), lblHinh.getHeight());
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(selectedFile.getName());
        }
    }//GEN-LAST:event_lblHinhMouseClicked

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        try {
            index = tblList.getSelectedRow();
            String ma = (String) tblList.getValueAt(index, 0);
            Sach s = new SachDAO().selectById(ma);
            txtMasach.setText(s.getMaSach());
            txtTensach.setText(s.getTenSach());
            txtNhaXB.setText(s.getNhaXB());
            txtNamXB.setText(String.valueOf(s.getNamXB()));
            txtGiasach.setText(String.valueOf(s.getGia()));
            txtGhiChu.setText(s.getGhiChu());
            TacGia selectedTacGia = (TacGia) cboTacgia.getSelectedItem();
            TheLoai selectedTheLoai = (TheLoai) cboTheLoai.getSelectedItem();
            selectedTacGia.setTentg(s.getTentacgia());
            selectedTheLoai.setTenTheLoai(s.getTheloai());
            cboTacgia.setSelectedItem(selectedTacGia.getTentg());
            cboTheLoai.setSelectedItem(selectedTheLoai.getTenTheLoai());
            if (!s.getHinh().equals("")) {
                lblHinh.setIcon(XImage.read(s.getHinh(), lblHinh.getWidth(), lblHinh.getHeight()));
                lblHinh.setToolTipText(s.getHinh());
            }
            tabSach.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private model.ComboBoxSuggestion cboTacgia;
    private model.ComboBoxSuggestion cboTheLoai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane tabDanhSach;
    private javax.swing.JTabbedPane tabSach;
    private javax.swing.JPanel tabThongTin;
    private javax.swing.JTable tblList;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiasach;
    private javax.swing.JTextField txtMasach;
    private javax.swing.JTextField txtNamXB;
    private javax.swing.JTextField txtNhaXB;
    private javax.swing.JTextField txtTensach;
    // End of variables declaration//GEN-END:variables
}
