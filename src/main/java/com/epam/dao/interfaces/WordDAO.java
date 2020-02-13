package com.epam.dao.interfaces;

import com.epam.domain.Word;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WordDAO {
    void create(String rus, String eng) throws SQLException;
    ArrayList<Word> read() throws SQLException;
}
