package com.epam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class WordsController {
    public static void main(String[] args) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Russian: ");
        String rus = reader.readLine();

        System.out.print("English: ");
        String eng = reader.readLine();

        addNewWordToDB(rus, eng);
    }

    private static void addNewWordToDB(String rus, String eng) throws SQLException {
        Statement statement = null;
        String query = "INSERT INTO words" +
                "(id, russian_translation, english_translation) " +
                "VALUES (DEFAULT, '" + rus + "', '" + eng + "');";

        Connection connection = getConnectionToDB();
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Statement creating error");
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
    }

    private static Connection getConnectionToDB(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/epam_test_db","epamwinter", "epamWinter");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
        return connection;
    }
}
