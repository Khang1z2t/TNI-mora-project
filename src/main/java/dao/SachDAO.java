/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Sach;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JDBCHelper;
import utils.XDate;
/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class SachDAO {
   public void insert(Sach model){
       String sql="Insert into sach values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
       utils.JDBCHelper.update(sql, 
               model.getMaSach(),
               model.getTenSach(),
               model.getNamXB(),    
               model.getNhaXB(),
               model.getGia(),
               model.getTentacgia(),
               model.getTheloai(),
               model.getGhiChu(),
                model.getHinh(),
                model.getMaTheLoai(),
                model.getMaTacGia(),
                model.getSoluong(),
                model.getNgayton());
   }
   public void update(Sach model){
        String sql="UPDATE sach SET tensach = ?, namXB = ?, nhaXB = ?, tentacgia = ?,theloai =?, GhiChu = ?,hinh = ?,matheloai =?,matacgia=? ,soluong=?,ngayton = getdate() WHERE masach = ?";
        utils.JDBCHelper.update(sql, 
                model.getTenSach(), 
                model.getNamXB(), 
                model.getNhaXB(),
                model.getTentacgia(),
                model.getTheloai(),
                model.getGhiChu(),
                model.getHinh(),
                model.getMaTheLoai(),
                model.getMaTacGia(),
                model.getSoluong(),
                model.getNgayton(),
                //
                model.getMaSach());
    }
    //Update so luong
   public void updateSL(Sach model){
       String sql = "update sach set soluong = ?,ngayton = getdate() where masach = ?";
       utils.JDBCHelper.update(sql, 
               model.getNgayton(),
               model.getSoluong(),
               model.getMaSach());
   }
    public void delete(String MaNV){
        String sql="DELETE FROM sach WHERE masach = ?";
        utils.JDBCHelper.update(sql, MaNV);
    }
    
    public Sach selectById(String masach){
        String sql = "SELECT * FROM sach WHERE masach = ?";
        List<Sach> list = this.SelectBySQL(sql, masach);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Sach> SelectBySQL(String sql, Object... args) {
        List<Sach> listS = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while (rs.next()) {
                    Sach st = new Sach();
                    st.setMaSach(rs.getString(1));
                    st.setTenSach(rs.getString(2));
                    st.setNamXB(rs.getInt(3));
                    st.setNhaXB(rs.getString(4));
                    st.setGia(rs.getInt(5));
                    st.setTentacgia(rs.getString(6));
                    st.setTheloai(rs.getString(7));
                    st.setGhiChu(rs.getString(8));
                    st.setHinh(rs.getString(9));
                    st.setMaTheLoai(rs.getString(10));
                    st.setMaTacGia(rs.getString(11));
                    st.setSoluong(rs.getInt(12));
                    st.setNgayton(rs.getDate(13));
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
    public List<Sach> SelectAll() {
        String sql = "SELECT * FROM sach";
        return SelectBySQL(sql);
    }
           public List<String> getNgayton() {
            String sql = "SELECT DISTINCT ngayton FROM sach ORDER BY ngayton DESC";
            List<String> list = new ArrayList<>();
            try {
                ResultSet rs = null;
                try {
                    rs = utils.JDBCHelper.query(sql);
                    while (rs.next()) {
                        Date ngayton = rs.getDate("ngayton");
                        list.add(XDate.toString(ngayton, "dd-MM-yyyy"));
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
    public List<Sach> SelectByKeyword(String keyword) {
        String sql = "SELECT * FROM sach WHERE tensach LIKE ?";
        return SelectBySQL(sql, "%" + keyword + "%");
    }
    public List<Sach> selectByTheLoai(String Matl) {
        String sql = "SELECT * FROM Sach WHERE theloai = ?";
        return SelectBySQL(sql, Matl);
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
public List<Object[]> saveHangTon(List<Sach> sachList) {
    String sql = "{CALL TenProc (?, ?, ?, ?)}";
    String[] cols = {"masach", "tensach", "soluong", "ngayton"};

    List<Object[]> resultList = new ArrayList<>();

    try {
        for (Sach sach : sachList) {
            ResultSet rs = JDBCHelper.query(sql, sach.getMaSach(), sach.getTenSach(), sach.getSoluong(), new java.sql.Date(sach.getNgayton().getTime()));

            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                resultList.add(vals);
            }

            rs.getStatement().getConnection().close();
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    return resultList;
}
        public List<Object[]> getHangTon(Date ngayton) {
            String sql = "{CALL sp_HangTon (?)}";
            String[] cols = {"masach", "tensach", "soluong"};
            return this.getListOfArray(sql, cols, ngayton);
        }
        public List<Sach> getHang(Date ngayton){
            String sql = "select masach,tensach,soluong from sach where ngayton = ?";
            return SelectBySQL(sql, ngayton);
        }
}
