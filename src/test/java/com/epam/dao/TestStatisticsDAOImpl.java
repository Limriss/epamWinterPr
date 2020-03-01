package com.epam.dao;
import com.epam.domain.Statistic;
import org.junit.Assert;
import org.junit.Test;

public class TestStatisticsDAOImpl {
    private StatisticsDAOImpl testStatisticsDAOImpl1 = new StatisticsDAOImpl();
    private Statistic s1 = new Statistic("Anna", (byte) 21);

    @Test
    public void testCreate(){
        Assert.assertEquals(testStatisticsDAOImpl1.create(s1), s1);
    }

    @Test
    public void testRead(){
        Assert.assertFalse(testStatisticsDAOImpl1.read().isEmpty());
    }

    private Statistic s2 = new Statistic("Anna", (byte) 33);
    @Test
    public void  testUpdate(){
        Assert.assertEquals(testStatisticsDAOImpl1.update(s2), s2);
    }

    @Test
    public void testDelete(){
        Assert.assertEquals(testStatisticsDAOImpl1.delete(s2), s2);
    }
}
