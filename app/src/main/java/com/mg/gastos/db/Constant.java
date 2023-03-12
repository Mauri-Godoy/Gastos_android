package com.mg.gastos.db;

public class Constant {

    public static final String TABLE_CONTACTS = "contacts";

    public static final String CREATE_TABLE ="CREATE TABLE " + TABLE_CONTACTS + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT , " +
            "phone TEXT , " +
            "email TEXT)";
}
