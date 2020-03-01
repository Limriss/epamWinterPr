package com.epam.service;

import com.epam.dao.StatisticsDAOImpl;
import com.epam.domain.Statistic;

import java.util.ArrayList;

public class Quest {
    private StatisticsDAOImpl statisticsDAOImpl = new StatisticsDAOImpl();
    private ArrayList<Statistic> stats;

    private Statistic statistic(String name, int wonGamesCount, int lostGamesCount){
        Statistic result;
        byte stat = (byte) (100 * wonGamesCount / (wonGamesCount + lostGamesCount));

        stats = statisticsDAOImpl.read();

        Statistic statistic = getStatisticByName(name);

        if (statistic == null) {
            result = statisticsDAOImpl.create(new Statistic(name, stat));
        }
        else {
            statistic.setStat(stat);
            result = statisticsDAOImpl.update(statistic);
        }

        return result;
    }

    private Statistic getStatisticByName(String name){
        int i = 0;
        while (i < stats.size()){
            if (stats.get(i).getName().equals(name))
                return stats.get(i);
            i++;
        }

        return null;
    }
}
