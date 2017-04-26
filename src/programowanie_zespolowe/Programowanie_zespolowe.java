package programowanie_zespolowe;

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

    public static void main(String[] args) {
        launch(args);
    }
}
