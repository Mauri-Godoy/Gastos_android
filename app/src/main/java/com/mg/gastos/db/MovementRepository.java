package com.mg.gastos.db;

import android.content.Context;

import com.mg.gastos.entity.Movement;

import org.apache.commons.math3.util.Precision;

import java.time.LocalDateTime;
import java.util.List;

public class MovementRepository extends Repository {

    private static MovementRepository instance;

    private MovementRepository(Context context) {
        super(context);
    }

    public static MovementRepository getInstance(Context context) {
        if (instance == null)
            instance = new MovementRepository(context);

        return instance;
    }

    public void insert(final Movement movement) {
        movement.setDate(LocalDateTime.now());
        database.movementDao().insert(movement);
    }

    public List<Movement> getAll() {
        return database.movementDao().getAll();
    }

    public Double getTotal() {
        return Precision.round(database.movementDao().getTotal(), 2);
    }

    public List<Movement> getMonthAndValues() {
        return database.movementDao().getMonthAndValues();
    }
}
