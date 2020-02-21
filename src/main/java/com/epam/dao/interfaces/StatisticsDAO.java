package com.epam.dao.interfaces;

import com.epam.domain.Statistic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StatisticsDAO {
    Statistic create(Statistic statistic);
    ArrayList<Statistic> read();
    Statistic update(Statistic statistic);
    Statistic delete(Statistic statistic);
}
