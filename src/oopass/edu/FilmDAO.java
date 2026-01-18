package oopass.edu;

import java.sql.*;

public class FilmDAO {

    public static void addFilm(String title, String genre, int duration) throws SQLException {
        String sql = "INSERT INTO films (title, genre, duration) VALUES (?, ?, ?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, genre);
        ps.setInt(3, duration);
        ps.executeUpdate();
    }

    public static void showAllFilms() throws SQLException {
        String sql = "SELECT * FROM films ORDER BY id";
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " | " +
                            rs.getString("title") + " | " +
                            rs.getString("genre") + " | " +
                            rs.getInt("duration") + " min"
            );
        }
    }

    public static void updateFilm(int id, String title) throws SQLException {
        String sql = "UPDATE films SET title = ? WHERE id = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    public static void deleteFilm(int id) throws SQLException {
        String sql = "DELETE FROM films WHERE id = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}

