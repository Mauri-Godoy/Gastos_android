package com.mg.gastos.data.converters;

import androidx.room.TypeConverter;

import org.apache.commons.math3.util.Precision;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    static DateTimeFormatter dtfDB = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @TypeConverter
    public static LocalDateTime toLocalDateTime(String string) {
        LocalDateTime localDateTime = null;

        try {
            localDateTime = LocalDateTime.parse(string, dtfDB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localDateTime;
    }

    @TypeConverter
    public static String toString(LocalDateTime localDateTime) {
        return dtfDB.format(localDateTime);
    }
}
