package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.base.DataBase;
import sample.base.Word;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerWordsWindow {

    private String url = "jdbc:mysql://localhost:3306/vocabulary?useSSL=false";
    private Properties p;
    private List<String> selectionSet;

    @FXML
    private TableView<?> tableWords;

    @FXML
    private TableColumn<?, ?> table;

    @FXML
    private ComboBox<String> comboBoxSection;

    @FXML
    private Button addSectionButton;

    @FXML
    private Button removeSectionButton;

    @FXML
    private Button addWordButton;

    @FXML
    private Button addWordInSection;

    @FXML
    private Button closeButton;

    @FXML
    private Button removeWordsButton;

    @FXML
    private Button changeWordButton;

    private void settingProperties() {
        p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "123");
        p.setProperty("useUnicode", "true");
        p.setProperty("characterEncoding", "cp1251");
    }

    private String createTable(String nameTable) {
        String result = "create table " + nameTable + '\n';
        result = result +
                "(\n" +
                "    id int auto_increment primary key ,\n" +
                "    wordInEn varchar(30),\n" +
                "    wordInRu varchar(30)\n" +
                ");";
        return result;
    }

    private String addWord(String nameTable, Word word){
        String result ="insert " + nameTable + "(wordInEn, wordInRu) values("
                + DataBase.attributePreparation(word.getWordInEn()) + ", "
                + DataBase.attributePreparation(word.getWordInRu()) + ");";
        return result;
    }

    private String removeWord(String nameTable, Word word){
        String result ="delete from " + nameTable + "where wordInEn="
                + DataBase.attributePreparation(word.getWordInEn()) + " and wordInRu="
                + DataBase.attributePreparation(word.getWordInRu()) + ";";
        return result;
    }

    private String changeWord(String nameTable, Word word1, Word word2){
        String result = "";

        return result;
    }

    private void setTooltipForButtons(){
        addSectionButton.setTooltip(new Tooltip("Click the button \nto create a new section"));
        removeSectionButton.setTooltip(new Tooltip("Click the button \nto remove this section"));
        addWordButton.setTooltip(new Tooltip("Click the button \nto add new word in this section"));
        addWordInSection.setTooltip(new Tooltip("Click the button \nto add selected word(s) in section"));
        removeWordsButton.setTooltip(new Tooltip("Click the button \nto delete the selected word(s)"));
        closeButton.setTooltip(new Tooltip("Click the button \nto close this window"));
        changeWordButton.setTooltip(new Tooltip("Click the button \nto change selected word"));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        setTooltipForButtons();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            selectionSet = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select * from Section");
            while (resultSet.next()) {
                selectionSet.add(DataBase.databaseNameToUserNameTranslator(resultSet.getString(2)));
//                dataBase.add(new Word(resultSet.getString(2), resultSet.getString(4), resultSet.getString(3), resultSet.getString(5), dataBase));
            }
            System.out.println("We're created database.");
        }
        ObservableList<String> observableArrayList = FXCollections.observableArrayList(selectionSet);
        comboBoxSection.setItems(observableArrayList);
        comboBoxSection.setValue(observableArrayList.get(0));
        addSectionButton.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("addSectionWindow.fxml"));
                /*
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */

                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Add selection");
                stage.setScene(scene);
                stage.showAndWait();
                if (!ControllerAddSectionWindow.nameSelection.isEmpty()) {
                    selectionSet.add(ControllerAddSectionWindow.nameSelection);
                    ObservableList<String> selectionSetO = FXCollections.observableArrayList(selectionSet);
                    comboBoxSection.setItems(selectionSetO);
                    comboBoxSection.setValue(selectionSetO.get(selectionSetO.size() - 1));
                    try (Statement statement = connection.createStatement()) {
                        statement.executeUpdate("insert into Section (section) values (" + DataBase.attributePreparation(DataBase.userNameToDatabaseNameTranslator(ControllerAddSectionWindow.nameSelection)) + ");");
                        statement.executeUpdate(createTable(DataBase.userNameToDatabaseNameTranslator(ControllerAddSectionWindow.nameSelection)));
                        System.out.println(createTable(DataBase.userNameToDatabaseNameTranslator(ControllerAddSectionWindow.nameSelection)));
                        System.out.println("We're added section.");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        });
        removeSectionButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete section");
            alert.setHeaderText("Are you sure want to remove this section?");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {
                System.out.println(1);
            } else if (option.get() == ButtonType.OK) {
                String nameRemovedSection = comboBoxSection.getValue();
                if (!nameRemovedSection.equals("All worlds")) {
                    selectionSet.remove(nameRemovedSection);
                    ObservableList<String> selectionSetO = FXCollections.observableArrayList(selectionSet);
                    comboBoxSection.setItems(selectionSetO);
                    comboBoxSection.setValue(selectionSetO.get(0));
                    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
                    alertInfo.setTitle("Complete");
                    alertInfo.setHeaderText(null);
                    alertInfo.setContentText("You removed this section.");
                    alertInfo.showAndWait();
                    try (Statement statement = connection.createStatement()) {
                        statement.executeUpdate("delete from Section where section =" + DataBase.attributePreparation(nameRemovedSection) + ';');
                        statement.executeUpdate("drop table " + DataBase.userNameToDatabaseNameTranslator(nameRemovedSection));
                        System.out.println("We're deleted.");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
                    alertInfo.setTitle("Error");
                    alertInfo.setHeaderText(null);
                    alertInfo.setContentText("You did not enter a section name.");
                    alertInfo.showAndWait();
                }
            } else if (option.get() == ButtonType.CANCEL) {
                System.out.println(3);
            } else {
                System.out.println(4);
            }
        });
    }
}