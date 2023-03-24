package com.mg.gastos.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mg.gastos.R;
import com.mg.gastos.gui.fragments.CreateFragment;
import com.mg.gastos.gui.fragments.MovementHistoryFragment;
import com.mg.gastos.gui.fragments.MovementStatsFragment;
import com.mg.gastos.gui.fragments.UnderConstructionFragment;
import com.mg.gastos.utils.Animator;
import com.mg.gastos.utils.PermissionHelper;

public class MovementActivity extends AppCompatActivity {

    private static MovementActivity instance = null;

    public static MovementActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);

        PermissionHelper.requestPermission(this);

        setContentView(R.layout.activity_movement);
        setNavBottomData();
    }

    private void setNavBottomData() {
        replaceFragment(new CreateFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(1).getItemId());

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.createMovementItem:
                    replaceFragment(new CreateFragment());
                    break;
                case R.id.movementStatsItem:
                    replaceFragment(new MovementStatsFragment());
                    break;
                case R.id.movementHistoryItem:
                    replaceFragment(new MovementHistoryFragment());
                    break;
                default:
                    replaceFragment(new UnderConstructionFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {

        Animator.transition(getSupportFragmentManager(), R.id.layout_fragments, fragment);
    }

}