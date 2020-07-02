package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.vocabulary.Word;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Word word = new Word("1","2","qwe\nqew\nwqeqeweqw", "qwe\nqew\nwqeqeweqw");
        System.out.println(142);
        //        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//        DataBase dataBase = new DataBase("Hi","Hello", "Привет","Здравствуйте");
//        dataBase.clear();
//        dataBase.add("Hi","Hello", "Привет","Здравствуйте");
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
