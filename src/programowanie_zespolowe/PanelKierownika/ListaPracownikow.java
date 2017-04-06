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
    

 public ListaPracownikow(String Imie, String Nazwisko,String Specjalizacja,String Status,String Numer) {
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
        this.Numer = new SimpleStringProperty(Numer);
        this.Specjalizacja = new SimpleStringProperty(Specjalizacja);
        this.Status= new SimpleStringProperty(Status);
    }

    public String getImie() {
        return Imie.getValue();
    }
    public String getNazwisko() {
        return Nazwisko.getValue();
    }
    public String getNumer() {
        return Numer.getValue();
    }
    public String getSpecjalizacja() {
        return Specjalizacja.getValue();
    }
    public String getStatus() {
        return Status.getValue();
    }
    
    public void setImie(String value) {
        Imie.set(value);
    }
    public void setNazwisko(String value) {
        Nazwisko.set(value);
    }
      public void setNumer(String value) {
        Numer.set(value);
    }

    public void setSpecjalizacja(String value) {
        Specjalizacja.set(value);
    }
    public void setStatus(String value){
        Status.set(value);
    }
//    public StringProperty ImieProperty() {
//        return Imie;
//    }
//    public StringProperty NazwiskoProperty() {
//        return Nazwisko;
//    }
//   
//    public StringProperty NumerProperty() {
//        return Numer;
//    }
//    public StringProperty SpecjalizacjaProperty() {
//        return Specjalizacja;
//    }
//    public StringProperty StatusProperty() {
//        return Status;
//    }
    
 
}