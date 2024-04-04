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
       String sql="Insert into giohang values(?,?,?,?,?)";
       utils.JDBCHelper.update(sql, 
               model.getMasach(),
               model.getTensach(),
               model.getGia(),
               model.getSoluong(),
               model.getManv());
   }
   public void update(Giohang model){
    // Kiểm tra xem có mặt hàng trong giỏ hàng có tên giống với mặt hàng cần cập nhật không
        String sqlCheck = "SELECT * FROM giohang WHERE masach = ?";
        List<Giohang> list = this.SelectBySQL(sqlCheck, model.getMasach());

        // Nếu có mặt hàng cùng tên trong giỏ hàng
        if (!list.isEmpty()) {
            // Lấy ra mặt hàng đầu tiên trong danh sách
            Giohang existingItem = list.get(0);

            // Cập nhật số lượng mới bằng cách cộng thêm số lượng mới vào số lượng hiện tại
            int newQuantity = existingItem.getSoluong() + model.getSoluong();

            // Cập nhật số lượng mới cho mặt hàng đã tồn tại
            String sqlUpdate = "UPDATE giohang SET soluong = ? WHERE masach = ?";
            utils.JDBCHelper.update(sqlUpdate, newQuantity, existingItem.getMasach());
        } else {
            // Nếu không có mặt hàng cùng tên trong giỏ hàng, thêm mặt hàng mới vào giỏ hàng
            String sqlInsert = "INSERT INTO giohang (masach, tensach,gia, soluong, manv) VALUES (?, ?, ?, ?, ?)";
            utils.JDBCHelper.update(sqlInsert, 
                                    model.getMasach(),   
                                    model.getTensach(),
                                    model.getGia(),
                                    model.getSoluong(),
                                    model.getManv());
        }
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
    
    public Giohang selectbyMasach(String masach){
        String sql = "select * from giohang where masach = ?";
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
                    st.setMagiohang(rs.getInt(1));
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
