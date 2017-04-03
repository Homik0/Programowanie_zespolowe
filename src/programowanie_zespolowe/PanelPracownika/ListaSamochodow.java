package programowanie_zespolowe.PanelPracownika;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ListaSamochodow {
    private final StringProperty namecar;
    private final StringProperty owner;
    private final StringProperty nrtel;
    private final StringProperty todo;
    private final StringProperty stancar;

    //Default constructor
    public ListaSamochodow(String namecar, String owner, String nrtel, String todo, String stancar) {
        this.namecar = new SimpleStringProperty(namecar);
        this.owner = new SimpleStringProperty(owner);
        this.nrtel = new SimpleStringProperty(nrtel);
        this.todo = new SimpleStringProperty(todo);
        this.stancar = new SimpleStringProperty(stancar);
    }

    //Getters
    public String getNameCar() {
        return namecar.get();
    }
    public String getOwner() {
        return owner.get();
    }
    public String getNrtel() {
        return nrtel.get();
    }
    public String getTodo() {
        return todo.get();
    }
    public String getStancar() {
        return stancar.get();
    }
    
    //Setters
    public void setNameCar(String value) {
        namecar.set(value);
    }
    public void setOwner(String value) {
        owner.set(value);
    }
    public void setNrtel(String value) {
        nrtel.set(value);
    }
    public void setTodo(String value) {
        todo.set(value);
    }
    public void setStancar(String value) {
        stancar.set(value);
    }
    
    //Property values
    public StringProperty namecarProperty() {
        return namecar;
    }
    public StringProperty ownerProperty() {
        return owner;
    }
    public StringProperty nrtelProperty() {
        return nrtel;
    }
    public StringProperty todoProperty() {
        return todo;
    }
    public StringProperty stancarProperty() {
        return stancar;
    }
}
