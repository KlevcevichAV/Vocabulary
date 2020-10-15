package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.base.Word;

import java.sql.SQLException;

public class ChangeWordWindowController {

    private static Word newWord;
    private static boolean check;

    public static boolean getCheck(){
        return check;
    }

    public static void setCheck(){
        check = false;
    }

    public static Word getNewWord(){
        return newWord;
    }

    @FXML
    private TextField textFieldEn;

    @FXML
    private TextField textFieldRu;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        check = false;
        textFieldEn.setText(WordsWindowController.getChangeWord().getWordInEn());
        textFieldRu.setText(WordsWindowController.getChangeWord().getWordInRu());
        buttonAdd.setOnAction(e->{
            check = true;
            newWord = new Word(textFieldEn.getText(), textFieldRu.getText());
            Stage stage = (Stage) buttonAdd.getScene().getWindow();
            stage.close();
        });
        buttonCancel.setOnAction(e->{
            check = false;
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
        });
    }
}
