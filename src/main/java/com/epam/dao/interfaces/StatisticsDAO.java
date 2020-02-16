package com.epam.dao.interfaces;

import com.epam.domain.Statistic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StatisticsDAO {
    String create(Statistic statistic) throws SQLException;
    ArrayList<Statistic> read() throws SQLException;
    String update(Statistic statistic) throws SQLException;
}
