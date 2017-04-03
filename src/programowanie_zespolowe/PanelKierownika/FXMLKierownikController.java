package programowanie_zespolowe.PanelKierownika;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

public class FXMLKierownikController implements Initializable {
    
    @FXML
    private Button zadanie;
    @FXML
    private Button samochod;
    @FXML
    private Button przydziel;
    
    @FXML
    private void infoAddSamochod(ActionEvent event)  {
        showMessageDialog(null, "Samochód dodany!"); 
    }
    
    @FXML
    private void infoAddZadanie(ActionEvent event)  {
        showMessageDialog(null, "Zadanie zostało dodane!"); 
    }
    
    @FXML
    private void infoAddPrzydzielPracownika(ActionEvent event)  {
        showMessageDialog(null, "Już przydzieliłeś!"); 
    }
    
    @FXML
    private void dodajSamochod(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("dodajSamochod.fxml"));
        Scene add_scene = new Scene(load);
        Stage add_stage = new Stage();
        add_stage.setTitle("Dodaj Samochod");
        add_stage.setScene(add_scene);
        add_stage.setResizable(false);
        add_stage.sizeToScene();
        add_stage.initModality(Modality.APPLICATION_MODAL);
        add_stage.initOwner(samochod.getScene().getWindow());
        add_stage.showAndWait();
    }
    
    @FXML
    private void dodajZadanie(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("dodajZadanie.fxml"));
        Scene add_scene = new Scene(load);
        Stage add_stage = new Stage();
        add_stage.setTitle("Dodaj Zadanie");
        add_stage.setScene(add_scene);
        add_stage.setResizable(false);
        add_stage.sizeToScene();
        add_stage.initModality(Modality.APPLICATION_MODAL);
        add_stage.initOwner(zadanie.getScene().getWindow());
        add_stage.showAndWait();
    }
    
    @FXML
    private void przydzielZadanie(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("przydzielPracownika.fxml"));
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
    
    @FXML
    private void odswiezClick2(ActionEvent event) {
        showMessageDialog(null, "Już właśnie odświeżyłeś!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}