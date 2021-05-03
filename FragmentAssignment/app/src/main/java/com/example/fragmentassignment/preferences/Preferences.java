package com.example.fragmentassignment.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

public class Preferences {
    private static final String LOGGED_IN_USER = "LOGGED_IN_USER";
    public static final String MXROLE = "MXROLE";
    public static final String USERID = "USERID";
    public static final String MXROLEKEY = "MXROLEKEY";
    private static final String TOKEN = "TOKEN";
    private static final String IS_CLIENT = "IS_CLIENT";
    private static final String LAT = "LAT";
    private static final String LNG = "LNG";
    private static final String IP = "IP";
    Set<String> strings;
    private Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected SharedPreferences getSharedPreferences(String key) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getString(String key, String def) {
        SharedPreferences prefs = getSharedPreferences(key);
        return prefs.getString(key, def);
    }

    public void setString(String key, String val) {
        SharedPreferences prefs = getSharedPreferences(key);
        SharedPreferences.Editor e = prefs.edit();
        e.putString(key, val);
        e.apply();
    }

    private long getLong(String key, long def) {
        SharedPreferences prefs = getSharedPreferences(key);
        return prefs.getLong(key, def);
    }

    public void setLong(String key, long val) {
        SharedPreferences prefs = getSharedPreferences(key);
        SharedPreferences.Editor e = prefs.edit();
        e.putLong(key, val);
        e.apply();
    }

    private boolean getBoolean(String key, boolean def) {
        SharedPreferences prefs = getSharedPreferences(key);
        boolean b = prefs.getBoolean(key, def);
        return b;
    }

    private void setBoolean(String key, boolean val) {
        SharedPreferences prefs = getSharedPreferences(key);
        SharedPreferences.Editor e = prefs.edit();
        e.putBoolean(key, val);
        e.apply();
    }

    public boolean isLoggedInUser() {
        String json = getString(LOGGED_IN_USER, null);
        return json != null;
    }

    public void logOutUser() {
        SharedPreferences prefs = getSharedPreferences(LOGGED_IN_USER);
        SharedPreferences.Editor e = prefs.edit();
        e.clear();
        e.apply();
    }

    /*public LoginResponse getLoggedInUser() {
        String json = getString(LOGGED_IN_USER, null);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return new Gson().fromJson(json, LoginResponse.class);
    }

    public void setLoggedInUser(LoginResponse user) {
        setString(LOGGED_IN_USER, new Gson().toJson(user));
    }*/

    private Set<String> getStringSet(String key, Set<String> def) {
        SharedPreferences prefs = getSharedPreferences(key);
        return prefs.getStringSet(key, def);
    }

    public void setStringSet(String key, Set<String> val) {
        SharedPreferences prefs = getSharedPreferences(key);
        SharedPreferences.Editor e = prefs.edit();
        e.putStringSet(key, val);
        e.apply();
    }

    public String getToken() {
        return getString(TOKEN, null);
    }

    public void setToken(String token) {
        setString(TOKEN, token);
    }

    public boolean isClient() {
        return getBoolean(IS_CLIENT, false);
    }

    public void setClient(boolean isClient) {
        setBoolean(IS_CLIENT, isClient);
    }

    public String getLat() {
        return getString(LAT, null);
    }

    public void setLat(String lat) {
        setString(LAT, lat);
    }

    public String getLng() {
        return getString(LNG, null);
    }

    public void setLng(String lng) {
        setString(LNG, lng);
    }

    public String getIp() {
        return getString(IP, null);
    }

    public void setIp(String ip) {
        setString(IP, ip);
    }
}
