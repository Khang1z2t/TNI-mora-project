package dao;

import entities.NhaCungCap;
import utils.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {
    String insert_SQL = "insert into NhaCungCap values(?,?,?,?)";
    String update_SQL = "update NhaCungCap set TenNhaCC = ?, SDT = ?, DiaChi = ? where MaNhaCC = ?";
    String delete_SQL = "delete from NhaCungCap where MaNhaCC = ?";
    String selectById_SQL = "select * from NhaCungCap where MaNhaCC = ?";
    String selectAll_SQL = "select * from NhaCungCap";

    public void insert(NhaCungCap model) {
        JDBCHelper.update(insert_SQL, model.getMaNhaCC(), model.getTenNhaCC(), model.getSDT(), model.getDiaChi());
    }

    public void update(NhaCungCap model) {
        JDBCHelper.update(update_SQL, model.getTenNhaCC(), model.getSDT(), model.getDiaChi(), model.getMaNhaCC());
    }

    public void delete(NhaCungCap model) {
        JDBCHelper.update(delete_SQL, model.getMaNhaCC());
    }

    public List<NhaCungCap> selectAll() {
        return selectBySQL(selectAll_SQL);
    }

    public NhaCungCap selectById(String maNhaCC) {
        List<NhaCungCap> list = selectBySQL(selectById_SQL, maNhaCC);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public List<NhaCungCap> selectBySQL(String sql, Object... args) {
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            List<NhaCungCap> list = new ArrayList<>();
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNhaCC(rs.getString(1));
                ncc.setTenNhaCC(rs.getString(2));
                ncc.setSDT(rs.getString(3));
                ncc.setDiaChi(rs.getString(4));
                list.add(ncc);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}