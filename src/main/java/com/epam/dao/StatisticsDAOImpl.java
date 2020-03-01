package com.epam.dao;

import com.epam.utils.ConnectionPool;
import com.epam.utils.SQLReader;
import com.epam.dao.interfaces.StatisticsDAO;
import com.epam.domain.Statistic;

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
        connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, statistic.getName());
            statement.setByte(2, statistic.getStat());

            statement.executeUpdate();
            return statistic;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public ArrayList<Statistic> read(){
        ArrayList<Statistic> statistics = new ArrayList<>();

        String query = SQLReader.readSQL("readStatistics.sql");

        Connection connection;
        connection = pool.getConnection();

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
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Statistic update(Statistic statistic){
        String query = SQLReader.readSQL("updateStatistic.sql");

        Connection connection;
        connection = pool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setByte(1, statistic.getStat());
            statement.setString(2, statistic.getName());

            statement.executeUpdate();
            return statistic;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Statistic delete(Statistic statistic) {
        String query = SQLReader.readSQL("deleteStatistic.sql");

        Connection connection;
        connection = pool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, statistic.getName());
            statement.setByte(2, statistic.getStat());

            statement.executeUpdate();
            return statistic;
        } catch (SQLException e) {
            System.out.println("Statement creating error");
            return null;
        } finally {
            pool.releaseConnection(connection);
        }
    }
}
