package com.epam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class WordsController{

    public static void addNewWordToDB(String rus, String eng) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Statement statement = null;
        String query = "INSERT INTO words" +
                "(id, russian_translation, english_translation) " +
                "VALUES (DEFAULT, '" + rus + "', '" + eng + "');";

        Connection connection = pool.getConnection();
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Statement creating error");
        } finally {
            if (statement != null)
                statement.close();
            pool.realiseConnection(connection);
        }
    }
}
