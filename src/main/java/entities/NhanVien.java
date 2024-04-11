/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.Timestamp;

public class NhanVien {
    private String MaNhanVien;
    private String MatKhau;
    private String HoVaTen;
    private String Email;
    private boolean VaiTro = false;
    private boolean SA = false;
    private String cap;
    private Timestamp ngaytraluong;

    public NhanVien() {
    }

    @Override
    public String toString() {
        return MaNhanVien + "-" + HoVaTen;
    }

    public NhanVien(String maNhanVien, String matKhau, String hoVaTen, String email, boolean vaiTro, boolean SA, String cap, Timestamp ngaytraluong) {
        MaNhanVien = maNhanVien;
        MatKhau = matKhau;
        HoVaTen = hoVaTen;
        Email = email;
        VaiTro = vaiTro;
        this.SA = SA;
        this.cap = cap;
        this.ngaytraluong = ngaytraluong;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        HoVaTen = hoVaTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        VaiTro = vaiTro;
    }

    public boolean isSA() {
        return SA;
    }

    public void setSA(boolean SA) {
        this.SA = SA;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public Timestamp getNgaytraluong() {
        return ngaytraluong;
    }

    public void setNgaytraluong(Timestamp ngaytraluong) {
        this.ngaytraluong = ngaytraluong;
    }
}
