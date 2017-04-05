package programowanie_zespolowe.PanelKierownika;

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
import programowanie_zespolowe.PanelPracownika.ListaSamochodow;
import programowanie_zespolowe.dbConnection;

public class FXMLKierownikController implements Initializable {
    
    private dbConnection dc;
    private ObservableList<ListaSamochodow> data;
    @FXML
    private TableView<ListaSamochodow> tableCar;
    @FXML
    private TableColumn<ListaSamochodow, String> columnNamecar;
    @FXML
    private TableColumn<ListaSamochodow, String> columnOwner;
    @FXML
    private TableColumn<ListaSamochodow, String> columnNrtel;
    @FXML
    private TableColumn<ListaSamochodow, String> columnTodo;
    @FXML
    private TableColumn<ListaSamochodow, String> columnStancar;
    @FXML
    private Button btnOdswClick;
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
    
    /*@FXML
    private void dodajSamochod(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("dodajSamochod.fxml"));
        Scene add_scene = new Scene(load);
        Stage add_stage = new Stage();
        add_stage.setTitle("Dodaj Samochod");
        add_stage.setScene(add_scene);
        add_stage.setResizable(false);
        add_stage.sizeToScene();
        add_stage.initModality(Modality.APPLICATION_MODAL);
        add_stage.initOwner(zadanie.getScene().getWindow());
        add_stage.showAndWait();
    }*/
    
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
    
    /*@FXML
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
    }*/
    
    @FXML
    private void odswiezClick(ActionEvent event) {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select * from zlecenia");
            while (rs.next()) {
                data.add(new ListaSamochodow(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        columnNamecar.setCellValueFactory(new PropertyValueFactory<>("namecar"));
        columnOwner.setCellValueFactory(new PropertyValueFactory<>("owner"));
        columnNrtel.setCellValueFactory(new PropertyValueFactory<>("nrtel"));
        columnTodo.setCellValueFactory(new PropertyValueFactory<>("todo"));
        columnStancar.setCellValueFactory(new PropertyValueFactory<>("stancar"));
        
        tableCar.setItems(null);
        tableCar.setItems(data);
        
        showMessageDialog(null, "Już właśnie odświeżyłeś!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new dbConnection();
    }    
}