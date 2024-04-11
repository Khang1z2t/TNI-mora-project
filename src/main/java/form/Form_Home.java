/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDAO;
import dao.ThongKeDAO;
import entities.NhanVien;
import model.Model_Card;
import model.StatusType;
import ui.ScrollBar;
import utils.MoneyFormat;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class Form_Home extends javax.swing.JPanel {

    NhanVienDAO dao = new NhanVienDAO();
    ThongKeDAO tkdao = new ThongKeDAO();

    public Form_Home() {
        initComponents();
        init();
    }

    private void init() {
        initCard();
        //Thêm row
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        table.setUseStatus(false);
        fillTable();
    }

    private void initCard() {
        List<Object[]> topDT = tkdao.getTopDoanhThu();
        List<Object[]> topNV = tkdao.getTopNV();
        List<Object[]> topSach = tkdao.getTopSach();
        if (!topDT.isEmpty() && !topNV.isEmpty() && !topSach.isEmpty() && topDT != null && topNV != null && topSach != null) {
            // Lấy dữ liệu từ danh sách
            Object[] dtData = topDT.get(0);
            Object[] nvData = topNV.get(0);
            Object[] sachData = topSach.get(0);

            // Tạo các đối tượng Model_Card
            Model_Card dtCard = new Model_Card(new ImageIcon(getClass().getResource("/icon/8.png")), "Doanh thu", MoneyFormat.format(dtData[0]), dtData[1].toString());
            Model_Card nvCard = new Model_Card(new ImageIcon(getClass().getResource("/icon/2.png")), "Best Staff Tháng", nvData[0].toString(), nvData[1] + " Đơn - " + MoneyFormat.format(nvData[2]));
            Model_Card sachCard = new Model_Card(new ImageIcon(getClass().getResource("/icon/3.png")), "Bán chạy nhất", sachData[0].toString(), "Đã bán được " + sachData[1] + " Bản");

            // Điền dữ liệu vào thẻ
            card1.setData(dtCard);
            card2.setData(nvCard);
            card3.setData(sachCard);
        } else {
            card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/8.png")), "Doanh thu", "$200000", "Tăng nhanh hơn so với 2023"));
            card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/3.png")), "Bán chạy nhất", "Pháp sư tiễn táng Frieren", "10000 Bản"));
            card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/2.png")), "Best Staff Tháng", "Nguyễn Đình Tuấn", "Không sai bill"));
        }

    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new Object[]{
                "STT", "Mã NV", "Tên NV", "Đánh giá"
        });
        model.setRowCount(0);
        int i = 1;
        List<NhanVien> list = dao.selectAll();
        for (NhanVien nv : list) {
            int num = tkdao.getSoLuongDon(nv.getMaNhanVien());
            model.addRow(new Object[]{
                    i++,
                    nv.getMaNhanVien(),
                    nv.getHoVaTen(),
                    getDanhGia(num)
            });
        }
    }

    private String getDanhGia(int num) {
        String danhGia;
        if (num < 10) {
            danhGia = "BAD";
        } else if (num < 50) {
            danhGia = "NORMAL";
        } else if (num < 100) {
            danhGia = "GOOD";
        } else {
            danhGia = "EXCELLENT";
        }
        return danhGia;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        card1 = new component.Card();
        card2 = new component.Card();
        card3 = new component.Card();
        panelBorder1 = new swing.PanelBorder();
        spTable = new javax.swing.JScrollPane();
        table = new swing.Table();
        jLabel1 = new javax.swing.JLabel();

        panel.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        card1.setDoubleBuffered(false);
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "STT", "Mã NV", "Tên NV", "Đánh giá"
                }
        ));
        spTable.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("ĐÁNH GIÁ NHÂN VIÊN THÁNG");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spTable)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGap(195, 195, 195)
                                                .addComponent(jLabel1)
                                                .addGap(0, 220, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(203, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Card card1;
    private component.Card card2;
    private component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private swing.Table table;
    // End of variables declaration//GEN-END:variables
}
