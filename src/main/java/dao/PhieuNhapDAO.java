package dao;

import entities.PhieuNhap;
import utils.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhieuNhapDAO {
    String insert_SQL = "insert into PhieuNhap values(?,?,?,?,?)";
    String update_SQL = "update PhieuNhap set NgayTao = ?, MaNV = ?, MaNhaCC = ?, TongTien = ? where MaNhap = ?";
    String delete_SQL = "delete from PhieuNhap where MaNhap = ?";
    String selectById_SQL = "select * from PhieuNhap where MaNhap = ?";
    String selectAll_SQL = "select * from PhieuNhap";

    public void insert(PhieuNhap model) {
        JDBCHelper.update(insert_SQL, model.getMaNhap(), model.getNgayNhap(), model.getMaNV(), model.getMaNhaCC(), model.getTongTien());
    }

    public void update(PhieuNhap model) {
        JDBCHelper.update(update_SQL, model.getNgayNhap(), model.getMaNV(), model.getMaNhaCC(), model.getTongTien(), model.getMaNhap());
    }

    public void delete(Object key) {
        JDBCHelper.update(delete_SQL, key);
    }

    public List<PhieuNhap> selectAll() {
        return selectBySQL(selectAll_SQL);
    }

    public PhieuNhap selectById(String maNhap) {
        List<PhieuNhap> list = selectBySQL(selectById_SQL, maNhap);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public List<PhieuNhap> selectBySQL(String sql, Object... args) {
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            List<PhieuNhap> list = new ArrayList<>();
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaNhap(rs.getString(1));
                pn.setNgayNhap(rs.getTimestamp(2));
                pn.setMaNV(rs.getString(3));
                pn.setMaNhaCC(rs.getString(4));
                pn.setTongTien(rs.getDouble(5));
                list.add(pn);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<PhieuNhap> selectByKeyword(String keyword, String fromDate, String toDate, String minPrice, String maxPrice) {
        String sql = "select * from PhieuNhap where (MaNhap like ? OR MaNV like ? OR MaNhaCC like ? OR TongTien like ?) ";
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

        return new PhieuNhapDAO().selectBySQL(sql, params.toArray());
    }
}
