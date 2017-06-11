package programowanie_zespolowe.PanelDyrektora;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import static programowanie_zespolowe.FXML_LogowanieController.passwordP;
import static programowanie_zespolowe.FXML_LogowanieController.userNameP;
import programowanie_zespolowe.Programowanie_zespolowe;
import programowanie_zespolowe.dbConnection;

/**
 *
 * @author atoja
 */
public class FXMLDyrektorController implements Initializable {

    private dbConnection dc;
    private String ZmiennaLogin;
    private ObservableList<ListaPracownikow> listaZadan;
    private static String FILE = "C:\\Users\\Homik\\Documents\\NetBeansProjects\\Programowanie_zespolowe\\Raporty\\Raport-" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + ".pdf";
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
    public void GenerowanieRaportów(ActionEvent event) throws Exception, DocumentException {
        String sql = "";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 28, BaseColor.BLACK);
        Font smallFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 11, BaseColor.BLACK);
        Paragraph docTitle = new Paragraph("RAPORT", titleFont);
        docTitle.add(new Paragraph(""));
        docTitle.add(new Paragraph("Raport wygenerowany dnia: " + new Date(), smallFont));
        docTitle.add(new Paragraph(""));
        try {
            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("select concat(imie,' ',nazwisko) from users where login='" + userNameP + "' and password='" + passwordP + "'");
            if (rs.next()) {
                sql = rs.toString();
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        docTitle.add(new Paragraph("Raport wygenerowany przez:" +" "+ sql, smallFont));
        docTitle.add(new Paragraph(""));
        document.add(docTitle);
        Paragraph statusTitle = new Paragraph("Status pracowników:", titleFont);
        statusTitle.add(new Paragraph(""));
        statusTitle.add(new Paragraph(""));
        document.add(statusTitle);
        PdfPTable table1 = new PdfPTable(8);
        table1.setWidthPercentage(500 / 5.23f);
        table1.addCell(getNormalCell("Id_pracownika",  8));
        table1.addCell(getNormalCell("Imie i nazwisko",  8));
        table1.addCell(getNormalCell("Staz pracy", 8));
        table1.addCell(getNormalCell("nr. tel.", 8));
        table1.addCell(getNormalCell("Wynagrodzenie", 8));
        table1.addCell(getNormalCell("Stanowisko", 8));
        table1.addCell(getNormalCell("Specjalizacja", 8));
        table1.addCell(getNormalCell("Status", 8));
        document.add(table1);

        try {
            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("select id_pracownik,concat(users.imie,' ',users.nazwisko),staz_pracy,nr_tel,wynagrodzenie,stanowisko,specjalizacja, status from pracownik,users where users.id_user=pracownik.id_user");
            while (rs.next()) {
                PdfPTable table = new PdfPTable(8);
                table.setWidthPercentage(500 / 5.23f);
                for (int aw = 0; aw < 8; aw++) {
                    table.addCell(getNormalCell(rs.getString(aw + 1),8));
                }
                document.add(table);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        Paragraph dodaneTitle = new Paragraph("Dodane zadania z ostatnich 24H:", titleFont);
        dodaneTitle.add(new Paragraph(""));
        dodaneTitle.add(new Paragraph(""));
        document.add(dodaneTitle);
        PdfPTable table2 = new PdfPTable(6);
        table2.setWidthPercentage(375 / 5.23f);
        table2.addCell(getNormalCell("Id_zadania",  8));
        table2.addCell(getNormalCell("Imie i nazwisko",  8));
        table2.addCell(getNormalCell("Samochod", 8));
        table2.addCell(getNormalCell("Do zrobienia", 8));
        table2.addCell(getNormalCell("Specjalizacja", 8));
        table2.addCell(getNormalCell("Data dodania", 8));
        document.add(table2);

        try {
            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("select listazadan.id_zadania, concat(users.imie,' ',users.nazwisko),zlecenia.name_car,listazadan.to_do,listazadan.specjalizacja,listazadan.data_dodawania from listazadan, zlecenia, users,pracownik where listazadan.id_zlecenia=zlecenia.id_zlecenia and listazadan.id_pracownik=pracownik.id_pracownik and pracownik.id_user=users.id_user  and DATE_SUB(CURDATE(),INTERVAL 1 DAY)<=listazadan.data_dodawania");
            while (rs.next()) {
                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(375 / 5.23f);
                for (int aw = 0; aw < 6; aw++) {
                    table.addCell(getNormalCell(rs.getString(aw + 1),8));
                }
                document.add(table);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        Paragraph dodaneZlTitle = new Paragraph("Dodane Zlecenia z ostatnich 24H:", titleFont);
        dodaneZlTitle.add(new Paragraph(""));
        dodaneZlTitle.add(new Paragraph(""));
        document.add(dodaneZlTitle);
        PdfPTable table4 = new PdfPTable(7);
        table4.setWidthPercentage(437 / 5.23f);
        table4.addCell(getNormalCell("Id_zlecenia",  8));
        table4.addCell(getNormalCell("Samochod",  8));
        table4.addCell(getNormalCell("Owner", 8));
        table4.addCell(getNormalCell("Nr. tel.", 8));
        table4.addCell(getNormalCell("Do zrobienia", 8));
        table4.addCell(getNormalCell("Stan samochodu", 8));
        table4.addCell(getNormalCell("Data dodania", 8));
        document.add(table4);

        try {
            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("select * from  zlecenia where  DATE_SUB(CURDATE(),INTERVAL 1 DAY)<=zlecenia.data_dodawania");
            while (rs.next()) {
                PdfPTable table = new PdfPTable(7);
                table.setWidthPercentage(437 / 5.23f);
                for (int aw = 0; aw < 7; aw++) {
                    table.addCell(getNormalCell(rs.getString(aw + 1),8));
                }
                document.add(table);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        Paragraph wykonanieTitle = new Paragraph("Zakonczone zadania z ostatnich 24H:", titleFont);
        wykonanieTitle.add(new Paragraph(""));
        wykonanieTitle.add(new Paragraph(""));
        document.add(wykonanieTitle);
        PdfPTable table3 = new PdfPTable(4);
        table3.setWidthPercentage(250 / 5.23f);
        table3.addCell(getNormalCell("Id_zadania",  8));
        table3.addCell(getNormalCell("Imie i nazwisko",  8));
        table3.addCell(getNormalCell("Samochod", 8));
        table3.addCell(getNormalCell("Do zrobienia", 8));
        document.add(table3);

        try {
            Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery("select listazadan.id_zadania, concat(users.imie,' ',users.nazwisko),zlecenia.name_car,listazadan.to_do from listazadan, users,pracownik,zlecenia where listazadan.id_zlecenia=zlecenia.id_zlecenia and listazadan.id_pracownik=pracownik.id_pracownik and pracownik.id_user=users.id_user and listazadan.stan_zadania='Zakonczone'");
            while (rs.next()) {
                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(250 / 5.23f);
                for (int aw = 0; aw < 4; aw++) {
                    table.addCell(getNormalCell(rs.getString(aw + 1),8));
                }
                document.add(table);
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        document.close();

    }

    public static PdfPCell getNormalCell(String string, float size)
            throws DocumentException, IOException {
        if (string != null && "".equals(string)) {
            return new PdfPCell();
        } 

        Font f= FontFactory.getFont("Arial");
        f.setSize(size);
        PdfPCell cell = new PdfPCell(new Phrase(string, f));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    @FXML
    private void odswiezClick(ActionEvent event) {

        try {
            Connection conn = dc.Connect();
            listaZadan = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select users.imie,users.nazwisko,pracownik.staz_pracy,pracownik.nr_tel,pracownik.wynagrodzenie,pracownik.specjalizacja,pracownik.stanowisko,users.login,users.password from users,pracownik where users.id_user=pracownik.id_user");
            while (rs.next()) {
                listaZadan.add(new ListaPracownikow(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
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

        tablePracownik.setItems(listaZadan);

    }

    @FXML
    private void wczytajBaze() {

        try {
            Connection conn = dc.Connect();
            listaZadan = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select users.imie,users.nazwisko,pracownik.staz_pracy,pracownik.nr_tel,pracownik.wynagrodzenie,pracownik.specjalizacja,pracownik.stanowisko,users.login,users.password from users,pracownik where users.id_user=pracownik.id_user");
            while (rs.next()) {
                listaZadan.add(new ListaPracownikow(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
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
        tablePracownik.setItems(null);
        tablePracownik.setItems(listaZadan);

    }

    @FXML
    private void wczytajAction(MouseEvent event) {
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

                st.executeUpdate("update users set imie='" + Imie2Field.getText() + "',stan_user='" + (String) comboStanowisko.getValue() + "',nazwisko='" + Nazwisko2Field.getText() + "',login='" + Login2Field.getText() + "',password='" + Haslo2Field.getText() + "' where login='" + ZmiennaLogin + "'");
                st.executeUpdate("update pracownik set staz_pracy='" + StazPracy2Field.getText() + "',nr_tel='" + NrTel2Field.getText() + "',wynagrodzenie='" + Wynagrodzenie2Field.getText() + "',stanowisko='" + (String) comboStanowisko.getValue() + "',specjalizacja='" + (String) comboSpecjalizacja.getValue() + "' where id_user='" + temp + "'");

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
        edit_stage.setTitle("Dodawanie Pracownika");
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

        try {
            wczytajBaze();
        } catch (Exception e) {

        }

    }
}
