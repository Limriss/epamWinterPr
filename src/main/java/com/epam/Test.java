package com.epam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) throws IOException {
        HashMap<String, String> map = new HashMap<>();

        map.put("Собака", "Dog");
        map.put("Кошка", "Cat");
        map.put("Дом", "House");

        HashMap<Integer, String> map2 = new HashMap<>();

        map2.put(1, "Собака");
        map2.put(2, "Кошка");
        map2.put(3, "Дом");

        int randomIndex = (int) Math.round(Math.random() * 2 + 1);

        String russianVer = map2.get(randomIndex);

        System.out.println(russianVer);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        reader.readLine();

        System.out.println(map.get(russianVer));
    }
}