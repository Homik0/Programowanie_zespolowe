package programowanie_zespolowe.PanelDyrektora;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import programowanie_zespolowe.PanelDyrektora.ListaPracownikow;
import programowanie_zespolowe.dbConnection;

public class FXMLDyrektorController implements Initializable {
    private dbConnection dc;
    private ObservableList<ListaPracownikow> data;
    @FXML
    private Button edycja;
    @FXML
    private Button wprowadzZmiany;
    @FXML
    private Button usunPracownika;
    @FXML
    private Button odswiezanie;
    @FXML
    private TableView<ListaPracownikow> tablePracownik;
    @FXML
    private TableColumn<ListaPracownikow, String> columnImie;
    @FXML
    private TableColumn<ListaPracownikow, String> columnNazwisko;
    @FXML
    private TableColumn<ListaPracownikow, String> columnStaz;
    @FXML
    private TableColumn<ListaPracownikow, String> columnNumer;
    @FXML
    private TableColumn<ListaPracownikow, String> columnWynagrodzenie;
     @FXML
    private TableColumn<ListaPracownikow, String> coulumSpecjalizacja;
     @FXML
    private TableColumn<ListaPracownikow, String> coulumStanowisko;
   
    
   
    @FXML
    private void dodajPracownika(ActionEvent event)  {
        showMessageDialog(null, "Pracownik dodany!");
        
    }
    @FXML
    private void odswiezClick(ActionEvent event) {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select users.imie,users.nazwisko,pracownik.staz_pracy,pracownik.nr_tel,pracownik.wynagrodzenie,pracownik.specjalizacja,users.stan_user from users,pracownik where users.id_user=pracownik.id_user");
            while (rs.next()) {
                data.add(new ListaPracownikow(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        columnImie.setCellValueFactory(new PropertyValueFactory<>("Imie"));
        columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
        columnStaz.setCellValueFactory(new PropertyValueFactory<>("Staz"));
        columnNumer.setCellValueFactory(new PropertyValueFactory<>("Numer"));
        columnWynagrodzenie.setCellValueFactory(new PropertyValueFactory<>("Wynagrodzenie"));
        coulumSpecjalizacja.setCellValueFactory(new PropertyValueFactory<>("Specjalizacja"));
        coulumStanowisko.setCellValueFactory(new PropertyValueFactory<>("Stanowisko"));
        
       
        tablePracownik.setItems(data);
        
        showMessageDialog(null, "Już właśnie odświeżyłeś!");
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
        dc = new dbConnection();
    }    
}