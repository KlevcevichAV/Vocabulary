package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.base.Word;

import java.util.ArrayList;
import java.util.List;

// переписать функции, чтобы добавление было как в конструкторе.
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        DataBase dataBase = new DataBase();
//        System.out.println(142);
////        dataBase.add("Hi","Helo", "Привет","Здравствуйте");
        List<Word> database = new ArrayList<Word>();
        Word word = new Word("Hi","Привет");
        database.add(word);
        word = new Word("Hello", "Здравствуйте");
        database.add(word);

        Controller controller = new Controller(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
