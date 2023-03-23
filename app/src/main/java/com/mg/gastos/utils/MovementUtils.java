package com.mg.gastos.utils;

import com.mg.gastos.data.entity.Movement;

import java.util.List;

public class MovementUtils {

    public static Double getTotal(List<Movement> movementList) {
        return movementList.stream().mapToDouble(movement -> movement.isNegativeAmount() ? (movement.getAmount() * -1) : movement.getAmount()).sum();
    }
}
