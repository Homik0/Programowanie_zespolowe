package programowanie_zespolowe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gomex
 */
public class dbConnection {
    public Connection Connect() {
        try {
            String url = "jdbc:mariadb://localhost:3306/warsztat";
            String user = "root";
            String password = "root123";

            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}