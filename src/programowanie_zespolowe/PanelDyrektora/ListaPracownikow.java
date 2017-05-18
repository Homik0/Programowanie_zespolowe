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
    private final StringProperty Specjalizacja;
    private final StringProperty Stanowisko;
    private final StringProperty Login;
    private final StringProperty Haslo;
    
    /**
     *
     * @param Imie
     * @param Nazwisko
     * @param Staz
     * @param Numer
     * @param Wynagrodzenie
     * @param Specjalizacja
     * @param Stanowisko
     * @param Login
     * @param Haslo
     */
    public ListaPracownikow(String Imie, String Nazwisko, String Staz, String Numer,String Wynagrodzenie,String Specjalizacja,String Stanowisko,String Login,String Haslo) {
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
        this.Staz = new SimpleStringProperty(Staz);
        this.Numer = new SimpleStringProperty(Numer);
        this.Wynagrodzenie = new SimpleStringProperty(Wynagrodzenie);
        this.Specjalizacja = new SimpleStringProperty(Specjalizacja);
        this.Stanowisko= new SimpleStringProperty(Stanowisko);
        this.Login= new SimpleStringProperty(Login);
        this.Haslo= new SimpleStringProperty(Haslo);
    }

    /**
     *
     * @return zwraca Imię
     */
    public StringProperty getImie() {
        return Imie;
    }

    /**
     *
     * @return zwraca Nazwisko
     */
    public StringProperty getNazwisko() {
        return Nazwisko;
    }

    /**
     *
     * @return zwraca Staz
     */
    public StringProperty getStaz() {
        return Staz;
    }

    /**
     *
     * @return zwraca Numer
     */
    public StringProperty getNumer() {
        return Numer;
    }

    /**
     *
     * @return zwraca Wynagrodzenie
     */
    public StringProperty getWynagrodzenie() {
        return Wynagrodzenie;
    }

    /**
     *
     * @return zwraca Specjalizacje
     */
    public StringProperty getSpecjalizacja() {
        return Specjalizacja;
    }

    /**
     *
     * @return zwraca Stanowisko
     */
    public StringProperty getStanowisko() {
        return Stanowisko;
    }

    /**
     *
     * @return zwraca Login
     */
    public String getLogin() {
        return Login.getValue();
    }

    /**
     *
     * @return zwraca Haslo
     */
    public String getHaslo() {
        return Haslo.getValue();
    }
    
    /**
     *
     * @param value Imię
     */
    public void setImie(String value) {
        Imie.set(value);
    }

    /**
     *
     * @param value Login
     */
    public void setLogin(String value) {
        Login.set(value);
    }

    /**
     *
     * @param value Nazwisko
     */
    public void setNazwisko(String value) {
        Nazwisko.set(value);
    }

    /**
     *
     * @param value Staz
     */
    public void setStaz(String value) {
        Staz.set(value);
    }

    /**
     *
     * @param value Numer
     */
    public void setNumer(String value) {
        Numer.set(value);
    }

    /**
     *
     * @param value Wynagrodzenie
     */
    public void setWynagrodzenie(String value) {
        Wynagrodzenie.set(value);
    }

    /**
     *
     * @param value Specjalizacja
     */
    public void setSpecjalizacja(String value) {
        Specjalizacja.set(value);
    }

    /**
     *
     * @param value Haslo
     */
    public void setHaslo(String value){
        Haslo.set(value);
    }

    /**
     *
     * @param value Stanowisko
     */
    public void setStanowisko(String value){
        Stanowisko.set(value);
    }

    /**
     *
     * @return zwraca Imię
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
     * @return zwraca Staz
     */
    public StringProperty StazProperty() {
        return Staz;
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
     * @return zwraca Wynagrodzenie
     */
    public StringProperty WynagrodzenieProperty() {
        return Wynagrodzenie;
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
     * @return zwraca Stanowisko
     */
    public StringProperty StanowiskoProperty() {
        return Stanowisko;
    }

    /**
     *
     * @return zwraca Haslo
     */
    public StringProperty HasloProperty() {
        return Haslo;
    }
    
 
}