/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class Kho {
    private int makho;
    private String Masach;
    private String Tensach;
    private String GhiChu;
    private Date ngaytao = new Date();
    private int soluong;

    public Kho() {
    }

    public Kho(int makho, String Masach, String Tensach, String GhiChu, int soluong) {
        this.makho = makho;
        this.Masach = Masach;
        this.Tensach = Tensach;
        this.GhiChu = GhiChu;
        this.soluong = soluong;
    }

    public int getMakho() {
        return makho;
    }

    public void setMakho(int makho) {
        this.makho = makho;
    }

    public String getMasach() {
        return Masach;
    }

    public void setMasach(String Masach) {
        this.Masach = Masach;
    }

    public String getTensach() {
        return Tensach;
    }

    public void setTensach(String Tensach) {
        this.Tensach = Tensach;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
}