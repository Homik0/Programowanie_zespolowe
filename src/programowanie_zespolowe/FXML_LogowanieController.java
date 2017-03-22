/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanie_zespolowe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.management.Query.value;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author Homik
 */
public class FXML_LogowanieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private Button zmiana;

    @FXML
    private ChoiceBox wybor;

    @FXML
    private void logowanie(ActionEvent event) throws Exception {
        if ("Dyrektor".equals(wybor.toString())) {
            Stage dyrektorStage = new Stage();
            Parent root1 = FXMLLoader.load(getClass().getResource("FXMLDyrektor.fxml"));
            Scene scene1 = new Scene(root1);
            dyrektorStage.setTitle("Panel Dyrektora");
            dyrektorStage.setScene(scene1);
            dyrektorStage.setResizable(false);
            dyrektorStage.sizeToScene();
            dyrektorStage.show();
        } else {
            if ("Kierownik".equals(wybor.toString())) {
                    Stage kierownikStage = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("FXMLKierownik.fxml"));
                    Scene scene1 = new Scene(root1);
                    kierownikStage.setTitle("Panel Kierownika");
                    kierownikStage.setScene(scene1);
                    kierownikStage.setResizable(false);
                    kierownikStage.sizeToScene();
                    kierownikStage.show();
            } else {
                if ("Pracownik".equals(wybor.toString())) {
                    Stage pracownikStage = new Stage();
                    Parent root1 = FXMLLoader.load(getClass().getResource("FXMLPracownik.fxml"));
                    Scene scene1 = new Scene(root1);
                    pracownikStage.setTitle("Panel Pracownika");
                    pracownikStage.setScene(scene1);
                    pracownikStage.setResizable(false);
                    pracownikStage.sizeToScene();
                    pracownikStage.show();
                } else {
                    showMessageDialog(null, "Niepoprawny login lub hasło");
                }
            }
        }
    }
    @FXML
    private void zmianaHasla(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("zmianahasla.fxml"));
        Scene edit_scene = new Scene(load);
        Stage edit_stage = new Stage();
        edit_stage.setTitle("Zmiana hasła");
        edit_stage.setScene(edit_scene);
        edit_stage.setResizable(false);
        edit_stage.sizeToScene();
        edit_stage.initModality(Modality.APPLICATION_MODAL);
        edit_stage.initOwner(zmiana.getScene().getWindow());
        edit_stage.showAndWait();
    }
}
