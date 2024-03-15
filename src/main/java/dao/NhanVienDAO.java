/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entities.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    public void insert(NhanVien model){
        String sql="INSERT INTO NHANVIEN VALUES (?, ?, ?, ?, ?,?)";
        utils.JDBCHelper.update(sql, 
                model.getMaNhanVien(), 
                model.getMatKhau(), 
                model.getHoVaTen(), 
                model.getEmail(),
                model.isVaiTro(),
                model.isSA());
    }
    
    public void update(NhanVien model){
        String sql="UPDATE NHANVIEN SET MATKHAU = ?, HOTEN = ?, EMAIL = ? ,VAITRO = ?,isSuperAdmin =? WHERE MANV = ?";
        utils.JDBCHelper.update(sql, 
                model.getMatKhau(), 
                model.getHoVaTen(), 
                model.getEmail(),
                model.isVaiTro(),
                model.isSA(),
                model.getMaNhanVien());
    }
    
    public void delete(String MaNV){
        String sql="DELETE FROM NHANVIEN WHERE MANV = ?";
        utils.JDBCHelper.update(sql, MaNV);
    }
    
    public List<NhanVien> selectAll(){
        String sql="SELECT * FROM NHANVIEN";
        return this.selectBySql(sql);
    }
    
    public NhanVien selectById(String manv){
        String sql = "SELECT * FROM NHANVIEN WHERE MANV = ?";
        List<NhanVien> list = this.selectBySql(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<NhanVien> selectBySql(String sql, Object...args){
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while(rs.next()){
                    NhanVien entity = new NhanVien();
                    entity.setMaNhanVien(rs.getString(1));
                    entity.setMatKhau(rs.getString(2));
                    entity.setHoVaTen(rs.getString(3));
                    entity.setEmail(rs.getString(4));
                    entity.setVaiTro(rs.getBoolean(5));
                    entity.setSA(rs.getBoolean(6));
                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}
