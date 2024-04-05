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
public class Sach {
    private String tenSach;
    private String maSach;
    private int namXB;
    private int gia;
    private int soLuong;
    private String tentacgia;
    private String theloai;
    private String GhiChu;
    private String Hinh;
    private String NhaXB;
    private String maTheLoai;
    private String maTacGia;

    public Sach() {
    }

    @Override
    public String toString() {
        return maSach + "-" + tenSach;
    }

    public Sach(String tenSach, String maSach, int namXB, int gia, int soLuong, String tentacgia, String theloai, String ghiChu, String hinh, String nhaXB, String maTheLoai, String maTacGia) {
        this.tenSach = tenSach;
        this.maSach = maSach;
        this.namXB = namXB;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tentacgia = tentacgia;
        this.theloai = theloai;
        GhiChu = ghiChu;
        Hinh = hinh;
        NhaXB = nhaXB;
        this.maTheLoai = maTheLoai;
        this.maTacGia = maTacGia;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTentacgia() {
        return tentacgia;
    }

    public void setTentacgia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public String getNhaXB() {
        return NhaXB;
    }

    public void setNhaXB(String nhaXB) {
        NhaXB = nhaXB;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }
}