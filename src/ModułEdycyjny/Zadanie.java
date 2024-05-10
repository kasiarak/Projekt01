package ModułEdycyjny;

abstract public class Zadanie {
    String nazwa;
    String tresc;
    boolean zapisaneDoPliku;
    String scieżkaPliku;
    public Zadanie(String nazwa, String tresc) {
        this.nazwa = nazwa;
        this.tresc = tresc;
        this.zapisaneDoPliku = false;
    }

    @Override
    public String toString() {
        return "Zadanie " + "\""+nazwa+"\""+":\n" + tresc;
    }

    abstract public void edytujZadanie();
    abstract public void zapiszDoPliku();
    abstract public void dodajDoFolderu();
}
