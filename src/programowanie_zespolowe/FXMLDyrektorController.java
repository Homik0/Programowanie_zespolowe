/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanie_zespolowe;
import javafx.event.ActionEvent;
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
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Homik
 */
public class FXMLDyrektorController implements Initializable {
    
    
   private Label label;
    
   @FXML
    private Button edycja;
   @FXML
    private Button wprowadzZmiany;
   @FXML
    private Button usunPracownika;
   
    @FXML
    private void dodajPracownika(ActionEvent event)  {
        showMessageDialog(null, "Pracownik Dodany!");
        
    }
    @FXML
    private void zmiana(ActionEvent event)  {
        showMessageDialog(null, "Edyja zatwierdzona!");
        
    }
   
         @FXML
    private void  usunPracownika(ActionEvent event)  {
        showMessageDialog(null, "Pracownik Zwolniony!");
        
    }
     @FXML
    private void onkoEdycji(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("edycjaPracownika.fxml"));
        Scene edit_scene= new Scene(loader);
        Stage edit_stage =new Stage();
        edit_stage.setScene(edit_scene);
        edit_stage.initModality(Modality.APPLICATION_MODAL);
        edit_stage.initOwner(edycja.getScene().getWindow());
        edit_stage.showAndWait();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}