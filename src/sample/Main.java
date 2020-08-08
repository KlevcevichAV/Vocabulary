package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.base.DataBase;
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
//        for(int i = 0; i < database.size(); i++){
//            for(int j = 0; j < database.size(); j++){
//                database.get(i).addSynonym(database.get(j));
//            }
//        }
//        Word word1 = new Word("Hi","Привет", "Hello","Здравствуйте");
//        dataBase.edit(word, word1);
//        System.exit(0);
        Controller controller = new Controller(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
