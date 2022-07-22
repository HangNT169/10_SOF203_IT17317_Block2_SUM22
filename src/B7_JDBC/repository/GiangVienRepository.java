/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B7_JDBC.repository;

import B7_JDBC.model.GiangVien;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hangnt
 */
public class GiangVienRepository {

    public List<GiangVien> getAll() {
        // B1: Tao query
        String query = "SELECT * FROM giang_vien gv";
        // B2: Mo connection
        try ( Connection con = SQLServerConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            // B3: Statement:la 1 inteface cho phep 
            // cac gui cac cau len SQL toi Database
            // B4: Tao kieu tra ve cua ham
            List<GiangVien> listGiangVien = new ArrayList<>();
            // B5: Tao resultset: dai dien cho tap hop cac
            // ban ghi(record) co duoc sau khi thuc hien truy van ( query)
            ResultSet rs = ps.executeQuery();
            // B6: Doc data tu resultset
            while (rs.next()) {
                GiangVien gv = new GiangVien(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4), rs.getString(5),
                        rs.getBoolean(6));
                listGiangVien.add(gv);
            }
            return listGiangVien;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public GiangVien getOne(String maGV) {
        // B1: Tao query
        String query = "SELECT * FROM giang_vien gv WHERE gv.ma_gv  = ?";

        // B2: Mo connection
        try ( Connection con = SQLServerConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            // B3: Statement:la 1 inteface cho phep 
            // cac gui cac cau len SQL toi Database
            // B4: Set tham so truyen vao
            ps.setObject(1, maGV);
            // B5: Tao resultset: dai dien cho tap hop cac
            // ban ghi(record) co duoc sau khi thuc hien truy van ( query)
            ResultSet rs = ps.executeQuery();
            // B6: Doc data tu resultset
            if (rs.next()) {
                GiangVien gv = new GiangVien(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4), rs.getString(5),
                        rs.getBoolean(6));
                return gv;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
