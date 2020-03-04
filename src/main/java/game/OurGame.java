package game;

import java.util.*;

public class OurGame {

    public static void play() {
        InitDataForGame.initDictionary();
        InitDataForGame.initRandomWords();

        ArrayList<String> words = new ArrayList<>(3);

        while (true) {
            words.clear();
            String currentRusWord = InitDataForGame.randomWords.get(InitDataForGame.generateRandom());
            String currentEngWord = InitDataForGame.dictionary.get(currentRusWord);
            words.add(currentEngWord);
            System.out.println(currentRusWord);

            while (words.size() < 3) {
                String newWord = InitDataForGame.randomWords.get(InitDataForGame.generateRandom());
                String newEnglishWord = InitDataForGame.dictionary.get(newWord);
                if (!words.contains(newEnglishWord)) {
                    words.add(newEnglishWord);
                }
            }

            Collections.shuffle(words); // "встряхиваем" коллекцию
            System.out.println("a." + words.get(0));
            System.out.println("b." + words.get(1));
            System.out.println("c." + words.get(2));

            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            String choice = "";
            if (answer.equals("end")) {
                System.out.println("Вы завершили игру.");
                return;
            }
            else if (answer.equalsIgnoreCase("a")) {
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
