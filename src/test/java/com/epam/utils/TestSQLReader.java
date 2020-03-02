
package com.epam.utils;

import org.junit.Assert;
import org.junit.Test;


public class TestSQLReader {
    SQLReader TestSQLReader = new SQLReader();

    @Test
    public void testReadSQL() {
        Assert.assertEquals(TestSQLReader.readSQL("readWords.sql"), "SELECT * FROM words");
        Assert.assertEquals(TestSQLReader.readSQL("createWord.sql"), "INSERT INTO words (id, rus, eng) VALUES (DEFAULT, ?, ?)");
        Assert.assertEquals(TestSQLReader.readSQL("readStatistics.sql"), "SELECT * FROM statistics");
        Assert.assertEquals(TestSQLReader.readSQL("createStatistic.sql"), "INSERT INTO statistics (id, name, stat) VALUES (DEFAULT, ?, ?)");
        Assert.assertEquals(TestSQLReader.readSQL("updateStatistic.sql"), "UPDATE statistics SET stat = ? WHERE name = ?");
    }


}
