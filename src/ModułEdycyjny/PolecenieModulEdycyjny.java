package ModułEdycyjny;

import static ModułEdycyjny.ModulEdycyjny.scanner;

public enum PolecenieModulEdycyjny {
    POWROT(0), EDYCJA(1), PLIK(2);
    int numer;

    public int getNumer() {
        return numer;
    }

    PolecenieModulEdycyjny(int numer){
        this.numer = numer;
    }
    static PolecenieModulEdycyjny nadajPolecenie(int odpowiedz){
        for(PolecenieModulEdycyjny polecenie : PolecenieModulEdycyjny.values()){
            if(polecenie.getNumer() == odpowiedz){
                return polecenie;
            }
        }
        System.out.println("Spróbuj wpisać ponownie");
        scanner.nextLine();
        return null;
    }
}
