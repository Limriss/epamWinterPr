package com.epam.service;

import com.epam.dao.WordDAOImpl;
import com.epam.domain.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    private long chatId;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            chatId = message.getChatId();
            List<String> args = new ArrayList<>(Arrays.asList(message.getText().split(" ")));
            String command = args.get(0);
            args.remove(0);

            switch (command){
                case "saveNewWord":
                    if (args.size() == 2)
                        saveNewWord(args);
                    else sendResponse("Invalid arguments count. Need only two words.");
                    break;
                default:
                    sendResponse("I don't understand you.");
            }
        }
    }

    private void saveNewWord(List<String> args){
        String rus = args.get(0);
        String eng = args.get(1);

        Word word = new Word(rus, eng);
        WordDAOImpl wordDAO = new WordDAOImpl();

        word = wordDAO.create(word);
        if (word != null)
            sendResponse(word.toString() + " was added.");
        else
            sendResponse("Word wasn't saved.");
    }

    private void sendResponse(String text){
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(text);
        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @PostConstruct
    public void start(){
        logger.info("username: {}, token: {}", username, token);
    }
}
