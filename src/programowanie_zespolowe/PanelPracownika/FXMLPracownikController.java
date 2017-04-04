package programowanie_zespolowe.PanelPracownika;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import programowanie_zespolowe.dbConnection;
import static javax.swing.JOptionPane.showMessageDialog;

public class FXMLPracownikController implements Initializable {
    
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
    private void odswiezClick1(ActionEvent event) {
        showMessageDialog(null, "Już właśnie odświeżyłeś!");
//        try (Connection  connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/warsztat", "root", "root")) {
//            try (Statement stmt = connection.createStatement()) {
//                stmt.executeUpdate("CREATE TABLE a (id int not null primary key, value varchar(20))");
//            }
//        }
    }
    
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
