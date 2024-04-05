package dao;

import entities.ChiTietPhieuNhap;
import utils.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapDAO {
    String insert_SQL = "insert into ChiTietPhieuNhap values(?,?,?,?)";
    String update_SQL = "update ChiTietPhieuNhap set MaNhap = ?, Masach = ?, Soluong = ?, Gia = ? where MaNhap = ? and Masach = ?";
    String delete_SQL = "delete from ChiTietPhieuNhap where MaNhap = ?";
    String selectById_SQL = "select * from ChiTietPhieuNhap where MaNhap = ?";
    String selectAll_SQL = "select * from ChiTietPhieuNhap";

    public void insert(ChiTietPhieuNhap model) {
        JDBCHelper.update(insert_SQL, model.getMaNhap(), model.getMasach(), model.getSoluong(), model.getGia());
    }

    public void update(ChiTietPhieuNhap model) {
        JDBCHelper.update(update_SQL, model.getMaNhap(), model.getMasach(), model.getSoluong(), model.getGia(), model.getMaNhap(), model.getMasach());
    }

    public void delete(Object key) {
        JDBCHelper.update(delete_SQL, key);
    }

    public List<ChiTietPhieuNhap> selectAll() {
        return selectBySQL(selectAll_SQL);
    }

    public ChiTietPhieuNhap selectById(String maNhap) {
        List<ChiTietPhieuNhap> list = selectBySQL(selectById_SQL, maNhap);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public List<ChiTietPhieuNhap> selectBySQL(String sql, Object... args) {
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            List<ChiTietPhieuNhap> list = new ArrayList<>();
            while (rs.next()) {
                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
                ctpn.setMaNhap(rs.getString(1));
                ctpn.setMasach(rs.getString(2));
                ctpn.setSoluong(rs.getInt(3));
                ctpn.setGia(rs.getDouble(4));
                list.add(ctpn);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
