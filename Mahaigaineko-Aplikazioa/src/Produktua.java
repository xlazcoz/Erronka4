/**
 * Produktu baten informazioa gordetzeko klasea.
 */
public class Produktua {
    private int id;
    private String deskribapena;
    private String izena;
    private double prezioa;
    private int stocka;
    private String sorkuntzaData;
    private int kategoriaId;
    private String irudia;

    /**
     * Produktu hutsa sortzen du.
     */
    public Produktua() {}

    /**
     * Produktua parametro guztiekin sortzen du.
     */
    public Produktua(String izena, String deskribapena, double prezioa, int stocka, String sorkuntzaData, int kategoriaId, String irudia) {
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.prezioa = prezioa;
        this.stocka = stocka;
        this.sorkuntzaData = sorkuntzaData;
        this.kategoriaId = kategoriaId;
        this.irudia = irudia;
    }

    // --- GETTERS Y SETTERS ---

    /** ID-a lortzen du. */
    public int getId() { return id; }
    /** ID-a ezartzen du. */
    public void setId(int id) { this.id = id; }

    /** Izena lortzen du. */
    public String getIzena() { return izena; }
    /** Izena ezartzen du. */
    public void setIzena(String izena) { this.izena = izena; }

    /** Deskribapena lortzen du. */
    public String getDeskribapena() { return deskribapena; }
    /** Deskribapena ezartzen du. */
    public void setDeskribapena(String deskribapena) { this.deskribapena = deskribapena; }

    /** Prezioa lortzen du. */
    public double getPrezioa() { return prezioa; }
    /** Prezioa ezartzen du. */
    public void setPrezioa(double prezioa) { this.prezioa = prezioa; }

    /** Stocka lortzen du. */
    public int getStocka() { return stocka; }
    /** Stocka ezartzen du. */
    public void setStocka(int stocka) { this.stocka = stocka; }

    /** Sorkuntza data lortzen du. */
    public String getSorkuntzaData() { return sorkuntzaData; }
    /** Sorkuntza data ezartzen du. */
    public void setSorkuntzaData(String sorkuntzaData) { this.sorkuntzaData = sorkuntzaData; }

    /** Kategoria ID-a lortzen du. */
    public int getKategoriaId() { return kategoriaId; }
    /** Kategoria ID-a ezartzen du. */
    public void setKategoriaId(int kategoriaId) { this.kategoriaId = kategoriaId; }

    /** Irudiaren bidea lortzen du. */
    public String getIrudia() { return irudia; }
    /** Irudiaren bidea ezartzen du. */
    public void setIrudia(String irudia) { this.irudia = irudia; }

    /** Produktuaren datuak testu formatuan itzultzen ditu. */
    @Override
    public String toString() {
        return "ID: " + id + " | " + izena + " | Data: " + sorkuntzaData + " | Stock: " + stocka + " | Prezioa: " + prezioa + " EUR | Irudia: " + irudia;
    }
}