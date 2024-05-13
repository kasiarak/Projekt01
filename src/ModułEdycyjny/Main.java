package ModułEdycyjny;

import java.util.Scanner;

public class Main implements ModulEdycyjny{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean programWlaczony = true;
        while (programWlaczony){
           System.out.println("Aby wyświetlić treść danego zadania, wpisz odpowiadający mu numer. Aby dodać nowe wpisz 0. Lista zadań:");
           ModulEdycyjny.wyswietlZadania();
           if(scanner.hasNextInt()){
               int indeks = scanner.nextInt();
               if(indeks==0){
                   ModulEdycyjny.dodajNoweZadanie();
               }
               else{
                   ModulEdycyjny.wyswietlZadanie(indeks);
               }
           }else{
               System.out.println("Nie ma zadania o podanym indeksie");
           }
        }
    }
}
