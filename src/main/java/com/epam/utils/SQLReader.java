package com.epam.utils;

import java.io.*;

public class SQLReader {

    //public SQLReader(){}
    public static String readSQL(String fileName){
        String resultQuery;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/sql/" + fileName));
            resultQuery = reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            resultQuery = "";
        } catch (IOException e) {
            System.out.println("File read error");
            resultQuery = "";
        }

        return resultQuery;
    }
}
