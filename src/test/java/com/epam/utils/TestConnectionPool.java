

package com.epam.utils;
import com.epam.utils.ConnectionPool;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class TestConnectionPool {

    private static final int CAPACITY = 10;
    private static String url="jdbc:postgresql://localhost:5432/word_translate";
    private static String user="postgres";
    private static String password="postgres";
    private static ConnectionPool pool;
    private static List<Connection> availableConnections;
    private ConnectionPool TestConnectionPool1 = new ConnectionPool();



    @Test
    public void testGetInstance() {
        ConnectionPool cp = TestConnectionPool1.getInstance();
        try {
            Connection c = cp.getConnection();
            Assert.assertTrue(!c.isClosed());



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
