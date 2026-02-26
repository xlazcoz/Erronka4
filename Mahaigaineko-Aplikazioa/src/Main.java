import java.util.Scanner;
import java.util.ArrayList;

/**
 * Programaren sarrera puntua eta erabiltzaile-interfazea (menua).
 */
public class Main {

    /**
     * Menu nagusia exekutatzen duen metodoa.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Kudeatzailea kudeatzailea = new Kudeatzailea();
        int aukera = 0;

        do {
            System.out.println("\n===== KUDEAKETA MENUA =====");
            System.out.println("1. Produktua txertatu ");
            System.out.println("2. Produktuak zerrendatu ");
            System.out.println("3. Produktua eguneratu ");
            System.out.println("4. Produktua ezabatu ");
            System.out.println("5. Produktua bilatu ");
            System.out.println("6. Datuak CSV-tik kargatu ");
            System.out.println("7. Esportatu JSON fitxategiak ");
            System.out.println("8. Irten");
            System.out.print("Aukeratu bat: ");
            
            aukera = sc.nextInt();
            sc.nextLine(); 

            switch (aukera) {
                case 1:
                    System.out.println("\n--- PRODUKTUA TXERTATU ---");
                    Produktua pBerria = new Produktua();
                    
                    System.out.print("Izena: ");
                    pBerria.setIzena(sc.nextLine());
                    
                    System.out.print("Deskribapena: ");
                    pBerria.setDeskribapena(sc.nextLine());
                    
                    System.out.print("Prezioa: ");
                    pBerria.setPrezioa(sc.nextDouble());
                    
                    System.out.print("Stocka: ");
                    pBerria.setStocka(sc.nextInt());
                    sc.nextLine(); 
                    
                    System.out.print("Sorkuntza data (YYYY-MM-DD): ");
                    pBerria.setSorkuntzaData(sc.nextLine());
                    
                    System.out.print("Kategoria ID-a: ");
                    pBerria.setKategoriaId(sc.nextInt());
                    sc.nextLine(); 
                    
                    
                    System.out.print("Irudia (fitxategiaren izena edo URL-a): ");
                    pBerria.setIrudia(sc.nextLine());

                    kudeatzailea.txertatuProduktua(pBerria);
                    break;

                case 2:
                    System.out.println("\n--- PRODUKTUEN ZERRENDA ---");
                    ArrayList<Produktua> produktuak = kudeatzailea.lortuProduktuak();
                    
                    if (produktuak.isEmpty()) {
                        System.out.println("Ez dago produkturik.");
                    } else {
                        for (int i = 0; i < produktuak.size(); i++) {
                            Produktua p = produktuak.get(i);
                            System.out.println("ID: " + p.getId() + " | Izena: " + p.getIzena() + " | Prezioa: " + p.getPrezioa() + "€ | Stock: " + p.getStocka() + " | Irudia: " + p.getIrudia());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- PRODUKTUA EGUNERATU ---");
                    System.out.print("Sartu eguneratu nahi duzun produktuaren ID-a: ");
                    int idEguneratu = sc.nextInt();
                    
                    System.out.print("Sartu stock berria: ");
                    int stockBerria = sc.nextInt();
                    
                    System.out.print("Sartu prezio berria: ");
                    double prezioBerria = sc.nextDouble();
                    
                    kudeatzailea.eguneratuStockEtaPrezioa(idEguneratu, stockBerria, prezioBerria);
                    break;

                case 4:
                    System.out.println("\n--- PRODUKTUA EZABATU ---");
                    System.out.print("Sartu ezabatu nahi duzun produktuaren ID-a: ");
                    int idEzabatu = sc.nextInt();
                    kudeatzailea.ezabatuProduktua(idEzabatu);
                    break;

                case 5:
                    System.out.println("\n--- PRODUKTUA BILATU ---");
                    System.out.print("Sartu bilatzeko hitza (Izena edo deskribapena): ");
                    String hitza = sc.nextLine();
                    kudeatzailea.bilatuProduktua(hitza);
                    break;

                case 6:
                    System.out.println("\n--- CSV KARGATU ---");
                    System.out.print("Sartu CSV fitxategiaren izena edo ibilbidea (adibidez, produktuak.csv): ");
                    String ibilbidea = sc.nextLine();
                    kudeatzailea.kargatuCSV(ibilbidea);
                    break;

                case 7:
                    System.out.println("\n--- JSON FITXATEGIAK SORTZEN ---");
                    kudeatzailea.esportatuJSON("produktuak.json");
                    kudeatzailea.esportatuBezeroakJSON("bezeroak.json");
                    System.out.println("Fitxategiak ondo sortu dira karpeta nagusian.");
                    break;

                case 8:
                    System.out.println("Agur! Programa ixten...");
                    break;

                default:
                    System.out.println("Aukera okerra. Saiatu berriro.");
                    break;
            }

        } while (aukera != 8);

        sc.close();
    }
}