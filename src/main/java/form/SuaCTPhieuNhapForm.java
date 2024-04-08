package form;

import dao.ChiTietPhieuNhapDAO;
import dao.NhaCungCapDAO;
import dao.PhieuNhapDAO;
import dao.SachDAO;
import entities.ChiTietPhieuNhap;
import entities.NhaCungCap;
import entities.PhieuNhap;
import entities.Sach;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ui.Main;
import ui.ScrollBar;
import utils.Auth;
import utils.DialogHelper;
import utils.MoneyFormat;
import utils.PrintReport;
import utils.XDate;

/**
 * @author ngocd
 */
public class SuaCTPhieuNhapForm extends javax.swing.JPanel {

    SachDAO sachDAO = new SachDAO();
    NhaCungCapDAO nccDAO = new NhaCungCapDAO();
    PhieuNhapDAO pnDAO = new PhieuNhapDAO();
    ChiTietPhieuNhapDAO ctpnDAO = new ChiTietPhieuNhapDAO();
    List<ChiTietPhieuNhap> listCTPN = new ArrayList<>();
    List<ChiTietPhieuNhap> listCTPNOld = new ArrayList<>();

    public SuaCTPhieuNhapForm(PhieuNhap pn) {
        initComponents();
        init(pn);
    }

    private void init(PhieuNhap pn) {
        fillTableSach();
        initTableNhap();
        fillCboNCC();
        setForm(pn);
        tabs.setSelectedIndex(1);
    }

    private void editPhieuNhap() {
        try {
            PhieuNhap pn = getFormPN();
            pnDAO.update(pn);
            // Sửa số lượng trong kho tồn
            for (ChiTietPhieuNhap ctpnOld : listCTPNOld) {
                String maSach = ctpnOld.getMasach();
                ChiTietPhieuNhap ctpn = listCTPN.stream()
                        .filter(x -> x.getMasach().equals(maSach))
                        .findFirst()
                        .orElse(null);

                if (ctpn != null) {
                    int difference = ctpn.getSoluong() - ctpnOld.getSoluong();
                    Sach sach = sachDAO.selectById(maSach);
                    sach.setSoLuong(sach.getSoLuong() + difference);
                    sachDAO.update(sach);
                    ctpnDAO.update(ctpn);
                }
            }

            DialogHelper.alert(this, "Sửa phiếu nhập thành công!");
            Main.Instance.setForm(new KhoForm());
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi nhập hàng!");
            throw new RuntimeException(e);
        }
    }

    private void addSachToPN() {
        try {
            int row = tblSach.getSelectedRow();
            String maNhap = (String) tblSach.getValueAt(row, 0);
            Sach sach = sachDAO.selectById(maNhap);

            String slg = DialogHelper.prompt(this, "Nhập số lượng:");
            if (!checkSL(slg)) {
                return;
            }
            int soLuong = Integer.parseInt(slg);

            ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
            ctpn.setMaNhap(txtMaNhap.getText());
            ctpn.setMasach(sach.getMaSach());
            ctpn.setSoluong(soLuong);
            ctpn.setGia(sach.getGia());

            // Tìm sách trong danh sách
            Optional<ChiTietPhieuNhap> optionalCTPN = listCTPN.stream().filter(x -> x.getMasach().equals(ctpn.getMasach())).findFirst();

            if (optionalCTPN.isPresent()) {
                // Nếu sách đã tồn tại, cập nhật số lượng
                ChiTietPhieuNhap existingCTPN = optionalCTPN.get();
                existingCTPN.setSoluong(existingCTPN.getSoluong() + ctpn.getSoluong());
            } else {
                // Nếu sách không tồn tại, thêm sách vào danh sách
                listCTPN.add(ctpn);
            }

            fillTableNhap();
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi thêm sách vào phiếu nhập!");
            throw new RuntimeException(e);
        }
    }

    private void editSLPN() {
        try {
            int row = tblPhieuNhap.getSelectedRow();

            String slg = DialogHelper.prompt(this, "Nhập số lượng:");
            if (!checkSL(slg)) {
                return;
            }

            int soLuong = Integer.parseInt(slg);
            listCTPN.get(row).setSoluong(soLuong);
            fillTableNhap();
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi sửa số lượng sách trong phiếu nhập!");
            throw new RuntimeException(e);
        }
    }

    private void deleteSachtoPN() {
        try {
            int row = tblPhieuNhap.getSelectedRow();
            listCTPN.remove(row);
            fillTableNhap();
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi xoá sách trong phiếu nhập!");
            throw new RuntimeException(e);
        }
    }

    private void fillTableSach() {
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setColumnIdentifiers(new Object[]{"Mã Sách", "Tên Sách", "Giá", "Số Lượng",});
        model.setRowCount(0);
        try {
            String keyword = txtFindSach.getText();
            List<Sach> list = sachDAO.selectByKeyword(keyword);
            for (Sach sach : list) {
                model.addRow(new Object[]{sach.getMaSach(), sach.getTenSach(), MoneyFormat.format(sach.getGia()), sach.getSoLuong(),});
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
            throw new RuntimeException(e);
        }
        spTblSach.setVerticalScrollBar(new ScrollBar());
        spTblSach.getVerticalScrollBar().setBackground(Color.white);
        spTblSach.getViewport().setBackground(Color.white);
    }

    private void initTableNhap() {
//        DefaultTableModel model = (DefaultTableModel) tblPhieuNhap.getModel();
//        model.setColumnIdentifiers(new Object[]{"STT", "Mã Sách", "Tên Sách", "Số Lượng", "Giá", "Thành Tiền"});
//        model.setRowCount(0);
    }

    private void fillTableNhap() {
        DefaultTableModel model = (DefaultTableModel) tblPhieuNhap.getModel();
        model.setColumnIdentifiers(new Object[]{"STT", "Mã Sách", "Tên Sách", "Số Lượng", "Giá", "Thành Tiền"});
        model.setRowCount(0);
        double tongTien = 0;
        int stt = 1;
        for (ChiTietPhieuNhap ctpn : listCTPN) {
            Sach sach = sachDAO.selectById(ctpn.getMasach());
            double thanhTien = ctpn.getSoluong() * ctpn.getGia();
            tongTien += thanhTien;
            model.addRow(new Object[]{stt++, sach.getMaSach(), sach.getTenSach(), ctpn.getSoluong(), MoneyFormat.format(ctpn.getGia()), MoneyFormat.format(thanhTien)});
        }
        lblTongTien.setText(MoneyFormat.format(tongTien));
        lblTongTien.setToolTipText(String.valueOf(tongTien));
        spTblNhap.setVerticalScrollBar(new ScrollBar());
        spTblNhap.getVerticalScrollBar().setBackground(Color.white);
        spTblNhap.getViewport().setBackground(Color.white);
    }

    private void fillCboNCC() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNcc.getModel();
        model.removeAllElements();
        try {
            List<NhaCungCap> list = nccDAO.selectAll();
            for (NhaCungCap ncc : list) {
                model.addElement(ncc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PhieuNhap getFormPN() {
        PhieuNhap pn = new PhieuNhap();
        pn.setMaNhap(txtMaNhap.getText());
        pn.setNgayNhap(XDate.now());
        pn.setMaNV(Auth.user.getMaNhanVien());
        pn.setMaNhaCC(((NhaCungCap) cboNcc.getSelectedItem()).getMaNhaCC());
        pn.setCTPhieuNhap(listCTPN);
        pn.setTongTien(Double.parseDouble(lblTongTien.getToolTipText()));
        return pn;
    }

    private void setForm(PhieuNhap pn) {
        txtMaNhap.setText(pn.getMaNhap());
        cboNcc.setSelectedItem(nccDAO.selectById(pn.getMaNhaCC()));
        txtMaNV.setText(pn.getMaNV());
        listCTPN = ctpnDAO.selectById(pn.getMaNhap());
        listCTPNOld = ctpnDAO.selectById(pn.getMaNhap());
        fillTableNhap();
    }

    private boolean checkSL(String slg) {
        if (slg == null || slg.isEmpty()) {
            return false;
        }
        if (slg.matches("[a-zA-Z]+")) {
            DialogHelper.alert(this, "Số lượng phải là số!");
            return false;
        }
        if (!slg.matches("\\d+")) {
            DialogHelper.alert(this, "Số lượng phải là số nguyên!");
            return false;
        }
        if (Integer.parseInt(slg) <= 0) {
            DialogHelper.alert(this, "Số lượng phải lớn hơn 0!");
            return false;
        }

        return true;
    }

    private void showPopupSach(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = tblSach.rowAtPoint(e.getPoint());
            int column = tblSach.columnAtPoint(e.getPoint());
            tblSach.getSelectionModel().setSelectionInterval(row, row);
            popupMenuSach.show(tblSach, e.getX(), e.getY());
        }
    }

    private void showPopupNhap(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = tblPhieuNhap.rowAtPoint(e.getPoint());
            int column = tblPhieuNhap.columnAtPoint(e.getPoint());
            tblPhieuNhap.getSelectionModel().setSelectionInterval(row, row);
            popupMenuNhap.show(tblPhieuNhap, e.getX(), e.getY());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuSach = new javax.swing.JPopupMenu();
        pAdd = new javax.swing.JMenuItem();
        popupMenuNhap = new javax.swing.JPopupMenu();
        pEdit = new javax.swing.JMenuItem();
        pDelete = new javax.swing.JMenuItem();
        lblBack = new javax.swing.JLabel();
        lblBack1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtFindSach = new javax.swing.JTextField();
        spTblSach = new javax.swing.JScrollPane();
        tblSach = new swing.Table();
        jPanel1 = new javax.swing.JPanel();
        lblBack2 = new javax.swing.JLabel();
        lblBack3 = new javax.swing.JLabel();
        lblBack4 = new javax.swing.JLabel();
        cboNcc = new swing.ComboBoxSuggestion();
        spTblNhap = new javax.swing.JScrollPane();
        tblPhieuNhap = new swing.Table();
        jLabel1 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtMaNhap = new swing.TextFieldSuggestion();
        txtMaNV = new swing.TextFieldSuggestion();

        pAdd.setText("Thêm vào phiếu nhập");
        pAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pAddActionPerformed(evt);
            }
        });
        popupMenuSach.add(pAdd);

        pEdit.setText("Sửa số lượng");
        pEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pEditActionPerformed(evt);
            }
        });
        popupMenuNhap.add(pEdit);

        pDelete.setText("Xoá khỏi phiếu");
        pDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pDeleteActionPerformed(evt);
            }
        });
        popupMenuNhap.add(pDelete);

        setBackground(new java.awt.Color(255, 255, 255));

        lblBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack.setForeground(new java.awt.Color(0, 0, 0));
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/prevBtn.png"))); // NOI18N
        lblBack.setText("Trở lại");
        lblBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });

        lblBack1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBack1.setForeground(new java.awt.Color(0, 0, 0));
        lblBack1.setText("Sửa Chi Tiết Phiếu Nhập");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtFindSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindSachKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindSach)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSach.setBackground(new java.awt.Color(255, 255, 255));
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
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSachMouseReleased(evt);
            }
        });
        spTblSach.setViewportView(tblSach);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spTblSach, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTblSach, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("THÊM", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblBack2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack2.setForeground(new java.awt.Color(0, 0, 0));
        lblBack2.setText("Mã Phiếu Nhập");

        lblBack3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack3.setForeground(new java.awt.Color(0, 0, 0));
        lblBack3.setText("Nhà Cung Cấp");

        lblBack4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBack4.setForeground(new java.awt.Color(0, 0, 0));
        lblBack4.setText("Người Tạo Phiếu");

        cboNcc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblPhieuNhap.setBackground(new java.awt.Color(255, 255, 255));
        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseReleased(evt);
            }
        });
        spTblNhap.setViewportView(tblPhieuNhap);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tổng Tiền:");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(0, 0, 0));
        lblTongTien.setText("000.000.0000 đ");

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Sửa phiếu");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtMaNhap.setEditable(false);
        txtMaNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMaNV.setEditable(false);
        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTblNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblBack4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBack2)
                            .addComponent(lblBack3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBack2)
                    .addComponent(txtMaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBack3)
                    .addComponent(cboNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBack4)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTblNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(lblTongTien))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("NHẬP HÀNG", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBack)
                .addGap(14, 14, 14))
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBack1)
                    .addComponent(lblBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        Main.Instance.setForm(new PhieuNhapForm());
    }//GEN-LAST:event_lblBackMouseClicked

    private void txtFindSachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindSachKeyReleased
        // TODO add your handling code here:
        fillTableSach();
    }//GEN-LAST:event_txtFindSachKeyReleased

    private void tblSachMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            showPopupSach(evt);
        }
    }//GEN-LAST:event_tblSachMouseReleased

    private void tblPhieuNhapMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            showPopupNhap(evt);
        }
    }//GEN-LAST:event_tblPhieuNhapMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        editPhieuNhap();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pAddActionPerformed
        // TODO add your handling code here:
        addSachToPN();
    }//GEN-LAST:event_pAddActionPerformed

    private void pEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pEditActionPerformed
        // TODO add your handling code here:
        editSLPN();
    }//GEN-LAST:event_pEditActionPerformed

    private void pDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pDeleteActionPerformed
        // TODO add your handling code here:
        deleteSachtoPN();
    }//GEN-LAST:event_pDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ComboBoxSuggestion cboNcc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBack1;
    private javax.swing.JLabel lblBack2;
    private javax.swing.JLabel lblBack3;
    private javax.swing.JLabel lblBack4;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JMenuItem pAdd;
    private javax.swing.JMenuItem pDelete;
    private javax.swing.JMenuItem pEdit;
    private javax.swing.JPopupMenu popupMenuNhap;
    private javax.swing.JPopupMenu popupMenuSach;
    private javax.swing.JScrollPane spTblNhap;
    private javax.swing.JScrollPane spTblSach;
    private javax.swing.JTabbedPane tabs;
    private swing.Table tblPhieuNhap;
    private swing.Table tblSach;
    private javax.swing.JTextField txtFindSach;
    private swing.TextFieldSuggestion txtMaNV;
    private swing.TextFieldSuggestion txtMaNhap;
    // End of variables declaration//GEN-END:variables
}
