package com.mg.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

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
    }
}