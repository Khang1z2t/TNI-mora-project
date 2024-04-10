/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.JDBCHelper;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
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

    public List<Integer> selectThang() {
        String sql = "SELECT DISTINCT MONTH(ngaytao) MONTH FROM hoadon ORDER BY MONTH DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql);
                while (rs.next()) {
                    list.add(rs.getInt(1));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<Object[]> getSoLuongNV() {
        String sql = "{CALL sp_TongSoLuongNhanVien}";
        String[] cols = {"Tổng số lượng Nhân viên"};
        return this.getListOfArray(sql, cols);
    }
    
    public List<Object[]> getTongTien(int thang) {
        String sql = "{CALL sp_TongTienDaBan_TheoThang (?)}";
        String[] cols = {"Tổng tiền đã bán trong tháng"};
        return this.getListOfArray(sql, cols, thang);
    }
    
    public List<Object[]> getSoLuongSach(int thang) {
        String sql = "{CALL sp_TongSoLuongSachDaBan_TheoThang (?)}";
        String[] cols = {"Tổng số lượng sách đã bán trong tháng"};
        return this.getListOfArray(sql, cols, thang);
    }
    
        public List<Object[]> getTongTienLuong() {
        String sql = "{CALL sp_TongTienDaChiTraLuong}";
        String[] cols = {"Tổng tiền đã chi trả cho lương nhân viên"};
        return this.getListOfArray(sql, cols);
    }
        
        public List<Object[]> getThongKe(){
            String sql = "{CALL sp_ThongKe}";
            String[] cols = {"Tổng số lượng sách đã bán", "Tổng số lượng Nhân viên", "Tổng tiền đã bán", "Tổng tiền trả lương"};
            return this.getListOfArray(sql, cols);
        }
}
