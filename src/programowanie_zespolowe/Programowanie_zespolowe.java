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
        String baza="";
  FileReader fileReader = new FileReader(filePath);
  BufferedReader bufferedReader = new BufferedReader(fileReader);
  
  String textLine = bufferedReader.readLine();
  do {
    baza=baza+textLine;
  
    textLine = bufferedReader.readLine();
  } while(textLine != null);

  bufferedReader.close();
  return baza;
}

    /**
     *
     * @param args
     * W metodzie main, najpierw tworzy bazę danych Warsztat,
     * nastepnie tworzy wszystkie tablice w bazie Warsztat,
     * a na koniec uruchamia okno launch(args)
     * 
     */
    public static void main(String[] args) throws IOException {
       String createUsers="";
       String createPracownik="";
       String createZlecenia="";
       String createZadania="";
       try{
       createUsers=readFile("createUsers.txt");
       createPracownik=readFile("createPracownik.txt");
       createZlecenia=readFile("createZlecenia.txt");
       createZadania=readFile("createZadania.txt");
       
           
       }
       catch(IOException e)
       {
           System.out.println("Nie znaleziono pliku");
       }
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
                st.executeUpdate(createUsers);
                
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
                st.executeUpdate(createZlecenia);
                //Create table Pracownik
                st.executeUpdate(createPracownik);
                //Create table Listazadan
                st.executeUpdate(createZadania);

            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Błąd z połączeniem z bazą!");
        }

        launch(args);
    }
}
