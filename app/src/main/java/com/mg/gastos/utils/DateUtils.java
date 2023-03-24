package com.mg.gastos.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String parseToMonthAndYear(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM yy");

        return dtf.format(localDateTime).replace(".", "");
    }

    public static String parseToTableFormat(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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
