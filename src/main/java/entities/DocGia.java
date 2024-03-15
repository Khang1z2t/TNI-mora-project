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
public class DocGia {
    int MaDocGia;
    String TenDocGia;
    int Mahoadon;
    String Masach;
    String Sodienthoai;

    public DocGia() {
    }

    public DocGia(int MaDocGia, String TenDocGia, int Mahoadon, String Masach, String Sodienthoai) {
        this.MaDocGia = MaDocGia;
        this.TenDocGia = TenDocGia;
        this.Mahoadon = Mahoadon;
        this.Masach = Masach;
        this.Sodienthoai = Sodienthoai;
    }

    public int getMaDocGia() {
        return MaDocGia;
    }

    public void setMaDocGia(int MaDocGia) {
        this.MaDocGia = MaDocGia;
    }

    public String getTenDocGia() {
        return TenDocGia;
    }

    public void setTenDocGia(String TenDocGia) {
        this.TenDocGia = TenDocGia;
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

    public String getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(String Sodienthoai) {
        this.Sodienthoai = Sodienthoai;
    }
    
}
