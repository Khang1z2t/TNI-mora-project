/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import javax.swing.JComboBox;
import swing.*;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class ComboBoxSuggestion<E> extends JComboBox<E> {
    public ComboBoxSuggestion() {
        setUI(new ComboSuggestionUI());
    }
}
