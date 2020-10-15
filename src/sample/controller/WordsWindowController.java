package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Constant;
import sample.base.DataBase;
import sample.base.Word;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordsWindowController {

    private static List<String> selectionSet;
    private ObservableList<Word> vocabulary;
    private static Word changeWord;
    private DataBase dataBase;

    public static Word getChangeWord() {
        return changeWord;
    }

    public static List<String> getSelectionSet() {
        return selectionSet;
    }

    @FXML
    private TableView<Word> tableWords;

    @FXML
    private TableColumn<Word, String> columnEn;

    @FXML
    private TableColumn<Word, String> columnRu;

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

    private String createTable(String nameTable) {
        String result = Constant.CREATE_TABLE + nameTable + '\n';
        result = result + Constant.VALUES_CREATE_TABLE_SELECTIONS;
        return result;
    }

    private String addWord(String nameTable, Word word) {
        String result = Constant.INSERT + DataBase.userNameToDatabaseNameTranslator(nameTable) +
                Constant.ARGUMENT_INSERT + Constant.LEFT_PARENTHESIS + DataBase.attributePreparation(word.getWordInEn()) + Constant.COMMA +
                DataBase.attributePreparation(word.getWordInRu()) + Constant.RIGHT_PARENTHESIS;
        return result;
    }

    private String removeWord(String nameTable, Word word) {
        String result = Constant.DELETE_FROM + nameTable + Constant.WHERE + Constant.WORD_IN_EN
                + DataBase.attributePreparation(word.getWordInEn()) + Constant.AND + Constant.WORD_IN_RU
                + DataBase.attributePreparation(word.getWordInRu()) + Constant.SEMICOLON;
        return result;
    }

    private String changeWord(String nameTable, Word word1, Word word2) {
        String result = Constant.UPDATE + nameTable + Constant.SET +
                Constant.WORD_IN_EN + DataBase.attributePreparation(word2.getWordInEn()) + Constant.COMMA +
                Constant.WORD_IN_RU + DataBase.attributePreparation(word2.getWordInRu()) + Constant.WHERE +
                Constant.WORD_IN_EN + DataBase.attributePreparation(word1.getWordInEn()) + Constant.AND +
                Constant.WORD_IN_RU + DataBase.attributePreparation(word1.getWordInRu()) + Constant.SEMICOLON;
        return result;
    }

    private void setTooltipForButtons() {
        addSectionButton.setTooltip(new Tooltip("Click the button \nto create a new section"));
        removeSectionButton.setTooltip(new Tooltip("Click the button \nto remove this section"));
        addWordButton.setTooltip(new Tooltip("Click the button \nto add new word in this section"));
        addWordInSection.setTooltip(new Tooltip("Click the button \nto add selected word(s) in section"));
        removeWordsButton.setTooltip(new Tooltip("Click the button \nto delete the selected word(s)"));
        closeButton.setTooltip(new Tooltip("Click the button \nto close this window"));
        changeWordButton.setTooltip(new Tooltip("Click the button \nto change selected word"));
    }

    private void disabledWordButton(boolean check) {
        removeWordsButton.setDisable(true);
        addWordInSection.setDisable(true);
        changeWordButton.setDisable(true);
        if (check) addWordButton.setDisable(true);

    }

    private void enabledWordButton() {
        removeWordsButton.setDisable(false);
        addWordInSection.setDisable(false);
        changeWordButton.setDisable(false);
        addWordButton.setDisable(false);
    }

    private EventHandler<ActionEvent> addSection() {
        return event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/view/addSectionWindow.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Add selection");
                stage.setScene(scene);
                stage.showAndWait();
                if (!AddSectionWindowController.nameSelection.isEmpty()) {
                    selectionSet.add(AddSectionWindowController.nameSelection);
                    ObservableList<String> selectionSetO = FXCollections.observableArrayList(selectionSet);
                    comboBoxSection.setItems(selectionSetO);
                    dataBase.createSection(AddSectionWindowController.nameSelection);
                    System.out.println("We're added section.");
                }
            } catch (IOException | SQLException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        };
    }

    private EventHandler<ActionEvent> removeSection() {
        return e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete section");
            alert.setHeaderText("Are you sure want to remove this section?");
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
                    dataBase.removeSection(DataBase.userNameToDatabaseNameTranslator(nameRemovedSection));
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
        };
    }

    private EventHandler<ActionEvent> partitionSwitch() {
        return e -> {
            try {
                String nameSection = DataBase.userNameToDatabaseNameTranslator(comboBoxSection.getValue());
                if (nameSection != null) {
                    enabledWordButton();
                    dataBase = new DataBase(nameSection);
                    vocabulary = FXCollections.observableArrayList(dataBase.getVocabulary());
                    tableWords.setItems(vocabulary);
                }
            } catch (SQLException | ClassNotFoundException ee) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", ee);
            }
        };
    }

    private EventHandler<ActionEvent> addWord() {
        return event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/view/addWord.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Add word");
                stage.setScene(scene);
                stage.showAndWait();
                if (AddWordController.getCheck()) {
                    AddWordController.setCheck();
                    dataBase.addWord(AddWordController.getNewWord());
                    vocabulary = FXCollections.observableArrayList(dataBase.getVocabulary());
                    tableWords.setItems(vocabulary);
                }
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> close() {
        return e -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        };
    }

    private EventHandler<ActionEvent> removeWord() {
        return e -> {
            Word removedWord = tableWords.getSelectionModel().getSelectedItem();
            try {
                vocabulary.remove(removedWord);
                dataBase.deleteWord(removedWord);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> changeWordButton() {
        return e -> {
            try {
                changeWord = tableWords.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/view/changeWordWindow.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Change word");
                stage.setScene(scene);
                stage.showAndWait();
                if (ChangeWordWindowController.getCheck()) {
                    try {
                        dataBase.editWords(changeWord, ChangeWordWindowController.getNewWord());
                        tableWords.setItems(vocabulary);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            } catch (IOException ev) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        };
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        columnEn.setCellValueFactory(new PropertyValueFactory<Word, String>("wordInEn"));
        columnRu.setCellValueFactory(new PropertyValueFactory<Word, String>("wordInRu"));
        setTooltipForButtons();
        disabledWordButton(true);
        dataBase = new DataBase("all_worlds");
        selectionSet = dataBase.getSectionList();
        ObservableList<String> observableArrayList = FXCollections.observableArrayList(selectionSet);
        comboBoxSection.setItems(observableArrayList);
        addSectionButton.setOnAction(addSection());
        removeSectionButton.setOnAction(removeSection());
        comboBoxSection.setOnAction(partitionSwitch());
        addWordButton.setOnAction(addWord());
        closeButton.setOnAction(close());
        removeWordsButton.setOnAction(removeWord());
//        addWordInSection.setOnAction(addWordInSection());
        changeWordButton.setOnAction(changeWordButton());
    }
}
