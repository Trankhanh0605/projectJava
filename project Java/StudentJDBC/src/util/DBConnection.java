package util;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentDB;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "Khanh060506";
        return DriverManager.getConnection(url, user, password);
    }
}
