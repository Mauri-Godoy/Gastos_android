package com.mg.gastos.db;

import static com.mg.gastos.db.Database.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.mg.gastos.entity.Expense;

import org.apache.commons.math3.util.Precision;

import java.time.LocalDateTime;
import java.util.List;

public class ExpenseRepository {

    private final Database database;

    private static ExpenseRepository instance;

    private ExpenseRepository(Context context) {
        database = Database.getInstance(context);
    }

    public static ExpenseRepository getInstance(Context context) {
        if (instance == null)
            instance = new ExpenseRepository(context);

        return instance;
    }

    public void insert(final Expense expense) {
        expense.setDate(LocalDateTime.now());
        database.expenseDao().insert(expense);
    }

    public List<Expense> getAll() {
        return database.expenseDao().getAll();
    }

    public Double getTotal() {
        return Precision.round(database.expenseDao().getTotal(), 2);
    }

    public List<Expense> getMonthAndValues() {
        return database.expenseDao().getMonthAndValues();
    }
}
