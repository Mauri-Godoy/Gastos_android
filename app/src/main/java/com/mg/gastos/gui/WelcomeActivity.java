package com.mg.gastos.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.mg.gastos.R;
import com.mg.gastos.utils.Animator;

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
            Animator.alpha(v);
            Intent intent = new Intent(this, ExpenseActivity.class);
            this.finish();
            startActivity(intent);
        });
    }


//Example sharedPreferences
//    private void getPreference() {
//        SharedPreferences sharedPreferences = this.getSharedPreferences(
//                getString(R.string.sharedPrefName), Context.MODE_PRIVATE);
//
//        String name = sharedPreferences.getString("name", "");
//
//        TextView textView = findViewById(R.id.textView);
//        textView.setText(name);
//
//        create();
//    }
//
//    private void setName(){
//        SharedPreferences.Editor editor = this.getSharedPreferences(
//                getString(R.string.sharedPrefName), Context.MODE_PRIVATE).edit();
//
//        EditText editText = findViewById(R.id.et_name);
//
//        editor.putString("name", editText.getText().toString());
//
//        editor.apply();
//    }
}