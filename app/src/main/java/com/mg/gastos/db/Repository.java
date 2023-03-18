package com.mg.gastos.db;

import android.content.Context;

public abstract class Repository {

    Database database;

    protected Repository(Context context) {
        database = Database.getInstance(context);
    }
}
