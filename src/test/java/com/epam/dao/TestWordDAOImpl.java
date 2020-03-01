package com.epam.dao;
import com.epam.domain.Word;
import org.junit.Assert;
import org.junit.Test;

public class TestWordDAOImpl {
    private WordDAOImpl testWordDAO = new WordDAOImpl();
    private Word w = new Word("Кот", "Cat");

    @Test
    public void testCreate() {
        Assert.assertEquals(testWordDAO.create(w), w);
    }

    @Test
    public void testRead() {
        Assert.assertFalse(testWordDAO.read().isEmpty());
    }

    @Test
    public void testDelete(){
        Assert.assertEquals(testWordDAO.delete(w), w);
    }
}


