package oopass.edu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO {
    public static List<Film> getAllFilms() throws SQLException {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM films ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // Используем Builder для создания фильма
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
    public static void addFilm(Film film) throws java.sql.SQLException {
        String sql = "INSERT INTO films (title, genre, duration) VALUES (?, ?, ?)";
        try (java.sql.Connection conn = DBConnection.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, film.getTitle());
            ps.setString(2, film.getGenre());
            ps.setInt(3, film.getDuration());
            ps.executeUpdate();
        }
    }

    public static void deleteFilm(int id) throws java.sql.SQLException {
        String sql = "DELETE FROM films WHERE id = ?";
        try (java.sql.Connection conn = DBConnection.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}