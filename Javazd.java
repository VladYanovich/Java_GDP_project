import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.BigDecimal;
import java.io.FileWriter;
import java.io.IOException;


public class Javazd {
    public static void main(String[] args) {
           
        Scanner scanner = new Scanner(System.in);
        String answer;
        // Lista do przechowywania zestawów danych PKB
        List<double[]> allData = new ArrayList<>();
        // Lista do przechowywania wartości inflacji
        LinkedList<BigDecimal> inflationData = new LinkedList<>();
        // Tablica z nazwami zmiennych
        String[] PKB1 = {"year", "consumption", "investments", "goverspend", "external balance", "DW", "depreciation", "indirect taxes", "subsidies"};
        
        do {
            // Tablica do przechowywania jednego zestawu danych PKB
            double[] data = new double[PKB1.length];

            for (int i = 0; i < PKB1.length; i++) {
                System.out.print("Wprowadź wartość dla " + PKB1[i] + ": ");
                data[i] = scanner.nextInt();
            }
            // Wprowadzanie inflacji
            System.out.print("Wprowadź wartość inflacji: ");
            BigDecimal inflation = scanner.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);

            // Dodawanie wprowadzonych danych do list
            allData.add(data);
            inflationData.add(inflation);

            System.out.println("\nWprowadzone dane:");
            for (int i = 0; i < data.length; i++) {
                System.out.println(PKB1[i] + ": " + data[i]);
            }

            System.out.println("inflacja: " + inflation);

            scanner.nextLine();

            System.out.print("\nCzy chcesz wprowadzić inny zestaw danych? (tak/nie): ");
            answer = scanner.nextLine().toLowerCase();

        } while (answer.equalsIgnoreCase("tak"));

        // zmiana wartości inflacji
        System.out.print("\nCzy chcesz zmienić jakieś wartości inflacji? (tak/nie): ");
        answer = scanner.nextLine().toLowerCase();
        if (answer.equals("tak") || answer.equals("ta") || answer.equals("takk")) {
            for (BigDecimal item : inflationData) {
                System.out.print("Bieżąca wartość inflacji dla tabeli " + (inflationData.indexOf(item) + 1) + " równa sié " + item + ". Wprowadź nową wartość lub naciśnij Enter, aby ją zachować: ");
                String newValue = scanner.nextLine();
                if (!newValue.trim().isEmpty()) {
                    BigDecimal newInflationValue = new BigDecimal(newValue).setScale(2, RoundingMode.HALF_UP);
                    inflationData.set(inflationData.indexOf(item), newInflationValue);
                }
            }
        }
        // Wyświetlanie danych dla każdego zestawu
             for (int tableIndex = 0; tableIndex < allData.size(); tableIndex++) {
    
                double[] data = allData.get(tableIndex);
                BigDecimal inflation = inflationData.get(tableIndex);
                Wskaznik WSK = new Wskaznik();
                WSK.setYear(data[0]);
                PKB PKB1F = new PKB(data[1],data[2]);
                PKB PKB2F = new PKB(data[1],data[2]);
                PKB PKB3F = new PKB(data[1],data[2]);
                Narodowy PNB  = new Narodowy(data[1],data[2],data[3],data[4],data[5]);
                Narodowy PNN  = new Narodowy(data[1],data[2],data[3],data[4],data[5]);
                Narodowy DN  = new Narodowy(data[1],data[2],data[3],data[4],data[5]);


                System.out.println("\nRok " + WSK.getYear() + ":");
                System.out.println("PKB faza 1: " + PKB1F.Wyprowadzenie1F());



                if(PKB1F.Wyprowadzenie1F() != PKB2F.Wyprowadzenie(data[3])) System.out.println("PKB faza 2: " + PKB2F.Wyprowadzenie(data[3]));
                if(PKB2F.Wyprowadzenie(data[3]) != PKB3F.Wyprowadzenie(data[3],data[4])) System.out.println("PKB faza 3: " + PKB3F.Wyprowadzenie(data[3],data[4]));
                if(PKB3F.Wyprowadzenie(data[3],data[4]) != PNB.WyprowadzeniePNB()) System.out.println("PNB: " + PNB.WyprowadzeniePNB());
                if(PNB.WyprowadzeniePNB() != PNN.WyprowadzenieN(data[6])) System.out.println("PNN: " + PNN.WyprowadzenieN(data[6]));
                if(PNN.WyprowadzenieN(data[6]) != DN.WyprowadzenieN(data[6],data[7],data[8])) System.out.println("Dochód Narodowy: " + DN.WyprowadzenieN(data[6],data[7],data[8]));


                System.out.println("Inflacja: " + inflation);

             }

             saveDataToFile(allData, inflationData);
            }

        
        // Metoda zapisująca dane do pliku
    private static void saveDataToFile(List<double[]> allData, LinkedList<BigDecimal> inflationData) {
        try (FileWriter writer = new FileWriter("dane_pkb.txt")) {
            for (int i = 0; i < allData.size(); i++) {
                double[] data = allData.get(i);
                BigDecimal inflation = inflationData.get(i);
                Wskaznik WSK = new Wskaznik();
                WSK.setYear(data[0]);
                PKB PKB1F = new PKB(data[1], data[2]);
                PKB PKB2F = new PKB(data[1], data[2]);
                PKB PKB3F = new PKB(data[1], data[2]);
                Narodowy PNB = new Narodowy(data[1], data[2], data[3], data[4], data[5]);
                Narodowy PNN = new Narodowy(data[1], data[2], data[3], data[4], data[5]);
                Narodowy DN = new Narodowy(data[1], data[2], data[3], data[4], data[5]);

                writer.write("Zestaw " + (i + 1) + ":\n");
                writer.write("Rok: " + (int) data[0] + "\n");
                writer.write("Konsumpcja: " + data[1] + "\n");
                writer.write("Inwestycje: " + data[2] + "\n");
                writer.write("Wydatki rządowe: " + data[3] + "\n");
                writer.write("Saldo bilansu zagranicznego: " + data[4] + "\n");
                writer.write("DW: " + data[5] + "\n");
                writer.write("Amortyzacja: " + data[6] + "\n");
                writer.write("Podatki pośrednie: " + data[7] + "\n");
                writer.write("Subsydia: " + data[8] + "\n");
                writer.write("Inflacja: " + inflation + "\n\n");
                writer.write("PKB faza 1: " + PKB1F.Wyprowadzenie1F() + "\n");
                if (PKB1F.Wyprowadzenie1F() != PKB2F.Wyprowadzenie(data[3])) writer.write("PKB faza 2: " + PKB2F.Wyprowadzenie(data[3]) + "\n");
                if (PKB2F.Wyprowadzenie(data[3]) != PKB3F.Wyprowadzenie(data[3], data[4])) writer.write("PKB faza 3: " + PKB3F.Wyprowadzenie(data[3], data[4]) + "\n");
                if (PKB3F.Wyprowadzenie(data[3], data[4]) != PNB.WyprowadzeniePNB()) writer.write("PNB: " + PNB.WyprowadzeniePNB() + "\n");
                if (PNB.WyprowadzeniePNB() != PNN.WyprowadzenieN(data[6])) writer.write("PNN: " + PNN.WyprowadzenieN(data[6]) + "\n");
                if (PNN.WyprowadzenieN(data[6]) != DN.WyprowadzenieN(data[6], data[7], data[8])) writer.write("Dochód Narodowy: " + DN.WyprowadzenieN(data[6], data[7], data[8]) + "\n");
                writer.write("Inflacja: " + inflation + "\n\n");
            }
            System.out.println("Dane zostały zapisane do pliku dane_pkb.txt.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisywania danych do pliku: " + e.getMessage());
        }
    }
}


    