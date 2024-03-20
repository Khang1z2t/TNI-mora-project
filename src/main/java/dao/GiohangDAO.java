/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Giohang;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class GiohangDAO {
       public void insert(Giohang model){
       String sql="Insert into sach values(?,?,?,?,?,?)";
       utils.JDBCHelper.update(sql, 
               model.getGiohang(),
               model.getMasach(),
               model.getTensach(),    
               model.getGia(),
               model.getSoluong(),
               model.getManv());
   }
   public void update(Giohang model){
        String sql="UPDATE sach SET soluong = ? WHERE giohang = ?";
        utils.JDBCHelper.update(sql, 
                model.getSoluong(),
                //
                model.getGiohang());
    }
    public void delete(String MaNV){
        String sql="DELETE FROM giohang WHERE giohang = ?";
        utils.JDBCHelper.update(sql, MaNV);
    }
    
    public Giohang selectById(String masach){
        String sql = "SELECT * FROM Giohang WHERE giohang = ?";
        List<Giohang> list = this.SelectBySQL(sql, masach);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Giohang> SelectBySQL(String sql, Object... args) {
        List<Giohang> listS = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while (rs.next()) {
                    Giohang st = new Giohang();
                    st.setGiohang(rs.getInt(1));
                    st.setMasach(rs.getString(2));
                    st.setTensach(rs.getString(3));
                    st.setGia(rs.getInt(4));
                    st.setSoluong(rs.getInt(5));
                    st.setManv(rs.getString(6));
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
    public List<Giohang> SelectAll() {
        String sql = "SELECT * FROM giohang";
        return SelectBySQL(sql);
    }
}
