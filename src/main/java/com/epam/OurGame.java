package com.epam;

import java.util.*;

public class OurGame {

    static HashMap<String, String> dictionary = new HashMap<>();
    static HashMap<Integer, String> play = new HashMap<>();


    private static void initDictionary(){
        dictionary.put("Лиса","Fox" );
        dictionary.put("Волк", "Wolf");
        dictionary.put("Тигр", "Tiger");
        dictionary.put("Медведь","Bear");
        dictionary.put("Жираф", "Giraffe");
    }

    private static void initPlayDictionary(){
        play.put(0,"Лиса");
        play.put(1,"Волк");
        play.put(2,"Тигр");
        play.put(3,"Медведь");
        play.put(4,"Жираф");
    }

    private static int generateRandom(){
        Random r = new Random();
        int i = r.nextInt(5);
        return i;
    }


    public static void main(String[] args) {
        initDictionary();
        initPlayDictionary();

        ArrayList<String> words = new ArrayList<>(3);

        while (true) {
            words.clear();
            String currentWord = play.get(generateRandom());
            String currentEngWord = dictionary.get(currentWord);
            words.add(currentEngWord);
            System.out.println(currentWord);

            while (words.size() < 3) {
                String newWord = play.get(generateRandom());
                String newEnglishWord = dictionary.get(newWord);
                if (!words.contains(newEnglishWord)) {
                    words.add(newEnglishWord);
                }
            }

            Collections.shuffle(words);

            System.out.println("a." + words.get(0));
            System.out.println("b." + words.get(1));
            System.out.println("c." + words.get(2));

            Scanner scanner = new Scanner(System.in);

            String answer = scanner.nextLine();

            String choice = new String();

            if (answer.equalsIgnoreCase("a")) {
                choice = words.get(0);
            } else if (answer.equalsIgnoreCase("b")) {
                choice = words.get(1);
            } else if (answer.equalsIgnoreCase("c")) {
                choice = words.get(2);
            } else {
                System.out.print("Такого варианта не было!");
            }

            if (choice.equals(currentEngWord)) {
                System.out.println("Ответ правильный!");
            } else {
                System.out.println("Ответ неправильный!");
            }

        }
    }

}
