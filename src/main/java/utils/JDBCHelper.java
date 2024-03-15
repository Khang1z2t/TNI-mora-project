package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {

    private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String SERVER_NAME = "localhost";
    private static String HOST = "1433";
    private static String DATABASE_NAME = "QuanLyNhaSach";
    private static String USERNAME = "sa";
    private static String PASSWORD = "12345";

    private static String URL = "jdbc:sqlserver://" + SERVER_NAME + ":" + HOST + ";"
            + "databaseName=" + DATABASE_NAME
            + ";user=" + USERNAME
            + ";password=" + PASSWORD
            + ";encrypt=true;trustServerCertificate=true";

    public static Connection CONN = null;

    static {
        try {
            Class.forName(DRIVER);
            CONN = DriverManager.getConnection(URL);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        CONN = DriverManager.getConnection(URL);
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = CONN.prepareCall(sql);
        } else {
            stmt = CONN.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    public static void update(String sql, Object... args) {
        try {
            PreparedStatement stmt = getStmt(sql, args);
            try {
                stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement stmt = getStmt(sql, args);
        return stmt.executeQuery();
    }

}
