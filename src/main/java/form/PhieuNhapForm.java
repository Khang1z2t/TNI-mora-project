package form;

import ui.Main;

/**
 *
 * @author ngocd
 */
public class PhieuNhapForm extends javax.swing.JPanel {

    public PhieuNhapForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBack = new javax.swing.JLabel();

        setBackground(new java.awt.Color(229, 229, 229));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(615, Short.MAX_VALUE)
                .addComponent(lblBack)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack)
                .addContainerGap(459, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        Main.Instance.setForm(new KhoForm());
    }//GEN-LAST:event_lblBackMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBack;
    // End of variables declaration//GEN-END:variables
}
