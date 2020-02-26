package com.epam.dao;
import com.epam.domain.Statistic;
import com.epam.dao.StatisticsDAOImpl;
import com.epam.dao.interfaces.StatisticsDAO;
import com.epam.domain.Statistic;
import com.epam.utils.ConnectionPool;
import com.epam.utils.SQLReader;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class TestStatisticsDAOImpl {
    private StatisticsDAOImpl TestStatisticsDAOImpl1 = new StatisticsDAOImpl();
    private Statistic s1 = new Statistic("Anna", (byte) 21);
    //Для проверки метода read
    private ArrayList<Statistic> readstatistics = new ArrayList();



    @BeforeAll
    public void start(){
        String name = "Mariam";
        byte stat = 33;
        Statistic tempStatistic = new Statistic(name, stat);
        readstatistics.add(tempStatistic);
        readstatistics.add(new Statistic(name, (byte)1));
    }

    @Test
    public void testCreate() throws SQLException {
        Assert.assertEquals(TestStatisticsDAOImpl1.create(s1), "1 rows was created");
    }

    //создать тест для read
    @Test

    public void testRead() throws SQLException{
        Assert.assertEquals(TestStatisticsDAOImpl1.read(), readstatistics);
    }

    private StatisticsDAOImpl TestStatisticsDAOImpl2 = new StatisticsDAOImpl();
    private Statistic s2 = new Statistic("Anna", (byte) 33);
    @Test
    public void  testUpdate() throws SQLException{
        Assert.assertEquals(TestStatisticsDAOImpl1.update(s2), "1 rows was changed");
    }

}
