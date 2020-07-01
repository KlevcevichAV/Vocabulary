package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    String userName = "root";
    String password = "123";
    String url = "jdbc:mysql://localhost:3306/vocabulary";

    public DataBase(String wordInEn, String synonymForEn, String wordInRu, String synonymForRu) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        try (Statement statement = connection.createStatement()){
//            statement.executeUpdate("insert into vocabulary (wordInEn,synonymForEn,wordInRu,synonymForRu) value (" + wordInEn + ',' + synonymForEn + ',' + wordInRu + ',' + synonymForRu + ");");
            statement.executeUpdate("insert into vocabulary (wordInEn,synonymForEn,wordInRu,synonymForRu) values (Hi,Hello,Привет," + wordInEn + ',' + synonymForEn + ',' + wordInRu + ',' + synonymForRu + ");");
            System.out.println("We're connected.");
        }
    }

    public void add(String wordInEn, String synonymForEn, String wordInRu, String synonymForRu) throws SQLException {
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO vocabulary (wordInEn,synonymForEn,wordInRu,synonymForRu) VALUE (" + wordInEn + ',' + synonymForEn + ',' + wordInRu + ',' + synonymForRu + ");");
    }
}
