package com.mg.gastos.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DateUtils {

    static DateTimeFormatter dtfDB = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static String parseToMonth(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM");

        return dtf.format(localDateTime);
    }

    public static String parseToSimpleDate(LocalDateTime localDateTime) {

        String pattern = "dd 'de' MMMM HH:mm";

        LocalDateTime startDay = LocalDateTime.now().toLocalDate().atStartOfDay();

        if (localDateTime.isAfter(startDay))
            pattern = "HH:mm";
        else if (Duration.between(localDateTime, startDay).toDays() < 6)
            pattern = "EEEE HH:mm";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);

        return dtf.format(localDateTime);
    }
}
