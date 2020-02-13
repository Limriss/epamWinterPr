package com.epam.dao;

import com.epam.domain.Word;
import com.epam.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WordDAO implements com.epam.dao.interfaces.WordDAO {
    private ConnectionPool pool;

    public WordDAO(){
        pool = ConnectionPool.getInstance();
    }

    @Override
    public void create(String rus, String eng) throws SQLException {
        Statement statement = null;
        String query = "INSERT INTO words " +
                "(id, russian_translation, english_translation) " +
                "VALUES (DEFAULT, '" + rus + "', '" + eng + "');";

        Connection connection = pool.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Statement creating error");
        } finally {
            if (statement != null)
                statement.close();
            pool.realiseConnection(connection);
        }
    }

    @Override
    public ArrayList<Word> read() throws SQLException {
        ArrayList<Word> words = new ArrayList<>();

        Statement statement = null;
        String query = "SELECT * FROM words";

        Connection connection = pool.getConnection();

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()){
                int id = result.getInt("id");
                String russian = result.getString("russian_translation");
                String english = result.getString("english_translation");

                Word tempWord = new Word(id, russian, english);

                words.add(tempWord);
            }
        } catch (SQLException e) {
            System.out.println("Statement creating error");
        } finally {
            if (statement != null)
                statement.close();
            pool.realiseConnection(connection);
        }

        return words;
    }
}
