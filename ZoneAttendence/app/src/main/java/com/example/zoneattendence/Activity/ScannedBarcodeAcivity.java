package com.example.zoneattendence.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zoneattendence.DbHelper;
import com.example.zoneattendence.R;
import com.example.zoneattendence.utils.Utils;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import info.androidhive.barcode.BarcodeReader;

import static com.example.zoneattendence.utils.Utils.getStringFromLocalDB;


public class ScannedBarcodeAcivity extends AppCompatActivity implements ServiceConnection, BarcodeReader.BarcodeReaderListener {

    private static final String TAG = ScannedBarcodeAcivity.class.getSimpleName();
    private static final int REQUESTPERMISSIONS = 100;
    DbHelper dbHelper;
    SQLiteDatabase database;
    public static final String FID_PACKAGE_NAME = "com.famoco.famocoid";

    public static final String FID_SERVICE_NAME = "com.famoco.famocoid.FamocoIdService";
    private Intent mIntent;
    private BarcodeReader barcodeReader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_barcode_acivity);
        Utils.context = this;
        dbHelper = new DbHelper();
        database = dbHelper.getWritableDatabase();
        mIntent = new Intent();
        mIntent.setClassName(FID_PACKAGE_NAME, FID_SERVICE_NAME);

        // get the barcode reader instance
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);

        /*if (!dbHelper.checkAdminRecordsInDb(database)) {
            Intent intent = new Intent(ScannedBarcodeAcivity.this, MenuActivity.class);
            startActivity(intent);
            //finish();
        }*/
        if (Utils.DEVICEID.equals("")) {
            Utils.DEVICEID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            Log.e("deviceid", Utils.DEVICEID);
        }

        Runnable runnable = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void run() {
                Log.e(TAG, "run: Timer Started");
                if (Utils.isConnected()) {
                    dbHelper.syncBarcodeMasterToServer(database, 0, 0);
                }
            }
        };

       ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        if (!service.isTerminated())
            service.scheduleAtFixedRate(runnable, 1, 60, TimeUnit.SECONDS);
    }

    @Override
    public void onScanned(Barcode barcode) {
        // playing barcode reader beep sound
        barcodeReader.playBeep();
        if (barcode == null || barcode.displayValue == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Log.e(TAG, "onActivityResult: BarCode====>" + barcode.displayValue);
            if (!checkAdminBarcode(barcode.displayValue))
                checkBarCodeInDB(barcode.displayValue);
        }

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        Toast.makeText(getApplicationContext(), "Error occurred while scanning " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraPermissionDenied() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUESTPERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults.length > 0) {

                    barcodeReader.resumeScanning();
                } else {
                    Toast.makeText(this, "To Operate the Application the permissions are required. Kindly restart App", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindToFamocoDevice();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean checkAdminBarcode(String scannedBarCode) {
        dbHelper = new DbHelper();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
//        Log.e(TAG, "checkAdminBarcode: quesry "+"SELECT barcodeID FROM " + DbHelper.MXADMINBARCODE + " WHERE barcodeNumber = '" + scannedBarCode );
        Cursor cursor = database.rawQuery("SELECT barcodeID FROM " + DbHelper.MXADMINBARCODE + " WHERE barcodeNumber = '" + scannedBarCode + "'", null);
        if (cursor.getCount() > 0) {
            Intent intent = new Intent(ScannedBarcodeAcivity.this, MenuActivity.class);
            intent.putExtra("fromActivity", "Main");
            startActivity(intent);
            finish();
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }


    private void checkBarCodeInDB(String barcodeNumber) {

        dbHelper = new DbHelper();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Intent intent = null;
//        Log.e(TAG, "checkBarCodeInDB: "+"SELECT * FROM " + DbHelper.MXBARCODE + " WHERE barcodeNumber = " + '"' + barcodeNumber + '"' );
        Cursor cursor = database.rawQuery("SELECT * FROM " + DbHelper.MXBARCODE + " WHERE barcodeNumber = " + '"' + barcodeNumber + '"', null);
        if (cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {

                if (cursor.getInt(cursor.getColumnIndex("attStatus")) == 0) {
                    Log.e(TAG, "checkBarCodeInDB: " + cursor.getInt(cursor.getColumnIndex("attStatus")));
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("barcodeID", cursor.getString(cursor.getColumnIndex("barcodeID")));
                    contentValues.put("barcodeNumber", barcodeNumber);
                    contentValues.put("deviceNo", Utils.DEVICEID);
                    contentValues.put("attStatus", 1);
                    contentValues.put("zoneID", getStringFromLocalDB("zoneID"));
                    long id = database.insert(DbHelper.MXATTENDANCE, null, contentValues);
                    database.execSQL("UPDATE " + DbHelper.MXBARCODE + " SET attStatus = 1 WHERE barcodeNumber = " + '"' + barcodeNumber + '"');
                    if (id > 0) {
                        Log.e(TAG, "checkBarCodeInDB: Data inserted");

                    } else {
                        Log.v("ERROR INSERT:", "CANNOT INSERT MXBARCODE");
                    }
                    intent = new Intent(ScannedBarcodeAcivity.this, InformationActivity.class);
                    intent.putExtra("attCount", "1"); // attendance count
                    intent.putExtra("scanStatus", "GREEN");
                    intent.putExtra("customerName", cursor.getString(cursor.getColumnIndex("customerName")));

                } else if (cursor.getInt(cursor.getColumnIndex("attStatus")) == 1) {
                    Log.e(TAG, "checkBarCodeInDB: " + cursor.getInt(cursor.getColumnIndex("attStatus")));
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("barcodeID", cursor.getString(cursor.getColumnIndex("barcodeID")));
                    contentValues.put("barcodeNumber", barcodeNumber);
                    contentValues.put("deviceNo", Utils.DEVICEID);
                    contentValues.put("attStatus", 0);
                    contentValues.put("zoneID", getStringFromLocalDB("zoneID"));
                    long id = database.insert(DbHelper.MXATTENDANCE, null, contentValues);
                    if (id > 0) {

                    } else {
                        Log.v("ERROR INSERT:", "CANNOT INSERT MXBARCODE");
                    }
                    intent = new Intent(ScannedBarcodeAcivity.this, InformationActivity.class);
                    intent.putExtra("attCount", String.valueOf(dbHelper.getAttendanceCount(database, barcodeNumber)));  // attendance count
                    intent.putExtra("scanStatus", "ORANGE");
                    intent.putExtra("customerName", cursor.getString(cursor.getColumnIndex("customerName")));
                } else {

                    intent = new Intent(ScannedBarcodeAcivity.this, InformationActivity.class);
                    intent.putExtra("scanStatus", "RED");
                }
            }


        } else {
            intent = new Intent(ScannedBarcodeAcivity.this, InformationActivity.class);
            intent.putExtra("scanStatus", "RED");

        }
        cursor.close();
        startActivity(intent);
        finish();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        //Log.e(TAG, "onServiceConnected");
        //Toast.makeText(this, "onServiceConnected", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onServiceConnected");
        FamocoDevice famocoDevice = null;
        try {
            famocoDevice = IRemoteInterface.Stub.asInterface(service).getFamocoDevice();
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        if (famocoDevice != null) {
            String famocoId = famocoDevice.getFamocoId();
            if (famocoId.contains(" ")) {
                String strArr[] = famocoId.split(" ");
                famocoId = strArr[0];
            }
            Utils.DEVICEID = famocoDevice.getFamocoId();
            //Toast.makeText(this, "onServiceConnected ==> " + Utils.DEVICEID, Toast.LENGTH_SHORT).show();
            Log.e("FAMOCO DEVICEID:", famocoId);
        }
    }



    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private void bindToFamocoDevice() {
        try {
            bindService(mIntent, this, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

}
