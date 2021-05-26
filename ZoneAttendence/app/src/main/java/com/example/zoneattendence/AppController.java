package com.example.zoneattendence;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by mxmacmini on 29/05/18.
 */

public class AppController extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
