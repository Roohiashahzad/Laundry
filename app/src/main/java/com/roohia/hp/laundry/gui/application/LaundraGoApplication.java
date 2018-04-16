package com.roohia.hp.laundry.gui.application;

import android.support.multidex.MultiDexApplication;



public class LaundraGoApplication extends MultiDexApplication {
    private static LaundraGoApplication instance;

    public static LaundraGoApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
