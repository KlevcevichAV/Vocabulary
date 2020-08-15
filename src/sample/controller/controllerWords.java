package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class controllerWords {

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

    private void settingProperties() {
        p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "123");
        p.setProperty("useUnicode", "true");
        p.setProperty("characterEncoding", "cp1251");
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            selectionSet = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select * from Section");
            while (resultSet.next()){
                selectionSet.add(resultSet.getString(2));
//                dataBase.add(new Word(resultSet.getString(2), resultSet.getString(4), resultSet.getString(3), resultSet.getString(5), dataBase));
            }
            System.out.println("We're created database.");
        }
        ObservableList<String> observableArrayList = FXCollections.observableArrayList(selectionSet);
        comboBoxSection.setItems(observableArrayList);
        comboBoxSection.setValue(observableArrayList.get(0));
    }
}
