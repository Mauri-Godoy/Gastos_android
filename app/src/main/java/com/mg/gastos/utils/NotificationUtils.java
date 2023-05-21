package com.mg.gastos.utils;

import static com.mg.gastos.receiver.AlarmNotification.MY_CHANNEL_ID;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.mg.gastos.receiver.AlarmNotification;

import java.util.Calendar;

public class NotificationUtils {

    public static void scheduleNotification(Context context) {
        createChannel(context);
        Intent intent = new Intent(context.getApplicationContext(), AlarmNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, AlarmNotification.NOTIFICATION_ID, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 15000, pendingIntent);
    }

    private static void createChannel(Context context) {
        NotificationChannel channel = new NotificationChannel(MY_CHANNEL_ID, "MY channel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}
