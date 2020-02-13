package com.epam;

import com.epam.dao.StatisticsDAO;
import com.epam.dao.WordDAO;
import com.epam.domain.Statistic;
import com.epam.domain.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class App {
    static WordDAO wordDAO;
    static StatisticsDAO statisticsDAO;
    static ArrayList<Statistic> stats;


    public static void main(String[] args){
        wordDAO = new WordDAO();
        statisticsDAO = new StatisticsDAO();

        saveNewWord();
        printAllWords();

        saveNewStatistic();
        printAllStatistics();
        updateStatistic();

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

            wordDAO.create(rus, eng);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printAllWords() {
        try {
            ArrayList<Word> words = wordDAO.read();
            for (Word word: words) {
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

        int rightCount, wrongCount;
        try{
            System.out.print("Number of right choices: ");
            rightCount = Integer.parseInt(reader.readLine());

            System.out.print("Number of wrong choices: ");
            wrongCount = Integer.parseInt(reader.readLine());

            statisticsDAO.create(rightCount, wrongCount);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printAllStatistics() {
        try {
            stats = statisticsDAO.read();
            for (Statistic statistic: stats) {
                System.out.println("Id: " + statistic.getId() +
                        "; Number of right choices: " + statistic.getRightChoicesCount() +
                        "; Number of wrong choices: " + statistic.getWrongChoicesCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateStatistic(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int id, newRightCount, newWrongCount;
        try {
            System.out.print("Enter id: ");
            id = Integer.parseInt(reader.readLine());

            while (stats.size() < id | id <= 0) {
                System.out.print("Wrong id. Enter another: ");
                id = Integer.parseInt(reader.readLine());
            }

            System.out.print("Enter new number of right choices: ");
            newRightCount = Integer.parseInt(reader.readLine());

            System.out.print("Enter new number of wrong choices: ");
            newWrongCount = Integer.parseInt(reader.readLine());

            statisticsDAO.update(id, newRightCount, newWrongCount);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
