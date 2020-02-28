package main.java.com.epam.domain;

public class Word {
    private String russian;
    private String english;

    public Word(String russian, String english){
        this.russian = russian;
        this.english = english;
    }

    public String getRussian() {
        return russian;
    }

    public String getEnglish() {
        return english;
    }
}
