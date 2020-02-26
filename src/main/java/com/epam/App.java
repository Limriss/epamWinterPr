package com.epam;

import com.epam.dao.StatisticsDAOImpl;
import com.epam.dao.WordDAOImpl;
import com.epam.domain.Statistic;
import com.epam.domain.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class App {
    static WordDAOImpl wordDAOImpl;
    static StatisticsDAOImpl statisticsDAOImpl;
    static ArrayList<Statistic> stats;
    static ArrayList<Word> words;


    public static void main(String[] args){
        wordDAOImpl = new WordDAOImpl();
        statisticsDAOImpl = new StatisticsDAOImpl();

        //saveNewWord();
        //printAllWords();

        //saveNewStatistic();
        //printAllStatistics();
        //updateStatistic();

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

            String result = wordDAOImpl.create(new Word(rus, eng));
            System.out.println(result);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printAllWords() {
        try {
            words = wordDAOImpl.read();
            for (Word word: words) {
                System.out.println("Russian: " + word.getRussian() +
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

            String result = statisticsDAOImpl.create(new Statistic(name, stat));
            System.out.println(result);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printAllStatistics() {
        try {
            stats = statisticsDAOImpl.read();
            for (Statistic statistic: stats) {
                System.out.println("Name: " + statistic.getName() +
                                   "; Stat: " + statistic.getStat());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateStatistic(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String name;
        byte stat;
        try {
            System.out.print("Enter name: ");
            name = reader.readLine();

            System.out.print("Enter new stat: ");
            stat = Byte.parseByte(reader.readLine());

            String result = statisticsDAOImpl.update(new Statistic(name, stat));
            System.out.println(result);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
