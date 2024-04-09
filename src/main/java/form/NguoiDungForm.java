/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.LuongDAO;
import dao.NguoiDungDAO;
import dao.NhanVienDAO;
import entities.Luong;
import entities.NguoiDung;
import entities.NhanVien;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import ui.Main;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class NguoiDungForm extends javax.swing.JPanel {

    DefaultTableModel tblModel;
    DefaultComboBoxModel cboModel;
    int index = -1;
    List<NguoiDung> listND = new ArrayList<>();
    String path = null;

    public NguoiDungForm() {
        initComponents();
        initTable();
        initCBXLuong();
        DBFillToList();
        setSelected(0);
        if (!utils.Auth.isManager()) {
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
        }
        initCBX();
    }

    private void initCBX() {
        cboModel = new DefaultComboBoxModel();
        cboModel.removeAllElements();
        List<NhanVien> lst = new NhanVienDAO().selectAll();
        for (NhanVien tl : lst) {
            cboModel.addElement(tl);
        }
        cbxNV.setModel(cboModel);
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
                "Mã Người Dùng",
                "Họ Và Tên",
                "Giới tính",
                "Ngày sinh",
                "Số điện thoại",
                "Cấp",
        });
        tblList.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblList.getTableHeader().setOpaque(false);
        tblList.getTableHeader().setBackground(new Color(32, 136, 203));
        tblList.getTableHeader().setForeground(new Color(255, 255, 255));
        tblList.setRowHeight(25);
        tblList.setModel(tblModel);
    }

    void showDetail(int index) {
        NguoiDung nd = listND.get(index);
        NhanVien nv = (NhanVien) cbxNV.getSelectedItem();
        nd.setMaNguoiDung(nv.getMaNhanVien());
        nd.setTenNguoiDung(nv.getHoVaTen());
        cbxNV.setSelectedItem(nd.getMaNguoiDung() + "-" + nd.getTenNguoiDung());
        Luong lg = (Luong) cbxLevel.getSelectedItem();
        nd.setCap(lg.getCap());
        txtSDT.setText(nd.getDienThoai());
        txtDate.setText(utils.XDate.toString(nd.getNgaySinh(), "dd-MM-yyyy"));
        if (nd.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        if (!utils.Auth.isManager()) {
            btnXoa.setEnabled(false);
        }
    }

    void previous() {
        try {
            if (index == 0) {
                index = listND.size();
            }
            index--;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    void next() {
        try {
            if (index == listND.size() - 1) {
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
            index = listND.size() - 1;
            showDetail(index);
        } catch (Exception e) {
        }
    }

    private void fillToTable(List<NguoiDung> lst) {
        tblModel.setRowCount(0);

        for (NguoiDung nd : lst) {
            tblModel.addRow(new Object[]{
                    nd.getMaNguoiDung(),
                    nd.getTenNguoiDung(),
                    nd.isGioiTinh() ? "Nam" : "Nữ",
                    utils.XDate.toString(nd.getNgaySinh(), "dd-MM-yyyy"),
                    nd.getDienThoai(),
                    nd.getCap(),
            });
        }
    }

    private void DBFillToList() {
        NguoiDungDAO nvd = new NguoiDungDAO();
        String key = txtTim.getText();
        listND = nvd.SelectByKeyword(key);
        fillToTable(listND);
    }

    private void setSelected(int index) {
        try {
            tblList.setRowSelectionInterval(index, index);
            showDetail(index);
        } catch (Exception e) {

        }
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

    public NguoiDung readForm() {
        NguoiDung nd = new NguoiDung();
        NhanVien nv = (NhanVien) cbxNV.getSelectedItem();

        String ngaySinh = txtDate.getText();
        String dienThoai = txtSDT.getText();
        Luong lg = (Luong) cbxLevel.getSelectedItem();
        String cap = lg.getCap();
        Boolean gioiTinh = true;

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        boolean isValid = isValidDate(ngaySinh, "dd-MM-yyyy");

        if (ngaySinh.trim().length() == 0 || dienThoai.trim().length() == 0) {
            utils.DialogHelper.alert(this, "Vui lòng nhập đầy đủ thông tin!");
            return null;
        }


        if (!isValid) {
            utils.DialogHelper.alert(this, "Ngày sinh không hợp lệ!");
            return null;
        }

        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        String format = simpleDateFormat.format(date);

        int age = Integer.parseInt(format) - Integer.parseInt(ngaySinh.substring(6));

        if (age < 16) {
            utils.DialogHelper.alert(this, "Người dùng phải đủ 16 tuổi!");
            return null;
        }

        nd.setMaNguoiDung(nv.getMaNhanVien());
        nd.setTenNguoiDung(nv.getHoVaTen());
        if (rdoNu.isSelected()) {
            gioiTinh = false;
        }
        nd.setGioiTinh(gioiTinh);
        nd.setNgaySinh(utils.XDate.toDate(ngaySinh, "dd-MM-yyyy"));
        nd.setDienThoai(dienThoai);
        nd.setCap(cap);
        nd.setMaNhanVien(utils.Auth.user.getMaNhanVien());
        return nd;
    }

    private void clearForm() {
        grpGender.clearSelection();
        txtDate.setText("");
        txtSDT.setText("");
    }

    private void addNguoiDung() {
        NguoiDung nd = readForm();
        NguoiDungDAO ndDAO = new NguoiDungDAO();
        if (nd != null) {
            for (NguoiDung s : listND) {
                if (nd.getMaNguoiDung().equals(s.getMaNguoiDung())) {
                    utils.DialogHelper.alert(this, "Mã đã tồn tại!");
                    return;
                }
            }
            listND.add(nd);
            ndDAO.insert(nd);
            DBFillToList();
            clearForm();
            utils.DialogHelper.alert(this, "Thêm mới thành công!");
        }
    }

    private void deleteNguoiDung(NguoiDung nd) {
        NguoiDungDAO ndDAO = new NguoiDungDAO();
        ndDAO.delete(nd);
        listND.remove(nd);
        index = 0;
        DBFillToList();
    }

    private void updateNguoiDung(NguoiDung nd) {
        NhanVien nv = (NhanVien) cbxNV.getSelectedItem();
        boolean gioitinh = true;
        NguoiDungDAO ndd = new NguoiDungDAO();
        nd.setTenNguoiDung(nv.getHoVaTen());
        if (rdoNu.isSelected()) {
            gioitinh = false;
        }
        nd.setGioiTinh(gioitinh);
        nd.setNgaySinh(utils.XDate.toDate(txtDate.getText(), "dd-MM-yyyy"));
        nd.setDienThoai(txtSDT.getText());
        Luong lg = (Luong) cbxLevel.getSelectedItem();
        nd.setCap(lg.getCap());
        nd.setMaNhanVien(utils.Auth.user.getMaNhanVien());
        ndd.update(nd);
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
        tabND = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxLevel = new swing.ComboBoxSuggestion();
        txtDate = new swing.TextFieldSuggestion();
        txtSDT = new swing.TextFieldSuggestion();
        lblNextPage = new javax.swing.JLabel();
        cbxNV = new swing.ComboBoxSuggestion();
        panelBorder2 = new swing.PanelBorder();
        btnFirst = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(229, 229, 229));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(153, 153, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("MÃ NHÂN VIÊN");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 44, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("GIỚI TÍNH");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 112, -1, -1));

        grpGender.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(32, 136, 203));
        rdoNam.setText("NAM");
        jPanel1.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 138, -1, -1));

        grpGender.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(32, 136, 203));
        rdoNu.setText("NỮ");
        jPanel1.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 138, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("NGÀY SINH (DD-MM-YYYY)");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("SỐ ĐIỆN THOẠI");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("LEVEL");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));
        jPanel1.add(cbxLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 170, 30));
        jPanel1.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 185, -1));
        jPanel1.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 185, -1));

        lblNextPage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNextPage.setForeground(new java.awt.Color(0, 0, 0));
        lblNextPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/prevBtn.png"))); // NOI18N
        lblNextPage.setText("TRỞ LẠI");
        lblNextPage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNextPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNextPageMouseClicked(evt);
            }
        });
        jPanel1.add(lblNextPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));

        cbxNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNVActionPerformed(evt);
            }
        });
        jPanel1.add(cbxNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 72, 200, -1));

        panelBorder2.setBackground(new java.awt.Color(214, 214, 214));

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/first.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pre.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(32, 136, 203));
        btnMoi.setText("LÀM MỚI");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(32, 136, 203));
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(32, 136, 203));
        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(32, 136, 203));
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
                panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnMoi)
                                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnSua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
        );
        panelBorder2Layout.setVerticalGroup(
                panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnThem)
                                .addGap(12, 12, 12)
                                .addComponent(btnXoa)
                                .addGap(16, 16, 16)
                                .addComponent(btnSua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMoi)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(panelBorder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        tabND.addTab("CẬP NHẬT", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TÌM KIẾM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(32, 136, 203))); // NOI18N

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtTim, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtTim, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                .addContainerGap())
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
        jScrollPane2.setViewportView(tblList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(155, Short.MAX_VALUE))
                        .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabND.addTab("DANH SÁCH", jPanel2);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabND, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabND)
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
        addNguoiDung();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            int selectedRow = tblList.getSelectedRow();
            if (selectedRow == -1) {
                utils.DialogHelper.alert(this, "Vui lòng chọn dòng cần xóa!");
                return;
            }

            String maNguoiDung = (String) tblList.getValueAt(selectedRow, 0);
            NguoiDungDAO ndDAO = new NguoiDungDAO();
            NguoiDung nd = ndDAO.selectById(maNguoiDung);

            boolean choose = utils.DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa '" + nd.getTenNguoiDung() + "' không?");
            if (choose) {
                deleteNguoiDung(nd);
                utils.DialogHelper.alert(this, "Xóa thông tin thành công!");
                if (listND.isEmpty()) {
                    clearForm();
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        NhanVien nv = (NhanVien) cbxNV.getSelectedItem();
        String ma = nv.getMaNhanVien();
        for (NguoiDung nd : listND) {
            if (ma.equals(nd.getMaNguoiDung())) {
                updateNguoiDung(nd);
                utils.DialogHelper.alert(this, "Cập nhật thông tin thành công!");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        previous();        // TODO add your handling code here:
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        last();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtTimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyPressed
        DBFillToList();
        fillToTable(listND);
    }//GEN-LAST:event_txtTimKeyPressed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        try {
            index = tblList.getSelectedRow();
            String ma = (String) tblList.getValueAt(index, 0);
            NguoiDung nd = new NguoiDungDAO().selectById(ma);
            cbxNV.setSelectedItem(nd.getMaNguoiDung() + "-" + nd.getTenNguoiDung());
            txtDate.setText(utils.XDate.toString(nd.getNgaySinh(), "dd-MM-yyyy"));
            txtSDT.setText(nd.getDienThoai());
            if (nd.isGioiTinh()) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            tabND.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblListMouseClicked

    private void lblNextPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextPageMouseClicked
        // TODO add your handling code here:
        Main.Instance.setForm(new NhanVienForm());
    }//GEN-LAST:event_lblNextPageMouseClicked

    private void cbxNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNVActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_cbxNVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private swing.ComboBoxSuggestion cbxLevel;
    private swing.ComboBoxSuggestion cbxNV;
    private javax.swing.ButtonGroup grpGender;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNextPage;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabND;
    private javax.swing.JTable tblList;
    private swing.TextFieldSuggestion txtDate;
    private swing.TextFieldSuggestion txtSDT;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
