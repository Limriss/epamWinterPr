package main.java.com.epam.dao.interfaces;

import main.java.com.epam.domain.Word;

import java.util.ArrayList;

public interface WordDAO {
    Word create(Word word);
    ArrayList<Word> read();
    Word delete(Word word);
}
