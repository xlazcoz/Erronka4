import java.sql.*;

/**
 * Konexio klasea.
 * Datu-basearekin konexioa sortzeko erabiltzen da.
 */
public class Konexioa {
    private static final String URL = "jdbc:mysql://localhost:3306/Erronka";
    private static final String ERABILTZAILEA = "root";
    private static final String PASAHITZA = "Lazcoz0720"; 

    /**
     * Datu-basearen konexioa lortzen du.
     */
    public static Connection getKonexioa() throws SQLException {
        return DriverManager.getConnection(URL, ERABILTZAILEA, PASAHITZA);
    }
}