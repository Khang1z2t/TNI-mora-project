package swing;

import javax.swing.*;

public class FileChooser extends JFileChooser {
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

    public FileChooser() {
        super();
    }
    public FileChooser(String dir) {
        super(dir);
    }
}
