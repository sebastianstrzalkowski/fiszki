package pl.strzalkowskisebastian;

import pl.strzalkowskisebastian.datebase.DBHandler;
import pl.strzalkowskisebastian.datebase.Read;
import pl.strzalkowskisebastian.datebase.WriteIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static String word;
    private static String commentary;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        Read.read();
    }
}
