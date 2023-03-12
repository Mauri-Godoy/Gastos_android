package com.mg.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.gastos.db.Constant;
import com.mg.gastos.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPreference();
    }

    private void getPreference() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(
                getString(R.string.sharedPrefName), Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "");

        TextView textView = findViewById(R.id.textView);
        textView.setText(name);

        create();
    }
    
    private void create(){
        DbHelper dbHelper = new DbHelper(this, null);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Toast.makeText(this, "BASE DE DATOS CREADA", Toast.LENGTH_SHORT).show();
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("id", 1);
            Long id =
            db.insert(Constant.TABLE_CONTACTS, "id", values);
        }
    }
}