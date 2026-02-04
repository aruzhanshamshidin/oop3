package oopass.edu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewerDAO implements Repository<Viewer> {
    @Override
    public List<Viewer> getAll() throws SQLException {
        List<Viewer> viewers = new ArrayList<>();
        String sql = "SELECT * FROM viewers";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

                viewers.add(new Viewer(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
            }
        }
        return viewers;
    }

    @Override
    public void add(Viewer viewer) throws SQLException {
        String sql = "INSERT INTO viewers (name, age) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, viewer.getName());
            ps.setInt(2, viewer.getAge());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM viewers WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}