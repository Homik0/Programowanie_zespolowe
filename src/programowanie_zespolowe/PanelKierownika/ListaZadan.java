package programowanie_zespolowe.PanelKierownika;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class ListaZadan {
    private final StringProperty Imie;
    private final StringProperty Nazwisko;
    private final StringProperty NameCar;
    private final StringProperty ToDo;
    private final StringProperty Date;
    private final StringProperty Status;

public ListaZadan(String Imie, String Nazwisko,String NameCar,String ToDo,String Date,String Status) {
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
        this.NameCar = new SimpleStringProperty(NameCar);
        this.ToDo = new SimpleStringProperty(ToDo);
        this.Date= new SimpleStringProperty(Date);
        this.Status= new SimpleStringProperty(Status);
    }

    public String getImie() {
        return Imie.getValue();
    }

    public String getNazwisko() {
        return Nazwisko.getValue();
    }

    public String getNameCar() {
        return NameCar.getValue();
    }

    public String getToDo() {
        return ToDo.getValue();
    }

    public String getDate() {
        return Date.getValue();
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
      public void setNameCar(String value) {
        NameCar.set(value);
    }
      public void setToDo(String value) {
        ToDo.set(value);
    }
    public void setDate(String value) {
       Date.set(value);
    }
      public void setStatus(String value) {
       Status.set(value);
    }

}