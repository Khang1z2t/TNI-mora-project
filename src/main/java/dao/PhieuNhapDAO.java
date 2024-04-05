package dao;

import entities.PhieuNhap;
import utils.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {
    String insert_SQL = "insert into PhieuNhap values(?,?,?,?,?)";
    String update_SQL = "update PhieuNhap set NgayNhap = ?, MaNV = ?, MaNhaCC = ?, TongTien = ? where MaNhap = ?";
    String delete_SQL = "delete from PhieuNhap where MaNhap = ?";
    String selectById_SQL = "select * from PhieuNhap where MaNhap = ?";
    String selectAll_SQL = "select * from PhieuNhap";

    public void insert(PhieuNhap model) {
        JDBCHelper.update(insert_SQL, model.getMaNhap(), model.getNgayNhap(), model.getMaNV(), model.getMaNhaCC(), model.getTongTien());
    }

    public void update(PhieuNhap model) {
        JDBCHelper.update(update_SQL, model.getNgayNhap(), model.getMaNV(), model.getMaNhaCC(), model.getTongTien(), model.getMaNhap());
    }

    public void delete(PhieuNhap model) {
        JDBCHelper.update(delete_SQL, model.getMaNhap());
    }

    public List<PhieuNhap> selectAll() {
        return selectBySQL(selectAll_SQL);
    }

    public PhieuNhap selectById(String maNhap) {
        List<PhieuNhap> list = selectBySQL(selectById_SQL, maNhap);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public List<PhieuNhap> selectBySQL(String sql, Object ...args){
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            List<PhieuNhap> list = new ArrayList<>();
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaNhap(rs.getString(1));
                pn.setNgayNhap(rs.getDate(2));
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
}
