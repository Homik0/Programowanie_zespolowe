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
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

public class FXMLDyrektorController implements Initializable {
    
    @FXML
    private Button edycja;
    @FXML
    private Button wprowadzZmiany;
    @FXML
    private Button usunPracownika;
   
    @FXML
    private void dodajPracownika(ActionEvent event)  {
        showMessageDialog(null, "Pracownik dodany!");
        
    }
    
    @FXML
    private void zmiana(ActionEvent event)  {
        showMessageDialog(null, "Edycja zatwierdzona!");
        
    }
   
    @FXML
    private void usunPracownika(ActionEvent event)  {
        showMessageDialog(null, "Pracownik zwolniony!"); 
    }
    
    @FXML
    private void onkoEdycji(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("edycjaPracownika.fxml"));
        Scene edit_scene = new Scene(loader);
        Stage edit_stage = new Stage();
        edit_stage.setTitle("Edycja Pracownika");
        edit_stage.setScene(edit_scene);
        edit_stage.setResizable(false);
        edit_stage.sizeToScene();
        edit_stage.initModality(Modality.APPLICATION_MODAL);
        edit_stage.initOwner(edycja.getScene().getWindow());
        edit_stage.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}