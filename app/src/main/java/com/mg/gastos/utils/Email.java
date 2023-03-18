package com.mg.gastos.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Email {
    private void sendEmail(Uri screenshot, Context context) {
        String email = "email";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});

        String appVersion = " getAppVersion(context))";
        String androidVersion = "  getAndroidVersion()";
        String deviceName = " getDeviceName()";

        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for App " + appVersion);
        intent.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n\nApp Version:" + appVersion
                + "\nAndroid Version:" +
                androidVersion + "\nDevice:" + deviceName);
        if (screenshot != null) {
            intent.putExtra(Intent.EXTRA_STREAM, screenshot);
        }
        context.startActivity(Intent.createChooser(intent, "Send feedback..."));
    }
}
