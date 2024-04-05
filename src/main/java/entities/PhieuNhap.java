package entities;

import java.util.Date;
import java.util.List;

public class PhieuNhap {
    private String MaNhap;
    private Date NgayNhap;
    private String MaNV;
    private List<ChiTietPhieuNhap> CTPhieuNhap;
    private String MaNhaCC;
    private double TongTien;

    public PhieuNhap() {
    }

    public PhieuNhap(String maNhap, Date ngayNhap, String maNV, List<ChiTietPhieuNhap> CTPhieuNhap, String maNhaCC, double tongTien) {
        MaNhap = maNhap;
        NgayNhap = ngayNhap;
        MaNV = maNV;
        this.CTPhieuNhap = CTPhieuNhap;
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

    public List<ChiTietPhieuNhap> getCTPhieuNhap() {
        return CTPhieuNhap;
    }

    public void setCTPhieuNhap(List<ChiTietPhieuNhap> CTPhieuNhap) {
        this.CTPhieuNhap = CTPhieuNhap;
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
