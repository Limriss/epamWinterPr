package com.epam.dao;

import com.epam.dao.interfaces.StatisticsDAO;
import com.epam.domain.Statistic;
import com.epam.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class StatisticsDAOImpl implements StatisticsDAO {
    private ConnectionPool pool;

    public StatisticsDAOImpl(){
        pool = ConnectionPool.getInstance();
    }

    @Override
    public void create(String name, byte stat) throws SQLException{
        Statement statement = null;
        String query = "INSERT INTO statistics " +
                "(id, name, stat) " +
                "VALUES (DEFAULT, '" + name + "', '" + stat + "');";

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
    public HashMap<Integer, Statistic> read() throws SQLException{
        HashMap<Integer, Statistic> statistics = new HashMap<>();

        Statement statement = null;
        String query = "SELECT * FROM statistics";

        Connection connection = pool.getConnection();

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                byte stat = result.getByte("stat");

                Statistic tempStatistic = new Statistic(id, name, stat);

                statistics.put(id, tempStatistic);
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
    public void update(int id, String name, byte stat) throws SQLException{
        Statement statement = null;
        String query = "UPDATE statistics " +
                "SET name = '" + name +
                "', stat = " + stat +
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
