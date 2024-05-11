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
}
