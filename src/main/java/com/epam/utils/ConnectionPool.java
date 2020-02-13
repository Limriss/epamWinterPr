package com.epam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final int CAPACITY = 10;
    private static final String url = "jdbc:postgresql://localhost:5432/epam_test_db";
    private static final String user = "epamwinter";
    private static final String password = "epamWinter";
    private static ConnectionPool pool;
    private static List<Connection> availableConnections;

    private ConnectionPool(){}

    public static ConnectionPool getInstance(){
        ConnectionPool localPool = pool;
        if (localPool == null){
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver not found");
            }

            availableConnections = new ArrayList<>();
            for (int i = 0; i < CAPACITY; i++) {
                try {
                    availableConnections.add(createConnection());
                } catch(SQLException e){
                    System.out.println("Connection failed");
                }
            }

            localPool = pool;
            if (localPool == null)
                pool = localPool = new ConnectionPool();
        }

        return localPool;
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public synchronized Connection getConnection() throws SQLException {
        if (availableConnections.isEmpty())
            return createConnection();

        Connection connection = availableConnections.get(0);
        availableConnections.remove(connection);

        return connection;
    }

    public synchronized void realiseConnection(Connection connection){
        if (connection == null){
            return;
        }

        try {
            if (availableConnections.size() < CAPACITY && !connection.isClosed()){
                connection.setAutoCommit(true);
                availableConnections.add(connection);
            }
            else
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
