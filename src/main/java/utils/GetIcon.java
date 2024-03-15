/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class GetIcon {

    public static Image getImageIcon(String path) {
        URL url = GetIcon.class.getResource("/icon/" + path);
        return new ImageIcon(url).getImage();
    }

    public static ImageIcon getIcon(String path, int width, int heigt) {
        ImageIcon resizedImage = new ImageIcon(getImageIcon(path).getScaledInstance(width, heigt, Image.SCALE_SMOOTH));
        return resizedImage;
    }

}
