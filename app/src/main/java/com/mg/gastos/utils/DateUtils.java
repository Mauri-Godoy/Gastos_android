package com.mg.gastos.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DateUtils {

    public static String parseToMonth(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM");

        return dtf.format(localDateTime);
    }

    public static String parseToTableFormat(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        return dtf.format(localDateTime);
    }

    public static String parseToSimpleDate(LocalDateTime localDateTime) {

        String pattern = "dd 'de' MMMM";

        LocalDateTime startDay = LocalDateTime.now().toLocalDate().atStartOfDay();

        if (localDateTime.isAfter(startDay))
            pattern = "HH:mm";
        else if (Duration.between(localDateTime, startDay).toDays() < 6)
            pattern = "EEEE";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);

        return dtf.format(localDateTime).toUpperCase();
    }
}
