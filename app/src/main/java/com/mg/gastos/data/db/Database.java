package com.mg.gastos.data.db;

import static com.mg.gastos.data.db.Database.DATABASE_VERSION;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.mg.gastos.data.converters.DateConverter;
import com.mg.gastos.data.converters.TypeConverter;
import com.mg.gastos.data.dao.CategoryDao;
import com.mg.gastos.data.dao.MovementDao;
import com.mg.gastos.data.dao.MovementTypeDao;
import com.mg.gastos.data.entity.Category;
import com.mg.gastos.data.entity.Movement;
import com.mg.gastos.data.entity.MovementType;

@androidx.room.Database(entities = {Movement.class, Category.class, MovementType.class},
        version = DATABASE_VERSION, exportSchema = false)
@TypeConverters({DateConverter.class, TypeConverter.class})
public abstract class Database extends RoomDatabase {
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "gastos.db";

    public abstract MovementDao movementDao();
    public abstract CategoryDao categoryDao();
    public abstract MovementTypeDao movementTypeDao();

    static Database instance;

    public static Database getInstance(Context context) {

        if (instance == null)
            build(context);

        return instance;
    }

    private static void build(Context context) {
        Log.i("DATABASE", "CONSTRUYENDO BASE DE DATOS");

        instance = Room.databaseBuilder(context, Database.class, DATABASE_NAME).fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }
}
