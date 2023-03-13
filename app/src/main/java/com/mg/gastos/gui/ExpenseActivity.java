package com.mg.gastos.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
        setNavBottomData();

    }

    private void setTitle(String title) {
        Toolbar materialToolbar = findViewById(R.id.toolbar);
        materialToolbar.setTitle(title);
    }

    private void setNavBottomData() {
        replaceFragment(new CreateFragment(), null);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(1).getItemId());

        bottomNavigationView.setOnItemSelectedListener(item -> {

            String title = item.getTitle().toString();

            switch (item.getItemId()) {
                case R.id.createExpenseItem:
                    replaceFragment(new CreateFragment(), title);
                    break;
                case R.id.expenseStatsItem:
                    replaceFragment(new ExpenseStatsFragment(), title);
                    break;
                case R.id.expenseHistoryItem:
                    replaceFragment(new ExpenseHistoryFragment(), title);
                    break;
                default:
                    replaceFragment(new UnderConstructionFragment(), title);
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment, String itemTitle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
        if (itemTitle != null)
            setTitle(itemTitle);
    }

}