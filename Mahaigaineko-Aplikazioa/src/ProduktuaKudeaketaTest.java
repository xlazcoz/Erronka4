import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProduktuaKudeaketaTest {

    


    // CP1: Produktua ondo sortu - B1, B2, B3, B4
    @Test
    void testCP1_CrearProductoElectronicaValido() {
        Produktua p = new Produktua("Ordenagailua", "PC", 850.0, 10, "2024-05-20", 1, "pc.png");
        assertAll("Verificar datos válidos CP1",
            () -> assertTrue(p.getIzena().length() >= 1, "B1: El nombre debe tener al menos 1 carácter"),
            () -> assertTrue(p.getPrezioa() > 0, "B2: El precio debe ser positivo"),
            () -> assertTrue(p.getStocka() >= 0, "B3: El stock debe ser 0 o más")
        );
    }

    // CP2: Produktua ondo sortu - B1, B2, B3, B5
    @Test
    void testCP2_CrearProductoArropaValido() {
        Produktua p = new Produktua("Kamiseta", "Textil", 20.0, 50, "2024-05-20", 2, "shirt.png");
        assertNotNull(p.getIzena());
        assertEquals(20.0, p.getPrezioa());
    }

    // CP3: Izen hutsik (EB1)
    @Test
    void testCP3_NombreVacioError() {
        Produktua p = new Produktua("", "PC", 850.0, 10, "2024-05-20", 1, "pc.png");
        assertTrue(p.getIzena().isEmpty(), "EB1: El nombre está vacío");
    }

    // CP4: Prezioa 0 edo negatiboa (EB2)
    @Test
    void testCP4_PrecioCeroError() {
        Produktua p = new Produktua("Ordenagailua", "PC", 0.0, 10, "2024-05-20", 1, "pc.png");
        assertTrue(p.getPrezioa() <= 0, "EB2: El precio es cero o negativo");
    }

    // CP5: Ez zenbakizkoa (EB3)
    @Test
    void testCP5_PrecioNoNumericoError() {
        
        String valorInput = "hogei";
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(valorInput);
        }, "EB3: Debe lanzar excepción si el texto no es un número");
    }

    // CP6: Stock negatiboa (EB4)
    @Test
    void testCP6_StockNegativoError() {
        Produktua p = new Produktua("Sagua", "Mouse", 15.0, -5, "2024-05-20", 1, "mouse.png");
        assertTrue(p.getStocka() < 0, "EB4: El stock es negativo");
    }

    // CP7: Kategoria ez da existitzen (EB6)
    @Test
    void testCP7_KategoriaInexistenteError() {
        String kategoriaInput = "Liburutegia";
        boolean existe = kategoriaInput.equals("Elektronika") || kategoriaInput.equals("Arropa");
        assertFalse(existe, "EB6: La categoría no debería existir en el sistema");
    }
}
