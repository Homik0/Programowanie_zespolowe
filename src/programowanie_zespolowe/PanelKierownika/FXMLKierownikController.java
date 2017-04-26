package programowanie_zespolowe.PanelKierownika;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import programowanie_zespolowe.PanelPracownika.ListaSamochodow;
import programowanie_zespolowe.dbConnection;

public class FXMLKierownikController implements Initializable {

    private dbConnection dc;

    private ObservableList<ListaSamochodow> data;
    private ObservableList<ListaPracownikow> data1;
    private ObservableList<ListaZadan> data2;
    @FXML
    private TableView<ListaSamochodow> tableCar;
    @FXML
    private TableView<ListaPracownikow> tablePracownik;
    @FXML
    private TableView<ListaZadan> tableZadania;
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
    private TableColumn<ListaPracownikow, String> columnImie;
    @FXML
    private TableColumn<ListaPracownikow, String> columnNazwisko;
    @FXML
    private TableColumn<ListaPracownikow, String> columnStatus;
    @FXML
    private TableColumn<ListaPracownikow, String> columnNumer;
    @FXML
    private TableColumn<ListaPracownikow, String> columnSpecjalizacja;
    @FXML
    private TableColumn<ListaZadan, String> columnImieZ;
    @FXML
    private TableColumn<ListaZadan, String> columnNazwiskoZ;
    @FXML
    private TableColumn<ListaZadan, String> columnNameCarZ;
    @FXML
    private TableColumn<ListaZadan, String> columnToDoZ;
    @FXML
    private TableColumn<ListaZadan, String> columnDateZ;
    @FXML
    private TableColumn<ListaZadan, String> columnStatusZ;
    @FXML
    private Button btnOdswClick;
    @FXML
    private Button zadanie;
    @FXML
    private Button zlecenie;
    @FXML
    private Button przydziel;
    @FXML
    private TextField NazwasamochoduField;
    @FXML
    private TextField WlascicielField;
    @FXML
    private TextField NrtelField;
    @FXML
    private TextField StansamochoduField;
    @FXML
    private TextArea DozrobieniaField;

    @FXML
    private void infoAddZadanie(ActionEvent event) {
        showMessageDialog(null, "Zadanie zostało dodane!");
    }

    @FXML
    private void infoAddZlecenie(ActionEvent event) {
        String nazwasamochodu;
        String wlasciciel;
        String nrtel;
        String stansamochodu;
        String dozrobienia;
        String stanowisko;
        String specjalizacja;
        String login;
        String haslo;

        int ind = 0;

        nazwasamochodu = NazwasamochoduField.getText();
        wlasciciel = WlascicielField.getText();
        nrtel = NrtelField.getText();
        stansamochodu = StansamochoduField.getText();
        dozrobienia = DozrobieniaField.getText();

        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {

                st.executeUpdate("insert into zlecenia (name_car, owner, nr_tel, to_do, stan_car) values ('" + nazwasamochodu + "','" + wlasciciel + "','" + nrtel + "','" + stansamochodu + "','" + dozrobienia + "')");
                
                
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Błąd z połączeniem!");
        }
        showMessageDialog(null, "Zlecenie zostało dodane!");
    }

    @FXML
    private void infoAddPrzydzielPracownika(ActionEvent event) {
        showMessageDialog(null, "Już przydzieliłeś!");
    }

    @FXML
    private void dodajZlecenie(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("dodajZlecenie.fxml"));
        Scene add_scene = new Scene(load);
        Stage add_stage = new Stage();
        add_stage.setTitle("Dodaj zlecenie");
        add_stage.setScene(add_scene);
        add_stage.setResizable(false);
        add_stage.sizeToScene();
        add_stage.initModality(Modality.APPLICATION_MODAL);
        add_stage.initOwner(zlecenie.getScene().getWindow());
        add_stage.showAndWait();
    }

//    @FXML
//    private void dodajZadanie(ActionEvent event) throws IOException {
//        Parent load = FXMLLoader.load(getClass().getResource("dodajZadanie.fxml"));
//        Scene add_scene = new Scene(load);
//        Stage add_stage = new Stage();
//        add_stage.setTitle("Dodaj zadanie");
//        add_stage.setScene(add_scene);
//        add_stage.setResizable(false);
//        add_stage.sizeToScene();
//        add_stage.initModality(Modality.APPLICATION_MODAL);
//        add_stage.initOwner(zadanie.getScene().getWindow());
//        add_stage.showAndWait();
//    }

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
        //wyswietlanie listy zlecen
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
        //wyswietlanie listy pracownikow
        try {
            Connection conn = dc.Connect();
            data1 = FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery("select users.imie,users.nazwisko,pracownik.specjalizacja,pracownik.status,pracownik.nr_tel from users,pracownik where users.id_user=pracownik.id_user");
            while (rs1.next()) {
                data1.add(new ListaPracownikow(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        columnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnSpecjalizacja.setCellValueFactory(new PropertyValueFactory<>("specjalizacja"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnNumer.setCellValueFactory(new PropertyValueFactory<>("numer"));

        tablePracownik.setItems(data1);
        //wyswietlanie listy zadan
        try {
            Connection conn = dc.Connect();
            data2 = FXCollections.observableArrayList();
            ResultSet rs2 = conn.createStatement().executeQuery("select users.imie,users.nazwisko,zlecenia.name_car,zlecenia.to_do,listazadan.data_dodawania,listazadan.stan_zadania from users,pracownik,zlecenia ,listazadan where users.id_user=pracownik.id_user and listazadan.id_zlecenia=zlecenia.id_zlecenia and listazadan.id_pracownik=pracownik.id_pracownik");
            while (rs2.next()) {
                data2.add(new ListaZadan(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        columnImieZ.setCellValueFactory(new PropertyValueFactory<>("imie"));
        columnNazwiskoZ.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnNameCarZ.setCellValueFactory(new PropertyValueFactory<>("NameCar"));
        columnToDoZ.setCellValueFactory(new PropertyValueFactory<>("ToDo"));
        columnDateZ.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnStatusZ.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableZadania.setItems(data2);
        showMessageDialog(null, "Już właśnie odświeżyłeś!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new dbConnection();
    }
}
