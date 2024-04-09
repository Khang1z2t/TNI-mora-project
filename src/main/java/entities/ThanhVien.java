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
public class ThanhVien {
    private int maTV; // mathanhvien la sdt
    private String tenTV;
    private Date ngaysinh;
    private boolean gioitinh; //Nam 0 - Nu 1
    private int diem;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String tenTV, Date ngaysinh, boolean gioitinh, int diem) {
        this.maTV = maTV;
        this.tenTV = tenTV;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.diem = diem;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }


}
