package programowanie_zespolowe.PanelDyrektora;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Misiek
 */
public class ListaPracownikow {
     private final StringProperty Imie;
    private final StringProperty Nazwisko;
    private final StringProperty Staz;
    private final StringProperty Numer;
    private final StringProperty Wynagrodzenie;
    private final StringProperty Stanowisko;
    

 public ListaPracownikow(String Imie, String Nazwisko, String Staz, String Numer,String Wynagrodzenie,String Stanowisko) {
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
        this.Staz = new SimpleStringProperty(Staz);
        this.Numer = new SimpleStringProperty(Numer);
        this.Wynagrodzenie = new SimpleStringProperty(Wynagrodzenie);
        this.Stanowisko = new SimpleStringProperty(Stanowisko);
    }

    public StringProperty getImie() {
        return Imie;
    }

    public StringProperty getNazwisko() {
        return Nazwisko;
    }

    public StringProperty getStaz() {
        return Staz;
    }

    public StringProperty getNumer() {
        return Numer;
    }

    public StringProperty getWynagrodzenie() {
        return Wynagrodzenie;
    }

    public StringProperty getStanowisko() {
        return Stanowisko;
    }
    public void setImie(String value) {
        Imie.set(value);
    }
    public void setNazwisko(String value) {
        Nazwisko.set(value);
    }
    public void setStaz(String value) {
        Staz.set(value);
    }
    public void setNumer(String value) {
        Numer.set(value);
    }
    public void setWynagrodzenie(String value) {
        Wynagrodzenie.set(value);
    }
    public void setStanowisko(String value) {
        Stanowisko.set(value);
    }
    public StringProperty ImieProperty() {
        return Imie;
    }
    public StringProperty NazwiskoProperty() {
        return Nazwisko;
    }
    public StringProperty StazProperty() {
        return Staz;
    }
    public StringProperty NumerProperty() {
        return Numer;
    }
    public StringProperty WynagrodzenieProperty() {
        return Wynagrodzenie;
    }
    public StringProperty StanowiskoProperty() {
        return Stanowisko;
    }
    
 
}