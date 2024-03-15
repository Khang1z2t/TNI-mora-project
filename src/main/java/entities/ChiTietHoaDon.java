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
public class ChiTietHoaDon {
    int MaHoaDon;
    int Soluong;
    double gia;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int MaHoaDon, int Soluong, double gia) {
        this.MaHoaDon = MaHoaDon;
        this.Soluong = Soluong;
        this.gia = gia;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
}
