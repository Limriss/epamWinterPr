package com.epam;

import com.epam.dao.StatisticsDAOImpl;
import com.epam.dao.WordDAOImpl;
import com.epam.domain.Statistic;
import com.epam.domain.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;

public class App {
    static WordDAOImpl wordDAOImpl;
    static StatisticsDAOImpl statisticsDAOImpl;
    static HashMap<Integer, Statistic> stats;
    static HashMap<Integer, Word> words;


    public static void main(String[] args){
        wordDAOImpl = new WordDAOImpl();
        statisticsDAOImpl = new StatisticsDAOImpl();

//        saveNewWord();
//        printAllWords();
//
//        saveNewStatistic();
//        printAllStatistics();
//        updateStatistic();

    }

    private static void saveNewWord(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String rus;
        String eng;
        try {
            System.out.print("Russian: ");
            rus = reader.readLine();

            System.out.print("English: ");
            eng = reader.readLine();

            wordDAOImpl.create(rus, eng);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printAllWords() {
        try {
            words = wordDAOImpl.read();
            for (Word word: words.values()) {
                System.out.println("Id: " + word.getId() +
                        "; Russian: " + word.getRussian() +
                        "; English: " + word.getEnglish());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void saveNewStatistic(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String name;
        byte stat;
        try{
            System.out.print("Name: ");
            name = reader.readLine();

            System.out.print("Stat: ");
            stat = Byte.parseByte(reader.readLine());

            statisticsDAOImpl.create(name, stat);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printAllStatistics() {
        try {
            stats = statisticsDAOImpl.read();
            for (Statistic statistic: stats.values()) {
                System.out.println("Id: " + statistic.getId() +
                        "; Name: " + statistic.getName() +
                        "; Stat: " + statistic.getStat());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateStatistic(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int id;
        String newName;
        byte newStat;
        try {
            System.out.print("Enter id: ");
            id = Integer.parseInt(reader.readLine());

            while (stats.size() < id | id <= 0) {
                System.out.print("Wrong id. Enter another: ");
                id = Integer.parseInt(reader.readLine());
            }

            System.out.print("New name: ");
            newName = reader.readLine();

            System.out.print("New stat: ");
            newStat = Byte.parseByte(reader.readLine());

            statisticsDAOImpl.update(id, newName, newStat);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
