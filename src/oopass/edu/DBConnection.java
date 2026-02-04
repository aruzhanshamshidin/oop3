package oopass.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String pass = "aruca0808";
            connection = DriverManager.getConnection(url, user, pass);
        }
        return connection;
    }
}
