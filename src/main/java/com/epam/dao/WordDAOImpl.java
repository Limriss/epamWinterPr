package com.epam.dao;

import com.epam.utils.ConnectionPool;
import com.epam.utils.SQLReader;
import com.epam.dao.interfaces.WordDAO;
import com.epam.domain.Word;

import java.sql.*;
import java.util.ArrayList;

public class WordDAOImpl implements WordDAO {
    private ConnectionPool pool;

    public WordDAOImpl(){
        pool = ConnectionPool.getInstance();
    }

    @Override
    public Word create(Word word) {
        String query = SQLReader.readSQL("createWord.sql");
        Connection connection;
        connection = pool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, word.getRussian());
            statement.setString(2, word.getEnglish());

            statement.executeUpdate();
            return word;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public ArrayList<Word> read(){
        ArrayList<Word> words = new ArrayList<>();

        String query = SQLReader.readSQL("readWords.sql");
        Connection connection;
        connection = pool.getConnection();

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String russian = result.getString("rus");
                String english = result.getString("eng");

                Word tempWord = new Word(russian, english);

                words.add(tempWord);
            }

            return words;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Word delete(Word word) {
        String query = SQLReader.readSQL("deleteWord.sql");

        Connection connection;
        connection = pool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, word.getRussian());
            statement.setString(2, word.getEnglish());

            statement.executeUpdate();
            return word;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.releaseConnection(connection);
        }
    }
}
