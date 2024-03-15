/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Kho;
import entities.Sach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.JDBCHelper;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class KhoDAO {
   public void insert(Kho model){
       String sql="Insert into khosach values(?,?,?,?,?)";
       utils.JDBCHelper.update(sql, 
               model.getMakho(),
               model.getMasach(),
               model.getTensach(),
               model.getSoluong(),
               model.getGhiChu(),
                model.getNgaytao());
   }
   public void update(Kho model){
        String sql="UPDATE khosach set makho=?, masach = ?,tensach= ?,soluong = ?, ghichu=?,ngaytao =? WHERE makho = ?";
        utils.JDBCHelper.update(sql, 
               model.getMakho(),
               model.getMasach(),
               model.getTensach(),
               model.getSoluong(),
               model.getGhiChu(),
                model.getNgaytao());
    }
    
    public void delete(String Ma){
        String sql="DELETE FROM khosach WHERE makho = ?";
        utils.JDBCHelper.update(sql, Ma);
    }
    
    public Kho selectById(String makho){
        String sql = "SELECT * FROM Khosach WHERE makho = ?";
        List<Kho> list = this.SelectBySQL(sql, makho);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Kho> SelectBySQL(String sql, Object... args) {
        List<Kho> listS = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while (rs.next()) {
                    Kho st = new Kho();
                    st.setMakho(rs.getInt(1));
                    st.setMasach(rs.getString(2));
                    st.setTensach(rs.getString(3));
                    st.setSoluong(rs.getInt(4));
                    st.setGhiChu(rs.getString(5));
                    st.setNgaytao(rs.getDate(6));
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
        public List<Date> getDate() {
            String sql = "SELECT DISTINCT ngayton FROM khosach ORDER BY ngayton DESC";
            List<Date> list = new ArrayList<>();
            try {
                ResultSet rs = null;
                try {
                    rs = utils.JDBCHelper.query(sql);
                    while (rs.next()) {
                        list.add(rs.getDate(1));
                    }
                } finally {
                    if (rs != null) {
                        rs.getStatement().getConnection().close();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
            return list;
        }


    public List<Kho> SelectAll() {
        String sql = "SELECT * FROM khosach";
        return SelectBySQL(sql);
    }
    
    public List<Kho> SelectByKeyword(String keyword) {
        String sql = "SELECT * FROM sach WHERE tensach LIKE ?";
        return SelectBySQL(sql, "%" + keyword + "%");
    }
    public List<Kho> selectBySach(String masach) {
        String sql = "SELECT * FROM khosach WHERE MAsach = ?";
        return SelectBySQL(sql, masach);
    }
    
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list=new ArrayList<>();
            ResultSet rs = JDBCHelper.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i=0; i<cols.length; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Object[]> getHangTon(Date ngayton){
        String sql = "{CALL sp_HangTon (?)}";
        String[] cols = {"Mã sách","Tên sách","Số lượng"};
        return this.getListOfArray(sql, cols, ngayton);
    }
}
