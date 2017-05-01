package programowanie_zespolowe;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Homik
 */
public class FXML_LogowanieController implements Initializable {

    private dbConnection dc;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new dbConnection();
    }
    @FXML
    private Label error;
    @FXML
    private TextField login;
    @FXML
    private PasswordField passwordfx;
    @FXML
    private Button Zaloguj;

    @FXML
    private void logowanie(ActionEvent event) throws Exception {
        String userName = login.getText().trim();
        String password = passwordfx.getText().trim();
        Connection conn = dc.Connect();

        String sql = "SELECT stan_user FROM users WHERE login = '"
                + userName
                + "' AND password = '"
                + password
                + "'";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString(1).equals("Dyrektor")) {
                    Stage stageCloseLogowanie = (Stage) Zaloguj.getScene().getWindow();
                    stageCloseLogowanie.close();

                    Parent root = FXMLLoader.load(getClass().getResource("PanelDyrektora/FXMLDyrektor.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.sizeToScene();
                    stage.setTitle("Panel Dyrektora");
                    stage.show();
                } else {
                    if (rs.getString(1).equals("Pracownik")) {
                        Stage stageCloseLogowanie = (Stage) Zaloguj.getScene().getWindow();
                        stageCloseLogowanie.close();

                        Parent root = FXMLLoader.load(getClass().getResource("PanelPracownika/FXMLPracownik.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.sizeToScene();
                        stage.setTitle("Panel Pracownika");
                        stage.show();
                    } else {
                        if (rs.getString(1).equals("Kierownik")) {
                            Stage stageCloseLogowanie = (Stage) Zaloguj.getScene().getWindow();
                            stageCloseLogowanie.close();

                            Parent root = FXMLLoader.load(getClass().getResource("PanelKierownika/FXMLKierownik.fxml"));
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.setResizable(false);
                            stage.sizeToScene();
                            stage.setTitle("Panel Kierownika");
                            stage.show();
                        } else {
                            error.setText("Niewłaściwa nazwa użytkownika lub hasło");
                            error.setTextFill(Color.web("#ff0000"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
