package com.mg.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.gastos.db.Constant;
import com.mg.gastos.db.DbExpense;
import com.mg.gastos.db.DbHelper;
import com.mg.gastos.models.Expense;

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
        DbExpense dbExpense = new DbExpense(this);

        Expense expense = new Expense();
        expense.setAmount(10);
        expense.setDescription("Hola");

        dbExpense.create(expense);
    }
}