package sample.base;

import sample.Constant;
import sample.controller.ControllerAddSectionWindow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBase {
    private String url = "jdbc:mysql://localhost:3306/vocabulary?useSSL=false";
    private Properties p;
    private List<Word> dataBase;
    private String section;
    private List<String> sectionList;

    private boolean equals(Word firstWord, Word secondWord) {
        if (firstWord.getWordInEn().equals(secondWord.getWordInEn())) {
            return firstWord.getWordInRu().equals(secondWord.getWordInRu());
        }
        return false;
    }

    private void settingProperties() {
        p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "123");
        p.setProperty("useUnicode", "true");
        p.setProperty("characterEncoding", "cp1251");
    }

    public static String attributePreparation(String attribute) {
        return '\'' + attribute + '\'';
    }

    public List<Word> getVocabulary() {
        return dataBase;
    }

    private String where(Word word) {
        String result = Constant.WHERE +
                Constant.WORD_IN_EN + attributePreparation(word.getWordInEn()) + Constant.AND + Constant.WORD_IN_RU + attributePreparation(word.getWordInRu());
        return result;
    }

    public DataBase(String section) throws SQLException, ClassNotFoundException {
        this.section = section;
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        try (Connection connection = DriverManager.getConnection(url, p); Statement statement = connection.createStatement()) {
            dataBase = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_FROM + section);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                dataBase.add(new Word(resultSet.getString(2), resultSet.getString(3)));
            }
            System.out.println("We're created database.");
        }
    }

    public static String databaseNameToUserNameTranslator(String databaseName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < databaseName.length(); i++) {
            if (i == 0 && (databaseName.charAt(0) >= 'a' && databaseName.charAt(0) <= 'z')) {
                sb.append(Character.toUpperCase(databaseName.charAt(0)));
                continue;
            }
            if (databaseName.charAt(i) == '_') {
                sb.append(' ');
                continue;
            }
            sb.append(databaseName.charAt(i));
        }
        return sb.toString();
    }

    public static String userNameToDatabaseNameTranslator(String databaseName) {
        StringBuilder sb = new StringBuilder();
        if (databaseName == null) {
            System.out.println("ooooooo");
            return null;
        }
        for (int i = 0; i < databaseName.length(); i++) {
            if (i == 0 && (databaseName.charAt(0) >= 'A' && databaseName.charAt(0) <= 'Z')) {
                sb.append(Character.toLowerCase(databaseName.charAt(0)));
                continue;
            }
            if (databaseName.charAt(i) == ' ') {
                sb.append('_');
                continue;
            }
            sb.append(databaseName.charAt(i));
        }
        return sb.toString();
    }

    public void addWord(Word word) throws SQLException, ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(url, p); Statement statement = connection.createStatement()) {
            statement.executeUpdate("insert into" + section + "(wordInEn,wordInRu) values (" + attributePreparation(word.getWordInEn()) + attributePreparation(word.getWordInRu()) + ");");
            dataBase.add(word);
            System.out.println("We're added.");
        }
    }

    private String createTable(String nameSection) {
        String result = Constant.CREATE_TABLE + nameSection + '\n';
        result = result + Constant.VALUES_CREATE_TABLE_SELECTIONS;
        return result;
    }

    public void createSection(String nameSection) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, p); Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable(DataBase.userNameToDatabaseNameTranslator(nameSection)));
        }
    }

    public void deleteAllWord(Word deleteWord) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, p); Statement statement = connection.createStatement()) {
            for (int i = 0; i < dataBase.size(); i++) {
                if (equals(deleteWord, dataBase.get(i))) {
                    dataBase.remove(i);
                    statement.executeUpdate(Constant.DELETE_FROM + section + where(deleteWord) + Constant.SEMICOLON);
                    System.out.println("We're deleted.");
                    break;
                }
            }
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_FROM + Constant.SECTION);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                String section = resultSet.getString(2);
                statement.executeUpdate(Constant.DELETE_FROM + section + where(deleteWord) + Constant.SEMICOLON);
            }
        }
    }

    public void deleteWord(Word deleteWord) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, p); Statement statement = connection.createStatement()) {
            for (int i = 0; i < dataBase.size(); i++) {
                if (equals(deleteWord, dataBase.get(i))) {
                    dataBase.remove(i);
                    statement.executeUpdate(Constant.DELETE_FROM + section + where(deleteWord) + Constant.SEMICOLON);
                    System.out.println("We're deleted.");
                    return;
                }
            }
        }
    }

    private void editAll(Word oldWord, Word newWord) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, p); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_FROM + Constant.SECTION);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                String section = resultSet.getString(2);
                statement.executeUpdate(Constant.UPDATE + section + Constant.SET
                        + Constant.WORD_IN_EN + attributePreparation(newWord.getWordInEn())
                        + Constant.WORD_IN_RU + attributePreparation(newWord.getWordInRu())
                        + where(oldWord) + Constant.SEMICOLON);
            }
        }
    }

    public void editWord(Word oldWord, Word newWord) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, p); Statement statement = connection.createStatement()) {
            for (int i = 0; i < dataBase.size(); i++) {
                if (equals(oldWord, dataBase.get(i))) {
                    dataBase.set(i, newWord);
                    editAll(oldWord, newWord);
                    System.out.println("We're deleted.");
                    return;
                }
            }
        }
    }

    public void clear() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, p); Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from " + section + ";");
            System.out.println("Clear.");
        }
    }
}
