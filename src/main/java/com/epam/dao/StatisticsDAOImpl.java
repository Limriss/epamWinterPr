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
    public String create(Statistic statistic) throws SQLException{
        PreparedStatement statement = null;
        String result;

        String query = SQLReader.readSQL("createStatistic.sql");

        Connection connection = pool.getConnection();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, statistic.getName());
            statement.setByte(2, statistic.getStat());

            statement.executeUpdate();
            result = "Success";
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            result = "Statement creating error";
        } finally {
            if (statement != null)
                statement.close();
            pool.realiseConnection(connection);
        }

        return result;
    }

    @Override
    public ArrayList<Statistic> read() throws SQLException{
        ArrayList<Statistic> statistics = new ArrayList<>();

        String query = SQLReader.readSQL("readStatistics.sql");
        Connection connection = pool.getConnection();

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String name = result.getString("name");
                byte stat = result.getByte("stat");

                Statistic tempStatistic = new Statistic(name, stat);

                statistics.add(tempStatistic);
            }
        } catch (SQLException e) {
            System.out.println("Statement creating error");
        } finally {
            pool.realiseConnection(connection);
        }

        return statistics;
    }

    @Override
    public String update(Statistic statistic) throws SQLException{
        String result;

        String query = SQLReader.readSQL("updateStatistic.sql");
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setByte(1, statistic.getStat());
            statement.setString(2, statistic.getName());

            statement.executeUpdate();
            result = "Success";
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            result = "Statement creating error";
        } finally {
            pool.realiseConnection(connection);
        }

        return result;
    }
}
