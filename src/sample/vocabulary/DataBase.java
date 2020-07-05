package sample.vocabulary;

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
                if(firstWord.getSynonymForEn().equals(secondWord.getSynonymForEn())){
                    if(firstWord.getSynonymForRu().equals(secondWord.getSynonymForRu())) return true;
                }
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

    private String attributePreparation(String attribute) {
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
                dataBase.add(new Word(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
            System.out.println("We're created database.");
        }
    }

    public void add(String wordInEn, String synonymForEn, String wordInRu, String synonymForRu) throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("insert into vocabulary (wordInEn,synonymForEn,wordInRu,synonymForRu) values (" + attributePreparation(wordInEn) + ',' + attributePreparation(synonymForEn) + ',' + attributePreparation(wordInRu) + ',' + attributePreparation(synonymForRu) + ");");
            dataBase.add(new Word(wordInEn, synonymForEn, wordInRu, synonymForRu));
            System.out.println("We're added.");
        }
    }

    public void delete(Word deleteWord) throws SQLException {
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

//    UPDATE employee_data SET
//            salary=220000, perks=55000
//    WHERE title='директор';

    public void edit(Word oldWord, Word newWord) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            for(int i = 0; i < dataBase.size(); i++){
                if(equals(oldWord, dataBase.get(i))){
                    dataBase.set(i, newWord);
                    statement.executeUpdate("update vocabulary set wordInEn=" + newWord.getWordInEn()+ " wordInRu=" + attributePreparation(newWord.getWordInRu())+ " synonymForEn=" + attributePreparation(newWord.getSynonymForEn()) + " synonymForRu=" + attributePreparation(newWord.getSynonymForRu()) + "where id=" + attributePreparation(Integer.toString(start + i)) + ';');
                    System.out.println("We're deleted.");
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
