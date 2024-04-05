/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.TacGia;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class TacGiaDAO {
   public void insert(TacGia model){
       String sql="Insert into qltacgia values(?,?)";
       utils.JDBCHelper.update(sql, 
               model.getMatg(),
               model.getTentg());
   }
   public void update(TacGia model){
        String sql="UPDATE qltacgia SET tentacgia = ? WHERE matacgia = ?";
        utils.JDBCHelper.update(sql, 
                model.getTentg(), 
                model.getMatg());
                }
    public void delete(TacGia tg){
        String sql="DELETE FROM qltacgia WHERE matacgia = ?";
        utils.JDBCHelper.update(sql, tg);
    }
    
    public TacGia selectById(String matg){
        String sql = "SELECT * FROM qltacgia WHERE matacgia = ?";
        List<TacGia> list = this.SelectBySQL(sql, matg);
        return list.size() > 0 ? list.get(0) : null;
    }
    public TacGia selectByName(TacGia tentg){
        String sql = "SELECT * FROM qltacgia WHERE tentacgia = ?";
        List<TacGia> list = this.SelectBySQL(sql, tentg);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<TacGia> SelectBySQL(String sql, Object... args) {
        List<TacGia> listtl = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while (rs.next()) {
                    TacGia st = new TacGia();
                    st.setMatg(rs.getString(1));
                    st.setTentg(rs.getString(2));
                    listtl.add(st);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listtl;
    }

    public List<TacGia> SelectAll() {
        String sql = "SELECT * FROM qltacgia";
        return SelectBySQL(sql);
    }
    
    public List<TacGia> SelectByKeyword(String keyword) {
        String sql = "SELECT * FROM qltacgia WHERE tentacgia LIKE ?";
        return SelectBySQL(sql, "%" + keyword + "%");
    }
}
