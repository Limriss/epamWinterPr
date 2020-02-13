package com.epam.domain;

public class Word {
    int id;
    String russian;
    String english;

    public Word(int id, String russian, String english){
        this.id = id;
        this.russian = russian;
        this.english = english;
    }

    public int getId() {
        return id;
    }

    public String getRussian() {
        return russian;
    }

    public String getEnglish() {
        return english;
    }
}
