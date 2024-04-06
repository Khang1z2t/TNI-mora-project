/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class TheLoai {
    private String tenTheLoai;
    private int maTheLoai;

    public TheLoai() {
    }

    public TheLoai(String tenTheLoai, int maTheLoai) {
        this.tenTheLoai = tenTheLoai;
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    @Override
    public String toString() {
        return getTenTheLoai(); // Assuming you have a method like getTenTheLoai() in your TheLoai class
    }
}
