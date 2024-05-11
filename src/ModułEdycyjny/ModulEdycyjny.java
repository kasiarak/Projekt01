package ModułEdycyjny;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public interface ModulEdycyjny {
    List<Zadanie> list = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public static void dodajNoweZadanie(){
        System.out.println("Podaj nazwę nowego zadania:");
        String nazwa = scanner.nextLine();
        System.out.println("Podaj treść zadania:");
        String treść = "";
        String wiersz = "";
        while(!(wiersz=scanner.nextLine()).isEmpty()){
            treść += wiersz+"\n";
        }
        list.add(new Zadanie(nazwa, treść) {
            @Override
            public void edytujZadanie() {
                System.out.println("Podaj nową treść zadania");
                scanner.nextLine();
                String treść = "";
                String wiersz = "";
                while(!(wiersz=scanner.nextLine()).isEmpty()){
                    treść += wiersz+"\n";
                }
                this.tresc = treść;
                if(this.zapisaneDoPliku){
                    try(FileWriter fileWriter = new FileWriter(this.scieżkaPliku)){
                        fileWriter.write(this.tresc);
                    }catch (IOException e){
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Edytowano zadanie");
            }

            @Override
            public void dodajDoFolderu(){
                System.out.println("Podaj dokładną ścieżkę folderu, do którego chcesz dodać plik z zadaniem");
                scanner.nextLine();
                this.scieżkaPliku = scanner.nextLine()+"\\"+this.nazwa;
                try(FileWriter fileWriter = new FileWriter(this.scieżkaPliku)){
                    fileWriter.write(this.tresc);
                    System.out.println("Zapisano zadanie do pliku");
                }catch (FileNotFoundException e){
                    System.out.println("Podana ścieżka nie istnieje");
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void zapiszDoPliku() {
                this.zapisaneDoPliku = true;
                this.scieżkaPliku = "C:\\Users\\annar\\" + nazwa +".txt";
                try(FileWriter fileWriter = new FileWriter(this.scieżkaPliku)){
                    fileWriter.write(this.tresc);
                    System.out.println("Zapisano zadanie do pliku");
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        });
    };
    public static void wyswietlZadania(){
        Iterator<Zadanie> iterator = list.iterator();
        int indeks = 0;
        while (iterator.hasNext()){
            Zadanie zadanie = iterator.next();
            System.out.println(indeks+1 +". " + zadanie.nazwa);
            indeks++;
        }
    };
    public static void wyswietlZadanie(int indeks){
        if(indeks < 0 || indeks > list.size()){
            System.out.println("Nie ma zadania o podanym indeksie");
        }else{
            System.out.println("Aby wrócić do listy wpisz 0, jeśli chcesz edytować zadanie wpisz 1. ");
            if(!list.get(indeks-1).zapisaneDoPliku) System.out.print("Aby zapisać do pliku wpisz 2.\n");
            else System.out.print("Aby dodać plik z zadaniem do folderu wpisz 2.\n");
            System.out.println(list.get(indeks-1));
            if(scanner.hasNextInt()){
                int odpowiedz = scanner.nextInt();
                PolecenieModulEdycyjny polecenie = PolecenieModulEdycyjny.nadajPolecenie(odpowiedz);
                if(polecenie != null){
                    switch (polecenie){
                        case EDYCJA -> {
                            list.get(indeks-1).edytujZadanie();
                            break;
                        }
                        case PLIK -> {
                            if(list.get(indeks-1).zapisaneDoPliku){
                                list.get(indeks-1).dodajDoFolderu();
                            }else{
                                list.get(indeks-1).zapiszDoPliku();
                            }
                            break;
                        }
                        case POWROT -> {
                            break;
                        }
                    }
                }
            }else {
                System.out.println("Spróbuj wpisać ponownie");
                scanner.nextLine();
            }
        }
    }
}
