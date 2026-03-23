import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProduktuaKudeaketaTest {

    // CP1: Produktua ondo sortu eta datuak gorde - getter bidez egiaztatu
    @Test
    void testCP1_KonstruktoreaDatuakOndoGordetzen() {
        Produktua p = new Produktua("Ordenagailua", "PC potentea", 850.0, 10, "2024-05-20", 1, "pc.png");
        assertAll("Konstruktoreak sartutako datuak getter bidez egiaztatu",
            () -> assertEquals("Ordenagailua", p.getIzena()),
            () -> assertEquals(850.0, p.getPrezioa()),
            () -> assertEquals(10, p.getStocka())
        );
    }

    // CP2: Setter bidez eguneratutako prezioa getter-ak itzultzen du
    @Test
    void testCP2_SetterGetterPrezioa() {
        Produktua p = new Produktua("Kamiseta", "Textil", 20.0, 50, "2024-05-20", 2, "shirt.png");
        p.setPrezioa(35.0);
        assertEquals(35.0, p.getPrezioa(), "Prezio berriak 35.0 izan behar du");
    }

    // CP3: Setter bidez eguneratutako stocka getter-ak itzultzen du
    @Test
    void testCP3_SetterGetterStocka() {
        Produktua p = new Produktua("Sagua", "Mouse", 15.0, 5, "2024-05-20", 1, "mouse.png");
        p.setStocka(20);
        assertEquals(20, p.getStocka(), "Stock berriak 20 izan behar du");
    }

    // CP4: Prezio ez-numerikoak NumberFormatException sortzen du
    @Test
    void testCP4_PrecioNoNumericoError() {
        String valorInput = "hogei";
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(valorInput);
        }, "Testua zenbakira bihurtzean errore bat egon behar da");
    }

    // CP5: CSV lerro bat 7 eremu ditu - produktua ondo sortzen da
    @Test
    void testCP5_CSVLerroa7EremuZuzenak() {
        String lerroa = "Kamiseta,Textila,20.0,50,2024-05-20,2,shirt.png";
        String[] datuak = lerroa.split(",");
        Produktua p = new Produktua(datuak[0], datuak[1], Double.parseDouble(datuak[2]),
                Integer.parseInt(datuak[3]), datuak[4], Integer.parseInt(datuak[5]), datuak[6]);
        assertAll("CSV-tik sortutako produktua egiaztatu",
            () -> assertEquals("Kamiseta", p.getIzena()),
            () -> assertEquals(20.0, p.getPrezioa()),
            () -> assertEquals(50, p.getStocka())
        );
    }

    // CP6: Setter bidez eguneratutako izena getter-ak itzultzen du
    @Test
    void testCP6_SetterGetterIzena() {
        Produktua p = new Produktua("Ordenagailua", "PC", 850.0, 10, "2024-05-20", 1, "pc.png");
        p.setIzena("Teklatu berria");
        assertEquals("Teklatu berria", p.getIzena(), "Izen berriak 'Teklatu berria' izan behar du");
    }

    // CP7: Setter bidez eguneratutako kategoria ID-a getter-ak itzultzen du
    @Test
    void testCP7_SetterGetterKategoriaId() {
        Produktua p = new Produktua("Kamiseta", "Textil", 20.0, 50, "2024-05-20", 2, "shirt.png");
        p.setKategoriaId(3);
        assertEquals(3, p.getKategoriaId(), "Kategoria ID berriak 3 izan behar du");
    }

    // CP8: toString() metodoak izena eta prezioa biltzen ditu
    @Test
    void testCP8_ToStringIzenaEtaPrezioa() {
        Produktua p = new Produktua("Ordenagailua", "PC", 850.0, 10, "2024-05-20", 1, "pc.png");
        String resultado = p.toString();
        assertAll("toString-ek datuak erakutsi behar ditu",
            () -> assertTrue(resultado.contains("Ordenagailua"), "toString-ek izena erakutsi behar du"),
            () -> assertTrue(resultado.contains("850.0"), "toString-ek prezioa erakutsi behar du")
        );
    }

    // CP9: Stock ez-numerikoak NumberFormatException sortzen du
    @Test
    void testCP9_StockNoNumericoError() {
        String valorInput = "hamar";
        assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt(valorInput);
        }, "Testua zenbakira bihurtzean errore bat egon behar da");
    }
}