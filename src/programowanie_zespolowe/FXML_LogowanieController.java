/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private void logowanie(ActionEvent event) throws Exception {
        String userName = login.getText().trim();
        String password = passwordfx.getText().trim();
        Connection conn = dc.Connect();

        String sql = "SELECT stan_user FROM users WHERE login = ? AND password = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString(1).equals("Dyrektor")) {
                    Parent root = FXMLLoader.load(getClass().getResource("PanelDyrektora/FXMLDyrektora.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Panel Dyrektora");
                    stage.show();
                } else {
                    if (rs.getString(1).equals("pracownik")) {
                        Parent root = FXMLLoader.load(getClass().getResource("PanelPracownika/FXMLPracownik.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Panel Pracownika");
                        stage.show();
                    } else {
                        if (rs.getString(1).equals("Kierownik")) {
                            Parent root = FXMLLoader.load(getClass().getResource("PanelKierownika/FXMLKierownik.fxml"));
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
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
