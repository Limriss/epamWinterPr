package com.epam.service;

import com.epam.dao.StatisticsDAOImpl;
import com.epam.domain.Statistic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quest {
    private StatisticsDAOImpl statisticsDAOImpl = new StatisticsDAOImpl();
    private ArrayList<Statistic> stats;

    public void testChanging(){
        Scanner reader = new Scanner(System.in);

        String name = reader.nextLine();
        int wonCount = reader.nextInt();
        int lostCount = reader.nextInt();

        String result = statistic(name, wonCount, lostCount);
        System.out.println(result);
    }

    private String statistic(String name, int wongGamesCount, int lostGamesCount){
        String result;
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
