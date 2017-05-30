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
import static programowanie_zespolowe.Programowanie_zespolowe.readFile;

/**
 *
 * @author atoja, Homik
 */
public class DataBase {

    private final dbConnection dc;

    public DataBase(String urlDB, String userDB, String passwordDB) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection(urlDB, userDB, passwordDB);
            Statement stDB = conn1.createStatement();

            stDB.executeUpdate("CREATE DATABASE IF NOT EXISTS warsztat");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        dc = new dbConnection();
        createAdmin();
    }

    public void createTable(String fileName) {
        String createTable = "";

        try {
            createTable = readFile(fileName);
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku");
        }

        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                //Create table
                st.executeUpdate(createTable);
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Błąd z połączeniem z bazą!");
        }
    }

    private void createAdmin() {
        try {
            Connection conn = dc.Connect();
            Statement st = conn.createStatement();
            try {
                //Create konto Administratora (jako Dyrektor)
                String sql = "SELECT login FROM users WHERE login='admin' and password='admin'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next() == FALSE) {
                    st.executeUpdate("INSERT INTO users (imie, nazwisko, login, password, stan_user) VALUES"
                            + "('admin', 'admin', 'admin', 'admin', 'Dyrektor')");
                } else {
                }
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        } catch (SQLException e) {
            System.out.println("Uwaga! Błąd z połączeniem z bazą! ");
        }
    }

    private String readFile(String filePath) throws IOException {
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
}
