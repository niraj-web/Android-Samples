package com.example.combineassignment.utils;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.combineassignment.preferences.Preferences;


public class App extends Application {
    private Preferences preferences;
    private ApiRequestHelper apiRequestHelper;
    private ApiRequestHelper1 apiRequestHelper1;

    @Override
    public void onCreate() {
        super.onCreate();
        doInit();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private void doInit() {
        this.preferences = new Preferences(this);
        this.apiRequestHelper = ApiRequestHelper.init(this);
        this.apiRequestHelper1 = ApiRequestHelper1.init(this);
    }

    public synchronized ApiRequestHelper getApiRequestHelper() {
        return apiRequestHelper;
    }

    public synchronized ApiRequestHelper1 getApiRequestHelper1()
    {
        return apiRequestHelper1;
    }

    public synchronized Preferences getPreferences() {
        return preferences;
    }
}
