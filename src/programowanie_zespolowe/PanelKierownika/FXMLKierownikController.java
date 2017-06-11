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
import javafx.scene.control.CheckBox;
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
    private Stany stan = new Stany();
    private ObservableList<ListaSamochodow> listaSamochodow;
    private ObservableList<ListaPracownikow> listaPracownikow;
    private ObservableList<ListaZadan> listaZadan;
    private ObservableList<Lista> lista;
    @FXML
    private TableView<ListaSamochodow> tableCar;
    @FXML
    private TableView<ListaPracownikow> tablePracownik;
    @FXML
    private TableView<Lista> tableZadania;
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
    private TableColumn<Lista, String> columnNameCarZ;
    @FXML
    private TableColumn<Lista, String> columnToDoZ;
    @FXML
    private TableColumn<Lista, String> columnSpec;
    @FXML
    private TableColumn<Lista, String> columnDateZ;
    @FXML
    private TableColumn<Lista, String> columnStatusZ;
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
    private Button zlecenie;
    @FXML
    private Button btnWyloguj;
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
    private CheckBox pokaz;
    @FXML
    private void pokaz(ActionEvent event) throws Exception {
        dodajZadanieA.managedProperty().bind(pokaz.selectedProperty());
        dodajZadanieA.visibleProperty().bind(pokaz.selectedProperty());
        dodajZlecenieA.managedProperty().bind(pokaz.selectedProperty().not());
        dodajZlecenieA.visibleProperty().bind(pokaz.selectedProperty().not());
    }
    @FXML
    private void infoAddZlecenie(ActionEvent event) {
        String nazwasamochodu = NazwasamochoduField.getText();
        String wlasciciel = WlascicielField.getText();
        String nrtel = NrtelField.getText();
        String dozrobienia = DozrobieniaField.getText();
        
        String ask = "insert into zlecenia (name_car, owner, nr_tel, to_do, stan_car) values ('" + nazwasamochodu + "','" + wlasciciel + "','" + nrtel + "','" + dozrobienia + "','Oczekujący')";

        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                st.executeUpdate(ask);

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
        stan.setStan2(columnNamecar.getCellData(selectedIndex));
        
        String ask = "select id_zlecenia from zlecenia where name_car='" + stan.getStan2() + "'";
        
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                ResultSet rs = conn.createStatement().executeQuery(ask);
                if (rs.next()) {
                    stan.setStan1(rs.getString(1));
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
                st.executeUpdate("DELETE from listazadan where id_zlecenia=" + stan.getStan1());
                st.executeUpdate("DELETE from zlecenia where id_zlecenia=" + stan.getStan1());

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
        stan.setStan2((columnNamecar.getCellData(selectedIndex)));
        
        String ask = "select id_zlecenia from zlecenia where name_car='" + stan.getStan2() + "'";
        
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                ResultSet rs = conn.createStatement().executeQuery(ask);
                if (rs.next()) {
                    stan.setStan1(rs.getString(1));
                }
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
            String sql ="select zlecenia.name_car, zlecenia.owner,listazadan.to_do,listazadan.specjalizacja,listazadan.stan_zadania from listazadan,zlecenia where listazadan.id_zlecenia=zlecenia.id_zlecenia and zlecenia.id_zlecenia='"+stan.getStan1()+"'";
            PreparedStatement pst = dc.Connect().prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            lista = FXCollections.observableArrayList();
            while (rs2.next()) {
               lista.add(new Lista(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5)));         
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
        tableLista.setItems(lista);
    
        idZlecField.setText(stan.getStan1());    
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
        
        String ask1 = "insert into listazadan  (id_zlecenia, to_do, specjalizacja, stan_zadania) values ('" + id_zlec + "','" + todo + "','" + specjalizacja + "','Oczekujace')";
        String ask2 = "insert into listazadan (id_pracownik, id_zlecenia, to_do, specjalizacja, stan_zadania) values ('" + id_prac + "','" + id_zlec + "','" + todo + "','" + specjalizacja + "','W naprawie')";
        String ask3 = "update pracownik SET status='Przydzielony'where id_pracownik='"+id_prac+"' ";

        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                if ("".equals(id_prac))
                st.executeUpdate(ask1);
                else
                st.executeUpdate(ask2);
                st.executeUpdate(ask3);
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
    private void przydzielZadanie(MouseEvent event) throws IOException {
        int selectedIndex = tableZadania.getSelectionModel().getSelectedIndex();
        int selectedIndex2 = tableZadania.getSelectionModel().getSelectedIndex();
        String toDO = (columnToDoZ.getCellData(selectedIndex));
        String nameCar = (columnNameCarZ.getCellData(selectedIndex2));
        System.out.println(toDO);
        
        String ask = "select id_zadania from listazadan ,zlecenia where listazadan.id_zlecenia=zlecenia.id_zlecenia and listazadan.to_do='" + toDO + "' and zlecenia.name_car='"+nameCar+"'";
        
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                ResultSet rs = conn.createStatement().executeQuery(ask);
                if (rs.next()) {
                    stan.setStan1(rs.getString(1));
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
        
            String ask1 = "UPDATE listazadan SET listazadan.stan_zadania = 'W naprawie',	listazadan.id_pracownik = (select id_pracownik from pracownik, users where  users.id_user=pracownik.id_user and CONCAT(users.imie,' ', users.nazwisko) ='"+ idPracField.getValue()+"')WHERE id_zadania='" + stan.getStan1() + "'";
            String ask2 = "UPDATE pracownik, users SET pracownik.status = 'Przydzielony' WHERE users.id_user=pracownik.id_user and CONCAT(users.imie,' ', users.nazwisko) ='"+idPracField.getValue()+"'";
            
            
            try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                st.executeUpdate(ask1);
                st.executeUpdate(ask2);

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
        String ask = "select * from zlecenia";
        try {
            Connection conn = dc.Connect();
            listaSamochodow = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(ask);
            while (rs.next()) {
                listaSamochodow.add(new ListaSamochodow(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
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
        tableCar.setItems(listaSamochodow);
    }

    @FXML
    private void odswiezClick(ActionEvent event) {
        //wyswietlanie listy zlecen
        
        String ask = "select * from zlecenia";
        
        try {
            Connection conn = dc.Connect();
            listaSamochodow = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(ask);
            while (rs.next()) {
                listaSamochodow.add(new ListaSamochodow(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
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
        tableCar.setItems(listaSamochodow);
        //wyswietlanie listy pracownikow
        
        String ask2 = "select users.imie,users.nazwisko,pracownik.specjalizacja,pracownik.status,pracownik.nr_tel from users,pracownik where users.id_user=pracownik.id_user and users.stan_user!='Dyrektor'";
        try {
            Connection conn = dc.Connect();
            listaPracownikow = FXCollections.observableArrayList();
            ResultSet rs1 = conn.createStatement().executeQuery(ask2);
            while (rs1.next()) {
                listaPracownikow.add(new ListaPracownikow(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        columnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnSpecjalizacja.setCellValueFactory(new PropertyValueFactory<>("specjalizacja"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnNumer.setCellValueFactory(new PropertyValueFactory<>("numer"));

        tablePracownik.setItems(listaPracownikow);
        //wyswietlanie listy zadan
        
        String ask3 = "select zlecenia.name_car,listazadan.to_do,listazadan.specjalizacja,listazadan.data_dodawania,listazadan.stan_zadania from zlecenia ,listazadan where listazadan.id_zlecenia=zlecenia.id_zlecenia and listazadan.stan_zadania='Oczekujace'";
        try {
            Connection conn = dc.Connect();
            lista = FXCollections.observableArrayList();
            ResultSet rs2 = conn.createStatement().executeQuery(ask3);
            while (rs2.next()) {
                lista.add(new Lista(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        columnNameCarZ.setCellValueFactory(new PropertyValueFactory<>("NameCar"));
        columnToDoZ.setCellValueFactory(new PropertyValueFactory<>("Owner"));
        columnSpec.setCellValueFactory(new PropertyValueFactory<>("ToDo"));
        columnDateZ.setCellValueFactory(new PropertyValueFactory<>("Spec"));
        columnStatusZ.setCellValueFactory(new PropertyValueFactory<>("Status"));

        tableZadania.setItems(lista);

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
