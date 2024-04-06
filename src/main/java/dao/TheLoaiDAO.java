/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.TheLoai;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class TheLoaiDAO {
   public void insert(TheLoai model){
       String sql="Insert into qltheloai values(?,?)";
       utils.JDBCHelper.update(sql, 
               model.getMaTheLoai(),
               model.getTenTheLoai());
   }
   public void update(TheLoai model){
        String sql="UPDATE qltheloai SET tentheloai = ? WHERE matheloai = ?";
        utils.JDBCHelper.update(sql, 
                model.getTenTheLoai(), 
                model.getMaTheLoai());
                }
    public void delete(int ma){
        String sql="DELETE FROM qltheloai WHERE matheloai = ?";
        utils.JDBCHelper.update(sql, ma);
    }
    
    public TheLoai selectById(int matl){
        String sql = "SELECT * FROM qltheloai WHERE matheloai = ?";
        List<TheLoai> list = this.SelectBySQL(sql, matl);
        return list.size() > 0 ? list.get(0) : null;
    }
    public TheLoai selectByName(TheLoai tentl){
        String sql = "SELECT * FROM qltheloai WHERE tentheloai = ?";
        List<TheLoai> list = this.SelectBySQL(sql, tentl);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<TheLoai> SelectBySQL(String sql, Object... args) {
        List<TheLoai> listtl = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while (rs.next()) {
                    TheLoai st = new TheLoai();
                    st.setMaTheLoai(rs.getInt(1));
                    st.setTenTheLoai(rs.getString(2));
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

    public List<TheLoai> SelectAll() {
        String sql = "SELECT * FROM qltheloai";
        return SelectBySQL(sql);
    }
    
    public List<TheLoai> SelectByKeyword(String keyword) {
        String sql = "SELECT * FROM qltheloai WHERE tentheloai LIKE ?";
        return SelectBySQL(sql, "%" + keyword + "%");
    }
}
