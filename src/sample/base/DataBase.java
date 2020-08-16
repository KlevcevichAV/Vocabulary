package sample.base;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBase {
    private String url = "jdbc:mysql://localhost:3306/vocabulary?useSSL=false";
    private Properties p;
    private int start;
    private List<Word> dataBase;

    private boolean equals(Word firstWord, Word secondWord){
        if(firstWord.getWordInEn().equals(secondWord.getWordInEn())){
            if(firstWord.getWordInRu().equals(secondWord.getWordInRu())){
//                if(firstWord.getSynonymForEn().equals(secondWord.getSynonymForEn())){
//                    if(firstWord.getSynonymForRu().equals(secondWord.getSynonymForRu())) return true;
//                }
                return true;
            }
        }
        return false;
    }

    private void settingProperties(){
        p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "123");
        p.setProperty("useUnicode", "true");
        p.setProperty("characterEncoding", "cp1251");
    }

    public static String attributePreparation(String attribute) {
        return '\'' + attribute + '\'';
    }

    public List<Word> getVocabulary(){
        return dataBase;
    }

    public DataBase() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            dataBase = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("select * from vocabulary");
            boolean check = false;
            while (resultSet.next()){
                if(!check){
                    start = resultSet.getInt(1);
                    check = true;
                }
                System.out.println(resultSet.getInt(1));
                dataBase.add(new Word(resultSet.getString(2), resultSet.getString(3)));
//                dataBase.add(new Word(resultSet.getString(2), resultSet.getString(4), resultSet.getString(3), resultSet.getString(5), dataBase));
            }
            System.out.println("We're created database.");
        }
    }

    public static String databaseNameToUserNameTranslator(String databaseName){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < databaseName.length(); i++){
            if(i == 0 && (databaseName.charAt(0) >= 'a' && databaseName.charAt(0) <= 'z')){
                sb.append(Character.toUpperCase(databaseName.charAt(0)));
                continue;
            }
            if(databaseName.charAt(i) == '_'){
                sb.append(' ');
                continue;
            }
            sb.append(databaseName.charAt(i));
        }
        return sb.toString();
    }

    public static String userNameToDatabaseNameTranslator(String databaseName){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < databaseName.length(); i++){
            if(i == 0 && (databaseName.charAt(0) >= 'A' && databaseName.charAt(0) <= 'Z')){
                sb.append(Character.toLowerCase(databaseName.charAt(0)));
                continue;
            }
            if(databaseName.charAt(i) == ' '){
                sb.append('_');
                continue;
            }
            sb.append(databaseName.charAt(i));
        }
        return sb.toString();
    }

    public void addWord(Word word) throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("insert into vocabulary (wordInEn,wordInRu,) values (" + attributePreparation(word.getWordInEn()) + attributePreparation(word.getWordInRu()) + ");");
//            statement.executeUpdate("insert into vocabulary (wordInEn,synonymForEn,wordInRu,synonymForRu) values (" + attributePreparation(word.getWordInEn()) + ',' + attributePreparation(word.getSynonymForEn()) + ',' + attributePreparation(word.getWordInRu()) + ',' + attributePreparation(word.getSynonymForRu()) + ");");
            dataBase.add(word);
            System.out.println("We're added.");
        }
    }

    public void deleteWord(Word deleteWord) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            for(int i = 0; i < dataBase.size(); i++){
                if(equals(deleteWord, dataBase.get(i))){
                    dataBase.remove(i);
                    statement.executeUpdate("delete from vocabulary where id=" + attributePreparation(Integer.toString(start + i)) + ';');
                    System.out.println("We're deleted.");
                    return;
                }
            }
        }
    }

    public void editWord(Word oldWord, Word newWord) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            for(int i = 0; i < dataBase.size(); i++){
                if(equals(oldWord, dataBase.get(i))){
                    dataBase.set(i, newWord);
                    int id = start + i;
//                    String operation = "update vocabulary set wordInRu = " + attributePreparation(newWord.getWordInRu()) + ','
//                            + "wordInEn = " + attributePreparation(newWord.getWordInEn()) + ',' +
//                            "synonymForEn = " + attributePreparation(newWord.getSynonymForEn()) + ',' +
//                            "synonymForRu = " + attributePreparation(newWord.getSynonymForRu()) +
//                            "where id = " + id +';';
                    String operation = "update vocabulary set wordInRu = " + attributePreparation(newWord.getWordInRu()) + ','
                            + "wordInEn = " + attributePreparation(newWord.getWordInEn()) +
                            "where id = " + id +';';
                    statement.executeUpdate(operation);
                    System.out.println("We're edited.");
                    return;
                }
            }
        }
    }

    public void clear() throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from vocabulary where 1=1;");
            System.out.println("Clear.");
        }
    }
}
