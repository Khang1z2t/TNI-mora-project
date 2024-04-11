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
        initCBX();
//        initThongKe();
//        fillTableTK();
        fillDatatoChar();
    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{
                "Mã Nhân viên",
                "Tên Nhân viên",
                "Loại Nhân viên",
                "Cấp nhân viên",
                "Lương",
        });
        tblLuong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblLuong.getTableHeader().setOpaque(false);
        tblLuong.getTableHeader().setBackground(new Color(32, 136, 203));
        tblLuong.getTableHeader().setForeground(new Color(255, 255, 255));
        tblLuong.setRowHeight(25);
        tblLuong.setModel(tblModel);
    }
    private void initCBX() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxThang.getModel();
        model.removeAllElements();
        List<Integer> lst = new LuongDAO().selectThang();
        for (Integer thang : lst) {
            model.addElement(thang);
        }
        cbxThang.setSelectedIndex(0);
    }

    private void fillTable() {
        DefaultTableModel tblModel = (DefaultTableModel) tblLuong.getModel();
        tblModel.setRowCount(0);
        try {
            int thang = (int) cbxThang.getSelectedItem();
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

    private void fillDatatoChar(){
        List<Double[]> listTK = tkd.getThongKe();

        String[] name = new String[]{
//                "Tổng số lượng sách đã nhập",
//                "Tổng số lượng sách đã bán",
//                "Tổng số lượng nhân viên",
                "Tổng tiền đã nhập",
                "Tổng tiền đã bán",
                "Tổng tiền đã trả lương NV"
        };
        int index = 0;
        for(Double[] values : listTK){
            for(Double value : values) {
                pieChart.addData(new ModelPieChart(name[index], value, getColor(index)));
                index++;
            }
        }
    }


    private Color getColor(int index) {
        Color[] color = new Color[]{
                new Color(135, 189, 245),
                new Color(189, 135, 245),
                new Color(139, 229, 222),
                new Color(132, 41, 255),
                new Color(149, 255, 17),
                new Color(30, 255, 101),
        };
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
        pieChart = new swing.PieChart();
        panelBorder3 = new swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLuong = new swing.Table();
        panelBorder5 = new swing.PanelBorder();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSach = new swing.Table();
        panelBorder6 = new swing.PanelBorder();
        jScrollPane5 = new javax.swing.JScrollPane();
        table3 = new swing.Table();
        panelBorder4 = new swing.PanelBorder();
        cbxThang = new swing.ComboBoxSuggestion();

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

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pieChart, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addComponent(pieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
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

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        tab.addTab("Lương mỗi tháng", panelBorder3);

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblSach);

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        tab.addTab("Tổng số lượng sách", panelBorder5);

        panelBorder6.setBackground(new java.awt.Color(255, 255, 255));

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(table3);

        javax.swing.GroupLayout panelBorder6Layout = new javax.swing.GroupLayout(panelBorder6);
        panelBorder6.setLayout(panelBorder6Layout);
        panelBorder6Layout.setHorizontalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
        );
        panelBorder6Layout.setVerticalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        tab.addTab("Doanh thu", panelBorder6);

        panelBorder4.setBackground(new java.awt.Color(204, 204, 204));
        panelBorder4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÁNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N

        cbxThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxThangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxThang, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void cbxThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxThangActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_cbxThangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ComboBoxSuggestion cbxThang;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private swing.PanelBorder panelBorder3;
    private swing.PanelBorder panelBorder4;
    private swing.PanelBorder panelBorder5;
    private swing.PanelBorder panelBorder6;
    private swing.PieChart pieChart;
    private swing.MaterialTabbed tab;
    private swing.Table table3;
    private swing.Table tblLuong;
    private swing.Table tblSach;
    // End of variables declaration//GEN-END:variables
}
