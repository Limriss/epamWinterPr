package com.epam.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {

    private static final int CAPACITY = 10;
    private static String url;
    private static String user;
    private static String password;
    private static ConnectionPool pool;
    private static List<Connection> availableConnections;

    private ConnectionPool(){}

    public static ConnectionPool getInstance(){
        ConnectionPool localPool = pool;
        if (localPool == null){
            try {
                Class.forName("org.postgresql.Driver");
                Properties property = new Properties();
                property.load(ConnectionPool.class.getClassLoader().getResourceAsStream("db.properties"));
                url = property.getProperty("db.url");
                user = property.getProperty("db.user");
                password = property.getProperty("db.password");
            } catch (ClassNotFoundException | IOException e) {
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

    public synchronized Connection getConnection() {
        if (availableConnections.isEmpty()) {
            try {
                return createConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        Connection connection = availableConnections.get(0);
        availableConnections.remove(connection);

        return connection;
    }

    public synchronized void releaseConnection(Connection connection){
        if (connection == null){
            return;
        }

        try {
            if (availableConnections.size() < CAPACITY && !connection.isClosed()){
                connection.close();
                availableConnections.add(createConnection());
            }
            else
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
