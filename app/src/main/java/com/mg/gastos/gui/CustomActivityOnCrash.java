package com.mg.gastos.gui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.mg.gastos.R;
import com.mg.gastos.utils.EmailSender;

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


        Button btnSendReport = findViewById(R.id.btn_sendReport);


        btnSendReport.setOnClickListener(v -> sendReport());
    }

    private void sendReport() {
        String errorInformation = cat.ereza.customactivityoncrash.CustomActivityOnCrash.getAllErrorDetailsFromIntent(CustomActivityOnCrash.this, getIntent());

        EmailSender.sendEmail(this, "REPORTE ERROR ANDROID", errorInformation);
    }
}