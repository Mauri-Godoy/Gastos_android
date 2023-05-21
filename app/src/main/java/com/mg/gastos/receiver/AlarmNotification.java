package com.mg.gastos.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.mg.gastos.R;
import com.mg.gastos.gui.NotificationScheduler;

public class AlarmNotification extends BroadcastReceiver {

    public static final String MY_CHANNEL_ID = "channel";
    public static final int NOTIFICATION_ID = 1;

    private Context context;
    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createSimpleNotification();

    }

    private void createSimpleNotification() {

        Intent intent = new Intent(context, NotificationScheduler.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(context, MY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logo).setColor(context.getColor(R.color.primary)).setContentTitle("My title")
                .setContentText("Texto").setStyle(new NotificationCompat.BigTextStyle(
                ).bigText("Hola hola")).setContentIntent(pendingIntent).build();

         notificationManager.notify(NOTIFICATION_ID, notification);
    }

}
