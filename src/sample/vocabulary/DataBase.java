package sample.vocabulary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBase {
    String url = "jdbc:mysql://localhost:3306/vocabulary?useSSL=false";
    Properties p;

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



    public DataBase(String wordInEn, String synonymForEn, String wordInRu, String synonymForRu) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            System.out.println("We're connected.");
        }
    }

    public void add(String wordInEn, String synonymForEn, String wordInRu, String synonymForRu) throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("insert into vocabulary (wordInEn,synonymForEn,wordInRu,synonymForRu) values ('Hi','Hello','Привет','Здравствуйте');");
            System.out.println("We're added.");
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
