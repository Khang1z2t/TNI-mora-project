/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Hoadon;
import entities.PhieuNhap;
import utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author NGUYEN THI NGUYET VY
 */
public class HoadonDAO {
    private String insert_SQL = "insert into Hoadon values(?,?,?,?,?)";
    private String update_SQL = "update Hoadon set NgayTao = ?, MaNV = ?, mathanhvien = ?, TongTien = ? where MaHoaDon = ?";
    private String delete_SQL = "delete from Hoadon where MaHoaDon = ?";
    private String selectById_SQL = "select * from Hoadon where MaHoaDon = ?";
    private String selectAll_SQL = "select * from Hoadon";

    public void insert(Hoadon model) {
        JDBCHelper.update(insert_SQL, model.getMaHoaDon(), model.getNgayTao(), model.getMaNV(), model.getMaTV(), model.getTongTien());
    }

    public void update(Hoadon model) {
        JDBCHelper.update(update_SQL, model.getNgayTao(), model.getMaNV(), model.getMaTV(), model.getTongTien(), model.getMaHoaDon());
    }

    public void delete(Object key) {
        JDBCHelper.update(delete_SQL, key);
    }

    public List<Hoadon> selectAll() {
        return selectBySQL(selectAll_SQL);
    }

    public Hoadon selectById(String maHoaDon) {
        List<Hoadon> list = selectBySQL(selectById_SQL, maHoaDon);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public List<Hoadon> selectBySQL(String sql, Object... args) {
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            List<Hoadon> list = new ArrayList<>();
            while (rs.next()) {
                Hoadon hd = new Hoadon();
                hd.setMaHoaDon(rs.getString(1));
                hd.setNgayTao(rs.getTimestamp(2));
                hd.setMaNV(rs.getString(3));
                hd.setMaTV(rs.getInt(4));
                hd.setTongTien(rs.getDouble(5));
                list.add(hd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Hoadon> selectByKeyword(String keyword, String fromDate, String toDate, String minPrice, String maxPrice) {
        String sql = "select * from HoaDon where (MaHoaDon like ? OR MaNV like ? OR MaThanhVien like ? OR TongTien like ?) ";
        String key = "%" + keyword + "%";
        List<Object> params = new ArrayList<>(Arrays.asList(key, key, key, key));


        if (!fromDate.isEmpty() && !toDate.isEmpty()) {
            sql += "AND (NgayTao BETWEEN ? AND ?)";
            params.add(fromDate);
            params.add(toDate);
        }

        if (!minPrice.isEmpty() && !maxPrice.isEmpty()) {
            sql += "AND (TongTien BETWEEN ? AND ?)";
            params.add(minPrice);
            params.add(maxPrice);
        }

        return new HoadonDAO().selectBySQL(sql, params.toArray());
    }
}
