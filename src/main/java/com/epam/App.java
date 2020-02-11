package com.epam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class App {
    public static void main(String[] args){
        saveNewWord();
    }

    private static void saveNewWord(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String rus;
        String eng;
        try {
            System.out.print("Russian: ");
            rus = reader.readLine();

            System.out.print("English: ");
            eng = reader.readLine();

            WordsController.addNewWordToDB(rus, eng);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
