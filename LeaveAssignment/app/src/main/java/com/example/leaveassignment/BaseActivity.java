package com.example.leaveassignment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.leaveassignment.utils.App;
import com.example.leaveassignment.utils.ConnectionDetector;

public abstract class BaseActivity extends AppCompatActivity {
    private final static String TAG = "BaseActivity";

    public ConnectionDetector cd;
    public App app;
    public Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getActivityLayout());
        mContext = com.example.leaveassignment.BaseActivity.this;
        app = (App) getApplication();
        cd = new ConnectionDetector(mContext);
    }

    protected abstract int getActivityLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

