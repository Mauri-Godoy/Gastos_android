package com.mg.gastos.db;

import static com.mg.gastos.db.Database.DATABASE_VERSION;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mg.gastos.converters.DateConverter;
import com.mg.gastos.dao.CategoryDao;
import com.mg.gastos.dao.ExpenseDao;
import com.mg.gastos.entity.Category;
import com.mg.gastos.entity.Expense;

import java.util.List;

@androidx.room.Database(entities = {Expense.class, Category.class},
        version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class Database extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gastos.db";

    public abstract ExpenseDao expenseDao();

    public abstract CategoryDao categoryDao();

    static Database instance;

    public static Database getInstance(Context context) {

        if (instance == null)
            build(context);

        return instance;
    }

    private static void build(Context context) {
        instance = Room.databaseBuilder(context, Database.class, DATABASE_NAME).fallbackToDestructiveMigration()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        new Thread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Database.getInstance(context).categoryDao()
                                                .insert(DefaultData.categoryList);
                                    }
                                }
                        ).start();
                    }
                }).allowMainThreadQueries().build();
    }
}
