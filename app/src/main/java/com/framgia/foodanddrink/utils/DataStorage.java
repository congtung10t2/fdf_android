package com.framgia.foodanddrink.utils;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by framgia on 14/11/2016.
 */
public class DataStorage {
    public static void saveValue(Context context, String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    public static boolean getValue(Context context, String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }
}
