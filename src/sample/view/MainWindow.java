package sample.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {
    private BorderPane root;
    private Button showVocabulary;
    private Button exercise;
    private Button setting;
    private Button exit;

    public Button getShowVocabulary() {
        return showVocabulary;
    }

    private void initializeButton() {
        showVocabulary = new Button("Words");
        exercise = new Button("Exercise");
        setting = new Button("Setting");
        exit = new Button("Exit");
        VBox group = new VBox(showVocabulary, exercise, setting, exit);
//        root.setCenter(group);
        root.setCenter(showVocabulary);
        root.setCenter(exercise);
    }

    public MainWindow(Stage primaryStage) {
        root = new BorderPane();
        initializeButton();
        primaryStage.setTitle("Vocabulary");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
