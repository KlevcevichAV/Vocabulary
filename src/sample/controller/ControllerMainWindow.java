package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.base.DataBase;
import sample.base.Vocabulary;
import sample.view.MainWindow;
import sample.view.WordTable;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerMainWindow {
    @FXML
    private Button showWords;

    @FXML
    private Button exerciceButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize(){
        showWords.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Label secondLabel = new Label("I'm a Label on new Window");

//                StackPane secondaryLayout = new StackPane();
//                secondaryLayout.getChildren().add(secondLabel);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/home/sanchir/IdeaProjects/Vocabulary/src/sample/words.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(root != null){
                    System.out.println(12);
                    Scene secondScene = new Scene(root, 230, 100);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Second Stage");
                    newWindow.setScene(secondScene);
                    newWindow.show();
                }
            }
        });
        exitButton.setOnAction(e->{
            System.exit(0);
        });
    }

}
