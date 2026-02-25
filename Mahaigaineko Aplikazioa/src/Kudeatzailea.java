import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Kudeatzailea {

    // 1. TXERTATU (Insertar)
    public void txertatuProduktua(Produktua p) {
        try {
            Connection con = Konexioa.getKonexioa();
            String sql = "INSERT INTO produktua (Deskribapena_produktua, Izena_produktua, prezioa, stocka, sorkuntza_data_produktua, Id_kategoria, irudia) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, p.getDeskribapena());
            pstmt.setString(2, p.getIzena());
            pstmt.setDouble(3, p.getPrezioa());
            pstmt.setInt(4, p.getStocka());
            pstmt.setString(5, p.getSorkuntzaData());
            pstmt.setInt(6, p.getKategoriaId());
            pstmt.setString(7, p.getIrudia()); 
            
            pstmt.executeUpdate();
            System.out.println("Produktua txertatu da.");
            
            pstmt.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.println("Errorea datu-basean txertatzean: " + e.getMessage());
        }
    }

    // 2. LORTU (Listar todos)
    public ArrayList<Produktua> lortuProduktuak() {
        ArrayList<Produktua> zerrenda = new ArrayList<>();
        
        try {
            Connection con = Konexioa.getKonexioa();
            String sql = "SELECT * FROM produktua";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Produktua p = new Produktua();
                p.setId(rs.getInt("Id_produktua"));
                p.setDeskribapena(rs.getString("Deskribapena_produktua"));
                p.setIzena(rs.getString("Izena_produktua"));
                p.setPrezioa(rs.getDouble("prezioa"));
                p.setStocka(rs.getInt("stocka"));
                p.setSorkuntzaData(rs.getString("sorkuntza_data_produktua"));
                p.setKategoriaId(rs.getInt("Id_kategoria"));
                p.setIrudia(rs.getString("irudia")); 
                zerrenda.add(p);
            }
            
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.println("Errorea datuak irakurtzean: " + e.getMessage());
        } 
        
        return zerrenda;
    }

    // 3. EGUNERATU (Actualizar stock y precio)
    public void eguneratuStockEtaPrezioa(int id, int stockBerria, double prezioBerria) {
        try {
            Connection con = Konexioa.getKonexioa();
            String sql = "UPDATE produktua SET stocka = ?, prezioa = ? WHERE Id_produktua = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setInt(1, stockBerria);
            pstmt.setDouble(2, prezioBerria);
            pstmt.setInt(3, id);
            
            int ilarak = pstmt.executeUpdate();
            if (ilarak > 0) {
                System.out.println("Produktua eguneratu da.");
            } else {
                System.out.println("Ez da produktua aurkitu.");
            }
            
            pstmt.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.println("Errorea eguneratzean: " + e.getMessage());
        } 
    }

    // 4. EZABATU (Borrar)
    public void ezabatuProduktua(int id) {
        try {
            Connection con = Konexioa.getKonexioa();
            String sql = "DELETE FROM produktua WHERE Id_produktua = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            
            int ilarak = pstmt.executeUpdate();
            if (ilarak > 0) {
                System.out.println("Produktua ezabatu da.");
            } else {
                System.out.println("Ez da produktua aurkitu.");
            }
            
            pstmt.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.println("Errorea ezabatzean: " + e.getMessage());
        }
    }

    // 5. BILATU (Buscar por nombre o descripción)
    public void bilatuProduktua(String hitza) {
        try {
            Connection con = Konexioa.getKonexioa();
            String sql = "SELECT * FROM produktua WHERE Izena_produktua LIKE ? OR Deskribapena_produktua LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            String parametroa = "%" + hitza + "%";
            pstmt.setString(1, parametroa);
            pstmt.setString(2, parametroa);
            
            ResultSet rs = pstmt.executeQuery();
            boolean aurkituta = false;
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("Id_produktua") + " | Izena: " + rs.getString("Izena_produktua") + " | Prezioa: " + rs.getDouble("prezioa") + " EUR");
                aurkituta = true;
            }
            
            if (!aurkituta) {
                System.out.println("Ez da ezer aurkitu.");
            }
            
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.println("Errorea bilatzean: " + e.getMessage());
        } 
    }

    // 6. CSV KARGATU (Leer archivo)
    public void kargatuCSV(String ibilbidea) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ibilbidea));
            String lerroa;
            
            while ((lerroa = br.readLine()) != null) {
                String[] datuak = lerroa.split(",");
                
                if (datuak.length == 7) {
                    String izena = datuak[0];
                    String desk = datuak[1];
                    double prezioa = Double.parseDouble(datuak[2]);
                    int stock = Integer.parseInt(datuak[3]);
                    String data = datuak[4];
                    int kategoria = Integer.parseInt(datuak[5]);
                    String irudia = datuak[6]; 
                    
                    
                    Produktua p = new Produktua(izena, desk, prezioa, stock, data, kategoria, irudia);
                    txertatuProduktua(p); 
                }
            }
            System.out.println("CSV irakurri da.");
            
            br.close();
            
        } catch (IOException e) {
            System.out.println("Errorea CSV fitxategia irakurtzean: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Errorea zenbakiak bihurtzean: " + e.getMessage());
        }
    }


    // 7. JSON ESPORTATU PRODUKTUAK
    public void esportatuJSON(String ibilbidea) {
        ArrayList<Produktua> produktuak = lortuProduktuak();
        
        try {
            FileWriter fw = new FileWriter(ibilbidea);
            fw.write("[\n");
            
            for (int i = 0; i < produktuak.size(); i++) {
                Produktua p = produktuak.get(i);
                
                String jsonObjeto = "{\n" +
                "\"id\": " + p.getId() + ",\n" +
                "\"izena\": \"" + p.getIzena() + "\",\n" +
                "\"deskribapena\": \"" + p.getDeskribapena() + "\",\n" +
                "\"prezioa\": " + p.getPrezioa() + ",\n" +
                "\"stocka\": " + p.getStocka() + ",\n" +
                "\"data\": \"" + p.getSorkuntzaData() + "\",\n" +
                "\"irudia\": \"" + p.getIrudia() + "\"\n" +
                "}";
                
                fw.write(jsonObjeto);
                
                if (i < produktuak.size() - 1) {
                    fw.write(",\n");
                } else {
                    fw.write("\n");
                }
            }
            fw.write("]");
            System.out.println("Produktuen JSON sortu da.");
            
            fw.close();
            
        } catch (IOException e) {
            System.out.println("Errorea JSON idaztean: " + e.getMessage());
        }
    }

    // 8. JSON ESPORTATU BEZEROAK ETA ESKAERAK (Este no cambia)
    public void esportatuBezeroakJSON(String ibilbidea) {
        try {
            Connection con = Konexioa.getKonexioa();
            FileWriter fw = new FileWriter(ibilbidea);
            fw.write("[\n");

            String sql = "SELECT * FROM erabiltzailea";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<String> bezeroenZerrenda = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("Id_erabiltzailea");
                String izena = rs.getString("izena_erabiltzailea");
                String abizena = rs.getString("abizena_erabiltzailea");
                String emaila = rs.getString("emaila");
                String pasaitza = rs.getString("pasaitza");
                String data = rs.getString("sorkuntza_data_erabiltzailea");

                String sqlEskaera = "SELECT * FROM eskaera WHERE Id_erabiltzailea = ?";
                PreparedStatement pstmtEsk = con.prepareStatement(sqlEskaera);
                pstmtEsk.setInt(1, id);
                ResultSet rsEsk = pstmtEsk.executeQuery();

                ArrayList<String> eskaerenZerrenda = new ArrayList<>();
                while (rsEsk.next()) {
                    String eskaeraJson = "{\n" + 
                    "\"id_eskaera\": " + rsEsk.getInt("Id_Eskaera") + ",\n" +
                    "\"guztira\": " + rsEsk.getDouble("guztira") + ",\n" +
                    "\"data\": \"" + rsEsk.getString("data") + "\",\n" +
                    "\"egoera\": \"" + rsEsk.getString("egoera") + "\"\n" +
                    "}";
                    eskaerenZerrenda.add(eskaeraJson);
                }
                rsEsk.close();
                pstmtEsk.close();

                String eskaerakElkartuta = String.join(",\n", eskaerenZerrenda);

                String bezeroJson = "{\n" +
                "\"id\": " + id + ",\n" +
                "\"izena\": \"" + izena + "\",\n" +
                "\"abizena\": \"" + abizena + "\",\n" +
                "\"emaila\": \"" + emaila + "\",\n" +
                "\"pasaitza\": \"" + pasaitza + "\",\n" +
                "\"data\": \"" + data + "\",\n" +
                "\"eskaerak\": [\n" + eskaerakElkartuta + "\n]\n" +
                "}";
                
                bezeroenZerrenda.add(bezeroJson);
            }

            fw.write(String.join(",\n", bezeroenZerrenda));
            fw.write("\n]");
            System.out.println("Bezeroen JSON sortu da.");
            
            fw.close();
            rs.close();
            pstmt.close();
            con.close();
            
        } catch (SQLException | IOException e) {
            System.out.println("Errorea bezeroen JSON idaztean: " + e.getMessage());
        }
    }
}