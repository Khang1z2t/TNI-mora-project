/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Luong;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.JDBCHelper;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class LuongDAO {
    public Luong selectById(String cap) {
        String sql = "SELECT * FROM luong WHERE cap = ?";
        List<Luong> list = this.SelectBySQL(sql, cap);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Luong> SelectBySQL(String sql, Object... args) {
        List<Luong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while (rs.next()) {
                    Luong st = new Luong();
                    st.setCap(rs.getString(1));
                    st.setLuong(rs.getInt(2));
                    list.add(st);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Luong> SelectAll() {
        String sql = "SELECT * FROM luong";
        return SelectBySQL(sql);
    }

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

    public List<Object[]> getBangLuong(int thang) {
        String sql = "{CALL sp_BangLuong (?)}";
        String[] cols = {"Mã NV", "Tên NV", "Vai Trò", "Cấp", "Lương"};
        return this.getListOfArray(sql, cols, thang);
    }

    public List<Integer> selectThang() {
        String sql = "SELECT DISTINCT MONTH(NGAYDK) MONTH FROM NguoiDung ORDER BY MONTH DESC";
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
}
