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
        Stage pracownikStage = new Stage();
        Stage kierownikStage = new Stage();
        Stage dyrektorStage = new Stage();
        Stage logowanieStage = new Stage();
        Parent root1 = FXMLLoader.load(getClass().getResource("FXMLPracownik.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDyrektor.fxml"));
        Parent root3 = FXMLLoader.load(getClass().getResource("FXMLKierownik.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Logowanie.fxml"));
        Scene scene = new Scene(root);
        Scene scene1 = new Scene(root1);
        Scene scene2 = new Scene(root2);
        Scene scene3 = new Scene(root3);

        pracownikStage.setTitle("Panel Pracownika");
        pracownikStage.setScene(scene1);
        pracownikStage.setResizable(false);
        pracownikStage.sizeToScene();
        pracownikStage.show();
        
        kierownikStage.setTitle("Panel Kierownika");
        kierownikStage.setScene(scene3);
        kierownikStage.setResizable(false);
        kierownikStage.sizeToScene();
        kierownikStage.show();

        dyrektorStage.setTitle("Panel Dyrektora");
        dyrektorStage.setScene(scene2);
        dyrektorStage.setResizable(false);
        dyrektorStage.sizeToScene();
        dyrektorStage.show();

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
