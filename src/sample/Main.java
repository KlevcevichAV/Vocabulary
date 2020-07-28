package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.base.DataBase;
import sample.base.Word;
// переписать функции, чтобы добавление было как в конструкторе.
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        DataBase dataBase = new DataBase();
//        System.out.println(142);
////        dataBase.add("Hi","Helo", "Привет","Здравствуйте");
//        Word word = new Word("hi","привет", "hello","здравствуйте");
//        Word word1 = new Word("Hi","Привет", "Hello","Здравствуйте");
//        dataBase.edit(word, word1);
//        System.exit(0);
        Controller controller = new Controller(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
