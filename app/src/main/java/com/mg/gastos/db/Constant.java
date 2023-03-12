package com.mg.gastos.db;

public class Constant {

    public static final String TABLE_EXPENSE = "expense";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT = "amount";

    public static final String CREATE_TABLE ="CREATE TABLE " + TABLE_EXPENSE + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DATE + " TEXT NOT NULL, " +
            DESCRIPTION + " VARCHAR(255) NOT NULL, " +
            AMOUNT + " DOUBLE NOT NULL)";
}
