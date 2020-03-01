package com.epam.service;

import com.epam.dao.WordDAOImpl;
import com.epam.domain.Word;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    private long chatId;
    private boolean isPlaying;
    private Quest quest;
    private static InlineKeyboardMarkup inlineKeyboardMarkup;

    static{
        inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        buttonsRow.add(new InlineKeyboardButton().setText("a").setCallbackData("a"));
        buttonsRow.add(new InlineKeyboardButton().setText("b").setCallbackData("b"));
        buttonsRow.add(new InlineKeyboardButton().setText("c").setCallbackData("c"));
        buttons.add(buttonsRow);
        inlineKeyboardMarkup.setKeyboard(buttons);
    }

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Override
    public void onUpdateReceived(Update update) {
        String command = "";
        List<String> args = new ArrayList<>();
        Message message = update.getMessage();

        if (update.hasMessage()) {
            chatId = message.getChatId();
            args = new ArrayList<>(Arrays.asList(message.getText().split(" ")));
            command = args.get(0);
            args.remove(0);
        }
        else if (update.hasCallbackQuery()){
            command = update.getCallbackQuery().getData();
            chatId = update.getCallbackQuery().getMessage().getChatId();
        }

        switch (command){
            case "saveNewWord":
                if (args.size() == 2)
                    saveNewWord(args);
                else sendResponse("Invalid arguments count. Need only two words.");
                break;
            case "start":
                if (args.size() == 1 && !isPlaying)
                {
                    quest = new Quest(message.getFrom(), Integer.parseInt(args.get(0)));
                    String result = String.valueOf(quest.makeQuestion());
                    if (!result.equals("")) {
                        isPlaying = true;

                        sendResponse(result, inlineKeyboardMarkup);
                    }
                    else {
                        double stat = quest.getStat();
                        sendResponse("Game is ended. You've answered correctly on " + stat + "% questions.");
                        isPlaying = false;
                    }
                }
                else{
                    sendResponse("You've entered wrong number of arguments or you're already playing.");
                }
                break;
            case "a":
            case "b":
            case "c":
                if (isPlaying) {
                    String result = quest.operate(command);
                    sendResponse(result);
                    result = String.valueOf(quest.makeQuestion());
                    if (!result.equals("")) {
                        isPlaying = true;
                        sendResponse(result, inlineKeyboardMarkup);
                    }
                    else {
                        double stat = quest.getStat();
                        sendResponse("Game is ended. You've answered correctly on " + Math.round(stat) + "% questions.");
                        isPlaying = false;
                    }
                }
                else{
                    sendResponse("You're not playing now!");
                }
                break;
            case "stop":
                isPlaying = false;
                sendResponse("Stopped.");
                break;
            default:
                sendResponse("To start playing you need to enter command: \"start [number of rounds]\"");
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

    private void sendResponse(String text, InlineKeyboardMarkup markup){
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(text);
        if (markup != null)
            response.setReplyMarkup(markup);
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
