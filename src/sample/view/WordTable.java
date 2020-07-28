package sample.view;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.base.Word;
import sample.Constant;

import java.util.List;


public class WordTable {
    private static Stage window;
    private static TableView table;

    public static void createTable(){
        table = new TableView();
        TableColumn<Word, String> wordInEnglish = new TableColumn<>(Constant.WORD_IN_ENGLISH);
        TableColumn<Word, String> wordInRussian = new TableColumn<>(Constant.WORD_IN_RUSSIAN);

        wordInEnglish.setCellValueFactory(new PropertyValueFactory<>(Constant.WORD_IN_ENGLISH_EN));
        wordInRussian.setCellValueFactory(new PropertyValueFactory<>(Constant.WORD_IN_RUSSIAN_EN));

        table.getColumns().addAll(wordInEnglish, wordInRussian);
    }

    private static void fillingTable(List<Word> list) {
        table.setItems((ObservableList) list);
    }

    public static void newWindow(List<Word> list) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        createTable();
        fillingTable(list);
        BorderPane root = new BorderPane();
        root.setCenter(table);
        Scene scene = new Scene(root, 610, 200);
        window.setScene(scene);
        window.setTitle("Vocabulary");
        window.showAndWait();
    }
}
