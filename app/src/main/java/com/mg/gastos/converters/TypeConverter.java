package com.mg.gastos.converters;

import com.mg.gastos.entity.MovementType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TypeConverter {

    @androidx.room.TypeConverter
    public static MovementType toMovementType(String code) {

        return new MovementType(code, "");
    }

    @androidx.room.TypeConverter
    public static String toCode(MovementType movementType) {
        return movementType.getCode();
    }
}
