package sample;

import javafx.stage.Stage;
import sample.base.DataBase;
import sample.base.Vocabulary;
import sample.view.MainWindow;
import sample.view.WordTable;

import java.sql.SQLException;

public class Controller {
    private DataBase dataBase;
    private MainWindow window;

    private void event() {
        window.getShowVocabulary().setOnAction(e -> {
            WordTable.newWindow();
        });
    }

    public Controller(Stage primaryStage) throws SQLException, ClassNotFoundException {
        window = new MainWindow(primaryStage);
        event();
    }
}
