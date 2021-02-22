package com.example.onesignal1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    private static final String ONESIGNAL_APP_ID = ab505894-32b7-44b0-807c-7bc4b5b9ee3e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}