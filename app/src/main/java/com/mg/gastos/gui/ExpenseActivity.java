package com.mg.gastos.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mg.gastos.R;
import com.mg.gastos.gui.fragments.CreateFragment;
import com.mg.gastos.gui.fragments.ExpenseHistoryFragment;
import com.mg.gastos.gui.fragments.ExpenseStatsFragment;
import com.mg.gastos.gui.fragments.UnderConstructionFragment;
import com.mg.gastos.utils.Animator;

public class ExpenseActivity extends AppCompatActivity {

    private static ExpenseActivity instance = null;

    public static ExpenseActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        setNavBottomData();
    }

    public static void setToolbarTitle(String title) {
        Toolbar materialToolbar = getInstance().findViewById(R.id.toolbar);
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

        Animator.transition(getSupportFragmentManager(), R.id.frame_layout, fragment);

        if (itemTitle != null)
            setToolbarTitle(itemTitle);

    }

}