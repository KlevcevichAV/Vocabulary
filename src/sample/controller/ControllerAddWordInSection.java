package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControllerAddWordInSection {

    private static boolean additionCheck;
    private static String section;

    public static boolean getAdditionCheck(){
        return additionCheck;
    }

    public static String getSection(){
        return section;
    }

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> comboBoxSection;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        additionCheck = false;
        ObservableList<String> observableArrayList = FXCollections.observableArrayList(ControllerWordsWindow.getSelectionSet());
        comboBoxSection.setItems(observableArrayList);
        cancelButton.setOnAction(e->{
            additionCheck = false;
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
        addButton.setOnAction(e->{
            additionCheck = true;
            section = comboBoxSection.getValue();
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        });

    }

}