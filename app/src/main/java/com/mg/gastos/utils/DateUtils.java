package com.mg.gastos.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    static DateTimeFormatter dtfDB = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public static LocalDateTime parseFromDB(String str) {
        LocalDateTime localDateTime = null;

        try {
            localDateTime = LocalDateTime.parse(str, dtfDB);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        return localDateTime;
    }

    public static String parseForDb(LocalDateTime localDateTime) {
        return dtfDB.format(localDateTime);
    }

    public static String parseToMonth(LocalDateTime localDateTime) {
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM");

        return dtf.format(localDateTime);
    }

    public static String parseToSimpleDate(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd 'de' MMM HH:mm");

        return dtf.format(localDateTime);
    }
}
