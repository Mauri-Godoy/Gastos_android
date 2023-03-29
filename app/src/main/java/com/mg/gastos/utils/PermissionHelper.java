package com.mg.gastos.utils;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class PermissionHelper {
    public static void requestPermission(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            if (!checkPermission(activity))
                                activity.requestPermissions(PERMISSIONS, 1);

                        }
                    }
            ).start();
        }
    }

    public static boolean checkPermission(Activity activity) {
        boolean res = true;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            res = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
        return res;
    }

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

}
