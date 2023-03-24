package com.mg.gastos.data.repository;

import android.content.Context;
import android.util.Log;

import com.mg.gastos.data.db.DefaultData;
import com.mg.gastos.data.entity.Category;

import java.util.List;

public class CategoryRepository extends Repository {

    private static CategoryRepository instance;

    private CategoryRepository(Context context) {
        super(context);
    }

    public static CategoryRepository getInstance(Context context) {
        if (instance == null)
            instance = new CategoryRepository(context);

        return instance;
    }

    public List<Category> getByTypeCode(String typeCode) {

        List<Category> categoryList = database.categoryDao().getByTypeCode(typeCode);

        if (categoryList.isEmpty()) {
            Log.i("DATABASE", "Insertando tipos de movimientos");
            database.movementTypeDao().insert(DefaultData.movementTypeList);

            Log.i("DATABASE", "Insertando categorías");
            database.categoryDao().insert(DefaultData.categoryList);
            categoryList = database.categoryDao().getByTypeCode(typeCode);
        }

        return categoryList;
    }
}