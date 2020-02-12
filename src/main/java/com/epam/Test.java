package com.epam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        HashMap<String, String> map = new HashMap<>();

        map.put("Собака", "Dog");
        map.put("Кошка", "Cat");
        map.put("Дом", "House");
        map.put("Лиса", "Fox");
        map.put("Дети", "Children");

        HashMap<Integer, String> map2 = new HashMap<>();

        map2.put(1, "Собака");
        map2.put(2, "Кошка");
        map2.put(3, "Дом");
        map2.put(4, "Лиса");
        map2.put(5, "Дети");

        int randomIndex = (int) Math.round(Math.random() * 4 + 1);

        String russianVer = map2.get(randomIndex);

        System.out.println(russianVer);

        String word = map.get(russianVer);

        String[] choice = new String[3];
        choice[0] = word;

        int a = 1;
        int r = 0;
        while (a == 1){
            int random1 = (int) Math.round(Math.random() * 4 + 1);
            if (random1 != randomIndex){
                String nextWord = map.get(map2.get(random1));
                choice[1] = nextWord;
                r = random1;
                a = 0;
            }
        }
        while (a == 0){
            int random2 = (int) Math.round(Math.random() * 4 + 1);
            if (random2 != randomIndex & random2!=r){
                String nextWord = map.get(map2.get(random2));
                choice[2] = nextWord;
                a = 1;
            }
        }

        Collections.shuffle(Arrays.asList(choice));

        String s = "abc";
        String f = "";
        for (int i = 0; i < choice.length; i++) {
            char ind = s.charAt(i);
            if (choice[i].equals(word)){
                f = String.valueOf(ind);
            }
            choice[i] = ind + ". " + choice[i];
            System.out.println(choice[i]);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
        String var = in.nextLine();
        in.close();

        if (var.equals(f)){
            System.out.printf("Правильно");
        }
        else{
            System.out.printf("Неправильно");
        }

    }
}