package oopass.edu;

import java.sql.*;
import java.util.ArrayList; // Добавлено!
import java.util.List;      // Добавлено!

public class ViewerDAO {

    // Этот метод нужен для твоего Main.java (строка 42 на скрине)
    public static List<Viewer> getAllViewers() throws SQLException {
        List<Viewer> viewers = new ArrayList<>();
        String sql = "SELECT * FROM viewers";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Убедись, что в классе Viewer есть конструктор (id, name, age)
                viewers.add(new Viewer(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
            }
        }
        return viewers;
    }

    // Этот метод нужен для POST запросов в Postman (строка 46 на скрине)
    public static void addViewer(Viewer viewer) throws SQLException {
        String sql = "INSERT INTO viewers (name, age) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, viewer.getName());
            ps.setInt(2, viewer.getAge());
            ps.executeUpdate();
        }
    }

    // Метод для удаления (DELETE)
    public static void deleteViewer(int id) throws SQLException {
        String sql = "DELETE FROM viewers WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}