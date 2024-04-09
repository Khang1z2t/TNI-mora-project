/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class Luong {
    private String cap;
    private int luong;

    public Luong() {
    }

    @Override
    public String toString() {
        return cap + "-" + luong + " triệu";
    }

    public Luong(String cap, int luong) {
        this.cap = cap;
        this.luong = luong;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

}
