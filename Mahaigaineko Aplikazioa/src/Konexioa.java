import java.sql.*;

public class Konexioa {
    private static final String URL = "";
    private static final String ERABILTZAILEA = "";
    private static final String PASAHITZA = "";

    public static Connection getKonexioa() throws SQLException {
        return DriverManager.getConnection(URL, ERABILTZAILEA, PASAHITZA);
    }
}
