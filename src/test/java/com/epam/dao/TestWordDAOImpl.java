package com.epam.dao;
import com.epam.domain.Statistic;
import com.epam.domain.Word;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestWordDAOImpl {
    private WordDAOImpl testWordDAO = new WordDAOImpl();
    private Word w = new Word("Кот", "Cat");

    private WordDAOImpl test2WordDAO = new WordDAOImpl();

    @Test
    public void testCreate() throws SQLException {
      //  Assert.assertEquals(testWordDAO.create(w), "1 rows was created");
    }



    @Test
    public void testRead() throws SQLException{
        Assert.assertFalse(testWordDAO.read().isEmpty());
    }
}


