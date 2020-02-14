package com.epam.dao.interfaces;

import com.epam.domain.Word;

import java.sql.SQLException;
import java.util.HashMap;

public interface WordDAO {
    void create(String rus, String eng) throws SQLException;
    HashMap<Integer, Word> read() throws SQLException;
}
