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
       String sql="Insert into giohang values(?,?,?,?)";
       utils.JDBCHelper.update(sql, 
               model.getTensach(),    
               model.getGia(),
               model.getSoluong(),
               model.getManv());
   }
   public void update(Giohang model){
        String sql="UPDATE giohang SET soluong = ? WHERE magiohang = ?";
        utils.JDBCHelper.update(sql, 
                model.getSoluong(),
                //
                model.getMagiohang());
    }
    public void delete(int magiohang){
        String sql="DELETE FROM giohang WHERE magiohang = ?";
        utils.JDBCHelper.update(sql, magiohang);
    }
    public void reset(){
        String sql="delete from giohang";
        utils.JDBCHelper.update(sql);
    }
    public Giohang selectById(int magiohang){
        String sql = "SELECT * FROM Giohang WHERE magiohang = ?";
        List<Giohang> list = this.SelectBySQL(sql, magiohang);
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
                    st.setMagiohang(rs.getInt(1));
                    st.setTensach(rs.getString(2));
                    st.setGia(rs.getInt(3));
                    st.setSoluong(rs.getInt(4));
                    st.setManv(rs.getString(5));
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
