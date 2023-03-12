package com.mg.gastos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mg.gastos.models.Expense;

import java.util.Date;

public class DbExpense {

    private final DbHelper dbHelper;

    public DbExpense(Context context){
        dbHelper = new DbHelper(context, null);
    }

    public long create(Expense expense) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.DESCRIPTION, expense.getDescription());
        values.put(Constant.DATE, new Date().toString());
        values.put(Constant.AMOUNT, expense.getAmount());

        return db.insert(Constant.TABLE_EXPENSE, Constant.ID, values);
    }
}
