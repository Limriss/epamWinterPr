package com.epam.translate;

import com.epam.dao.WordDAOImpl;
import com.epam.domain.Word;

import java.sql.SQLException;
import java.util.*;

public class GeneratingWord{

    static String generateWord(){
        Random r = new Random();
        ArrayList<String> rusWords = InitializingWords.rusWords;
        return rusWords.get(r.nextInt(rusWords.size()));

    }


    static class InitializingWords {

        static HashMap<Integer, Word> words;
        static ArrayList<String> rusWords;


        static {
            try {
                words = new WordDAOImpl().read();
                rusWords = generateAllRusWords();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        static ArrayList<String> generateAllRusWords(){
            Set<Map.Entry<Integer, Word>> entries = words.entrySet();
            ArrayList<String> rusWords = new ArrayList<>();
            for (Map.Entry<Integer, Word> entry : entries) {
                rusWords.add(entry.getValue().getRussian());
            }
            return rusWords;
        }
    }
}



