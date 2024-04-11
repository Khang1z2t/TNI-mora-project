package utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XImage {
    // Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ

    public static Image getAppIcon() {
        URL url = XImage.class.getResource("/icon/mora.png");
        return new ImageIcon(url).getImage();
    }

    //    /**
//     * Sao chép file logo sách vào thư mục logo
//     * @param src là đối tượng file ảnh
//     */   
    public static void save(File src) {
        File dst = new File("bia", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //    /**
//     * Đọc hình ảnh logo bìa sách
//     * @param fileName  là tên file logo
//     * @return ảnh đọc được
//     */   
    public static ImageIcon read(String fileName, int width, int heigt) {
        File path = new File("bia", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(width, heigt, Image.SCALE_SMOOTH));
    }
}