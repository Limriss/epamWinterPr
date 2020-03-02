package com.epam.utils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectionPool {

    @Test
    public void testGetInstance() {
        ConnectionPool cp = ConnectionPool.getInstance();
        try {
            Connection c = cp.getConnection();
            Assert.assertFalse(c.isClosed());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRealiseConnection()  {
        ConnectionPool cp = ConnectionPool.getInstance();
        try {
            Connection connection = cp.getConnection();
            cp.releaseConnection(connection);
            Assert.assertTrue(connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
