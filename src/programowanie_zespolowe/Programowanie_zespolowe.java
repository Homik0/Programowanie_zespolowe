package programowanie_zespolowe;

import static java.lang.Boolean.FALSE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Damian Kawka, Kamil Dudek, Marcin Patyka, Andrzej Patlewicz
 */

public class Programowanie_zespolowe extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage logowanieStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Logowanie.fxml"));
        Scene scene = new Scene(root);

        logowanieStage.setTitle("Logowanie");
        logowanieStage.setScene(scene);
        logowanieStage.setResizable(false);
        logowanieStage.sizeToScene();
        logowanieStage.show();
        
    }

    /**
     *
     * @param args
     * W metodzie main, najpierw tworzy bazę danych Warsztat,
     * nastepnie tworzy wszystkie tablice w bazie Warsztat,
     * a na koniec uruchamia okno launch(args)
     * 
     */
    public static void main(String[] args) {

        // Create database Warsztat
        try {
            String urlDB = "jdbc:mariadb://localhost:3306/";
            String userDB = "root";
            String passwordDB = "root";

            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection(urlDB, userDB, passwordDB);
            Statement stDB = conn1.createStatement();

            stDB.executeUpdate("CREATE DATABASE IF NOT EXISTS warsztat");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        dbConnection dc;
        dc = new dbConnection();

        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                //Create table Users
                st.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                        + "id_user INT(11) NOT NULL AUTO_INCREMENT,"
                        + "imie VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "nazwisko VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "login VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "password VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "stan_user VARCHAR(50) NOT NULL DEFAULT 'Pracownik' COLLATE 'utf8_polish_ci',"
                        + "data_dolaczenia DATETIME NULL DEFAULT CURRENT_TIMESTAMP,"
                        + "data_zalogowania DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                        + "PRIMARY KEY (id_user))"
                        + "COLLATE='utf8_polish_ci';");
                //Create konto Administratora (jako Dyrektor)
                String sql = "SELECT login FROM users WHERE login='admin' and password='admin'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next() == FALSE) {
                    st.executeUpdate("INSERT INTO users (imie, nazwisko, login, password, stan_user) VALUES"
                            + "('admin', 'admin', 'admin', 'admin', 'Dyrektor')");
                } else {
                }
                //Create table Zlecenia
                st.executeUpdate("CREATE TABLE IF NOT EXISTS zlecenia ("
                        + "id_zlecenia INT(64) NOT NULL AUTO_INCREMENT,"
                        + "name_car VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "owner VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "nr_tel VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "to_do VARCHAR(250) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "stan_car VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "PRIMARY KEY(id_zlecenia))"
                        + "COLLATE='utf8_polish_ci';");
                //Create table Pracownik
                st.executeUpdate("CREATE TABLE IF NOT EXISTS pracownik ("
                        + "id_pracownik INT(11) NOT NULL AUTO_INCREMENT,"
                        + "id_user INT(11) NOT NULL,"
                        + "staz_pracy VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "nr_tel VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "wynagrodzenie VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "stanowisko VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "specjalizacja VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "status VARCHAR(50) NOT NULL DEFAULT 'Jeszcze nie przydzielony' COLLATE 'utf8_polish_ci',"
                        + "PRIMARY KEY (id_pracownik),"
                        + "INDEX FK_pracownik_users (id_user),"
                        + "CONSTRAINT FK_pracownik_users FOREIGN KEY (id_user) REFERENCES users (id_user))"
                        + "COLLATE='utf8_polish_ci';");
                //Create table Listazadan
                st.executeUpdate("CREATE TABLE IF NOT EXISTS listazadan ("
                        + "id_zadania INT(11) NOT NULL AUTO_INCREMENT,"
                        + "id_pracownik INT(11)  NULL DEFAULT NULL COLLATE 'utf8_polish_ci',"
                        + "id_zlecenia INT(11) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "to_do VARCHAR(250) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "specjalizacja VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "stan_zadania VARCHAR(50) NOT NULL COLLATE 'utf8_polish_ci',"
                        + "data_dodawania DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                        + "PRIMARY KEY (id_zadania),"
                        + "INDEX id_user (id_pracownik),"
                        + "INDEX id_zlecenia (id_zlecenia),"
                        + "CONSTRAINT FKPracownik FOREIGN KEY (id_pracownik) REFERENCES pracownik (id_pracownik),"
                        + "CONSTRAINT FKZlecenia FOREIGN KEY (id_zlecenia) REFERENCES zlecenia (id_zlecenia))"
                        + "COLLATE='utf8_polish_ci';");

            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Błąd z połączeniem z bazą!");
        }

        launch(args);
    }
}
