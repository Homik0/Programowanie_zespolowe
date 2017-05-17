package programowanie_zespolowe.PanelKierownika;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Misiek
 */
public class ListaPracownikow {
    private final StringProperty Imie;
    private final StringProperty Nazwisko;
    private final StringProperty Numer;
    private final StringProperty Specjalizacja;
    private final StringProperty Status;
    
    /**
     *
     * @param Imie
     * @param Nazwisko
     * @param Specjalizacja
     * @param Status
     * @param Numer
     */
    public ListaPracownikow(String Imie, String Nazwisko,String Specjalizacja,String Status,String Numer) {
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
        this.Numer = new SimpleStringProperty(Numer);
        this.Specjalizacja = new SimpleStringProperty(Specjalizacja);
        this.Status= new SimpleStringProperty(Status);
    }

    /**
     *
     * @return zwraca Imię
     */
    public String getImie() {
        return Imie.getValue();
    }

    /**
     *
     * @return zwraca Nazwisko
     */
    public String getNazwisko() {
        return Nazwisko.getValue();
    }

    /**
     *
     * @return zwraca Numer
     */
    public String getNumer() {
        return Numer.getValue();
    }

    /**
     *
     * @return zwraca Specjalizacje
     */
    public String getSpecjalizacja() {
        return Specjalizacja.getValue();
    }

    /**
     *
     * @return zwraca Status
     */
    public String getStatus() {
        return Status.getValue();
    }
    
    /**
     *
     * @param value ustawia Imie
     */
    public void setImie(String value) {
        Imie.set(value);
    }

    /**
     *
     * @param value ustawia Imie
     */
    public void setNazwisko(String value) {
        Nazwisko.set(value);
    }

    /**
     *
     * @param value ustawia Numer
     */
    public void setNumer(String value) {
        Numer.set(value);
    }

    /**
     *
     * @param value ustawia Specjalizacja
     */
    public void setSpecjalizacja(String value) {
        Specjalizacja.set(value);
    }

    /**
     *
     * @param value ustawia Status
     */
    public void setStatus(String value){
        Status.set(value);
    }

    /**
     *
     * @return zwraca Imie
     */
    public StringProperty ImieProperty() {
        return Imie;
    }

    /**
     *
     * @return zwraca Nazwisko
     */
    public StringProperty NazwiskoProperty() {
        return Nazwisko;
    }
   
    /**
     *
     * @return zwraca Numer
     */
    public StringProperty NumerProperty() {
        return Numer;
    }

    /**
     *
     * @return zwraca Specjalizacja
     */
    public StringProperty SpecjalizacjaProperty() {
        return Specjalizacja;
    }

    /**
     *
     * @return zwraca Status
     */
    public StringProperty StatusProperty() {
        return Status;
    }
    
 
}