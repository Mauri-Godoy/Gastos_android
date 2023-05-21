package com.mg.gastos.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.mg.gastos.R;

public class NotificationScheduler extends AppCompatActivity {

    private static final String MY_CHANNEL_ID = "channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_scheduler);
        setButtonAction();
    }

    private void setButtonAction() {
        Button button = findViewById(R.id.button);
        createChannel();
        button.setOnClickListener(v ->
        {
            createSimpleNotification();
        });
    }

    NotificationManager notificationManager;


    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(MY_CHANNEL_ID, "MY channel", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    private void createSimpleNotification() {

        Intent intent = new Intent(this, NotificationScheduler.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_calendar).setContentTitle("My title")
                .setContentText("Texto").setStyle(new NotificationCompat.BigTextStyle(
                ).bigText("Hola hola")).setContentIntent(pendingIntent);

        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}