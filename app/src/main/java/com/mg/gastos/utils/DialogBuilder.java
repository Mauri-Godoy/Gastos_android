package com.mg.gastos.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.function.Consumer;

public class DialogBuilder {

    public static void dialogConfirm(Activity activity, Consumer<Boolean> consumer) {
        new AlertDialog.Builder(activity)
                .setMessage("¿Desea eliminar el movimiento?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        consumer.accept(true);
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}
