package ru.netology.graphics;

import ru.netology.graphics.image.Convert;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Convert(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
       // String url = "https://www.pngfind.com/pngs/m/597-5975111_unknown-small-copy-small-circle-png-transparent-png.png";
       // String imgTxt = converter.convert(url);
       // System.out.println(imgTxt);
    }
}
