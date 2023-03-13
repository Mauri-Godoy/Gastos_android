package com.mg.gastos.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mg.gastos.R;
import com.mg.gastos.gui.fragments.CreateFragment;
import com.mg.gastos.gui.fragments.ExpenseHistoryFragment;
import com.mg.gastos.gui.fragments.ExpenseStatsFragment;
import com.mg.gastos.gui.fragments.UnderConstructionFragment;

public class ExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        replaceFragment(new CreateFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(1).getItemId());

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.createExpenseItem:
                    replaceFragment(new CreateFragment());
                    break;
                case R.id.expenseStatsItem:
                    replaceFragment(new ExpenseStatsFragment());
                    break;
                case R.id.expenseHistoryItem:
                    replaceFragment(new ExpenseHistoryFragment());
                    break;
                default:
                    replaceFragment(new UnderConstructionFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

}