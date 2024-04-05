/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Sach;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class SachDAO {
   public void insert(Sach model){
       String sql="Insert into sach values(?,?,?,?,?,?,?,?,?,?,?,?)";
       utils.JDBCHelper.update(sql, 
               model.getMaSach(),
               model.getTenSach(),
               model.getNamXB(),    
               model.getNhaXB(),
               model.getGia(),
               model.getSoLuong(),
               model.getTentacgia(),
               model.getTheloai(),
               model.getGhiChu(),
                model.getHinh(),
                model.getMaTheLoai(),
                model.getMaTacGia());
   }
   public void update(Sach model){
        String sql="UPDATE sach SET tensach = ?, namXB = ?, nhaXB = ?, gia = ?, soluong = ?, tentacgia = ?,theloai =?, GhiChu = ?,hinh = ?,matheloai =?,matacgia=? WHERE masach = ?";
        utils.JDBCHelper.update(sql, 
                model.getTenSach(), 
                model.getNamXB(), 
                model.getNhaXB(),
                model.getGia(),
                model.getSoLuong(),
                model.getTentacgia(),
                model.getTheloai(),
                model.getGhiChu(),
                model.getHinh(),
                model.getMaTheLoai(),
                model.getMaTacGia(),
                //
                model.getMaSach());
    }
    //Update so luong
//   public void updateSL(Sach model){
//       String sql = "update sach set soluong = ?,ngayton = getdate() where masach = ?";
//       utils.JDBCHelper.update(sql, 
//               model.getNgayton(),
//               model.getSoluong(),
//               model.getMaSach());
//   }
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
                    st.setSoLuong(rs.getInt(6));
                    st.setTentacgia(rs.getString(7));
                    st.setTheloai(rs.getString(8));
                    st.setGhiChu(rs.getString(9));
                    st.setHinh(rs.getString(10));
                    st.setMaTheLoai(rs.getString(11));
                    st.setMaTacGia(rs.getString(12));
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
//           public List<String> getNgayton() {
//            String sql = "SELECT DISTINCT ngayton FROM sach ORDER BY ngayton DESC";
//            List<String> list = new ArrayList<>();
//            try {
//                ResultSet rs = null;
//                try {
//                    rs = utils.JDBCHelper.query(sql);
//                    while (rs.next()) {
//                        Date ngayton = rs.getDate("ngayton");
//                        list.add(XDate.toString(ngayton, "dd-MM-yyyy"));
//                    }
//                } finally {
//                    if (rs != null) {
//                        rs.getStatement().getConnection().close();
//                    }
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                throw new RuntimeException(ex);
//            }
//            return list;
//        }
    public List<Sach> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM sach WHERE (MaSach LIKE ? OR tensach LIKE ? OR NamXB LIKE ? OR NhaXB LIKE ? OR Gia LIKE ? OR TenTacGia LIKE ? OR TheLoai LIKE ?)";
        String key = "%" + keyword + "%";
        return SelectBySQL(sql, key, key, key, key, key, key, key);
    }
    public List<Sach> selectByTheLoai(String Matl) {
        String sql = "SELECT * FROM Sach WHERE theloai = ?";
        return SelectBySQL(sql, Matl);
    }
//    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
//        try {
//            List<Object[]> list=new ArrayList<>();
//            ResultSet rs = JDBCHelper.query(sql, args);
//            while(rs.next()){
//                Object[] vals = new Object[cols.length];
//                for(int i=0; i<cols.length; i++){
//                    vals[i] = rs.getObject(cols[i]);
//                }
//                list.add(vals);
//            }
//            rs.getStatement().getConnection().close();
//            return list;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//public List<Object[]> saveHangTon(List<Sach> sachList) {
//    String sql = "{CALL TenProc (?, ?, ?, ?)}";
//    String[] cols = {"masach", "tensach", "soluong", "ngayton"};
//
//    List<Object[]> resultList = new ArrayList<>();
//
//    try {
//        for (Sach sach : sachList) {
//            ResultSet rs = JDBCHelper.query(sql, sach.getMaSach(), sach.getTenSach(), sach.getSoluong(), new java.sql.Date(sach.getNgayton().getTime()));
//
//            while (rs.next()) {
//                Object[] vals = new Object[cols.length];
//                for (int i = 0; i < cols.length; i++) {
//                    vals[i] = rs.getObject(cols[i]);
//                }
//                resultList.add(vals);
//            }
//
//            rs.getStatement().getConnection().close();
//        }
//    } catch (Exception e) {
//        throw new RuntimeException(e);
//    }
//
//    return resultList;
//}
//        public List<Object[]> getHangTon(Date ngayton) {
//            String sql = "{CALL sp_HangTon (?)}";
//            String[] cols = {"masach", "tensach", "soluong"};
//            return this.getListOfArray(sql, cols, ngayton);
//        }
//        public List<Sach> getHang(Date ngayton){
//            String sql = "select masach,tensach,soluong from sach where ngayton = ?";
//            return SelectBySQL(sql, ngayton);
//        }
}
