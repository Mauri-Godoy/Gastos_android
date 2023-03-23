package com.mg.gastos.data.repository;

import android.content.Context;

import com.mg.gastos.data.entity.Movement;

import java.time.LocalDateTime;
import java.util.List;

public class MovementRepository extends Repository {

    private static MovementRepository instance;

    private MovementRepository(Context context) {
        super(context);
    }

    public static MovementRepository getInstance(Context context) {
        if (instance == null) instance = new MovementRepository(context);

        return instance;
    }

    public void insert(final Movement movement) {
        movement.setDate(LocalDateTime.now());
        database.movementDao().insert(movement);
    }

    public List<Movement> getAll() {
        return database.movementDao().getAll();
    }

    public List<Movement> getByDate(String dateFrom, String dateTo) {
        return database.movementDao().getByDate(dateFrom, dateTo);
    }

    public List<Movement> getMonthAndValues(boolean negativeAmount) {
        return database.movementDao().getMonthAndValues(negativeAmount);
    }
}
