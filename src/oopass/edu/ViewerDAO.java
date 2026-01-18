package oopass.edu;

import java.sql.*;

public class ViewerDAO {

    public static void addViewer(String name, int age, int filmId) throws SQLException {
        String sql = "INSERT INTO viewers (name, age, chosen_film_id) VALUES (?, ?, ?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setInt(3, filmId);
        ps.executeUpdate();
    }

    public static void showAllViewers() throws SQLException {
        String sql = """
                SELECT v.id, v.name, v.age, f.title
                FROM viewers v
                LEFT JOIN films f ON v.chosen_film_id = f.id
                """;

        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getInt("age") + " | Film: " +
                            rs.getString("title")
            );
        }
    }
}
