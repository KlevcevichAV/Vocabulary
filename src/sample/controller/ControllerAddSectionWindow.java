package sample.controller;


import com.sun.glass.ui.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControllerAddSectionWindow {

    public static String nameSelection;

    @FXML
    private Button addButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;

    @FXML
    void initialize() {
        closeButton.setOnAction(e->{
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });

        addButton.setOnAction(e->{
            nameSelection = nameField.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(nameSelection.isEmpty()){
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You did not enter a section name.");
                alert.showAndWait();
            }else{
                alert.setTitle("Complete");
                alert.setHeaderText(null);
                alert.setContentText("Selection added.");
                alert.showAndWait();
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();
            }
        });

    }
}
