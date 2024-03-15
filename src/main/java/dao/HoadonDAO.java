/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Hoadon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NGUYEN THI NGUYET VY
 */
public class HoadonDAO {
    protected List<Hoadon> selectBySql(String sql, Object...args){
        List<Hoadon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = utils.JDBCHelper.query(sql, args);
                while(rs.next()){
                    Hoadon hd = new Hoadon();
                    hd.setMahoadon(rs.getInt(1));
                    hd.setMasach(rs.getString(2));
                    hd.setNgaytao(rs.getDate(3));
                    hd.setMaDocGia(rs.getInt(4));
                    hd.setMaNV(rs.getString(5));
                    list.add(hd);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}
