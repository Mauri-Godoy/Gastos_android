package com.mg.gastos;

import com.mg.gastos.gui.CustomActivityOnCrash;

import cat.ereza.customactivityoncrash.config.CaocConfig;

public class Application extends android.app.Application {

    private static Application singleton;

    public Application getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT)
                .logErrorOnRestart(true)
                .errorActivity(CustomActivityOnCrash.class)
                .apply();

    }
}
