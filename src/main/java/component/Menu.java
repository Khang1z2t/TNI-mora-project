/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import event.EventMenuSelected;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import model.Model_Menu;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class Menu extends javax.swing.JPanel {

    /**
     * Creates new form Menu
     */
    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }
    public Menu() {
        initComponents();
        setOpaque(false);
        init();
    }

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }
    
    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        PanelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        PanelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }
    private void init() {
        listMenu1.addItem(new Model_Menu("1", "Trang chủ", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("2", "Quản lý nhân viên", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("3", "Thông tin nhân viên", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("4", "Quản lý sách", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("5", "Quản lý thể loại", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("6", "Quản lý tác giả", Model_Menu.MenuType.MENU));

        listMenu1.addItem(new Model_Menu("", "Thông tin", Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("7", "Đổi mật khẩu", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("8", "Doanh thu", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("9", "Hóa đơn", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("10", "Lương nhân viên", Model_Menu.MenuType.MENU));  
        listMenu1.addItem(new Model_Menu("11", "Đăng xuất", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listMenu1 = new swing.ListMenu<>();
        PanelMoving = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        listMenu1.setForeground(new java.awt.Color(32, 136, 203));
        listMenu1.setOpaque(false);

        PanelMoving.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 204));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/System.png"))); // NOI18N
        jLabel1.setText("MORA");

        javax.swing.GroupLayout PanelMovingLayout = new javax.swing.GroupLayout(PanelMoving);
        PanelMoving.setLayout(PanelMovingLayout);
        PanelMovingLayout.setHorizontalGroup(
            PanelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMovingLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMovingLayout.setVerticalGroup(
            PanelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(PanelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(PanelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMoving;
    private javax.swing.JLabel jLabel1;
    private swing.ListMenu<String> listMenu1;
    // End of variables declaration//GEN-END:variables
}
