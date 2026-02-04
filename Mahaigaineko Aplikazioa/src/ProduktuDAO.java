
import java.sql.*;
import java.time.LocalDate;

public class ProduktuDAO {

    // Produktu berri bat datu-basean gehitzeko metodoa
    public void produktuBerriaSortu(Produktua p) {
        String sql = "INSERT INTO PRODUKTUAK (izena, deskribapena, prezioa, stocka, kategoria_id, irudia_url, sorkuntza_data) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Konexioa.getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Parametroak esleitu
            pstmt.setString(1, p.getIzena());
            pstmt.setString(2, p.getDeskribapena());
            pstmt.setDouble(3, p.getPrezioa());
            pstmt.setInt(4, p.getStocka());
            pstmt.setInt(5, p.getKategoriaId());
            pstmt.setString(6, p.getIrudiaUrl());

            // Sorkuntza data automatikoki gaurko eguna jarri
            pstmt.setDate(7, java.sql.Date.valueOf(LocalDate.now()));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produktua ondo gorde da datu-basean.");
            } else {
                System.out.println("Ezin izan da produktua gorde.");
            }

        } catch (SQLException e) {
            System.err.println("Errorea datu-basearekin konektatzean: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
