package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.vocabulary.DataBase;
import sample.vocabulary.Word;
// переписать функции, чтобы добавление было как в конструкторе.
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DataBase dataBase = new DataBase();
        System.out.println(142);
//        dataBase.add("Hi","Helo", "Привет","Здравствуйте");
        Word word = new Word("hi","привет", "hello","здравствуйте");
        Word word1 = new Word("Hi","Привет", "Hello","Здравствуйте");
        dataBase.edit(word, word1);
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
