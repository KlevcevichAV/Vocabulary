package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerMainWindow {
    @FXML
    private Button showWords;

    @FXML
    private Button exerciseButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize(){
        showWords.setOnAction(event->{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("wordsWindow.fxml"));
                /*
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */

                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Words");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
                System.out.println(13);
            }
        });
        exitButton.setOnAction(e->{
            System.exit(0);
        });
    }

}
