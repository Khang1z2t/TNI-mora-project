/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class Giohang {
    private int magiohang;
    private String masach;
    private String tensach;
    private int gia;
    private int soluong;
    private int maTV;
    private String manv;

    public Giohang() {
    }

    @Override
    public String toString() {
        return tensach + "-" + gia + "-" + soluong;
    }

    public Giohang(int magiohang, String masach, String tensach, int gia, int soluong, int maTV, String manv) {
        this.magiohang = magiohang;
        this.masach = masach;
        this.tensach = tensach;
        this.gia = gia;
        this.soluong = soluong;
        this.maTV = maTV;
        this.manv = manv;
    }

    public int getMagiohang() {
        return magiohang;
    }

    public void setMagiohang(int magiohang) {
        this.magiohang = magiohang;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
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

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }


}