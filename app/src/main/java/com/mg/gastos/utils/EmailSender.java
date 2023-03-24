package com.mg.gastos.utils;

import android.content.Context;
import android.content.Intent;

import com.mg.gastos.R;

public class EmailSender {
    public static void sendEmail(Context context, String subject, String message) {
        String email = context.getString(R.string.email);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);

        context.startActivity(Intent.createChooser(intent, message));
    }
}
