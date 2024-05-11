package ModułSprawdzaniaZadań;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public interface ModulSprawdzaniaZadan {
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
             List<Path> list = map.get(key);
             for(Path path : list){
                 if(path.getFileName().toString().equalsIgnoreCase(nazwaZadania)) rozwiazaniaZadania++;
             }
         }
        System.out.println("Liczba nadesłanych rozwiązań: " + rozwiazaniaZadania);
    }
    public static void wyswietlWszystkieZadania(){

    }
    public static void ileZadanNadeslalStudent(String imieStudenta){
         if(map.containsKey(imieStudenta)){
             System.out.println("Liczba nadesłanych zadań przez studenta " + imieStudenta + ": " + map.get(imieStudenta).size());
         }else System.out.println("Nie ma zadań od studenta o podanym imieniu i nazwisku.");
    }
    public static void wyswietlWszystkieZadaniaStudneta(String imieStudenta){

    }
}
