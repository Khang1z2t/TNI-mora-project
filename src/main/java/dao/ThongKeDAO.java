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

    private List<Double[]> getListOfArrayDB(String sql, String[] cols, Double... args) {
        try {
            List<Double[]> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                Double[] vals = new Double[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getDouble(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> selectThangHD() {
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
    
    public List<Integer> selectThangTK() {
        String sql = "SELECT DISTINCT MONTH(ngaytao) MONTH FROM phieunhap ORDER BY MONTH DESC";
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
    
        public List<Double[]> getThongKe(double thang){
            String sql = "{CALL sp_ThongKe (?)}";
            String[] cols = { "Tổng tiền đã nhập", "Tổng tiền đã bán", "Tổng tiền trả lương"};
            return this.getListOfArrayDB(sql, cols,thang);
        }
        
        public List<Double[]> getTKSL(double thang){
            String sql = "{CALL sp_TongSLNhapBanTonKho (?)}";
            String [] cols = {"Tổng số lượng sách đã nhập trong tháng", "Tổng số lượng sách đã bán trong tháng", "Tổng số lượng sách còn trong kho"};
            return this.getListOfArrayDB(sql, cols,thang);
        }

        public Integer getSoLuongDon(String id){
            String sql = "{CALL sp_SoLuongDonHang (?)}";
            try {
                ResultSet rs = JDBCHelper.query(sql, id);
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        }

        public List<Object[]> getTopDoanhThu() {
            String sql = "{CALL sp_getTopDoanhThu}";
            String[] cols = {"TongTienBan", "ThongKe"};
            return this.getListOfArray(sql, cols) ;
        }

        public List<Object[]> getTopNV(){
            String sql = "{CALL sp_getTopNVBan}";
            String[] cols = {"TenNV", "SoDon", "TongTien"};
            return this.getListOfArray(sql, cols);
        }

        public List<Object[]> getTopSach(){
            String sql = "{CALL sp_getTopSachBan}";
            String[] cols = {"TenSach", "SoLuongBan"};
            return this.getListOfArray(sql, cols);
        }


}
