package com.epam.dao;

import com.epam.domain.Statistic;
import com.epam.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StatisticsDAO implements com.epam.dao.interfaces.StatisticsDAO {
    private ConnectionPool pool;

    public StatisticsDAO(){
        pool = ConnectionPool.getInstance();
    }

    @Override
    public void create(int rightChoicesCount, int wrongChoicesCount) throws SQLException{
        Statement statement = null;
        String query = "INSERT INTO statistics " +
                "(id, right_choices_count, wrong_choices_count) " +
                "VALUES (DEFAULT, '" + rightChoicesCount + "', '" + wrongChoicesCount + "');";

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
    public ArrayList<Statistic> read() throws SQLException{
        ArrayList<Statistic> statistics = new ArrayList<>();

        Statement statement = null;
        String query = "SELECT * FROM statistics";

        Connection connection = pool.getConnection();

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()){
                int id = result.getInt("id");
                int rightCount = result.getInt("right_choices_count");
                int wrongCount = result.getInt("wrong_choices_count");

                Statistic tempStatistic = new Statistic(id, rightCount, wrongCount);

                statistics.add(tempStatistic);
            }
        } catch (SQLException e) {
            System.out.println("Statement creating error");
        } finally {
            if (statement != null)
                statement.close();
            pool.realiseConnection(connection);
        }

        return statistics;
    }

    @Override
    public void update(int id, int rightChoicesCount, int wrongChoicesCount) throws SQLException{
        Statement statement = null;
        String query = "UPDATE statistics " +
                "SET right_choices_count = " + rightChoicesCount +
                ", wrong_choices_count = " + wrongChoicesCount +
                " WHERE id = " + id;

        Connection connection = pool.getConnection();

        try{
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
}
