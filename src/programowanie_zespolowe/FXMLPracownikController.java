package programowanie_zespolowe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javax.swing.JOptionPane.showMessageDialog;

public class FXMLPracownikController implements Initializable {
    
    @FXML
    private void odswiezClick(ActionEvent event) {
        showMessageDialog(null, "Już właśnie odświeżyłeś!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
