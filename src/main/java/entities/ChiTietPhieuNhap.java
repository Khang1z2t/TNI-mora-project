package entities;

public class ChiTietPhieuNhap {
    private String MaNhap;
    private String Masach;
    private int Soluong;
    private double Gia;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(String maNhap, String masach, int soluong, double gia) {
        MaNhap = maNhap;
        Masach = masach;
        Soluong = soluong;
        Gia = gia;
    }

    public String getMaNhap() {
        return MaNhap;
    }

    public void setMaNhap(String maNhap) {
        MaNhap = maNhap;
    }

    public String getMasach() {
        return Masach;
    }

    public void setMasach(String masach) {
        Masach = masach;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        Gia = gia;
    }
}
