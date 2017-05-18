package programowanie_zespolowe.PanelPracownika;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author student
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
     * @param string
     * @param string0
     * @param string1
     * @param string2
     * @param string3
     * @param string4
     */
    public ListaSamochodow(String string, String string0, String string1, String string2, String string3, String string4) {
        throw new UnsupportedOperationException("Nie są obsługiwane.");
    }

    /**
     *
     * @return get NameCar
     */
    public String getNameCar() {
        return namecar.get();
    }

    /**
     *
     * @return get owner
     */
    public String getOwner() {
        return owner.get();
    }

    /**
     *
     * @return get Nrtel
     */
    public String getNrtel() {
        return nrtel.get();
    }

    /**
     *
     * @return get Todo
     */
    public String getTodo() {
        return todo.get();
    }

    /**
     *
     * @return get Stancar
     */
    public String getStancar() {
        return stancar.get();
    }
    
    /**
     *
     * @param value ustawić wartości NameCar
     */
    public void setNameCar(String value) {
        namecar.set(value);
    }

    /**
     *
     * @param value ustawić wartości Owner
     */
    public void setOwner(String value) {
        owner.set(value);
    }

    /**
     *
     * @param value ustawić wartości Nrtel
     */
    public void setNrtel(String value) {
        nrtel.set(value);
    }

    /**
     *
     * @param value ustawić wartości Todo
     */
    public void setTodo(String value) {
        todo.set(value);
    }

    /**
     *
     * @param value ustawić wartości Stancar
     */
    public void setStancar(String value) {
        stancar.set(value);
    }
    
    /**
     *
     * zwraca namecar
     * @return 
     */
    public StringProperty namecarProperty() {
        return namecar;
    }

    /**
     *
     * zwraca owner 
     * @return 
     */
    public StringProperty ownerProperty() {
        return owner;
    }

    /**
     *
     * zwraca nrtel
     * @return 
     */
    public StringProperty nrtelProperty() {
        return nrtel;
    }

    /**
     *
     * zwraca todo
     * @return 
     */
    public StringProperty todoProperty() {
        return todo;
    }

    /**
     *
     * zwraca stancar
     * @return 
     */
    public StringProperty stancarProperty() {
        return stancar;
    }
}
