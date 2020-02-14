package com.epam.dao.interfaces;

import com.epam.domain.Statistic;

import java.sql.SQLException;
import java.util.HashMap;

public interface StatisticsDAO {
    void create(String name, byte stat) throws SQLException;
    HashMap<Integer, Statistic> read() throws SQLException;
    void update(int id, String name, byte stat) throws SQLException;
}
