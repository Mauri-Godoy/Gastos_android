package com.mg.gastos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mg.gastos.db.DbExpense;
import com.mg.gastos.entity.Expense;
import com.mg.gastos.gui.ExpensesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create();

        Button button = findViewById(R.id.btn_goToExpensesFragment);
        button.setOnClickListener(v -> {
            redirectTo();
        });
    }

    private void redirectTo() {
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, new ExpensesFragment()).commit();
    }


    private void create() {
        DbExpense dbExpense = new DbExpense(this);

        Expense expense = new Expense();
        expense.setAmount(10);
        expense.setDescription("Hola");

        dbExpense.create(expense);
    }
}