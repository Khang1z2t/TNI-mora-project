/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.NguoiDung;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class NguoiDungDAO {
    public void insert(NguoiDung nd) {
        String sql = "INSERT INTO NGUOIDUNG VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        utils.JDBCHelper.update(sql,
                nd.getMaNguoiDung(),
                nd.getTenNguoiDung(), 
                nd.isGioiTinh(),
                nd.getNgaySinh(),
                nd.getDienThoai(),
                nd.getEmail(),
                nd.getMaNhanVien(),
                nd.getNgayDangKi()
        );

    }
    
    public void update(NguoiDung nd) {
        String sql = "UPDATE NGUOIDUNG SET HOTEN = ?, GIOITINH= ?, NGAYSINH = ?, DIENTHOAI = ?, EMAIL = ?, MANV = ? WHERE MAND = ?";
        
        utils.JDBCHelper.update(sql, 
                nd.getTenNguoiDung(), 
                nd.isGioiTinh(),
                nd.getNgaySinh(),
                nd.getDienThoai(),
                nd.getEmail(),
                nd.getMaNhanVien(),
                nd.getMaNguoiDung()
                );
    }
    
    public void delete(NguoiDung nd) {
        String sql = "DELETE FROM NGUOIDUNG WHERE MAND = ?";
        utils.JDBCHelper.update(sql, nd.getMaNguoiDung());
    }
    
    public NguoiDung selectById(String maND){
        String sql = "SELECT * FROM NGUOIDUNG WHERE MAND = ?";
        List<NguoiDung> list = this.SelectBySQL(sql, maND);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<NguoiDung> SelectBySQL(String sql, Object... args) {
        List<NguoiDung> lstNguoiDung = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while (rs.next()) {
                    NguoiDung st = new NguoiDung();
                    st.setMaNguoiDung(rs.getString(1));
                    st.setTenNguoiDung(rs.getString(2));
                    st.setGioiTinh((rs.getInt(3) != 0));
                    st.setNgaySinh(rs.getDate(4));
                    st.setDienThoai(rs.getString(5));
                    st.setEmail(rs.getString(6));
                    lstNguoiDung.add(st);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lstNguoiDung;
    }

    public List<NguoiDung> SelectAll() {
        String sql = "SELECT * FROM NGUOIDUNG";
        return SelectBySQL(sql);
    }
    
    public List<NguoiDung> SelectByKeyword(String keyword) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE HOTEN LIKE ?";
        return SelectBySQL(sql, "%" + keyword + "%");
    }
    
}
