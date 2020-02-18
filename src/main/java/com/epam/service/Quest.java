package com.epam.service;

import com.epam.dao.StatisticsDAOImpl;
import com.epam.dao.WordDAOImpl;
import com.epam.domain.Statistic;
import com.epam.domain.Word;

import java.sql.SQLException;
import java.util.ArrayList;

public class Quest {
    private static WordDAOImpl wordDAOImpl = new WordDAOImpl();
    private static StatisticsDAOImpl statisticsDAOImpl = new StatisticsDAOImpl();
    private static ArrayList<Statistic> stats;
    private static ArrayList<Word> words;

    public static String statistic(String name, int wongGamesCount, int lostGamesCount){
        String result = "";
        byte stat = (byte) (100 * wongGamesCount / (wongGamesCount + lostGamesCount));

        try {
            stats = statisticsDAOImpl.read();

            Statistic statistic = getStatisticByName(name);

            if (statistic == null) {
                result = statisticsDAOImpl.create(new Statistic(name, stat));
            }
            else {
                statistic.setStat(stat);
                result = statisticsDAOImpl.update(statistic);
            }
        } catch (SQLException e) {
            result = "SQLException in statistic method";
        }

        return result;
    }

    private static Statistic getStatisticByName(String name){
        int i = 0;
        while (i < stats.size()){
            if (stats.get(i).getName().equals(name))
                return stats.get(i);
            i++;
        }

        return null;
    }
}
