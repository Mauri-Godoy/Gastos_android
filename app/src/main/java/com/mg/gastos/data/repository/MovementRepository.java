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

    public void create(final Movement movement) {
        movement.setDate(LocalDateTime.now());
        database.movementDao().insert(movement);
    }

    public void update(final Movement movement) {
        database.movementDao().update(movement);
    }

    public void delete(final Movement movement) {
        database.movementDao().update(movement);
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

    /**INSERT INTO movement(date, amount, code, negativeAmount, movementType, name)
     * values ('2022/12/21 12:27:56',24.0, 'OTHER' , 1, 'OTHER', 'Casa') **/
}
