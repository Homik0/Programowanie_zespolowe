package programowanie_zespolowe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Homik
 */
public class Programowanie_zespolowe extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Stage pracownikStage = new Stage();
        Stage dyrektorStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("FXMLPracownik.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDyrektor.fxml"));
        Scene scene = new Scene(root);
        Scene scene1 = new Scene(root1);
        Scene scene2 = new Scene(root2);
        
        stage.setScene(scene);
        stage.show();
        
        pracownikStage.setTitle("Panel Pracownika");
        pracownikStage.setScene(scene1);
        pracownikStage.setResizable(false);
        pracownikStage.sizeToScene();
        pracownikStage.show();
        
        dyrektorStage.setTitle("Panel Dyrektora");
        dyrektorStage.setScene(scene2);
        pracownikStage.setResizable(false);
        pracownikStage.sizeToScene();
        dyrektorStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}