package com.mg.gastos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mg.gastos.entity.Expense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbExpense {

    public static final String TABLE = "expense";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";

    public static final String CREATE_TABLE ="CREATE TABLE " + TABLE + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DATE + " TEXT NOT NULL, " +
            DESCRIPTION + " VARCHAR(255) NOT NULL, " +
            AMOUNT + " DOUBLE NOT NULL)";

    private final DbHelper dbHelper;

    private static DbExpense mInstance = null;

    public static DbExpense getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DbExpense(context);
        }
        return mInstance;
    }

    private DbExpense(Context context) {
        dbHelper = DbHelper.getInstance(context);
    }

    public long create(Expense expense) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DESCRIPTION, expense.getDescription());
        values.put(DATE, new Date().toString());
        values.put(AMOUNT, expense.getAmount());

        return db.insert(TABLE, ID, values);
    }

    public List<Expense> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = String.format("SELECT %s, %s, %s, %s FROM %s ", ID, AMOUNT, DESCRIPTION, DATE, TABLE);

        Cursor c = db.rawQuery(query, null);

        List<Expense> expenses = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                Expense expense = new Expense();
                expense.setId(c.getInt(0));
                expense.setAmount(c.getDouble(1));
                expense.setDescription(c.getString(2));
                expense.setDate(c.getString(3));

                expenses.add(expense);
            } while (c.moveToNext());
        }
        c.close();

        return expenses;
    }
}
