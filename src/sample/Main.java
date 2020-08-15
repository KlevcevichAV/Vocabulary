package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.base.DataBase;


// переписать функции, чтобы добавление было как в конструкторе.
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
//        primaryStage.setTitle("Vocabulary");
//        primaryStage.setScene(new Scene(root, 329, 489));
//        primaryStage.show();
        System.out.println(DataBase.userNameToDatabaseNameTranslator("all_worlds"));
        System.out.println(DataBase.userNameToDatabaseNameTranslator("All worlds"));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
