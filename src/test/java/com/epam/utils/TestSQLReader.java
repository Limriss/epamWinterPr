
package com.epam.utils;
import com.epam.dao.interfaces.WordDAO;
import com.epam.domain.Word;
import com.epam.utils.ConnectionPool;
import com.epam.utils.SQLReader;

import org.junit.Assert;
import org.junit.Test;


public class TestSQLReader {
    SQLReader TestSQLReader = new SQLReader();

    @Test
    public void testReadSQL() {
        Assert.assertEquals(TestSQLReader.readSQL("readWords.sql"), "SELECT * FROM words");
        Assert.assertEquals(TestSQLReader.readSQL("createWord.sql"), "INSERT INTO words (rus, eng, id) VALUES (?, ?, DEFAULT)");
        Assert.assertEquals(TestSQLReader.readSQL("readStatistics.sql"), "SELECT * FROM statistics");
        Assert.assertEquals(TestSQLReader.readSQL("createStatistic.sql"), "INSERT INTO statistics (id, name, stat) VALUES (DEFAULT, ?, ?)");
        Assert.assertEquals(TestSQLReader.readSQL("updateStatistic.sql"), "UPDATE statistics SET stat = ? WHERE name = ?");
    }


}
