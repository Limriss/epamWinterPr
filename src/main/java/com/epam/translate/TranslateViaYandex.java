package com.epam.translate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class TranslateViaYandex {
    final static String API_KEY = "trnsl.1.1.20200301T161827Z.b346febb9bcc00d2.5c0e266da1ae590c622e74b034f708212053e62b";
    final static String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    public static void main(String[] args) throws IOException {
        String ourString = GeneratingWord.generateWord();

        // Задание: создать приложение, которое считывает у пользователя
        // строку(и) текста и направление перевода, кодирует текст
        // с помощью класса URLEncoder ( https://docs.oracle.com/javase/8/docs/api/java/net/URLEncoder.html)
        // и выполняет перевод текста с помощью API Яндекс.переводчика
        // Описание API https://yandex.ru/dev/translate/doc/dg/reference/translate-docpage/
        // * предусмотреть ввод многострочного текста

        URL url = new URL(API_URL + "?text=+"+ourString+"%27s&format=plain&lang=en-ru&key="+API_KEY);
        InputStream stream = (InputStream) url.getContent();
        Scanner in = new Scanner(stream);
        String s = in.nextLine();
        System.out.println(s);
        //Gson gson = new Gson();
        // описать класс для обработки ответа от сервера
        //Translation cards = gson.fromJson(reader, Translation.class);
        // напечатать результат перевода

    }
}