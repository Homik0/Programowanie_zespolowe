package programowanie_zespolowe;

import javafx.scene.input.MouseEvent;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Homik
 */
public class FXML_LogowanieController implements Initializable {
    public static String userNameP;
    public static String passwordP;
    private dbConnection dc;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new dbConnection();
    }
    @FXML
    private Label error;

    @FXML
    private CheckBox checkbox;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private PasswordField passwordfx;
    @FXML
    private Button Zaloguj;

    @FXML
    private void logowanie(ActionEvent event) throws Exception {
        String userName = login.getText().trim();
        String password = passwordfx.getText().trim();
        userNameP=userName;
        passwordP=password;
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
                    if (rs.getString(1).equals("Pracownik") || rs.getString(1).equals("Wolny")) {
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

                        }

                    }

                }

            }
            error.setText("Niewłaściwa nazwa użytkownika lub hasło");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void logowanieEnter(KeyEvent event) throws Exception {
        if (event.getCode().equals(KeyCode.ENTER)) {
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
                        if (rs.getString(1).equals("Pracownik") || rs.getString(1).equals("Wolny")) {
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

                            }

                        }

                    }

                }
                error.setText("Niewłaściwa nazwa użytkownika lub hasło");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void hide(MouseEvent event) {
        error.setText("");
    }

    @FXML
    private void hidePassword(ActionEvent event) throws Exception {
        password.managedProperty().bind(checkbox.selectedProperty());
        password.visibleProperty().bind(checkbox.selectedProperty());
        passwordfx.managedProperty().bind(checkbox.selectedProperty().not());
        passwordfx.visibleProperty().bind(checkbox.selectedProperty().not());
        password.textProperty().bindBidirectional(passwordfx.textProperty());
    }

}
