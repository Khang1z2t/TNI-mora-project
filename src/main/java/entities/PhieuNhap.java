package entities;

import java.util.Date;

public class PhieuNhap {
    private String MaNhap;
    private Date NgayNhap;
    private String MaNV;
    private String MaNhaCC;
    private double TongTien;

    public PhieuNhap() {
    }

    public PhieuNhap(String maNhap, Date ngayNhap, String maNV, String maNhaCC, double tongTien) {
        MaNhap = maNhap;
        NgayNhap = ngayNhap;
        MaNV = maNV;
        MaNhaCC = maNhaCC;
        TongTien = tongTien;
    }

    public String getMaNhap() {
        return MaNhap;
    }

    public void setMaNhap(String maNhap) {
        MaNhap = maNhap;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getMaNhaCC() {
        return MaNhaCC;
    }

    public void setMaNhaCC(String maNhaCC) {
        MaNhaCC = maNhaCC;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double tongTien) {
        TongTien = tongTien;
    }
}
