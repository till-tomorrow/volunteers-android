package vola.systers.com.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by haroon on 28/05/18.
 */

public class PreferenceManager {

    private static final String PREFERENCE = "vola_preferences";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_LAUNCHED_ONCE = "launched_once";

    private final SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void clearSharedPreferences() {
        sharedPreferences.edit().clear().apply();
    }

    public void putLaunchedOnce() {
        sharedPreferences.edit().putBoolean(KEY_LAUNCHED_ONCE, true).apply();
    }

    public boolean getLaunchedOnce() {
        return sharedPreferences.getBoolean(KEY_LAUNCHED_ONCE, false);
    }

    public void putUserName(@NonNull String userName) {
        sharedPreferences.edit().putString(KEY_USERNAME, userName).apply();
    }

    public String getUserName(@NonNull String userName) {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }
}
