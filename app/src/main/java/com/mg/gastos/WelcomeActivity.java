package com.mg.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViewById(R.id.btn_next);
        setButtonAction();
    }

    private void setButtonAction() {
        Button button = findViewById(R.id.btn_next);
        button.setOnClickListener(v -> {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            v.startAnimation(shake);
            setName();
            Intent intent = new Intent(this, MainActivity.class);
            this.finish();
            startActivity(intent);
        });
    }

    private void setName(){
        SharedPreferences.Editor editor = this.getSharedPreferences(
                getString(R.string.sharedPrefName), Context.MODE_PRIVATE).edit();

        EditText editText = findViewById(R.id.et_name);

        editor.putString("name", editText.getText().toString());

        editor.apply();
    }

}