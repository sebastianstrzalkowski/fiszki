package pl.strzalkowskisebastian.datebase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operation {

    static private DBHandler dbHandler;
    static private Connection connection;
    static private PreparedStatement preparedStatement;

    public static void read() throws SQLException, ClassNotFoundException {
        dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();

        String query = "SELECT * from angielski";
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            System.out.println("ID: " + resultSet.getString("ID")+ " słowo: " + resultSet.getString("slowo") + " znaczenie: " + resultSet.getString("komentarz"));
        }
    }

    public static void write(String word, String commentary) throws SQLException, ClassNotFoundException {
        dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();

        String insert = "INSERT INTO angielski(Slowo, Komentarz)" + "VALUES(?,?)";
        preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
        preparedStatement.setString(1, word);
        preparedStatement.setString(2,commentary);
        preparedStatement.executeUpdate();
    }

    public static void update(String word, String commentary, int id){
        String query = "UPDATE angielski SET Slowo = ?, Komentarz = ? " + "where ID = ?";

        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, word);
            preparedStatement.setString(2,commentary);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id){
        String query = "DELETE FROM angielski where ID = ?";

        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
