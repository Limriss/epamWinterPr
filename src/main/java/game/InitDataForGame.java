package game;

import java.util.HashMap;
import java.util.Random;

public class InitDataForGame {

    private InitDataForGame(){};

    public static HashMap<String, String> dictionary = new HashMap<>(); // Инициализируем HashMap русских-английских слов
    public static HashMap<Integer, String> randomWords = new HashMap<>(); // Инициализируем HashMap для генерации
                                                                            // рандомных русских слов

    public static void initDictionary(){ // Заполняем HashMap русских-английских слов
        dictionary.put("Лиса","Fox" );
        dictionary.put("Волк", "Wolf");
        dictionary.put("Тигр", "Tiger");
        dictionary.put("Медведь","Bear");
        dictionary.put("Жираф", "Giraffe");
    }

    public static void initRandomWords(){ // Заполняем HashMap для генерации рандомных русских слов
        randomWords.put(0,"Лиса");
        randomWords.put(1,"Волк");
        randomWords.put(2,"Тигр");
        randomWords.put(3,"Медведь");
        randomWords.put(4,"Жираф");
    }

    public static int generateRandom(){ // генерируем рандомное число,
        // чтобы достать 2 неправильных слова и 1 правильное слово.
        Random r = new Random();
        int i = r.nextInt(5);
        return i;
    }
}
