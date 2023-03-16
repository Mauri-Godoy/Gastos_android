package com.mg.gastos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mg.gastos.entity.Expense;
import com.mg.gastos.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbExpense {

    public static final String TABLE = "expense";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DATE + " TEXT NOT NULL, " +
            DESCRIPTION + " VARCHAR(255), " +
            AMOUNT + " DOUBLE NOT NULL)";
    public static final String SELECT_QUERY =
            String.format("SELECT %s, %s, %s, %s FROM %s", ID, AMOUNT, DESCRIPTION, DATE, TABLE);

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

        LocalDateTime now = LocalDateTime.now();

        ContentValues values = new ContentValues();
        values.put(DESCRIPTION, expense.getDescription());
        values.put(DATE, DateUtils.parseForDb(now));
        values.put(AMOUNT, expense.getAmount());

        return db.insert(TABLE, ID, values);
    }

    public List<Expense> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery(SELECT_QUERY, null);

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

    public Map<String, Double> getMonthAndValues() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = String.format("SELECT SUM(%s) AS \"TOTAL\",  " +
                "%s FROM %S  GROUP BY SUBSTR(%s, 0, 8)", AMOUNT, DATE, TABLE, DATE);

        Cursor c = db.rawQuery(query, null);

        Map<String, Double> values = new HashMap<>();

        if (c.moveToFirst()) {
            do {
                values.put(c.getString(1), c.getDouble(0));
            } while (c.moveToNext());
        }
        c.close();

        return values;
    }
}
