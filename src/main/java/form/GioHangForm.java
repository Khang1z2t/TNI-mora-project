/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.*;
import entities.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import ui.Main;
import ui.ScrollBar;
import utils.Auth;
import utils.DialogHelper;
import utils.MoneyFormat;
import utils.XDate;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class GioHangForm extends javax.swing.JPanel {

    SachDAO sachDAO = new SachDAO();
    GiohangDAO ghDAO = new GiohangDAO();
    ThanhVienDAO tvDAO = new ThanhVienDAO();
    HoadonDAO hdDAO = new HoadonDAO();
    ChiTietHoaDonDAO cthdDAO = new ChiTietHoaDonDAO();

    ArrayList<Giohang> listGH = new ArrayList<>();
    ArrayList<ThanhVien> ist = new ArrayList<>();
    List<ChiTietHoaDon> listCTHD = new ArrayList<>();

    int index = -1;
    DefaultTableModel tblModel;
    DefaultComboBoxModel cbxModel;

    public GioHangForm() {
        initComponents();
        initCBX();
        time();
        DBToListGH();
        txtNV.setText(utils.Auth.user.getMaNhanVien() + "-" + utils.Auth.user.getHoVaTen());
        lblUser.setText(utils.Auth.user.getMaNhanVien() + "-" + utils.Auth.user.getHoVaTen());
        initTable();
        if (lblTien.getText().equals("NULL")) {
            lblTien.setText("0");
        }
        txtNV.enableInputMethods(false);

        //TAB 2 - Thanh Toan
        initPTTT();
        initSetTextTab2();
        DBtoListTV();
        CancelGD();

        //TA3 - Hóa Đơn
        fillTableHD();
    }

    private void time() {
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblTime.setText(XDate.toString(XDate.now(), "hh:mm a"));
            }
        }).start();
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
            "Giá",});
        tblGioHang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblGioHang.getTableHeader().setOpaque(false);
        tblGioHang.getTableHeader().setBackground(new Color(32, 136, 203));
        tblGioHang.getTableHeader().setForeground(new Color(255, 255, 255));
        tblGioHang.setRowHeight(25);
        tblGioHang.setModel(tblModel);
    }

    private void initCBX() {
        cbxModel = new DefaultComboBoxModel();
        cbxModel.removeAllElements();
        List<Sach> lst = sachDAO.SelectAll();
        for (Sach sach : lst) {
            cbxModel.addElement(sach);
        }
        cbxSach.setModel(cbxModel);
    }

    private void DBToListGH() {
        listGH = (ArrayList<Giohang>) ghDAO.SelectAll();
    }

    public Giohang readForm() {
        Sach sa = (Sach) cbxSach.getSelectedItem();
        Giohang gh = new Giohang();

        try {
            int soluong = Integer.parseInt(txtSoLuong.getText());

            if (soluong <= 0) {
                utils.DialogHelper.alert(this, "Số lượng phải lớn hơn hoặc bằng 1");
                return null;
            }

            gh.setSoluong(soluong);

        } catch (NumberFormatException e) {
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
        for (int i = 0; i < listGH.size(); i++) {
            Giohang gh = listGH.get(i);
            tblModel.addRow(new Object[]{
                i + 1,
                gh.getTensach(),
                gh.getSoluong(),
                MoneyFormat.format(gh.getGia()),});
        }
    }

    //thuat thuc tinh tong tien qua java:test 21/3/24
    private void tinhTien() {
        int tong = 0;
        for (Giohang it : listGH) {
            tong += it.getGia() * it.getSoluong();
        }
        lblTien.setText(MoneyFormat.format(tong));
    }

    private void addCart() {
        Giohang gh = readForm();
        if (gh != null) {
            //Neu them hang ma trung ten thi cap nhat so luong
            boolean ex = false;
            for (Giohang it : listGH) {
                if (it.getMasach().equalsIgnoreCase(gh.getMasach())) {
                    int newSL = it.getSoluong() + gh.getSoluong();
                    it.setSoluong(newSL);
                    ghDAO.update(it);
                    ex = true;
                    break;
                    //end
                }
            }
            if (!ex) {
                listGH.add(gh);
                ghDAO.insert(gh);
            }
            fillToTable();
            //cap nhat hien thi
            tinhTien();
            //tinh tong tien,setText
            txtSoLuong.setText("");
        }
    }

    private void deleteGioHang() {
        try {
            int selectedRow = tblGioHang.getSelectedRow();
            if (selectedRow != -1) {
                Giohang gh = listGH.get(selectedRow);
                String masach = gh.getMasach(); // Lấy mã giỏ hàng của mục được chọn
                ghDAO.delete(masach); // Xóa mục từ cơ sở dữ liệu
                listGH.remove(selectedRow); // Xóa mục từ danh sách
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
    }

    private void editSLGioHang() {
        try {
            int selectedRow = tblGioHang.getSelectedRow();
            Giohang gh = listGH.get(selectedRow);
            String maSach = gh.getMasach();
            String slg = DialogHelper.prompt(this, "Nhập số lượng:");
            if (!checkSL(slg)) {
                return;
            }
            int soluong = Integer.parseInt(slg);

            ghDAO.selectbyMasach(maSach).setSoluong(soluong);
            DialogHelper.alert(this, "Đụ á xàm lòn zc hàm setSoLuong éo chạy mọe nó "
                    + "\nMã Sách: " + maSach + " - Số lượng mới: " + soluong + " - Số lượng cũ: " + gh.getSoluong());
            ghDAO.update(gh);
            fillToTable();
        } catch (Exception e) {
            utils.DialogHelper.alert(this, "Lỗi: " + e.getMessage());
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuHoaDon = new javax.swing.JPopupMenu();
        pView = new javax.swing.JMenuItem();
        popupMenuGioHang = new javax.swing.JPopupMenu();
        pEdit = new javax.swing.JMenuItem();
        pDelete = new javax.swing.JMenuItem();
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
        tblGioHang = new swing.Table();
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
        btnHuy = new swing.ButtonSuggestion();
        jLabel7 = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        panelBorder5 = new swing.PanelBorder();
        jPanel3 = new javax.swing.JPanel();
        txtFindPhieuNhap = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtTuNgay = new com.toedter.calendar.JDateChooser();
        txtDenNgay = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        txtTuGia = new javax.swing.JTextField();
        txtDenGia = new javax.swing.JTextField();
        btnExcel = new javax.swing.JButton();
        spTblHoaDon = new javax.swing.JScrollPane();
        tblHoaDon = new swing.Table();

        pView.setText("Xem Chi Tiết");
        pView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pViewActionPerformed(evt);
            }
        });
        popupMenuHoaDon.add(pView);

        pEdit.setText("Sửa số lượng");
        pEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pEditActionPerformed(evt);
            }
        });
        popupMenuGioHang.add(pEdit);

        pDelete.setText("Xóa khỏi giỏ hàng");
        pDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pDeleteActionPerformed(evt);
            }
        });
        popupMenuGioHang.add(pDelete);

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setMaximumSize(new java.awt.Dimension(698, 594));
        panelBorder1.setMinimumSize(new java.awt.Dimension(698, 594));
        panelBorder1.setPreferredSize(new java.awt.Dimension(698, 594));

        tab.setForeground(new java.awt.Color(0, 0, 0));
        tab.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        tab.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("HÀNG");
        panelBorder2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 18, -1, -1));

        txtNV.setBackground(new java.awt.Color(255, 255, 255));
        txtNV.setEnabled(false);
        txtNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNV.setLabelText("Nhân viên thực hiện giao dịch");
        panelBorder2.add(txtNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 51, 307, -1));

        btnThem.setBackground(new java.awt.Color(153, 153, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 0, 0));
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        panelBorder2.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 15, -1, -1));

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(0, 0, 0));
        lblTongTien.setText("TỔNG TIỀN :");
        panelBorder2.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, -1, -1));

        lblTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTien.setForeground(new java.awt.Color(255, 153, 102));
        lblTien.setText("NULL");
        panelBorder2.add(lblTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, -1, -1));

        btnXoa.setBackground(new java.awt.Color(153, 153, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 0, 0));
        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        panelBorder2.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 51, -1, -1));

        txtSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoLuong.setLabelText("Số lượng");
        panelBorder2.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 103, 307, -1));
        panelBorder2.add(cbxSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 13, 247, -1));

        lblThongBao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelBorder2.add(lblThongBao, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 270, 25));

        txtMaTV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaTV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMaTV.setLabelText("Mã thành viên");
        txtMaTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTVActionPerformed(evt);
            }
        });
        panelBorder2.add(txtMaTV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 155, 307, -1));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblGioHangMouseReleased(evt);
            }
        });
        spPanel.setViewportView(tblGioHang);

        panelBorder2.add(spPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 223, 570, 230));

        btnThanhToan.setBackground(new java.awt.Color(153, 153, 255));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(0, 0, 0));
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        panelBorder2.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 180, 37));

        tab.addTab("Giỏ hàng", new javax.swing.ImageIcon(getClass().getResource("/icon/cart.png")), panelBorder2); // NOI18N

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(lblTime)
                .addGap(31, 31, 31))
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(lblTime))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("BẢNG GIÁ VÀ SẢN PHẨM");

        txtTongTien.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Hủy thanh toán");

        txtThanhVien.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("KHÁCH HÀNG");

        cbxHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxHangActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("TỔNG TIỀN HÀNG");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("TỔNG GIẢM GIÁ");

        txtTongGiamGia1.setEnabled(false);

        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnHuy.setBorderColor(new java.awt.Color(255, 255, 255));
        btnHuy.setColorClick(new java.awt.Color(204, 204, 204));
        btnHuy.setColorOver(new java.awt.Color(102, 102, 255));
        btnHuy.setName("Hủy giao dịch"); // NOI18N
        btnHuy.setRadius(50);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("PHƯƠNG THỨC THANH TOÁN");

        btnConfirm.setBackground(new java.awt.Color(153, 153, 255));
        btnConfirm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(0, 0, 0));
        btnConfirm.setText("XÁC NHẬN THANH TOÁN");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(13, 13, 13)
                        .addComponent(cbxHang, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)
                        .addGap(15, 15, 15)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6)
                        .addGap(25, 25, 25)
                        .addComponent(txtTongGiamGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addGap(38, 38, 38)
                        .addComponent(txtThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3))
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7)
                        .addGap(15, 15, 15)
                        .addComponent(cbxThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btnConfirm)))
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(cbxHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addComponent(txtTongGiamGia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4))
                    .addComponent(txtThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)))
                .addGap(4, 4, 4)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7))
                    .addComponent(cbxThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        tab.addTab("Thanh Toán", new javax.swing.ImageIcon(getClass().getResource("/icon/buy.png")), panelBorder3); // NOI18N

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtFindPhieuNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindPhieuNhapKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFindPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtFindPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Theo ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtTuNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTuNgayPropertyChange(evt);
            }
        });

        txtDenNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDenNgayPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Theo giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtTuGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTuGiaKeyReleased(evt);
            }
        });

        txtDenGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDenGiaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTuGia, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDenGia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTuGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDenGia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExcel.setBackground(new java.awt.Color(153, 153, 255));
        btnExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExcel.setForeground(new java.awt.Color(0, 0, 0));
        btnExcel.setText("Xuất Excel");
        btnExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseReleased(evt);
            }
        });
        spTblHoaDon.setViewportView(tblHoaDon);

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTblHoaDon)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExcel)))
                .addContainerGap())
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTblHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcel))
        );

        tab.addTab("Hóa đơn", new javax.swing.ImageIcon(getClass().getResource("/icon/Hoadon.png")), panelBorder5); // NOI18N

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        deleteGioHang();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        thanhvien();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtMaTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTVActionPerformed

    private void cbxHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxHangActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        CancelGD();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
//        Giohang gh = new Giohang();
        if (listGH.isEmpty()) {
            DialogHelper.alert(this, "Vui lòng chọn sản phẩm");
            return;
        }
        int newSL;
        for (Giohang gh : listGH) {
            Sach sa = sachDAO.selectById(gh.getMasach());
            if (sa.getSoLuong() <= 0) {
                DialogHelper.alert(this, "Sách" + sa.getTenSach() + " không còn hàng!");
                return;
            }
            newSL = sa.getSoLuong() - gh.getSoluong();
            sa.setSoLuong(newSL);
            sachDAO.update(sa);
            String pttt = (String) cbxThanhToan.getSelectedItem();
            if (pttt.equalsIgnoreCase("tiền mặt") || pttt.equalsIgnoreCase("Thẻ")
                    || pttt.equalsIgnoreCase("VNPAY") || pttt.equalsIgnoreCase("MOMO")) {
                lblThongBao.setText("Thanh toán thành công");
                lblThongBao.setForeground(Color.green);
                tab.setSelectedIndex(0);
            }
        }
        //add Hóa đơn
        Hoadon hd = getFormHD();
        addSPToHDCT(hd);
        hdDAO.insert(hd);
        for (ChiTietHoaDon cthd : listCTHD) {
            cthdDAO.insert(cthd);
        }
        // Thêm Timer
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện khi đến thời gian quy định
                Main.Instance.setForm(new GioHangForm());
                ghDAO.reset();
            }
        });
        timer.setRepeats(false); // Chỉ chạy một lần
        timer.start(); // Bắt đầu đếm thời gian
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void txtFindPhieuNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindPhieuNhapKeyReleased
        // TODO add your handling code here:
//        fillTableSach();
    }//GEN-LAST:event_txtFindPhieuNhapKeyReleased

    private void txtTuNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTuNgayPropertyChange
        // TODO add your handling code here:
//        fillTableSach();
    }//GEN-LAST:event_txtTuNgayPropertyChange

    private void txtDenNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDenNgayPropertyChange
        // TODO add your handling code here:
//        fillTableSach();
    }//GEN-LAST:event_txtDenNgayPropertyChange

    private void txtTuGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuGiaKeyReleased
        // TODO add your handling code here:
//        fillTableSach();
    }//GEN-LAST:event_txtTuGiaKeyReleased

    private void txtDenGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenGiaKeyReleased
        // TODO add your handling code here:
//        fillTableSach();
    }//GEN-LAST:event_txtDenGiaKeyReleased

    private void tblGioHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            showPopupGioHang(evt);
        }
    }//GEN-LAST:event_tblGioHangMouseReleased

    private void tblHoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            showPopupHoaDon(evt);
        }
    }//GEN-LAST:event_tblHoaDonMouseReleased

    private void pEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pEditActionPerformed
        // TODO add your handling code here:
        editSLGioHang();
    }//GEN-LAST:event_pEditActionPerformed

    private void pDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pDeleteActionPerformed
        // TODO add your handling code here:
        deleteGioHang();
    }//GEN-LAST:event_pDeleteActionPerformed

    private void pViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pViewActionPerformed
        // TODO add your handling code here:
        XemCTHoaDon();
    }//GEN-LAST:event_pViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnExcel;
    private swing.ButtonSuggestion btnHuy;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblTien;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblUser;
    private javax.swing.JMenuItem pDelete;
    private javax.swing.JMenuItem pEdit;
    private javax.swing.JMenuItem pView;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private swing.PanelBorder panelBorder3;
    private swing.PanelBorder panelBorder4;
    private swing.PanelBorder panelBorder5;
    private javax.swing.JPopupMenu popupMenuGioHang;
    private javax.swing.JPopupMenu popupMenuHoaDon;
    private javax.swing.JScrollPane spPanel;
    private javax.swing.JScrollPane spTblHoaDon;
    private swing.MaterialTabbed tab;
    private swing.Table tblGioHang;
    private swing.Table tblHoaDon;
    private javax.swing.JTextField txtDenGia;
    private com.toedter.calendar.JDateChooser txtDenNgay;
    private javax.swing.JTextField txtFindPhieuNhap;
    private swing.TextField txtMaTV;
    private swing.TextField txtNV;
    private swing.TextField txtSoLuong;
    private swing.TextFieldSuggestion txtThanhVien;
    private swing.TextFieldSuggestion txtTongGiamGia1;
    private swing.TextFieldSuggestion txtTongTien;
    private javax.swing.JTextField txtTuGia;
    private com.toedter.calendar.JDateChooser txtTuNgay;
    // End of variables declaration//GEN-END:variables

    //TAB 2 - THANH TOAN
    private void initCBXGioHang() {
        cbxModel = new DefaultComboBoxModel();
        cbxModel.removeAllElements();
        List<Giohang> lst = new GiohangDAO().SelectAll();
        for (Giohang gh : lst) {
            cbxModel.addElement(gh);
        }
        cbxHang.setModel(cbxModel);
    }

    private void initPTTT() {
        String[] phuongThucThanhToan = {"Tiền mặt", "Thẻ", "Momo", "VNPay"};
        cbxThanhToan.setModel(new DefaultComboBoxModel(phuongThucThanhToan));
    }

    private void initSetTextTab2() {
        int tong = 0;
        for (Giohang it : listGH) {
            tong += it.getGia() * it.getSoluong();
        }
        txtTongTien.setText(MoneyFormat.format(tong));
        txtTongTien.setToolTipText(String.valueOf(tong));
    }

    private void DBtoListTV() {
        ist = (ArrayList<ThanhVien>) tvDAO.SelectAll();
    }

    private void thanhvien() {
        if (listGH.isEmpty()) {
            DialogHelper.alert(this, "Vui lòng thêm sản phẩm vào giỏ hàng");
            return;
        }
        try {
            Giohang gh = new Giohang();
            String ma = txtMaTV.getText();
            for (ThanhVien tv : ist) {
                if (ma.trim().length() == tv.getMaTV()) {
                    gh.setMaTV(tv.getMaTV());
                    txtThanhVien.setText(tv.getTenTV());
                    tab.setSelectedIndex(1);
                    break;
                } else if (ma.trim().isEmpty()) {
                    txtThanhVien.setText("Không tích điểm");
                    tab.setSelectedIndex(1);
                    break;
                } else if (ma.trim().length() != tv.getMaTV()) {
                    utils.DialogHelper.alert(this, "Thành viên này không tồn tại!");
                    break;
                }
            }
            initCBXGioHang();
            initSetTextTab2();
        } catch (NumberFormatException e) {
            System.out.println("Lỗi số");
        }
    }

    private void DBFilltoTable() {
        listGH = (ArrayList<Giohang>) ghDAO.SelectAll();
        fillToTable();
    }

    private void CancelGD() {
        Giohang gh = new Giohang();
        ghDAO.reset();
        listGH.remove(gh);
        DBFilltoTable();
        tab.setSelectedIndex(0);
    }

    //Hoa don
    private String createID(List<Hoadon> hdList) {
        int id = hdList.size() + 1;
        String checkID = "";
        for (Hoadon hd : hdList) {
            if (hd.getMaHoaDon().equals("HD" + id)) {
                checkID = hd.getMaHoaDon();
            }
        }
        while (!checkID.isEmpty()) {
            String check = checkID;
            id++;
            for (Hoadon hoadon : hdList) {
                if (hoadon.getMaHoaDon().equals("HD" + id)) {
                    checkID = hoadon.getMaHoaDon();
                }
            }
            if (check.equals(checkID)) {
                checkID = "";
            }
        }
        return "HD" + id;
    }

    private Hoadon getFormHD() {
        Hoadon hd = new Hoadon();
        hd.setMaHoaDon(createID(hdDAO.selectAll()));
        hd.setNgayTao(XDate.now());
        hd.setMaNV(Auth.user.getMaNhanVien());
        if (txtMaTV.getText().isEmpty()) {
            hd.setMaTV(0);
        } else {
            hd.setMaTV(Integer.parseInt(txtMaTV.getText()));
        }
        hd.setTongTien(Integer.parseInt(txtTongTien.getToolTipText()));
        return hd;
    }

    private void addSPToHDCT(Hoadon hd) {
        for (Giohang gh : listGH) {
            ChiTietHoaDon cthd = new ChiTietHoaDon();
            cthd.setMaHoaDon(hd.getMaHoaDon());
            cthd.setMaSach(gh.getMasach());
            cthd.setSoLuong(gh.getSoluong());
            cthd.setDonGia(gh.getGia());
            listCTHD.add(cthd);
        }
    }

    private void fillTableHD() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setColumnIdentifiers(new Object[]{
            "Mã hóa đơn", "Nguời tạo", "Mã thành viên", "Ngày tạo", "Tổng tiền"
        });
        model.setRowCount(0);
        for (Hoadon hd : hdDAO.selectAll()) {
            Object[] row = new Object[]{
                hd.getMaHoaDon(),
                hd.getMaNV(),
                hd.getMaTV(),
                XDate.toString(hd.getNgayTao(), "dd/MM/yyyy hh:mm:ss"),
                MoneyFormat.format(hd.getTongTien())
            };
            model.addRow(row);
        }
        spTblHoaDon.setVerticalScrollBar(new ScrollBar());
        spTblHoaDon.getVerticalScrollBar().setBackground(Color.white);
        spTblHoaDon.getViewport().setBackground(Color.white);
    }
    
    private void XemCTHoaDon() {
        String maHD = (String) tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0);
        Hoadon hd = hdDAO.selectById(maHD);
        Main.Instance.setForm(new CTHoaDonForm(hd));
    }

    private void showPopupGioHang(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = tblGioHang.rowAtPoint(e.getPoint());
            int column = tblGioHang.columnAtPoint(e.getPoint());
            tblGioHang.getSelectionModel().setSelectionInterval(row, row);
            popupMenuGioHang.show(tblGioHang, e.getX(), e.getY());
        }
    }

    private void showPopupHoaDon(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            int row = tblHoaDon.rowAtPoint(e.getPoint());
            int column = tblHoaDon.columnAtPoint(e.getPoint());
            tblHoaDon.getSelectionModel().setSelectionInterval(row, row);
            popupMenuHoaDon.show(tblHoaDon, e.getX(), e.getY());
        }
    }
}
