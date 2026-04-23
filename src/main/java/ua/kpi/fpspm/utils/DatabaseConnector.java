package ua.kpi.fpspm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://localhost:5432/lab2_v9_hotel";
    private static final String USER = "postgres";
    private static final String PASSWORD = "151206";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
