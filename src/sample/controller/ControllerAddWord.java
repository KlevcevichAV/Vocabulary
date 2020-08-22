package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.base.Word;

public class ControllerAddWord {

    private static Word newWord;

    private static boolean check;

    public static boolean getCheck(){
        return check;
    }

    public static Word getNewWord(){
        return newWord;
    }

    public static void setCheck(){
        check = false;
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
    void initialize() {
        check = false;
        buttonAdd.setOnAction(e->{
            check = true;
            newWord = new Word(textFieldEn.getText(), textFieldRu.getText());
            Stage stage = (Stage) buttonAdd.getScene().getWindow();
            stage.close();
        });
        buttonCancel.setOnAction(e->{
            Stage stage = (Stage) buttonAdd.getScene().getWindow();
            stage.close();
        });
    }

}

