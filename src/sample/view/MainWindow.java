package sample.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow {
    private BorderPane root;
    private Button showVocabulary;

    public Button getShowVocabulary() {
        return showVocabulary;
    }

    private void initializeButton(){
        showVocabulary = new Button("Show Vocabulary");
        root.setCenter(showVocabulary);
    }

    public MainWindow(Stage primaryStage){
        root = new BorderPane();
        initializeButton();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
