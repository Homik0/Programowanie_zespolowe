package programowanie_zespolowe.PanelKierownika;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import programowanie_zespolowe.Programowanie_zespolowe;
import programowanie_zespolowe.dbConnection;

/**
 *
 * @author atoja
 */
public class FXMLKierownikController implements Initializable {

    private dbConnection dc;
    private String temp;
    private String temp2;
    private ObservableList<ListaSamochodow> data;
    private ObservableList<ListaPracownikow> data1;
    private ObservableList<ListaZadan> data2;
    private ObservableList<Lista> data3;
    @FXML
    private TableView<ListaSamochodow> tableCar;
    @FXML
    private TableView<ListaPracownikow> tablePracownik;
    @FXML
    private TableView<ListaZadan> tableZadania;
    @FXML
    private TableView<Lista> tableLista;
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
    private TableColumn<ListaZadan, String> columnSpec;
    @FXML
    private TableColumn<ListaZadan, String> columnDateZ;
    @FXML
    private TableColumn<ListaZadan, String> columnStatusZ;
    @FXML
    private TableColumn<Lista, String> nameCarZ;
    @FXML
    private TableColumn<Lista, String> ownerZ;
    @FXML
    private TableColumn<Lista, String> toDoZ;
    @FXML
    private TableColumn<Lista, String> spec;
    @FXML
    private TableColumn<Lista, String> statusZ;
    @FXML
    private Button zadanie;
    @FXML
    private Button zlecenie;
    @FXML
    private Button btnWyloguj;
    @FXML
    private Button infoAddZlecenie;
    @FXML
    private TextField NazwasamochoduField;
    @FXML
    private TextField WlascicielField;
    @FXML
    private TextField NrtelField;
    @FXML
    private TextArea DozrobieniaField;
    @FXML
    private ComboBox<String> idPracField;
    @FXML
    private TextField idZlecField;
    @FXML
    private ComboBox<String> specialField;
    @FXML
    private ComboBox<String> pracownikCombo;
    @FXML
    private TextField todoField;
    @FXML
    private AnchorPane dodajZadanieA;
    @FXML
    private AnchorPane dodajZlecenieA;

    @FXML
    private void infoAddZlecenie(ActionEvent event) {
        String nazwasamochodu;
        String wlasciciel;
        String nrtel;
        String stansamochodu;
        String dozrobienia;

        nazwasamochodu = NazwasamochoduField.getText();
        wlasciciel = WlascicielField.getText();
        nrtel = NrtelField.getText();
        dozrobienia = DozrobieniaField.getText();

        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                st.executeUpdate("insert into zlecenia (name_car, owner, nr_tel, to_do, stan_car) values ('" + nazwasamochodu + "','" + wlasciciel + "','" + nrtel + "','" + dozrobienia + "','Oczekujący')");

            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Błąd z połączeniem!");
        }
                
        dodajZlecenieA.setVisible(false);
        dodajZadanieA.setVisible(true);
        odswiez();

    }

    @FXML
    private void selectAction(MouseEvent event) {
        int selectedIndex = tableCar.getSelectionModel().getSelectedIndex();
        temp2 = (columnNamecar.getCellData(selectedIndex));
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                ResultSet rs = conn.createStatement().executeQuery("select id_zlecenia from zlecenia where name_car='" + temp2 + "'");
                if (rs.next()) {
                    temp = rs.getString(1);
                }
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Mamy problemy z polaczeniem!");
        }
    }

    @FXML
    private void usunAction(ActionEvent event) {
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                st.executeUpdate("DELETE from listazadan where id_zlecenia=" + temp);
                st.executeUpdate("DELETE from zlecenia where id_zlecenia=" + temp);

            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Mamy problemy z polaczeniem!");
        }
        odswiezClick(event);
    }

    @FXML
    private void wyswietlZadanie(MouseEvent event) {
        int selectedIndex = tableCar.getSelectionModel().getSelectedIndex();
        temp2 = (columnNamecar.getCellData(selectedIndex));
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                ResultSet rs = conn.createStatement().executeQuery("select id_zlecenia from zlecenia where name_car='" + temp2 + "'");
                if (rs.next()) {
                    temp = rs.getString(1);
                }
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
            String sql = "select zlecenia.name_car, zlecenia.owner,listazadan.to_do,pracownik.specjalizacja,listazadan.stan_zadania from pracownik,listazadan,zlecenia where pracownik.id_pracownik=listazadan.id_pracownik and listazadan.id_zlecenia=zlecenia.id_zlecenia and zlecenia.id_zlecenia='"+temp+"'";
            PreparedStatement pst = dc.Connect().prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            while (rs2.next()) {
                data3.add(new Lista(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5)));         
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        nameCarZ.setCellValueFactory(new PropertyValueFactory<>("NameCar"));
        ownerZ.setCellValueFactory(new PropertyValueFactory<>("Owner"));
        toDoZ.setCellValueFactory(new PropertyValueFactory<>("ToDo"));
        spec.setCellValueFactory(new PropertyValueFactory<>("Spec"));
        statusZ.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tableLista.setItems(null);
        tableLista.setItems(data3);
    
        idZlecField.setText(temp);    
    }

    @FXML
    private void dodajZlecenie(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("dodajZlecen.fxml"));
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

    @FXML
    private void infoAddZadanie(ActionEvent event) {
        String id_prac = "";
        String id_zlec;
        String todo;
        String specjalizacja;

        try {
            String sql = "select id_pracownik, CONCAT(users.imie,' ', users.nazwisko) AS imie_nazwisko from users,pracownik where pracownik.id_user=users.id_user and pracownik.status='Jeszcze nie przydzielony'and CONCAT(users.imie,' ', users.nazwisko)='" + idPracField.getValue() + "'";
            PreparedStatement pst = dc.Connect().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("id_pracownik");
                id_prac = name;

            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        id_zlec = idZlecField.getText();
        todo = todoField.getText();
        specjalizacja = specialField.getValue();

        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                if ("".equals(id_prac))
                st.executeUpdate("insert into listazadan  (id_zlecenia, to_do, specjalizacja, stan_zadania) values ('" + id_zlec + "','" + todo + "','" + specjalizacja + "','Oczekujace')");
                else
                st.executeUpdate("insert into listazadan (id_pracownik, id_zlecenia, to_do, specjalizacja, stan_zadania) values ('" + id_prac + "','" + id_zlec + "','" + todo + "','" + specjalizacja + "','W naprawie')");   
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Błąd z połączeniem!");
        }
        showMessageDialog(null, "Zadanie zostało dodane!");
    }

    @FXML
    private void dodajZadanie(ActionEvent event) throws IOException {

    }

    @FXML
    private void comboBox_wypelnienie() {
        try {

            String sql = "select id_pracownik, CONCAT(users.imie,' ', users.nazwisko) AS imie_nazwisko from users,pracownik where pracownik.id_user=users.id_user and status='Jeszcze nie przydzielony'";
            PreparedStatement pst = dc.Connect().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("imie_nazwisko");
                idPracField.getItems().add(name);
            }

        } catch (Exception e) {
            System.out.println("Error on comboBox_wypelnienie");
        }
    }

    @FXML
    private void przydzielZadanie(ActionEvent event) throws IOException {
        int selectedIndex = tableZadania.getSelectionModel().getSelectedIndex();
        int selectedIndex2 = tableZadania.getSelectionModel().getSelectedIndex();
        String toDO = (columnToDoZ.getCellData(selectedIndex));
        String nameCar = (columnNameCarZ.getCellData(selectedIndex2));
        System.out.println(toDO);
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                ResultSet rs = conn.createStatement().executeQuery("select id_zadania from listazadan ,zlecenia where listazadan.id_zlecenia=zlecenia.id_zlecenia, listazadan.to_do='" + toDO + "' and zlecenia.name_car='"+nameCar+"'");
                if (rs.next()) {
                    temp = rs.getString(1);
                }
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Mamy problemy z polaczeniem!");
        }
    }

    @FXML
    private void infoAddPrzydzielPracownika(ActionEvent event) {

            try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                st.executeUpdate("UPDATE listazadan SET stan_zadania = 'W naprawie' WHERE id_zadania='" + temp + "'");
                st.executeUpdate("UPDATE pracownik SET status = 'Przydzielony' WHERE CONCAT(users.imie,' ', users.nazwisko) ='"+pracownikCombo.getValue()+"'");

            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Błąd z połączeniem!");
        }
            
    }

    @FXML
    private void odswiez() {
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
    }

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
            ResultSet rs1 = conn.createStatement().executeQuery("select users.imie,users.nazwisko,pracownik.specjalizacja,pracownik.status,pracownik.nr_tel from users,pracownik where users.id_user=pracownik.id_user and users.stan_user!='Dyrektor'");
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
            ResultSet rs2 = conn.createStatement().executeQuery("select users.imie,users.nazwisko,zlecenia.name_car,listazadan.to_do,pracownik.specjalizacja,listazadan.data_dodawania,listazadan.stan_zadania from users,pracownik,zlecenia ,listazadan where users.id_user=pracownik.id_user and listazadan.id_zlecenia=zlecenia.id_zlecenia and listazadan.id_pracownik=pracownik.id_pracownik and listazadan.stan_zadania='Oczekujacy'");
            while (rs2.next()) {
                data2.add(new ListaZadan(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6), rs2.getString(7)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        columnImieZ.setCellValueFactory(new PropertyValueFactory<>("imie"));
        columnNazwiskoZ.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnNameCarZ.setCellValueFactory(new PropertyValueFactory<>("NameCar"));
        columnToDoZ.setCellValueFactory(new PropertyValueFactory<>("ToDo"));
        columnSpec.setCellValueFactory(new PropertyValueFactory<>("Spec"));
        columnDateZ.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnStatusZ.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableZadania.setItems(data2);

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
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
        dc = new dbConnection();
        comboBox_wypelnienie();


    }
}
