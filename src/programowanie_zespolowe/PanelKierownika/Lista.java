/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanie_zespolowe.PanelKierownika;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Homik
 */
public class Lista {

    private final StringProperty NameCar;
    private final StringProperty Owner;
    private final StringProperty ToDo;
    private final StringProperty Spec;
    private final StringProperty Status;


    public Lista(String NameCar, String Owner, String ToDo, String Spec, String Status) {
        this.NameCar = new SimpleStringProperty(NameCar);
        this.Owner = new SimpleStringProperty(Owner);
        this.ToDo = new SimpleStringProperty(ToDo);
        this.Spec = new SimpleStringProperty(Spec);
        this.Status = new SimpleStringProperty(Status);
    }


    public String getNameCar() {
        return NameCar.getValue();
    }

    public String getToDo() {
        return ToDo.getValue();
    }

    public String getSpec() {
        return Spec.getValue();
    }

    public String getOwner() {
        return Owner.getValue();
    }

    public String getStatus() {
        return Status.getValue();
    }


    public void setNameCar(String value) {
        NameCar.set(value);
    }

    public void setToDo(String value) {
        ToDo.set(value);
    }

    public void setSpec(String value) {
        Spec.set(value);
    }

    public void setOwner(String value) {
        Owner.set(value);
    }

    public void setStatus(String value) {
        Status.set(value);
    }
    public StringProperty NameCarProperty() {
        return NameCar;
    }
    public StringProperty OwnerProperty() {
        return Owner;
    }
   
    public StringProperty ToDoProperty() {
        return ToDo;
    }
    public StringProperty SpecProperty() {
        return Spec;
    }
    public StringProperty StatusProperty() {
        return Status;
    }
}
