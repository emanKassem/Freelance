package com.example.freelance.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    private static PrefUtils prefUtils;
    private PrefUtils(){ }
    public static PrefUtils getInstance(){
        if (prefUtils==null)
            prefUtils = new PrefUtils();
        return prefUtils;
    }
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("APP_PREF", Context.MODE_PRIVATE);
    }

    /**
     * Storing Keys in shared preferences to
     * use it in retrofit requests and in any part of the app.
     *
     * @param key key to save value to shared preferences.
     * @param value String Value which need to add to shared preferences.
     */
    public void storeKeys(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Storing Keys in shared preferences to
     * use it in retrofit requests and in any part of the app.
     *
     * @param key key to save value to shared preferences.
     * @param value Integer Value which need to add to shared preferences.
     */
    public void storeKeys(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     *function to remove determine value using it's key from shared preferences.
     * @param key key of need value from shared preferences.
     *
     * @return string value for that key.
     */
    public String getKeys(Context context, String key) {
        return getSharedPreferences(context).getString(key, null);
    }


    /**
     * Clear all Keys from shared preferences.
     */
    public void clear(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
    }

    /**
     *function to remove determine value using it's key from shared preferences.
     * @param key key of need value from shared preferences.
     */
    public void remove(Context context, String key){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(key);
        editor.apply();
    }
}

