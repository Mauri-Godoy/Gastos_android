package com.mg.gastos.data.converters;

import com.mg.gastos.data.entity.MovementType;

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
