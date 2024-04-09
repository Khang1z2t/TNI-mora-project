package dao;

import entities.ChiTietHoaDon;
import utils.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {
    private String insert_SQL = "insert into ChiTietHoaDon values(?,?,?,?)";
    private String update_SQL = "update ChiTietHoaDon set MaSach = ?, SoLuong = ?, DonGia = ? where MaHoaDon = ?";
    private String delete_SQL = "delete from ChiTietHoaDon where MaHoaDon = ?";
    private String selectById_SQL = "select * from ChiTietHoaDon where MaHoaDon = ?";
    private String selectAll_SQL = "select * from ChiTietHoaDon";

    public void insert(ChiTietHoaDon model) {
        JDBCHelper.update(insert_SQL, model.getMaHoaDon(), model.getMaSach(), model.getSoLuong(), model.getDonGia());
    }

    public void update(ChiTietHoaDon model) {
        JDBCHelper.update(update_SQL, model.getMaSach(), model.getSoLuong(), model.getDonGia(), model.getMaHoaDon());
    }

    public void delete(Object key) {
        JDBCHelper.update(delete_SQL, key);
    }

    public List<ChiTietHoaDon> selectAll() {
        return selectBySQL(selectAll_SQL);
    }

    public List<ChiTietHoaDon> selectById(String maHoaDon) {
        List<ChiTietHoaDon> list = selectBySQL(selectById_SQL, maHoaDon);
        return !list.isEmpty() ? list : null;
    }

    public List<ChiTietHoaDon> selectBySQL(String sql, Object... args) {
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            List<ChiTietHoaDon> list = new ArrayList<>();
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setMaHoaDon(rs.getString(1));
                cthd.setMaSach(rs.getString(2));
                cthd.setSoLuong(rs.getInt(3));
                cthd.setDonGia(rs.getDouble(4));
                list.add(cthd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
