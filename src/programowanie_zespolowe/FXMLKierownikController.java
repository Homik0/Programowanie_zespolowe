/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanie_zespolowe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Homik
 */
public class FXMLKierownikController implements Initializable {
    
    @FXML
    private Button zadanie;
    @FXML
    private Button samochod;
    @FXML
    private Button przydziel;
    @FXML
    private void dodajZadanie(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("dodajZadanie.fxml"));
        Scene edit_scene = new Scene(load);
        Stage edit_stage = new Stage();
        edit_stage.setTitle("Dodaj Zadanie");
        edit_stage.setScene(edit_scene);
        edit_stage.setResizable(false);
        edit_stage.sizeToScene();
        edit_stage.initModality(Modality.APPLICATION_MODAL);
        edit_stage.initOwner(zadanie.getScene().getWindow());
        edit_stage.showAndWait();
    }
    @FXML
    private void dodajSamochod(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("dodajSamochod.fxml"));
        Scene edit_scene = new Scene(load);
        Stage edit_stage = new Stage();
        edit_stage.setTitle("Dodaj Samochod");
        edit_stage.setScene(edit_scene);
        edit_stage.setResizable(false);
        edit_stage.sizeToScene();
        edit_stage.initModality(Modality.APPLICATION_MODAL);
        edit_stage.initOwner(samochod.getScene().getWindow());
        edit_stage.showAndWait();
    }
    @FXML
    private void przydzielZadanie(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("przydzielZadanie.fxml"));
        Scene edit_scene = new Scene(load);
        Stage edit_stage = new Stage();
        edit_stage.setTitle("Przydziel pracownika");
        edit_stage.setScene(edit_scene);
        edit_stage.setResizable(false);
        edit_stage.sizeToScene();
        edit_stage.initModality(Modality.APPLICATION_MODAL);
        edit_stage.initOwner(przydziel.getScene().getWindow());
        edit_stage.showAndWait();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
