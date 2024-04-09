package entities;

public class NhaCungCap {
    private String MaNhaCC;
    private String TenNhaCC;
    private String SDT;
    private String DiaChi;

    public NhaCungCap() {
    }

    public NhaCungCap(String maNhaCC, String tenNhaCC, String SDT, String diaChi) {
        MaNhaCC = maNhaCC;
        TenNhaCC = tenNhaCC;
        this.SDT = SDT;
        DiaChi = diaChi;
    }

    public String getMaNhaCC() {
        return MaNhaCC;
    }

    public void setMaNhaCC(String maNhaCC) {
        MaNhaCC = maNhaCC;
    }

    public String getTenNhaCC() {
        return TenNhaCC;
    }

    public void setTenNhaCC(String tenNhaCC) {
        TenNhaCC = tenNhaCC;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    @Override
    public String toString() {
        return TenNhaCC;
    }


}
