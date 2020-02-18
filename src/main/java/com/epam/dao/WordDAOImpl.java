package com.epam.dao;

import com.epam.dao.interfaces.WordDAO;
import com.epam.domain.Word;
import com.epam.utils.ConnectionPool;
import com.epam.utils.SQLReader;

import java.sql.*;
import java.util.ArrayList;

public class WordDAOImpl implements WordDAO {
    private ConnectionPool pool;

    public WordDAOImpl(){
        pool = ConnectionPool.getInstance();
    }

    @Override
    public String create(Word word) throws SQLException {
        String result;

        String query = SQLReader.readSQL("createWord.sql");
        Connection connection = pool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, word.getRussian());
            statement.setString(2, word.getEnglish());

            int rows = statement.executeUpdate();
            result = "Success";
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            result = "Statement creating error";
        } finally {
            pool.realiseConnection(connection);
        }
        return result;
    }

    @Override
    public ArrayList<Word> read() throws SQLException {
        ArrayList<Word> words = new ArrayList<>();

        String query = SQLReader.readSQL("readWords.sql");
        Connection connection = pool.getConnection();

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String russian = result.getString("rus");
                String english = result.getString("eng");

                Word tempWord = new Word(russian, english);

                words.add(tempWord);
            }
        } catch (SQLException e) {
            System.out.println("Statement creating error");
        } finally {
            pool.realiseConnection(connection);
        }

        return words;
    }
}
