package com.mathwhiz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manages JDBC database connectivity to MySQL for PROTHAM - MATH WHIZ.
 * Follows TASK 07 specifications.
 */
public class DBConnection {

    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "3306";
    private static final String DEFAULT_DB = "math_whiz_db";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "2441139";

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }

    /**
     * Gets database connection URL, prioritizing environment or system properties if provided.
     */
    public static String getDbUrl() {
        String host = System.getProperty("DB_HOST", System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : DEFAULT_HOST);
        String port = System.getProperty("DB_PORT", System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : DEFAULT_PORT);
        String db = System.getProperty("DB_NAME", System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : DEFAULT_DB);

        return "jdbc:mysql://" + host + ":" + port + "/" + db +
                "?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    }

    public static String getDbUser() {
        return System.getProperty("DB_USER", System.getenv("DB_USER") != null ? System.getenv("DB_USER") : DEFAULT_USER);
    }

    public static String getDbPassword() {
        return System.getProperty("DB_PASSWORD", System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : DEFAULT_PASSWORD);
    }

    /**
     * Obtains an active JDBC connection to MySQL.
     * Automatically ensures the table exists.
     *
     * @return active java.sql.Connection
     * @throws SQLException if a connection cannot be established
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPassword());
        ensureTableExists(conn);
        return conn;
    }

    /**
     * Ensures the game_scores table exists in the database as per TASK 07.
     */
    private static void ensureTableExists(Connection conn) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS game_scores (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "player_name VARCHAR(100) NOT NULL, " +
                "score INT NOT NULL, " +
                "difficulty VARCHAR(20) NOT NULL, " +
                "played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.err.println("Error initializing game_scores table: " + e.getMessage());
        }
    }
}
