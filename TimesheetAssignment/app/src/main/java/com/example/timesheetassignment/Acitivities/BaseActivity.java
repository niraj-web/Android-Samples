package com.example.timesheetassignment.Acitivities;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.timesheetassignment.utils.App;
import com.example.timesheetassignment.utils.ConnectionDetector;


public abstract class BaseActivity extends AppCompatActivity {
    private final static String TAG = "BaseActivity";

    public ConnectionDetector cd;
    public App app;
    public Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getActivityLayout());
        mContext = com.example.timesheetassignment.Acitivities.BaseActivity.this;
        app = (App) getApplication();
        cd = new ConnectionDetector(mContext);
    }

    protected abstract int getActivityLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
