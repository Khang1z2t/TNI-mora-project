/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.Icon;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class Model_Card {
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Model_Card(Icon icon, String title, String values, String description) {
        this.icon = icon;
        this.title = title;
        this.values = values;
        this.description = description;
    }

    public Model_Card() {
    }

    private Icon icon;
    private String title;
    private String values;
    private String description;
}
