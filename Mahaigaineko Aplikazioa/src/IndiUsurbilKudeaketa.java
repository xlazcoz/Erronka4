import java.util.Scanner;

public class IndiUsurbilKudeaketa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProduktuDAO dao = new ProduktuDAO();
        boolean irtenda = false;

        while (!irtenda) {
            System.out.println("\n--- INDI USURBIL KUDEAKETA ---");
            System.out.println("1. Produktu berria gehitu");
            System.out.println("2. Irten");
            System.out.print("Aukeratu aukera bat: ");

            int aukera = -1;
            try {
                aukera = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Mesedez, sartu zenbaki baliodun bat.");
                continue;
            }

            switch (aukera) {
                case 1:
                    // Produktu berria sortzeko formularioa
                    System.out.println("\n--- PRODUKTU BERRIA ---");
                    
                    System.out.print("Izena: ");
                    String izena = sc.nextLine();

                    System.out.print("Deskribapena: ");
                    String deskribapena = sc.nextLine();

                    double prezioa = 0.0;
                    while (true) {
                        try {
                            System.out.print("Prezioa (adib. 10.50): ");
                            prezioa = Double.parseDouble(sc.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Errorea: Prezioak zenbakia izan behar du (puntuarekin).");
                        }
                    }

                    int stocka = 0;
                    while (true) {
                        try {
                            System.out.print("Stock kopurua: ");
                            stocka = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Errorea: Stock-a zenbaki osoa izan behar da.");
                        }
                    }

                    System.out.println("Kategoria IDak: 1 (Men), 2 (Jewelery), 3 (Electronics), 4 (Women)");
                    int kategoriaId = 0;
                    while (true) {
                        try {
                            System.out.print("Kategoria ID: ");
                            kategoriaId = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Errorea: ID baliogabea.");
                        }
                    }

                    System.out.print("Irudiaren URL-a (aukerakoa): ");
                    String irudiaUrl = sc.nextLine();

                    // Objektua sortu
                    Produktua produktuBerria = new Produktua(izena, deskribapena, prezioa, stocka, kategoriaId, irudiaUrl);
                    
                    // DAOari deitu gordetzeko
                    dao.produktuBerriaSortu(produktuBerria);
                    break;

                case 2:
                    System.out.println("Agur!");
                    irtenda = true;
                    break;

                default:
                    System.out.println("Aukera okerra.");
            }
        }
        sc.close();
    }
}