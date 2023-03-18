package com.mg.gastos.db;

import android.content.Context;

import com.mg.gastos.entity.Category;

import java.util.List;

public class CategoryRepository {

    private final Database database;

    private static CategoryRepository instance;

    private CategoryRepository(Context context) {
        database = Database.getInstance(context);
    }

    public static CategoryRepository getInstance(Context context) {
        if (instance == null)
            instance = new CategoryRepository(context);

        return instance;
    }

    public List<Category> getAll() {
        return database.categoryDao().getAll();
    }
}
