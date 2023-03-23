package com.mg.gastos.data.repository;

import android.content.Context;

import com.mg.gastos.data.db.Database;

public abstract class Repository {

    Database database;

    protected Repository(Context context) {
        database = Database.getInstance(context);
    }
}
