package sample.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class WordTable {
    private static Stage window;


    public static void newWindow() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 610, 200);
        window.setScene(scene);
        window.setTitle("Vocabulary");
        window.showAndWait();
    }
}
