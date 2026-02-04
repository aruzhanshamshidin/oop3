package oopass.edu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements Repository<Film> {
    @Override
    public List<Film> getAll() throws SQLException {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM films";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                films.add(new Film.Builder()
                        .setId(rs.getInt("id"))
                        .setTitle(rs.getString("title"))
                        .setGenre(rs.getString("genre"))
                        .setDuration(rs.getInt("duration"))
                        .build());
            }
        }
        return films;
    }

    @Override
    public void add(Film film) throws SQLException {
        String sql = "INSERT INTO films (title, genre, duration) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, film.getTitle());
            ps.setString(2, film.getGenre());
            ps.setInt(3, film.getDuration());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM films WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}