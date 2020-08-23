package sample;

public class Constant {
    public static final String WORD_IN_ENGLISH = "wordInEn";
    public static final String WORD_IN_RUSSIAN = "wordInRu";
    public static final String SEMICOLON = ";";
    public static final String LEFT_PARENTHESIS = "(";
    public static final String RIGHT_PARENTHESIS = ");";
    public static final String NAME_SECTION = "section";
    public static final String SECTION = "Section";
    public static final String ENGLISH = "English";
    public static final String RUSSIAN = "Russian";
    public static final String ID = "id";
    public static final String COMMA = ", ";
    public static final String ALL_WORLDS_USER = "All worlds";
    public static final String ALL_WORLDS_TABLE = "all_worlds";
    public static final String CREATE_TABLE = "create table ";
    public static final String INSERT = "insert into ";
    public static final String INSERT_INTO_SECTION = "insert into Section (section) values";

    public static final String ARGUMENT_INSERT = "(wordInEn, wordInRu) values ";

    public static final String VALUES = "values(";
    public static final String AND = " and ";
    public static final String WHERE = " where ";
    public static final String UPDATE = "update ";
    public static final String SET = " set ";
    public static final String DELETE_FROM = "delete from ";
    public static final String DELETE_FROM_SECTION = "delete from Section where section =";
    public static final String WORD_IN_RU = "wordInRu=";
    public static final String WORD_IN_EN = "wordInEn=";
    public static final String SECTION_ = "section=";
    public static final String SELECT_FROM = "select * from ";
    public static final String SELECT_FROM_SECTION = "select * from Section";
    public static final String VALUES_CREATE_TABLE_SELECTIONS = "(" +
            "id int auto_increment primary key ," +
            "wordInEn varchar(30)," +
            "wordInRu varchar(30)" +
            ");";
    public static final String DROP_TABLE = "drop table ";
}
