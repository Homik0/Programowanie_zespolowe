package programowanie_zespolowe.PanelKierownika;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author atoja
 */
public class ListaSamochodow {
    private final StringProperty namecar;
    private final StringProperty owner;
    private final StringProperty nrtel;
    private final StringProperty todo;
    private final StringProperty stancar;

    /**
     *
     * @param namecar
     * @param owner
     * @param nrtel
     * @param todo
     * @param stancar
     */
    public ListaSamochodow(String namecar, String owner, String nrtel, String todo, String stancar) {
        this.namecar = new SimpleStringProperty(namecar);
        this.owner = new SimpleStringProperty(owner);
        this.nrtel = new SimpleStringProperty(nrtel);
        this.todo = new SimpleStringProperty(todo);
        this.stancar = new SimpleStringProperty(stancar);
    }

    /**
     *
     * @return zwraca nazwę samochodu
     */
    public String getNameCar() {
        return namecar.get();
    }

    /**
     *
     * @return zwraca właściciela
     */
    public String getOwner() {
        return owner.get();
    }

    /**
     *
     * @return zwraca numer telefonu
     */
    public String getNrtel() {
        return nrtel.get();
    }

    /**
     *
     * @return zwraca co jest do zrobienia
     */
    public String getTodo() {
        return todo.get();
    }

    /**
     *
     * @return zwraca stan samochodu
     */
    public String getStancar() {
        return stancar.get();
    }
    
    /**
     *
     * @param value ustawia numer samochodu
     */
    public void setNameCar(String value) {
        namecar.set(value);
    }

    /**
     *
     * @param value ustawia właściciela
     */
    public void setOwner(String value) {
        owner.set(value);
    }

    /**
     *
     * @param value ustawia numer telefonu
     */
    public void setNrtel(String value) {
        nrtel.set(value);
    }

    /**
     *
     * @param value ustawia co jest do zrobienia
     */
    public void setTodo(String value) {
        todo.set(value);
    }

    /**
     *
     * @param value ustawia stan samochodu
     */
    public void setStancar(String value) {
        stancar.set(value);
    }
    
    /**
     *
     * @return zwraca nazwę samochodu
     */
    public StringProperty namecarProperty() {
        return namecar;
    }

    /**
     *
     * @return zwraca właściciela samochodu
     */
    public StringProperty ownerProperty() {
        return owner;
    }

    /**
     *
     * @return zwraca numer telefonu
     */
    public StringProperty nrtelProperty() {
        return nrtel;
    }

    /**
     *
     * @return zwraca co jest do zrobienia
     */
    public StringProperty todoProperty() {
        return todo;
    }

    /**
     *
     * @return zwraca stan samochodu
     */
    public StringProperty stancarProperty() {
        return stancar;
    }
}
