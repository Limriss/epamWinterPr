package main.java.com.epam.service;


import main.java.com.epam.dao.WordDAOImpl;
import main.java.com.epam.domain.Statistic;
import main.java.com.epam.domain.Word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Quest {

    static WordDAOImpl wordDAOImpl;

    public void operate()
    {
        Scanner in = new Scanner(System.in);
        wordDAOImpl = new WordDAOImpl();

        ArrayList <Word> list = null;
        try {
            list = wordDAOImpl.read();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Ваше имя: ");
        String name = in.nextLine();
        System.out.print("Количество раундов: ");
        int round = in.nextInt();

        int right_ans = 0;
        int wrong_ans = 0;
        for (int j = 0; j < round; j++)
        {
            ArrayList <Statistic> List2 = new ArrayList<>();

            Collections.shuffle(list);

            String main = list.get(0).getRussian();
            String answer = list.get(0).getEnglish();
            String wrong1 = list.get(1).getEnglish();
            String wrong2 = list.get(2).getEnglish();

            System.out.println(main);

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
                System.out.println(ind + "." + choice[i]);
            }

            String var = in.nextLine();
            if (j == 0) {
                var = in.nextLine();
            }

            if (var.equals(f)){
                System.out.println("Правильно");
                right_ans++;
            }
            else{
                System.out.println("Неправильно");
                wrong_ans++;
            }
        }

        int stat = (int) ((float) right_ans/round*100);
        System.out.println("Правильных ответов: " + stat + "%");
        in.close();
    }

}
