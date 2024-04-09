/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

/**
 * @author NGUYEN THI NGUYET VY
 */

import javax.swing.JFileChooser;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import java.io.File;

public class FileChooser extends JFileChooser {

    public FileChooser() {
        super();
    }


    @Override
    public void updateUI() {
        LookAndFeel old = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            super.updateUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                UIManager.setLookAndFeel(old);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public FileChooser(String dir) {
        super(dir);
    }
}