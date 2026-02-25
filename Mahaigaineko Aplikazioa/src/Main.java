import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Kudeatzailea kudeatzailea = new Kudeatzailea();
        boolean irten = false;

        System.out.println("=== INDI USURBIL - GESTIO BACKENDA ===");

        while (!irten) {
            System.out.println("\n--- MENUA ---");
            System.out.println("1. Produktu berria gehitu");
            System.out.println("2. Produktuak CSV fitxategitik kargatu");
            System.out.println("3. Produktuak eguneratu (Stock eta Prezioa)");
            System.out.println("4. Produktuak zerrendatu");
            System.out.println("5. Produktua bilatu");
            System.out.println("6. Produktua ezabatu");
            System.out.println("7. Datuak JSON-era esportatu");
            System.out.println("0. Atera");
            System.out.print("Aukeratu opzio bat: ");
            
            int aukera = sc.nextInt();
            sc.nextLine(); 

            switch (aukera) {
                case 1:
                    System.out.println("--- SARTU PRODUKTUAREN DATUAK ---");
                    System.out.print("Izena: ");
                    String iz = sc.nextLine();
                    System.out.print("Deskribapena: ");
                    String desk = sc.nextLine();
                    System.out.print("Prezioa: ");
                    double prez = sc.nextDouble(); 
                    System.out.print("Stock erabilgarria: ");
                    int stock = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Sorkuntza data (UUUU-HH-EE): ");
                    String data = sc.nextLine();
                    System.out.print("Kategoria ID: ");
                    int kat = sc.nextInt();
                    sc.nextLine(); 
                    
                    Produktua pBerria = new Produktua(iz, desk, prez, stock, data, kat);
                    kudeatzailea.txertatuProduktua(pBerria);
                    break;
                    
                case 2:
                    System.out.println("--- CSV KARGATZEN ---");
                    kudeatzailea.kargatuCSV("productos.csv");
                    break;
                    
                case 3:
                    System.out.println("--- PRODUKTUA EGUNERATU ---");
                    System.out.print("Sartu produktuaren ID-a: ");
                    int idEguneratu = sc.nextInt();
                    System.out.print("Stock berria: ");
                    int stockBerria = sc.nextInt();
                    System.out.print("Prezio berria: ");
                    double prezioBerria = sc.nextDouble();
                    
                    kudeatzailea.eguneratuStockEtaPrezioa(idEguneratu, stockBerria, prezioBerria);
                    break;
                    
                case 4:
                    System.out.println("--- PRODUKTUAK ZERRENDATU ---");
                    ArrayList<Produktua> produktuak = kudeatzailea.lortuProduktuak();
                    for (int i = 0; i < produktuak.size(); i++) {
                        System.out.println(produktuak.get(i).toString());
                    }
                    break;
                    
                case 5:
                    System.out.println("--- PRODUKTUA BILATU ---");
                    System.out.print("Sartu bilatzeko hitza: ");
                    String bilaketa = sc.nextLine();
                    kudeatzailea.bilatuProduktua(bilaketa);
                    break;

                case 6:
                    System.out.println("--- PRODUKTUA EZABATU ---");
                    System.out.print("Sartu ezabatu nahi duzun produktuaren ID-a: ");
                    int idEzabatu = sc.nextInt();
                    kudeatzailea.ezabatuProduktua(idEzabatu);
                    break;
                    
                case 7:
                    System.out.println("--- JSON ESPORTATZEN ---");
                    kudeatzailea.esportatuJSON("productos.json");
                    break;
                    
                case 0:
                    irten = true;
                    System.out.println("Agur. Bukatu da programa.");
                    break;
                    
                default:
                    System.out.println("Aukeratu opzio balido bat.");
            }
        }
        
        sc.close();
    }
}