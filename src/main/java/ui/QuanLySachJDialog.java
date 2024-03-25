/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.SachDAO;
import dao.TacGiaDAO;
import dao.TheLoaiDAO;
import entities.Sach;
import entities.TacGia;
import entities.TheLoai;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import utils.DialogHelper;
import utils.XImage;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class QuanLySachJDialog extends javax.swing.JDialog {
    ArrayList<Sach> list = new ArrayList<>();
    DefaultComboBoxModel cboModel;
    int index = -1;
    DefaultTableModel tblModel;
    /**
     * Creates new form QuanLySachJDialog
     */
    public QuanLySachJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
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
        cboTacgia.setSelectedIndex(0);
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
        Date ngayton = new Date();
   
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

    private void deleteNhanVien(Sach sa) {
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

        jLabel1 = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboTacgia = new javax.swing.JComboBox<>();
        cboTheLoai = new javax.swing.JComboBox<>();
        txtNhaXB = new javax.swing.JTextField();
        tabDanhSach = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setText("QUẢN LÝ THÔNG TIN SÁCH");

        tabThongTin.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã sách");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên sách");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Thể loại");

        txtGiasach.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tác giả");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Năm xuất bản");

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(75, 26));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
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

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/first.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
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

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/last.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLast)
                    .addComponent(btnPre)
                    .addComponent(btnNext)
                    .addComponent(btnFirst))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Giá sách");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 102));
        jLabel8.setText("Ghi Chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Nhà xuất bản");

        cboTacgia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTacgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTacgiaActionPerformed(evt);
            }
        });

        cboTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout tabThongTinLayout = new javax.swing.GroupLayout(tabThongTin);
        tabThongTin.setLayout(tabThongTinLayout);
        tabThongTinLayout.setHorizontalGroup(
            tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tabThongTinLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabThongTinLayout.createSequentialGroup()
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMasach, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(txtTensach, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNamXB, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addComponent(txtGiasach))
                            .addComponent(jLabel7)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(tabThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(143, 143, 143)
                        .addComponent(jLabel4))
                    .addComponent(jLabel9)
                    .addGroup(tabThongTinLayout.createSequentialGroup()
                        .addComponent(cboTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboTheLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtNhaXB, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        tabThongTinLayout.setVerticalGroup(
            tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabThongTinLayout.createSequentialGroup()
                .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabThongTinLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMasach, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamXB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTensach, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(txtGiasach))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNhaXB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabThongTinLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabSach, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabSach, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        previous();
    }//GEN-LAST:event_btnPreActionPerformed

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

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearform();
        txtMasach.requestFocus();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            for (Sach sa : list) {
                if (txtMasach.getText().equalsIgnoreCase(sa.getMaSach())) {

                    boolean choose = utils.DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa sách " + sa.getTenSach() + "' không?");
                    if (choose) {
                        deleteNhanVien(sa);
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

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
first();        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
addSach();        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int res = fileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
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

    private void cboTacgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTacgiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTacgiaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLySachJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySachJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySachJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySachJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuanLySachJDialog dialog = new QuanLySachJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboTacgia;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
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
