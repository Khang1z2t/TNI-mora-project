/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.ThanhVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class ThanhVienDAO {
       public void insert(ThanhVien model){
       String sql="Insert into ThanhVien values(?,?,?,?,?)";
       utils.JDBCHelper.update(sql, 
               model.getMaTV(),
               model.getTenTV(),
               model.getNgaysinh(),
               model.isGioitinh(),
               model.getDiem());
   }
   public void update(ThanhVien model){
       String sql = "update ThanhVien set tenthanhvien = ?, ngaysinh = ?, gioitinh = ?, diem = ? where mathanhvien = ?";
       utils.JDBCHelper.update(sql, 
               model.getTenTV(),
               model.getNgaysinh(),
               model.isGioitinh(),
               model.getDiem(),
               model.getMaTV());
    }
    public void delete(int ma){
        String sql="DELETE FROM ThanhVien WHERE maThanhVien = ?";
        utils.JDBCHelper.update(sql, ma);
    }
    
    public ThanhVien selectById(int ma){
        String sql = "SELECT * FROM ThanhVien WHERE mathanhvien = ?";
        List<ThanhVien> list = this.SelectBySQL(sql, ma);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<ThanhVien> SelectBySQL(String sql, Object... args) {
        List<ThanhVien> listS = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while (rs.next()) {
                    ThanhVien st = new ThanhVien();
                    st.setMaTV(rs.getInt(1));
                    st.setTenTV(rs.getString(2));
                    st.setNgaysinh(rs.getDate(3));
                    st.setGioitinh(rs.getBoolean(4));
                    st.setDiem(rs.getInt(5));
                    listS.add(st);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listS;
    }
    public List<ThanhVien> SelectAll() {
        String sql = "SELECT * FROM thanhvien";
        return SelectBySQL(sql);
    }
}
