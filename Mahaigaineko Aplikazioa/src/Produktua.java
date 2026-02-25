public class Produktua {
    private int id;
    private String deskribapena;
    private String izena;
    private double prezioa;
    private int stocka;
    private String sorkuntzaData;
    private int kategoriaId;

    public Produktua() {}

    public Produktua(String izena, String deskribapena, double prezioa, int stocka, String sorkuntzaData, int kategoriaId) {
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.prezioa = prezioa;
        this.stocka = stocka;
        this.sorkuntzaData = sorkuntzaData;
        this.kategoriaId = kategoriaId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getIzena() { return izena; }
    public void setIzena(String izena) { this.izena = izena; }

    public String getDeskribapena() { return deskribapena; }
    public void setDeskribapena(String deskribapena) { this.deskribapena = deskribapena; }

    public double getPrezioa() { return prezioa; }
    public void setPrezioa(double prezioa) { this.prezioa = prezioa; }

    public int getStocka() { return stocka; }
    public void setStocka(int stocka) { this.stocka = stocka; }

    public String getSorkuntzaData() { return sorkuntzaData; }
    public void setSorkuntzaData(String sorkuntzaData) { this.sorkuntzaData = sorkuntzaData; }

    public int getKategoriaId() { return kategoriaId; }
    public void setKategoriaId(int kategoriaId) { this.kategoriaId = kategoriaId; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + izena + " | Data: " + sorkuntzaData + " | Stock: " + stocka + " | Prezioa: " + prezioa + " EUR";
    }
}