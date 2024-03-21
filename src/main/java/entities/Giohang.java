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
public class Giohang {
    private int magiohang;
    private String tensach;
    private int gia;
    private int soluong;
    private String manv;

    public Giohang() {
    }

    public Giohang(int magiohang, String tensach, int gia, int soluong, String manv) {
        this.magiohang = magiohang;
        this.tensach = tensach;
        this.gia = gia;
        this.soluong = soluong;
        this.manv = manv;
    }

    public int getMagiohang() {
        return magiohang;
    }

    public void setMagiohang(int magiohang) {
        this.magiohang = magiohang;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }
    
}