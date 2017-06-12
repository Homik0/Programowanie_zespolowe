package programowanie_zespolowe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static String readFile(String filePath) throws IOException {
        String baza = "";
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String textLine = bufferedReader.readLine();
        do {
            baza = baza + textLine;

            textLine = bufferedReader.readLine();
        } while (textLine != null);

        bufferedReader.close();
        return baza;
    }

    /**
     *
     * @param args W metodzie main, najpierw tworzy bazę danych Warsztat,
     * nastepnie tworzy wszystkie tablice w bazie Warsztat, a na koniec
     * uruchamia okno launch(args)
     *
     */
    public static void main(String[] args) throws IOException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection("jdbc:mariadb://localhost:3306/", "root", "root");
            Statement stDB = conn1.createStatement();

            stDB.executeUpdate("CREATE DATABASE IF NOT EXISTS warsztat");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String users = "createUsers.txt";
        String pracownik = "createPracownik.txt";
        String zlecenia = "createZlecenia.txt";
        String zadania = "createZadania.txt";

        String url = "jdbc:mariadb://localhost:3306/warsztat";
        String user = "root";
        String password = "root";

        DataBase warsztat = new DataBase(url,user,password);
        warsztat.createTable(users);
        warsztat.createTable(pracownik);
        warsztat.createTable(zlecenia);
        warsztat.createTable(zadania);
        warsztat.createAdmin();

        launch(args);
    }
}
