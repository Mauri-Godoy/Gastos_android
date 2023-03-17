package com.mg.gastos.db;

import static com.mg.gastos.db.Database.DATABASE_VERSION;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.mg.gastos.converters.DateConverter;
import com.mg.gastos.dao.ExpenseDao;
import com.mg.gastos.entity.Expense;

@androidx.room.Database(entities = {Expense.class}, version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class Database extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gastos.db";

    public abstract ExpenseDao daoAccess();
}
