package com.epam.dao;

import com.epam.dao.interfaces.WordDAO;
import com.epam.domain.Word;
import com.epam.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class WordDAOImpl implements WordDAO {
    private ConnectionPool pool;

    public WordDAOImpl(){
        pool = ConnectionPool.getInstance();
    }

    @Override
    public void create(String rus, String eng) throws SQLException {
        Statement statement = null;
        String query = "INSERT INTO words " +
                "(id, rus, eng) " +
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
    public HashMap<Integer, Word> read() throws SQLException {
        HashMap<Integer, Word> words = new HashMap<>();

        Statement statement = null;
        String query = "SELECT * FROM words";

        Connection connection = pool.getConnection();

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()){
                int id = result.getInt("id");
                String russian = result.getString("rus");
                String english = result.getString("eng");

                Word tempWord = new Word(id, russian, english);

                words.put(id, tempWord);
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
