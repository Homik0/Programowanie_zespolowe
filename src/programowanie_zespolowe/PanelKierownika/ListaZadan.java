package programowanie_zespolowe.PanelKierownika;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 2marc
 */
public class ListaZadan {
    private final StringProperty Imie;
    private final StringProperty Nazwisko;
    private final StringProperty NameCar;
    private final StringProperty ToDo;
    private final StringProperty Spec;
    private final StringProperty Date;
    private final StringProperty Status;

    /**
     *
     * @param Imie
     * @param Nazwisko
     * @param NameCar
     * @param ToDo
     * @param Spec
     * @param Date
     * @param Status
     */
    public ListaZadan(String Imie, String Nazwisko,String NameCar,String ToDo,String Spec,String Date,String Status) {
        this.Imie = new SimpleStringProperty(Imie);
        this.Nazwisko = new SimpleStringProperty(Nazwisko);
        this.NameCar = new SimpleStringProperty(NameCar);
        this.ToDo = new SimpleStringProperty(ToDo);
        this.Spec = new SimpleStringProperty(Spec);
        this.Date= new SimpleStringProperty(Date);
        this.Status= new SimpleStringProperty(Status);
    }

    /**
     *
     * @return Imie
     */
    public String getImie() {
        return Imie.getValue();
    }

    /**
     *
     * @return Nazwisko
     */
    public String getNazwisko() {
        return Nazwisko.getValue();
    }

    /**
     *
     * @return Nazwe Samochodu
     */
    public String getNameCar() {
        return NameCar.getValue();
    }

    /**
     *
     * @return DO zrobienia
     */
    public String getToDo() {
        return ToDo.getValue();
    }

    /**
     *
     * @return Specjalizacja
     */
    public String getSpec() {
        return Spec.getValue();
    }

    /**
     *
     * @return Data
     */
    public String getDate() {
        return Date.getValue();
    }
//ccsc

    /**
     *
     * @return Stan Zadania
     */
    public String getStatus() {
        return Status.getValue();
    }

    /**
     *
     * @param value Imie
     */
    public void setImie(String value) {
        Imie.set(value);
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
     * @param value Nazwa Samochodu
     */
    public void setNameCar(String value) {
        NameCar.set(value);
    }

    /**
     *
     * @param value do zrobienia
     */
    public void setToDo(String value) {
        ToDo.set(value);
    }

    /**
     *
     * @param value specjalizacja
     */
    public void setSpec(String value) {
        Spec.set(value);
    }

    /**
     *
     * @param value data
     */
    public void setDate(String value) {
       Date.set(value);
    }

    /**
     *
     * @param value stan zadania
     */
    public void setStatus(String value) {
       Status.set(value);
    }

}