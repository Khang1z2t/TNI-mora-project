/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class NguoiDung {
    private String MaNguoiDung;
    private String TenNguoiDung;
    private boolean GioiTinh;
    private Date ngaySinh;
    private String DienThoai;
    private String MaNhanVien;
    private Date NgayDangKi = new Date();

    public NguoiDung() {
    }

    public NguoiDung(String maNguoiDung, String tenNguoiDung, boolean gioiTinh, Date ngaySinh, String dienThoai, String maNhanVien, Date ngayDangKi) {
        MaNguoiDung = maNguoiDung;
        TenNguoiDung = tenNguoiDung;
        GioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        DienThoai = dienThoai;
        MaNhanVien = maNhanVien;
        NgayDangKi = ngayDangKi;
    }

    public String getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        MaNguoiDung = maNguoiDung;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        TenNguoiDung = tenNguoiDung;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        GioiTinh = gioiTinh;
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

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public Date getNgayDangKi() {
        return NgayDangKi;
    }

    public void setNgayDangKi(Date ngayDangKi) {
        NgayDangKi = ngayDangKi;
    }
}