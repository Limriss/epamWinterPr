package com.epam.dao;
import com.epam.domain.Word;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class TestWordDAOImpl {
    private WordDAOImpl testWordDAO = new WordDAOImpl();
    private Word w = new Word("Кот", "Cat");

    private WordDAOImpl test2WordDAO = new WordDAOImpl();


    @Test
    public void testCreate() throws SQLException {
      //  Assert.assertEquals(testWordDAO.create(w), "1 rows was created");
    }

    public void testRead() throws SQLException{
       // Assert.assertEquals();
    }

    //создать тест для read
}


