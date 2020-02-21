package com.epam.dao;

import com.epam.dao.interfaces.StatisticsDAO;
import com.epam.domain.Statistic;
import com.epam.utils.ConnectionPool;
import com.epam.utils.SQLReader;

import java.sql.*;
import java.util.ArrayList;

public class StatisticsDAOImpl implements StatisticsDAO {
    private ConnectionPool pool;

    public StatisticsDAOImpl(){
        pool = ConnectionPool.getInstance();
    }

    @Override
    public Statistic create(Statistic statistic){
        String query = SQLReader.readSQL("createStatistic.sql");

        Connection connection;
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, statistic.getName());
            statement.setByte(2, statistic.getStat());

            statement.executeUpdate();
            return statistic;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.realiseConnection(connection);
        }
    }

    @Override
    public ArrayList<Statistic> read(){
        ArrayList<Statistic> statistics = new ArrayList<>();

        String query = SQLReader.readSQL("readStatistics.sql");

        Connection connection;
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String name = result.getString("name");
                byte stat = result.getByte("stat");

                Statistic tempStatistic = new Statistic(name, stat);

                statistics.add(tempStatistic);
            }

            return statistics;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.realiseConnection(connection);
        }
    }

    @Override
    public Statistic update(Statistic statistic){
        String query = SQLReader.readSQL("updateStatistic.sql");

        Connection connection;
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setByte(1, statistic.getStat());
            statement.setString(2, statistic.getName());

            statement.executeUpdate();
            return statistic;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.realiseConnection(connection);
        }
    }

    @Override
    public Statistic delete(Statistic statistic) {
        String query = SQLReader.readSQL("deleteStatistic.sql");

        Connection connection;
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, statistic.getName());
            statement.setByte(2, statistic.getStat());

            statement.executeUpdate();
            return statistic;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.realiseConnection(connection);
        }
    }
}
