package com.example.zoneattendence.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.zoneattendence.DbHelper;
import com.example.zoneattendence.R;
import com.example.zoneattendence.utils.Utils;

public class SyncActivity extends AppCompatActivity {
    public CheckBox syncallowed;
    public static TextView syncedRec;
    static TextView unsyncedRec;
    Button btnSync;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        getSupportActionBar().setTitle("SYNC SERVER");

        TextView totRec = findViewById(R.id.totRec);
        totRec.setText("" + getBarCodeCount());
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SYNC SERVER");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        syncedRec = findViewById(R.id.syncedRec);

        unsyncedRec = findViewById(R.id.unsyncedRec);
        syncallowed = findViewById(R.id.reSync);
        btnSync = findViewById(R.id.btnSync);
        btnSync.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                if (Utils.isConnected()) {
                    DbHelper mxDb = new DbHelper();
                    SQLiteDatabase db = mxDb.getWritableDatabase();
                    mxDb.syncAttendanceToServer(db, 0,1);
                } else {
                    Utils.mxAlert("Please Check Your Internet Connection");
                }
            }
        });
        Utils.context = this;
        setSyncCount();
        syncallowed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    Utils.SYNCTYPE = 1;
                } else {
                    Utils.SYNCTYPE = 0;
                }
                setSyncCount();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSyncCount();
    }

    public static int getBarCodeCount() {
        DbHelper mxDb = new DbHelper();
        SQLiteDatabase db = mxDb.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT attID FROM " + DbHelper.MXATTENDANCE, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public static void setSyncCount() {
        syncedRec.setText("" + getSyncCount());
        unsyncedRec.setText("" + getUnSyncCount());
    }

    public static int getSyncCount() {

        int sType = 1;
        if (Utils.SYNCTYPE == 1) {
            sType = 0;
        }
        DbHelper mxDb = new DbHelper();
        SQLiteDatabase db = mxDb.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT attID FROM " + DbHelper.MXATTENDANCE + " WHERE syncStatus=" + sType, null);
        int cnt = cursor.getCount();
        Log.e("getSyncCount:", String.valueOf(cnt));
        cursor.close();
        return cnt;
    }

    public static int getUnSyncCount() {

        int sType = 0;
        if (Utils.SYNCTYPE == 1) {
            sType = 1;
        }

        DbHelper mxDb = new DbHelper();
        SQLiteDatabase db = mxDb.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT attID FROM " + DbHelper.MXATTENDANCE + " WHERE syncStatus=" + sType, null);
        int cnt = cursor.getCount();
        Log.e("getUnSyncCount:", String.valueOf(cnt));
        cursor.close();
        return cnt;
    }
}