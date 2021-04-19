package com.example.hotelbookingassignment.utils;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.hotelbookingassignment.preferences.Preferences;

public class App extends Application {
    private Preferences preferences;
    private ApiRequestHelper apiRequestHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        doInit();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }

    private void doInit() {
        this.preferences = new Preferences(this);
        this.apiRequestHelper = ApiRequestHelper.init(this);
    }

    public synchronized ApiRequestHelper getApiRequestHelper() {
        return apiRequestHelper;
    }

    public synchronized Preferences getPreferences() {
        return preferences;
    }
}
