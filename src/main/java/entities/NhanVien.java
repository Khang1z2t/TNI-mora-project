/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

public class NhanVien {
    private String MaNhanVien;
    private String MatKhau;
    private String HoVaTen;
    private String Email;
    private boolean VaiTro = false;
    private boolean SA = false;

    public NhanVien() {
    }

    @Override
    public String toString() {
        return MaNhanVien + "-" + HoVaTen;
    }

    public NhanVien(String MaNhanVien, String MatKhau, String HoVaTen, String Email) {
        this.MaNhanVien = MaNhanVien;
        this.MatKhau = MatKhau;
        this.HoVaTen = HoVaTen;
        this.Email = Email;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String HoVaTen) {
        this.HoVaTen = HoVaTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

    public boolean isSA() {
        return SA;
    }

    public void setSA(boolean SA) {
        this.SA = SA;
    }

 
}
