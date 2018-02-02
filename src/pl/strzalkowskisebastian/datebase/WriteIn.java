package pl.strzalkowskisebastian.datebase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteIn {
    static private DBHandler dbHandler;
    static private Connection connection;
    static private PreparedStatement preparedStatement;

    public static void write(String word, String commentary) throws SQLException, ClassNotFoundException {
        dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();

        String insert = "INSERT INTO angielski(slowo, komentarz)" + "VALUES(?,?)";
        preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
        preparedStatement.setString(1, word);
        preparedStatement.setString(2,commentary);
        preparedStatement.executeUpdate();
    }
}