package com.mg.gastos.db;

import static com.mg.gastos.db.Database.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.mg.gastos.entity.Expense;
import com.mg.gastos.utils.DateUtils;

import org.apache.commons.math3.util.Precision;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ExpenseRepository {

    private final Database database;

    private static ExpenseRepository instance;

    private ExpenseRepository(Context context) {
        database = Room.databaseBuilder(context, Database.class, DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public static ExpenseRepository getInstance(Context context) {
        if (instance == null)
            instance = new ExpenseRepository(context);

        return instance;
    }

    public void insert(final Expense expense) {
        expense.setDate(LocalDateTime.now());
        database.daoAccess().insert(expense);
    }

    public List<Expense> getAll() {
        return database.daoAccess().getAll();
    }

    public Double getTotal() {
        return Precision.round(database.daoAccess().getTotal(), 2);
    }

    public List<Expense> getMonthAndValues() {
        return database.daoAccess().getMonthAndValues();
    }
}
