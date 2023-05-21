package com.mg.gastos.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.mg.gastos.R;
import com.mg.gastos.utils.NotificationUtils;

public class NotificationScheduler extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_scheduler);
        setButtonAction();
    }

    private void setButtonAction() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v ->
        {
            NotificationUtils.scheduleNotification(this);
        });
    }


}