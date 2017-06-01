package programowanie_zespolowe.PanelPracownika;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.stage.Stage;
import programowanie_zespolowe.dbConnection;
import programowanie_zespolowe.Programowanie_zespolowe;

/**
 *
 * Jest to konroler Pracownika
 */
public class FXMLPracownikController implements Initializable {

    private dbConnection dc;
    private ObservableList<ListaSamochodow> listaSamochodow;
    @FXML
    private TableView<ListaSamochodow> tableCar;
    @FXML
    private TableColumn<ListaSamochodow, String> columnNamecar;
    @FXML
    private TableColumn<ListaSamochodow, String> columnOwner;
    @FXML
    private TableColumn<ListaSamochodow, String> columnData;
    @FXML
    private TableColumn<ListaSamochodow, String> columnTodo;
    @FXML
    private TableColumn<ListaSamochodow, String> columnStancar;
    @FXML
    private Button btnWyloguj;

    @FXML
    private void odswiezClick(ActionEvent event) {
        try {
            Connection conn = dc.Connect();
            listaSamochodow = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select zlecenia.name_car, zlecenia.owner, listazadan.to_do,listazadan.data_dodawania,listazadan.stan_zadania from listazadan, zlecenia, pracownik,users where listazadan.id_zlecenia=zlecenia.id_zlecenia and listazadan.id_pracownik=pracownik.id_pracownik and users.id_user=pracownik.id_user and users.login='" + programowanie_zespolowe.FXML_LogowanieController.userNameP + "'and users.password='" + programowanie_zespolowe.FXML_LogowanieController.passwordP + "'");
            while (rs.next()) {
                listaSamochodow.add(new ListaSamochodow(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnNamecar.setCellValueFactory(new PropertyValueFactory<>("namecar"));
        columnOwner.setCellValueFactory(new PropertyValueFactory<>("owner"));
        columnTodo.setCellValueFactory(new PropertyValueFactory<>("nrtel"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("todo"));
        columnStancar.setCellValueFactory(new PropertyValueFactory<>("stancar"));

        tableCar.setItems(null);
        tableCar.setItems(listaSamochodow);

    }

    @FXML
    private void zakonczZadania(ActionEvent event) throws IOException {
        int selectedIndex = tableCar.getSelectionModel().getSelectedIndex();
        String Data = (columnTodo.getCellData(selectedIndex));
        String idZadania = "";
        System.out.println(Data);
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                ResultSet rs = conn.createStatement().executeQuery("select id_zadania from listazadan where to_do='" + Data + "'");
                if (rs.next()) {

                    idZadania = rs.getString(1);
                    System.out.println(idZadania);
                }
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
            st.executeUpdate("update listazadan SET stan_zadania='Zakonczone' where id_zadania='" + idZadania + "'");
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void wylogujClick(ActionEvent event) throws IOException {
        Stage stageCloseWindow = (Stage) btnWyloguj.getScene().getWindow();
        stageCloseWindow.close();

        Parent root = FXMLLoader.load(Programowanie_zespolowe.class.getResource("FXML_Logowanie.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Logowanie");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new dbConnection();
    }
}
