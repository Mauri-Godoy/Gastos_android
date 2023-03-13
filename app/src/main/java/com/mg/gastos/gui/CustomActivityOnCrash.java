package com.mg.gastos.gui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.gastos.R;

import cat.ereza.customactivityoncrash.activity.DefaultErrorActivity;
import cat.ereza.customactivityoncrash.config.CaocConfig;

public class CustomActivityOnCrash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_on_crash);

        Button restartButton = findViewById(R.id.customactivityoncrash_error_activity_restart_button);

        final CaocConfig config = cat.ereza.customactivityoncrash.CustomActivityOnCrash.getConfigFromIntent(getIntent());

        if (config == null) {
            finish();
            return;
        }

        restartButton.setOnClickListener(v -> cat.ereza.customactivityoncrash.CustomActivityOnCrash.restartApplication(CustomActivityOnCrash.this, config));


        Button moreInfoButton = findViewById(R.id.customactivityoncrash_error_activity_more_info_button);


        moreInfoButton.setOnClickListener(v -> {
            //We retrieve all the error data and show it
            AlertDialog dialog = new AlertDialog.Builder(CustomActivityOnCrash.this)
                    .setTitle("Detalles del error")
                    .setMessage(cat.ereza.customactivityoncrash.CustomActivityOnCrash.getAllErrorDetailsFromIntent(CustomActivityOnCrash.this, getIntent()))
                    .setPositiveButton("Cerrar", null)
                    .setNeutralButton("Copiar en el portapapeles",
                            (dialog1, which) -> copyErrorToClipboard())
                    .show();
        });
    }

    private void copyErrorToClipboard() {
        String errorInformation = cat.ereza.customactivityoncrash.CustomActivityOnCrash.getAllErrorDetailsFromIntent(CustomActivityOnCrash.this, getIntent());

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        //Are there any devices without clipboard...?
        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText("Información de error", errorInformation);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(CustomActivityOnCrash.this, "Copiado en el portapapeles", Toast.LENGTH_SHORT).show();
        }
    }
}