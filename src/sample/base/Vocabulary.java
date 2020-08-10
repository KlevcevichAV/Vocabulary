package sample.base;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Vocabulary {
    private List<Word> vocabulary;
    private String nameVocabulary;
    private String url = "jdbc:mysql://localhost:3306/vocabulary?useSSL=false";
    private Properties p;
    private List<Boolean> checkUpdate;
    private void settingProperties(){
        p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "123");
        p.setProperty("useUnicode", "true");
        p.setProperty("characterEncoding", "cp1251");
    }

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

    public List<Word> getVocabulary(){
        return vocabulary;
    };

    private String attributePreparation(String attribute) {
        return '\'' + attribute + '\'';
    }

    public Vocabulary(String nameSelection) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            vocabulary = new ArrayList<>();
            checkUpdate = new ArrayList<>();
            nameVocabulary = nameSelection;
            ResultSet resultSet = statement.executeQuery("select * from " + nameSelection);
            boolean check = false;
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1));
                vocabulary.add(new Word(resultSet.getString(2), resultSet.getString(3)));
                checkUpdate.add(false);
//                dataBase.add(new Word(resultSet.getString(2), resultSet.getString(4), resultSet.getString(3), resultSet.getString(5), dataBase));
            }
            System.out.println("We're created database.");
        }
    }

    public void addWord(Word word) throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("insert into " + nameVocabulary + " (wordInEn,wordInRu,) values (" + attributePreparation(word.getWordInEn()) + ',' + attributePreparation(word.getWordInRu()) + ");");
//            statement.executeUpdate("insert into vocabulary (wordInEn,synonymForEn,wordInRu,synonymForRu) values (" + attributePreparation(word.getWordInEn()) + ',' + attributePreparation(word.getSynonymForEn()) + ',' + attributePreparation(word.getWordInRu()) + ',' + attributePreparation(word.getSynonymForRu()) + ");");
            vocabulary.add(word);
            System.out.println("We're added.");
        }
    }

    public void deleteWord(Word deleteWord) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            for(int i = 0; i < vocabulary.size(); i++){
                if(equals(deleteWord, vocabulary.get(i))){
                    vocabulary.remove(i);
                    statement.executeUpdate("delete from " + nameVocabulary + " where id=" + attributePreparation(Integer.toString(i)) + ';');
                    System.out.println("We're deleted.");
                    return;
                }
            }
        }
    }

    public void editWord(Word oldWord, Word newWord) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            for(int i = 0; i < vocabulary.size(); i++){
                if(equals(oldWord, vocabulary.get(i))){
                    vocabulary.set(i, newWord);
                    checkUpdate.set(i, true);
                    int id = i;
//                    String operation = "update vocabulary set wordInRu = " + attributePreparation(newWord.getWordInRu()) + ','
//                            + "wordInEn = " + attributePreparation(newWord.getWordInEn()) + ',' +
//                            "synonymForEn = " + attributePreparation(newWord.getSynonymForEn()) + ',' +
//                            "synonymForRu = " + attributePreparation(newWord.getSynonymForRu()) +
//                            "where id = " + id +';';
                    String operation = "update " + nameVocabulary + " set wordInRu = " + attributePreparation(newWord.getWordInRu()) + ','
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
