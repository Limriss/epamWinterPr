package com.epam.service;

import com.epam.utils.ConnectionPool;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        String txt = msg.getText();
        sendMsg(msg, "Hello, world! This is simple bot!");
    }

    public String getBotUsername() {
        try {
            Properties property = new Properties();
            property.load(ConnectionPool.class.getClassLoader().getResourceAsStream("bot.properties"));
            return property.getProperty("bot.username");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getBotToken() {
        try {
            Properties property = new Properties();
            property.load(ConnectionPool.class.getClassLoader().getResourceAsStream("bot.properties"));
            return property.getProperty("bot.token");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            execute(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
