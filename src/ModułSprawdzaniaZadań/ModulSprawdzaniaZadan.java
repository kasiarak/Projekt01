package ModułSprawdzaniaZadań;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public interface ModulSprawdzaniaZadan {
    Scanner scanner = new Scanner(System.in);
    HashMap<String, List<Path>> map = new HashMap<>();
     public static void stworzMape(){
         Path glownyFolder = Paths.get("C:\\Users\\annar\\Zadania");
         try{
             List<Path> folderyZNazwiskami = Files.list(glownyFolder).collect(Collectors.toList());
             for(Path folderZNazwiskiem : folderyZNazwiskami){
                 List<Path> folderyZZadaniami = Files.list(folderZNazwiskiem).collect(Collectors.toList());
                 map.put(folderZNazwiskiem.getFileName().toString(), folderyZZadaniami);
             }
         }catch (IOException e){
             e.printStackTrace();
         }
    }
    public static void ileRozwiazanZadania(String nazwaZadania){
         int rozwiazaniaZadania = 0;
         for(String key : map.keySet()){
             List<Path> folderyZZadaniami = map.get(key);
             for(Path path : folderyZZadaniami){
                 if(path.getFileName().toString().equalsIgnoreCase(nazwaZadania)) rozwiazaniaZadania++;
             }
         }
        System.out.println("Liczba nadesłanych rozwiązań: " + rozwiazaniaZadania);
    }
    public static void wyswietlWszystkieZadania(){
         try{
            List<Path> folderyZZadaniami = new ArrayList<>();
            for(String key : map.keySet()){
                for(Path folder : map.get(key)){
                    folderyZZadaniami.add(folder);
                }
            }
            int indeks = 0;
            boolean sprawdzanieZadan = true;
            while (sprawdzanieZadan){
                String imieStudenta = "";
                for(String key : map.keySet()){
                    if(map.get(key).contains(folderyZZadaniami.get(indeks))) imieStudenta = key;
                }
                System.out.println("Imię i nazwisko studenta: " + imieStudenta + "   Zadanie \"" + folderyZZadaniami.get(indeks).getFileName() + "\":");
                List<Path> plikiDoZadania = Files.list(folderyZZadaniami.get(indeks)).collect(Collectors.toList());
                for(Path plikDoZadania : plikiDoZadania){
                    System.out.println("Plik \"" + plikDoZadania.getFileName() + "\":");
                    FileReader fileReader = new FileReader(plikDoZadania.toFile());
                    int znak;
                    while((znak = fileReader.read()) != -1){
                        System.out.print((char)znak);
                    }
                }
                System.out.println("Aby przejść do następnego pliku, wpisz \"dalej\", do wcześniejszego \"poprzedni\". Żeby przestać oglądać pliki wpisz \"koniec\".");
                String odpowiedz = scanner.next();
                if(odpowiedz.equalsIgnoreCase("dalej")){
                    indeks++;
                    if(indeks == folderyZZadaniami.size()) indeks = 0;
                }else if(odpowiedz.equalsIgnoreCase("poprzedni")){
                    indeks--;
                    if(indeks<0) indeks = folderyZZadaniami.size()-1;
                }else if(odpowiedz.equalsIgnoreCase("koniec")) sprawdzanieZadan = false;
                else System.out.println("Spróbuj wpisać ponownie.");
            }
         }catch (IOException e){
             e.printStackTrace();
         }
     }
    public static void ileZadanNadeslalStudent(String imieStudenta){
         if(map.containsKey(imieStudenta)){
             System.out.println("Liczba nadesłanych zadań przez studenta " + imieStudenta + ": " + map.get(imieStudenta).size());
         }else System.out.println("Nie ma zadań od studenta o podanym imieniu i nazwisku.");
    }
    public static void wyswietlWszystkieZadaniaStudenta(String imieStudenta) {
         try {
             if(map.containsKey(imieStudenta)){
                 List<Path> wszystkiePlikiStudenta = new ArrayList<>();
                 for(Path path : map.get(imieStudenta)){
                     Files.list(path).forEach(wszystkiePlikiStudenta::add);
                 }
                 boolean sprawdzanieZadan = true;
                 int indeks = 0;
                 while(sprawdzanieZadan){
                     System.out.println("Plik \""+ wszystkiePlikiStudenta.get(indeks).getFileName()+"\":");
                     FileReader fileReader = new FileReader(wszystkiePlikiStudenta.get(indeks).toFile());
                     int znak;
                     while((znak = fileReader.read()) != -1){
                         System.out.print((char)znak);
                     }
                     System.out.println("Aby przejść do następnego pliku, wpisz \"dalej\", do wcześniejszego \"poprzedni\". Żeby przestać oglądać pliki wpisz \"koniec\".");
                     String odpowiedz = scanner.next();
                     if(odpowiedz.equalsIgnoreCase("dalej")){
                         indeks++;
                         if(indeks == wszystkiePlikiStudenta.size()) indeks = 0;
                     }else if(odpowiedz.equalsIgnoreCase("poprzedni")){
                         indeks--;
                         if(indeks<0) indeks = wszystkiePlikiStudenta.size()-1;
                     }else if(odpowiedz.equalsIgnoreCase("koniec")) sprawdzanieZadan = false;
                     else System.out.println("Spróbuj wpisać ponownie.");
                 }
             }else System.out.println("Nie ma zadań od studenta o podanym imieniu i nazwisku.");
         }catch (IOException e){
             e.printStackTrace();
         }
    }
}
