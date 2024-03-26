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
public class NguoiDung {
    private String MaNguoiDung;
    private String TenNguoiDung;
    private boolean GioiTinh;
    private Date ngaySinh;
    private String DienThoai;
    private String cap;
    private String MaNhanVien;
    private Date NgayDangKi = new Date();

    public NguoiDung() {
    }

    public NguoiDung(String MaNguoiDung, String TenNguoiDung, boolean GioiTinh, Date ngaySinh, String DienThoai, String cap, String MaNhanVien) {
        this.MaNguoiDung = MaNguoiDung;
        this.TenNguoiDung = TenNguoiDung;
        this.GioiTinh = GioiTinh;
        this.ngaySinh = ngaySinh;
        this.DienThoai = DienThoai;
        this.cap = cap;
        this.MaNhanVien = MaNhanVien;
    }

    public String getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(String MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String TenNguoiDung) {
        this.TenNguoiDung = TenNguoiDung;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public Date getNgayDangKi() {
        return NgayDangKi;
    }

    public void setNgayDangKi(Date NgayDangKi) {
        this.NgayDangKi = NgayDangKi;
    }

}