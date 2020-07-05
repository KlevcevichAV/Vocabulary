package sample.vocabulary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBase {
    private String url = "jdbc:mysql://localhost:3306/vocabulary?useSSL=false";
    private Properties p;
    private List<Word> dataBase;

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
            while (resultSet.next()){
                dataBase.add(new Word(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
            System.out.println("We're created database.");
        }
    }

    public void add(String wordInEn, String synonymForEn, String wordInRu, String synonymForRu) throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("insert into vocabulary (wordInEn,synonymForEn,wordInRu,synonymForRu) values ('Hi','Hello','Привет','Здравствуйте');");
            dataBase.add(new Word(wordInEn, synonymForEn, wordInRu, synonymForRu));
            System.out.println("We're added.");
        }
    }

    public void delete(Word deleteWord){

    }

    public void edit(Word word){
        
    }

    public void clear() throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from vocabulary where 1=1;");
            System.out.println("Clear.");
        }
    }
}
