package com.epam.dao.interfaces;

import com.epam.domain.Statistic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StatisticsDAO {
    void create(int rightCount, int wrongCount) throws SQLException;
    ArrayList<Statistic> read() throws SQLException;
    void update(int id, int rightCount, int wrongCount) throws SQLException;
}
