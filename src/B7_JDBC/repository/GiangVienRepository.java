/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B7_JDBC.repository;

import B7_JDBC.model.GiangVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hangnt
 */
public class GiangVienRepository {
    
    public List<GiangVien> getAll() {
        // B1: Create query
        String query = "SELECT * FROM giang_vien";
        try ( Connection con = SQLServerConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            List<GiangVien> lists = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new GiangVien(rs.getString(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getBoolean(6)));
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        new GiangVienRepository().getAll().forEach(s -> System.out.println(s.toString()));
    }
}
