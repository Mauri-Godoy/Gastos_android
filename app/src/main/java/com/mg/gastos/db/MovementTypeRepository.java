package com.mg.gastos.db;

import android.content.Context;

public class MovementTypeRepository extends Repository {

    private MovementTypeRepository(Context context) {
        super(context);
    }

    private static MovementTypeRepository instance;

    public static MovementTypeRepository getInstance(Context context) {
        if (instance == null)
            instance = new MovementTypeRepository(context);

        return instance;
    }
}
