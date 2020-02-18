package com.epam;

import com.epam.service.Quest;

import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);

        String name = reader.nextLine();
        int wonCount = reader.nextInt();
        int lostCount = reader.nextInt();

        String result = Quest.statistic(name, wonCount, lostCount);
        System.out.println(result);
    }
}
