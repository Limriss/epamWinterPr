package main.java.com.epam.service;


import main.java.com.epam.domain.Statistic;
import main.java.com.epam.domain.Word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Quest {

    void Operate()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Ваше имя: ");
        int name = in.nextInt();
        System.out.print("Количество раундов: ");
        int round = in.nextInt();
        //System.out.printf("Your number: %d \n", num);
        in.close();

        int right_ans = 0;
        int wrong_ans = 0;
        for (int j = 0; j < round; j++)
        {
            ArrayList <Word> list = new ArrayList<>();
            ArrayList <Statistic> List2 = new ArrayList<>();

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
                if (choice[i].equals(answer)){
                    f = String.valueOf(ind);
                }
                choice[i] = ind + ". " + choice[i];
                System.out.println(choice[i]);
            }

            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //Scanner in = new Scanner(System.in);
            String var = in.nextLine();
            in.close();

            if (var.equals(f)){
                System.out.println("Правильно");
                right_ans++;
            }
            else{
                System.out.println("Неправильно");
                wrong_ans++;
            }
        }

        int stat = (int) (right_ans/round*100);
        System.out.printf("Правильных ответов: ", stat, "%");
    }

}
