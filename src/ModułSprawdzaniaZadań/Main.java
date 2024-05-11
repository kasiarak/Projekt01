package ModułSprawdzaniaZadań;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main implements ModulSprawdzaniaZadan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ModulSprawdzaniaZadan.stworzMape();
        boolean programWlaczony = true;
        while (programWlaczony) {
            System.out.println("Podaj numer polecenia\n1. Liczba otrzymanych rozwiązań konkretnego zadania" +
                    "\n2. Podgląd wszystkich zadań po kolei\n3. Informacja ile zadań nadesłał konkretny studnet" +
                    "\n4. Podgląd wszystkich zadań konkretnego studenta");
            if (scanner.hasNextInt()) {
                int odpowiedz = scanner.nextInt();
                switch (odpowiedz) {
                    case 1 -> {
                        System.out.println("Podaj nazwe zdania");
                        String nazwaZadania = scanner.next();
                        ModulSprawdzaniaZadan.ileRozwiazanZadania(nazwaZadania);
                        break;
                    }
                    case 2 -> {
                        ModulSprawdzaniaZadan.wyswietlWszystkieZadania();
                        break;
                    }
                    case 3 -> {
                        scanner.nextLine();
                        System.out.println("Podaj imię i nazwisko studenta");
                        String imieStudenta = scanner.nextLine();
                        ModulSprawdzaniaZadan.ileZadanNadeslalStudent(imieStudenta);
                        break;
                    }
                    case 4 -> {
                        System.out.println("Podaj imię i nazwisko studenta");
                        String imieStudenta = scanner.next();
                        ModulSprawdzaniaZadan.wyswietlWszystkieZadaniaStudneta(imieStudenta);
                        break;
                    }
                    default -> {
                        System.out.println("Nie ma polecenia o podanym numerze.");
                    }
                }
            }else {
                System.out.println("Nie ma polecenia o podanym numerze.");
                scanner.next();
            }
        }
    }
}