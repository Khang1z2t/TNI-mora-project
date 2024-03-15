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
public class Hoadon {
    int Mahoadon;
    String Masach;
    Date ngaytao = new Date();
    int MaDocGia;
    String MaNV;
    String sdt;

    public Hoadon() {
    }

    public Hoadon(int Mahoadon, String Masach, int MaDocGia, String MaNV, String sdt) {
        this.Mahoadon = Mahoadon;
        this.Masach = Masach;
        this.MaDocGia = MaDocGia;
        this.MaNV = MaNV;
        this.sdt = sdt;
    }

    public int getMahoadon() {
        return Mahoadon;
    }

    public void setMahoadon(int Mahoadon) {
        this.Mahoadon = Mahoadon;
    }

    public String getMasach() {
        return Masach;
    }

    public void setMasach(String Masach) {
        this.Masach = Masach;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getMaDocGia() {
        return MaDocGia;
    }

    public void setMaDocGia(int MaDocGia) {
        this.MaDocGia = MaDocGia;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
}
