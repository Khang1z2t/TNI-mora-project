/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.List;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class Hoadon {

    private String maHoaDon;
    private Date ngayTao;
    private String maNV;
    private String maTV;
    private List<ChiTietHoaDon> CTHoaDon;
    private double tongTien;

    public Hoadon() {
    }

    public Hoadon(String maHoaDon, Date ngayTao, String maNV, String maTV, List<ChiTietHoaDon> CTHoaDon, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.maNV = maNV;
        this.maTV = maTV;
        this.CTHoaDon = CTHoaDon;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaTV() {
        return maTV;
    }

    public void setMaTV(String maTV) {
        this.maTV = maTV;
    }

    public List<ChiTietHoaDon> getCTHoaDon() {
        return CTHoaDon;
    }

    public void setCTHoaDon(List<ChiTietHoaDon> CTHoaDon) {
        this.CTHoaDon = CTHoaDon;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
