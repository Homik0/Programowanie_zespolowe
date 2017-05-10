package programowanie_zespolowe.PanelDyrektora;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import programowanie_zespolowe.Programowanie_zespolowe;
import programowanie_zespolowe.dbConnection;

public class FXMLDyrektorController implements Initializable {

    private dbConnection dc;
    private String ZmiennaLogin;
    private ObservableList<ListaPracownikow> data;
    @FXML
    private Button dodaj;
    @FXML
    private Button edycja;
    @FXML
    private Button wczytaj;
    @FXML
    private Button wprowadzZmiany;
    @FXML
    private Button usunPracownika;
    @FXML
    private Button odswiezanie;
    @FXML
    private Button btnWyloguj;
    //pola w oknie edit
    @FXML
    private TextField ImieField;
    @FXML
    private TextField NazwiskoField;
    @FXML
    private TextField StazPracyField;
    @FXML
    private TextField NrTelField;
    @FXML
    private TextField WynagrodzenieField;
    @FXML
    private TextField StanowiskoField;
    @FXML
    private TextField SpecjalizacjaField;
    @FXML
    private TextField LoginField;
    @FXML
    private PasswordField HasloField;
    @FXML
    private ComboBox choiseStanowisko;
    @FXML
    private ComboBox choiseSpecjalizacja;
    //pola textfield w panelu dyrektora
    @FXML
    private ComboBox comboStanowisko;
     @FXML
    private ComboBox comboSpecjalizacja;
    @FXML
    private TextField Imie2Field;
    @FXML
    private TextField Nazwisko2Field;
    @FXML
    private TextField StazPracy2Field;
    @FXML
    private TextField NrTel2Field;
    @FXML
    private TextField Wynagrodzenie2Field;
    @FXML
    private TextField Haslo2Field;
    @FXML
    private TextField Stanowisko2Field;
    @FXML
    private TextField Login2Field;
    @FXML
    private Tab listaPracownikow;
    //pola w tabeli
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
    private TableColumn<ListaPracownikow, String> coulumLogin;
    @FXML
    private TableColumn<ListaPracownikow, String> coulumHaslo;

    @FXML
    private void dodajPracownika(ActionEvent event) {
        showMessageDialog(null, "Pracownik dodany!");

    }

    @FXML
    private void odswiezClick(ActionEvent event) {

        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select users.imie,users.nazwisko,pracownik.staz_pracy,pracownik.nr_tel,pracownik.wynagrodzenie,pracownik.specjalizacja,pracownik.stanowisko,users.login,users.password from users,pracownik where users.id_user=pracownik.id_user");
            while (rs.next()) {
                data.add(new ListaPracownikow(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnImie.setCellValueFactory(new PropertyValueFactory<>("Imie"));
        columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
        columnStaz.setCellValueFactory(new PropertyValueFactory<>("Staz"));
        columnNumer.setCellValueFactory(new PropertyValueFactory<>("Numer"));
        columnWynagrodzenie.setCellValueFactory(new PropertyValueFactory<>("Wynagrodzenie"));
        coulumStanowisko.setCellValueFactory(new PropertyValueFactory<>("Stanowisko"));
        coulumSpecjalizacja.setCellValueFactory(new PropertyValueFactory<>("Specjalizacja"));
        coulumLogin.setCellValueFactory(new PropertyValueFactory<>("Login"));
        coulumHaslo.setCellValueFactory(new PropertyValueFactory<>("Haslo"));

        tablePracownik.setItems(data);
        

    }

    @FXML
    private void wczytajAction(ActionEvent event) {
        int selectedIndex = tablePracownik.getSelectionModel().getSelectedIndex();
        Imie2Field.setText(columnImie.getCellData(selectedIndex));
        Nazwisko2Field.setText(columnNazwisko.getCellData(selectedIndex));
        StazPracy2Field.setText(columnStaz.getCellData(selectedIndex));
        NrTel2Field.setText(columnNumer.getCellData(selectedIndex));
        Wynagrodzenie2Field.setText(columnWynagrodzenie.getCellData(selectedIndex));      
        Login2Field.setText(coulumLogin.getCellData(selectedIndex));
        ZmiennaLogin = coulumLogin.getCellData(selectedIndex);
        Haslo2Field.setText(coulumHaslo.getCellData(selectedIndex));
        comboStanowisko.setValue(coulumStanowisko.getCellData(selectedIndex));
        comboSpecjalizacja.setValue(coulumSpecjalizacja.getCellData(selectedIndex));
    }

    @FXML
    private void zmiana(ActionEvent event) throws SQLException {

        String imie;
        String nazwisko;
        String stazPracy;
        String nrTel;
        String wynagrodzenie;
        String stanowisko;
        String specjalizacja;
        String login;
        String haslo;

        int ind = 0;
        imie = ImieField.getText();
        nazwisko = NazwiskoField.getText();
        stazPracy = StazPracyField.getText();
        nrTel = NrTelField.getText();
        wynagrodzenie = WynagrodzenieField.getText();
        stanowisko = (String) choiseStanowisko.getValue();
        specjalizacja = (String) choiseSpecjalizacja.getValue();
        login = LoginField.getText();
        haslo = HasloField.getText();
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {

                st.executeUpdate("insert into users (imie,nazwisko,login,password,stan_user) values ('" + imie + "','" + nazwisko + "','" + login + "','" + haslo + "','" + stanowisko + "')");
                ResultSet rs = conn.createStatement().executeQuery("select max(id_user) from users");
                if (rs.next()) {
                    ind = rs.getInt(1);
                }
                st.executeUpdate("insert into pracownik (id_user,staz_pracy,nr_tel,wynagrodzenie,specjalizacja,stanowisko) values ('" + ind + "','" + stazPracy + " years','" + nrTel + "','" + wynagrodzenie + " zł','" + specjalizacja + "','" + stanowisko + "')");

            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Mamy problemy z połączeniem!");
        }
        showMessageDialog(null, "Pracownik dodany!");

    }

    @FXML
    private void edytujAction(ActionEvent event) {
        String temp = "";
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                ResultSet rs = conn.createStatement().executeQuery("select id_user from users where login='" + Login2Field.getText() + "'");
                if (rs.next()) {
                    temp = rs.getString(1);
                }

                st.executeUpdate("update users set imie='" + Imie2Field.getText() + "',nazwisko='" + Nazwisko2Field.getText() + "',login='" + Login2Field.getText() + "',password='"+Haslo2Field.getText() +"' where login='" + ZmiennaLogin + "'");
                st.executeUpdate("update pracownik set staz_pracy='" + StazPracy2Field.getText() + "',nr_tel='" + NrTel2Field.getText() + "',wynagrodzenie='" + Wynagrodzenie2Field.getText() + "',stanowisko='" + (String)comboStanowisko.getValue() + "',specjalizacja='" + (String)comboSpecjalizacja.getValue() + "' where id_user='" + temp + "'");

            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Mamy problemy z połączeniem!");
        }
        showMessageDialog(null, "Edytowałeś pracownika");
        odswiezClick(event);
    }

    @FXML
    private void usunAction(ActionEvent event) {

        String temp = "";
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                ResultSet rs = conn.createStatement().executeQuery("select id_user from users where login='" + Login2Field.getText() + "'");
                if (rs.next()) {
                    temp = rs.getString(1);
                }

                st.executeUpdate("DELETE from pracownik where id_user=" + temp);
                st.executeUpdate("DELETE from users where id_user=" + temp);
//        st.executeUpdate("update pracownik set staz_pracy='"+StazPracy2Field.getText()+"',nr_tel='"+NrTel2Field.getText()+"',wynagrodzenie='"+Wynagrodzenie2Field.getText()+"' where id_user='"+temp+"'");

            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Mamy problemy z połączeniem!");
        }
        showMessageDialog(null, "Usunięto");
        odswiezClick(event);

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
