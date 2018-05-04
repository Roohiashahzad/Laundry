package com.roohia.hp.laundry.model.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.roohia.hp.laundry.gui.application.LaundraGoApplication;


public class PreferenceUtils {
    public static final String PREFERENCE_NAME = "Laundra_GO";
    private static PreferenceUtils ourInstance = new PreferenceUtils();

    public static PreferenceUtils getInstance() {
        return ourInstance;
    }

    private PreferenceUtils() {
    }

    public static void saveString(Context context, String key, String value) {
        SharedPreferences.Editor settings = context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        settings.putString(key, value);
        settings.apply();
    }
    public static void saveInt(Context context, String key, int value) {
        SharedPreferences.Editor settings = context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        settings.putInt(key, value);
        settings.apply();
    }
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getString(key, defValue);
    }
    public static String getAccessToken(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getString("token", "");
    }
    public static int getUserId(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getInt("userId", 0);
    }
    public static String getUserId() {
        SharedPreferences settings = LaundraGoApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, 0);
        return String.valueOf(settings.getInt("userId", 0));
    }

    public static String getUserEmail() {
        SharedPreferences settings = LaundraGoApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getString("userEmail", "");
    }

    public static void saveUserEmail(Context context, String value) {
        SharedPreferences.Editor settings = context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        settings.putString("userEmail", value);
        settings.apply();
    }

    public static void saveItemId(Context context, int value) {
        SharedPreferences.Editor settings = context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        settings.putInt("itemId", value);
        settings.apply();
    }

    public static int getItemId() {
        SharedPreferences settings = LaundraGoApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getInt("itemId", 0) + 1;
    }

    public static String getString(Context context, String key) {
        return getString(context,key,"");
    }
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getInt(key,defValue);
    }
    public static int getInt(Context context, String key) {
        return getInt(context,key,0);
    }
}
