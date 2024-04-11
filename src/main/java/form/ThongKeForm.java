/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import chart.ModelChart;
import component.Chart;
import dao.LuongDAO;
import dao.ThongKeDAO;
import entities.Luong;
import model.ModelPieChart;
import utils.MoneyFormat;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class ThongKeForm extends javax.swing.JPanel {

    ThongKeDAO tkd = new ThongKeDAO();

    ArrayList<Luong> list = new ArrayList<>();
    int index = -1;
    DefaultTableModel tblModel;

    public ThongKeForm() {
        initComponents();
        initTable();
        initCBXS();
        initCBXTK();
        initCBX();
//        initThongKe();
//        fillTableTK();
        fillChartSL();
        fillDatatoChar();

    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{
            "Mã Nhân viên",
            "Tên Nhân viên",
            "Loại Nhân viên",
            "Cấp nhân viên",
            "Lương",});
        tblLuong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblLuong.getTableHeader().setOpaque(false);
        tblLuong.getTableHeader().setBackground(new Color(32, 136, 203));
        tblLuong.getTableHeader().setForeground(new Color(255, 255, 255));
        tblLuong.setRowHeight(25);
        tblLuong.setModel(tblModel);
    }

    private void initCBX() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxThangL.getModel();
        model.removeAllElements();
        List<Integer> lst = new LuongDAO().selectThang();
        for (Integer thang : lst) {
            model.addElement(thang);
        }
        cbxThangL.setSelectedIndex(0);
    }
    
    private void initCBXTK(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxThangTK.getModel();
        model.removeAllElements();
        List<Integer> lst = new ThongKeDAO().selectThangTK();
        for (Integer thang : lst) {
            model.addElement(thang);
        }
        cbxThangTK.setSelectedIndex(0);   
    }
    
    private void initCBXS(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxThangS.getModel();
        model.removeAllElements();
        List<Integer> lst = new ThongKeDAO().selectThangHD();
        for (Integer thang : lst) {
            model.addElement(thang);
        }
        cbxThangS.setSelectedIndex(0);   
    }
    
    private void fillTable() {
        DefaultTableModel tblModel = (DefaultTableModel) tblLuong.getModel();
        tblModel.setRowCount(0);
        try {
            int thang = (int) cbxThangTK.getSelectedItem();
            List<Object[]> lst = new LuongDAO().getBangLuong(thang);

            for (Object[] obj : lst) {
                obj[4] = MoneyFormat.format(obj[4]);
                tblModel.addRow(obj);
            }
        } catch (Exception e) {
        }
    }

//    private void fillTableTK() {
//        DefaultTableModel tblModel = (DefaultTableModel) tblThongKe.getModel();
//        tblModel.setRowCount(0);
//        try {
//            int thang = (int) cbxThang.getSelectedItem();
//            List<Object[]> lst = tkd.getThongKe();
//
//            for (Object[] obj : lst) {
//                obj[3] = MoneyFormat.format(obj[3]);
//                obj[4] = MoneyFormat.format(obj[4]);
//                obj[5] = MoneyFormat.format(obj[5]);
//                tblModel.addRow(obj);
//            }
//        } catch (Exception e) {
//        }
//    }
//private void initThongKe() {
//        tblModel = new DefaultTableModel();
//        tblModel.setColumnIdentifiers(new String[]{
//                "Tổng số lượng sách đã nhập",
//                "Số lượng sách đã bán",
//                "Số lượng nhân viên",
//                "Tổng tiền đã nhập",
//                "Tổng tiền đã bán",
//                "Tổng tiền đã trả lương NV",
//        });
//        tblThongKe.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
//        tblThongKe.getTableHeader().setOpaque(false);
//        tblThongKe.getTableHeader().setBackground(new Color(32, 136, 203));
//        tblThongKe.getTableHeader().setForeground(new Color(255, 255, 255));
//        tblThongKe.setRowHeight(25);
//        tblThongKe.setModel(tblModel);
//}
    private void fillDatatoChar() {
        int thang = (int) cbxThangTK.getSelectedItem();
        List<Double[]> listTK = tkd.getThongKe(thang);

        String[] name = new String[]{
            //                "Tổng số lượng sách đã nhập",
            //                "Tổng số lượng sách đã bán",
            //                "Tổng số lượng nhân viên",
            "Tổng tiền đã nhập",
            "Tổng tiền đã bán",
            "Tổng tiền đã trả lương NV"
        };
        int index = 0;
        for (Double[] values : listTK) {
            for (Double value : values) {
                pieChart.addData(new ModelPieChart(name[index], value, getColor(index)));
                index++;
            }
        }
    }

    private void fillChartSL() {
        int thang = (int) cbxThangTK.getSelectedItem();
        List<Double[]> ist = tkd.getTKSL(thang);

        String[] name = new String[]{
            "Tổng số lượng sách đã nhập trong tháng",
            "Tổng số lượng sách đã bán trong tháng",
            "Tổng số lượng sách còn trong kho"
        };
        int index = 0;
        for (Double[] values : ist) {
            for (Double value : values) {
                tksl.addData(new ModelPieChart(name[index], value, getColor(index)));
                index++;
            }
        }
        tksl.setType("Số lượng");
    }

    private Color getColor(int index) {
        Color[] color = new Color[]{
            new Color(135, 189, 245),
            new Color(189, 135, 245),
            new Color(139, 229, 222),
            new Color(132, 41, 255),
            new Color(149, 255, 17),
            new Color(30, 255, 101),};
        return color[index];
    }

    private void DBFilltoList() {
        LuongDAO ld = new LuongDAO();
        list = (ArrayList<Luong>) ld.SelectAll();
        fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new swing.PanelBorder();
        tab = new swing.MaterialTabbed();
        panelBorder2 = new swing.PanelBorder();
        panelBorder4 = new swing.PanelBorder();
        cbxThangTK = new swing.ComboBoxSuggestion();
        pieChart = new swing.PieChart();
        panelBorder3 = new swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLuong = new swing.Table();
        panelBorder9 = new swing.PanelBorder();
        cbxThangL = new swing.ComboBoxSuggestion();
        panelBorder5 = new swing.PanelBorder();
        panelBorder10 = new swing.PanelBorder();
        cbxThangS = new swing.ComboBoxSuggestion();
        tksl = new swing.PieChart();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(698, 594));
        setMinimumSize(new java.awt.Dimension(698, 594));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(698, 594));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        tab.setBackground(new java.awt.Color(255, 255, 255));
        tab.setForeground(new java.awt.Color(153, 153, 255));
        tab.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        panelBorder4.setBackground(new java.awt.Color(204, 204, 204));
        panelBorder4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÁNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N

        cbxThangTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxThangTKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxThangTK, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxThangTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
            .addComponent(pieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pieChart, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
        );

        tab.addTab("Tổng Thống Kê", panelBorder2);

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        tblLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblLuong);

        panelBorder9.setBackground(new java.awt.Color(204, 204, 204));
        panelBorder9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÁNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N

        cbxThangL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxThangLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder9Layout = new javax.swing.GroupLayout(panelBorder9);
        panelBorder9.setLayout(panelBorder9Layout);
        panelBorder9Layout.setHorizontalGroup(
            panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxThangL, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder9Layout.setVerticalGroup(
            panelBorder9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxThangL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(panelBorder9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
        );

        tab.addTab("Lương mỗi tháng", panelBorder3);

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        panelBorder10.setBackground(new java.awt.Color(204, 204, 204));
        panelBorder10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÁNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N

        cbxThangS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxThangSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder10Layout = new javax.swing.GroupLayout(panelBorder10);
        panelBorder10.setLayout(panelBorder10Layout);
        panelBorder10Layout.setHorizontalGroup(
            panelBorder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxThangS, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder10Layout.setVerticalGroup(
            panelBorder10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxThangS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(panelBorder10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
            .addComponent(tksl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tksl, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
        );

        tab.addTab("Tổng số lượng sách", panelBorder5);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void cbxThangSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxThangSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxThangSActionPerformed

    private void cbxThangLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxThangLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxThangLActionPerformed

    private void cbxThangTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxThangTKActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_cbxThangTKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ComboBoxSuggestion cbxThang1;
    private swing.ComboBoxSuggestion cbxThang2;
    private swing.ComboBoxSuggestion cbxThangL;
    private swing.ComboBoxSuggestion cbxThangS;
    private swing.ComboBoxSuggestion cbxThangTK;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder10;
    private swing.PanelBorder panelBorder2;
    private swing.PanelBorder panelBorder3;
    private swing.PanelBorder panelBorder4;
    private swing.PanelBorder panelBorder5;
    private swing.PanelBorder panelBorder7;
    private swing.PanelBorder panelBorder8;
    private swing.PanelBorder panelBorder9;
    private swing.PieChart pieChart;
    private swing.MaterialTabbed tab;
    private swing.Table tblLuong;
    private swing.PieChart tksl;
    // End of variables declaration//GEN-END:variables
}
