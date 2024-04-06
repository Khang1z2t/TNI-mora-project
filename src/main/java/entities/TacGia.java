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
public class TacGia {
    private int matg;
    private String tentg;

    public TacGia() {
    }

    public TacGia(int matg, String tentg) {
        this.matg = matg;
        this.tentg = tentg;
    }

    public int getMatg() {
        return matg;
    }

    public void setMatg(int matg) {
        this.matg = matg;
    }

    public String getTentg() {
        return tentg;
    }

    public void setTentg(String tentg) {
        this.tentg = tentg;
    }

    // Inside TacGia class
@Override
public String toString() {
    return getTentg(); // Assuming you have a method like getTenTacGia() in your TacGia class
}

}
