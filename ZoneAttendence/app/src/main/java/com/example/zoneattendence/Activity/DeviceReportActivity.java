package com.example.zoneattendence.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.zoneattendence.DbHelper;
import com.example.zoneattendence.R;

public class DeviceReportActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtTotalPresent,txtTotalAbsent,txtTotalScanned,txtTotalBarcodes,txtDeviceAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_report);
        getSupportActionBar().setTitle("DEVICE REPORT");

        txtTotalPresent = findViewById(R.id.txtTotalPresent);
        txtTotalAbsent = findViewById(R.id.txtTotalAbsent);
        txtTotalScanned = findViewById(R.id.txtTotalScanned);
        txtTotalBarcodes = findViewById(R.id.txtTotalBarcodes);
        txtDeviceAttendance = findViewById(R.id.txtDeviceAttendance);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecordCount();
    }

    public void setRecordCount() {
        txtTotalPresent.setText("" + getPresentCount());
        txtTotalAbsent.setText("" + getAbsentCount());
        txtTotalScanned.setText("" + getTotalScannedCount());
        txtTotalBarcodes.setText("" + getTotalBarcodesCount());
        txtDeviceAttendance.setText("" + getDeviceScannedCount());
    }

    public static int getPresentCount() {

        DbHelper mxDb = new DbHelper();
        SQLiteDatabase db = mxDb.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT(barcodeID) FROM " + DbHelper.MXBARCODE + " WHERE attStatus='1'", null);
        int cnt = cursor.getCount();
        Log.e("getPresentCount:", String.valueOf(cnt));
        cursor.close();
        db.close();
        return cnt;
    }

    public static int getDeviceScannedCount() {

        DbHelper mxDb = new DbHelper();
        SQLiteDatabase db = mxDb.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT(barcodeID) FROM " + DbHelper.MXATTENDANCE + " WHERE attStatus='1'", null);
        int cnt = cursor.getCount();
        Log.e("getDeviceScannedCount:", String.valueOf(cnt));
        cursor.close();
        db.close();
        return cnt;
    }

    public static int getTotalBarcodesCount() {

        DbHelper mxDb = new DbHelper();
        SQLiteDatabase db = mxDb.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT(barcodeID) FROM " + DbHelper.MXBARCODE , null);
        int cnt = cursor.getCount();
        Log.e("getTotalBarcodesCount:", String.valueOf(cnt));
        cursor.close();
        db.close();
        return cnt;
    }

    public static int getAbsentCount() {

        DbHelper mxDb = new DbHelper();
        SQLiteDatabase db = mxDb.getWritableDatabase();
        int absentCnt = 0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.MXBARCODE , null);
        int cnt = cursor.getCount();
        int presentCnt = getPresentCount();
        absentCnt = cnt - presentCnt;
        Log.e("getSyncCount:", String.valueOf(absentCnt));
        cursor.close();
        db.close();
        return absentCnt;
    }

    public static int getTotalScannedCount() {

        DbHelper mxDb = new DbHelper();
        SQLiteDatabase db = mxDb.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.MXATTENDANCE , null);
        int cnt = cursor.getCount();
        Log.e("getTotalScannedCount:", String.valueOf(cnt));
        cursor.close();
        db.close();
        return cnt;
    }
}