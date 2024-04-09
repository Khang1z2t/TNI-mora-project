/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

import model.StatusType;

public class TableStatus extends JLabel {

    public StatusType getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    private StatusType type;

    public void setType(StatusType type) {
        this.type = type;
        setText(type.toString());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g;
            if (type == StatusType.GOOD) {
                g = new GradientPaint(0, 0, new Color(186, 123, 247), 0, getHeight(), new Color(167, 94, 236));
            } else if (type == StatusType.EXELENT) {
                g = new GradientPaint(0, 0, new Color(142, 142, 250), 0, getHeight(), new Color(123, 123, 245));
            } else {
                g = new GradientPaint(0, 0, new Color(241, 208, 62), 0, getHeight(), new Color(211, 184, 61));
            }
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }
        super.paintComponent(grphcs);
    }
}
