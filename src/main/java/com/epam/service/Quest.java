package com.epam.service;

import com.epam.dao.StatisticsDAOImpl;
import com.epam.domain.Statistic;
import com.epam.dao.WordDAOImpl;
import com.epam.domain.Word;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Quest {
    private StatisticsDAOImpl statisticsDAOImpl;
    private WordDAOImpl wordDAOImpl;
    private ArrayList<Statistic> stats;
    private ArrayList <Word> list;
    private User user;
    private int rightAnsCount;
    private int wrongAnsCount;
    private String rightAnswer;
    private int roundNumber;

    public Quest(User user, int roundNumber){
        statisticsDAOImpl = new StatisticsDAOImpl();
        wordDAOImpl = new WordDAOImpl();
        list = wordDAOImpl.read();
        this.user = user;
        this.roundNumber = roundNumber;
        rightAnsCount = 0;
        wrongAnsCount = 0;
        rightAnswer = "";
    }

    public StringBuilder makeQuestion() {
        wordDAOImpl = new WordDAOImpl();

        StringBuilder question;

        if (roundNumber > 0) {
            Collections.shuffle(list);

            String main = list.get(0).getRussian();
            String answer = list.get(0).getEnglish();
            String wrong1 = list.get(1).getEnglish();
            String wrong2 = list.get(2).getEnglish();

            question = new StringBuilder(main + ": \n");

            String[] choice = new String[3];
            choice[0] = answer;
            choice[1] = wrong1;
            choice[2] = wrong2;

            Collections.shuffle(Arrays.asList(choice));

            String s = "abc";
            String f = "";
            for (int i = 0; i < choice.length; i++) {
                char ind = s.charAt(i);
                if (choice[i].equals(answer))
                    f = String.valueOf(ind);
                question.append(ind).append(") ").append(choice[i]).append(";\n");
            }
            question.append("Enter a, b or c to choose answer.\n");

            return question;
        } else {
            statistic(user.getFirstName(), rightAnsCount, wrongAnsCount);
            return new StringBuilder("");
        }
    }

    public String operate(String answer){
        if (answer.equals(rightAnswer)){
            rightAnsCount++;
            return "Yes, that's right!";
        }
        else{
            wrongAnsCount++;
            return "No, you're wrong!";
        }
    }

    public double getStat(){
        return 100 * ((double) rightAnsCount / (wrongAnsCount + rightAnsCount));
    }

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
