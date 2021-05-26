package com.example.zoneattendence.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.zoneattendence.DbHelper;
import com.example.zoneattendence.R;
import com.example.zoneattendence.ScannedActivity;
import com.example.zoneattendence.SecondA;
import com.example.zoneattendence.mxCallService;
import com.example.zoneattendence.utils.Utils;

import java.util.HashMap;

import static com.example.zoneattendence.utils.Utils.mxAlert;

public class    MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button update,synData,deviceReport,reset,scanning,btnLoadAdminBarcodes,btnLoadBarcodes;
    public static TextView tvEventTitle,tvZoneTitle;

    private static final String TAG = MenuActivity.class.getSimpleName();
    DbHelper dbHelper;
    private static final int REQUESTPERMISSIONS = 100;

    private Dialog dialog;
    private ProgressDialog proDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        update = (Button) findViewById(R.id.btnUpdate);
        synData = (Button) findViewById(R.id.btnSyncData);
        deviceReport = (Button) findViewById(R.id.btnDeviceReport);
        reset = (Button) findViewById(R.id.btnReset);
        scanning = (Button) findViewById(R.id.btnStartScanning);
        btnLoadAdminBarcodes = (Button) findViewById(R.id.btnLoadAdminBarcodes);
        btnLoadBarcodes = (Button) findViewById(R.id.btnLoadBarcodes);
        tvEventTitle = (TextView) findViewById(R.id.tvEventTitle);
        tvZoneTitle = (TextView) findViewById(R.id.tvZoneTitle);
        CheckBox chkManual = (CheckBox) findViewById(R.id.chkManual);

        synData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SyncActivity.class));
            }
        });

        deviceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, DeviceReportActivity.class));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(MenuActivity.this);
                dialog.setContentView(R.layout.update);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                dialog.show();

                TextView cancel = (TextView) dialog.findViewById(R.id.tvUpdateCancel);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MenuActivity.this);
                dialog.setContentView(R.layout.reset);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                dialog.show();

                TextView cancel = (TextView) dialog.findViewById(R.id.tvResetCancel);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        scanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MenuActivity.this);
                dialog.setContentView(R.layout.scan);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                dialog.show();

                TextView allow = (TextView) dialog.findViewById(R.id.tvScanAllow);
                TextView deny = (TextView) dialog.findViewById(R.id.tvScanDeny);

                allow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MenuActivity.this, ScannedBarcodeAcivity.class));
                    }
                });
                deny.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        btnLoadAdminBarcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, FirstA.class));
            }
        });

        btnLoadBarcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, SecondA.class));

            }
        });

        Utils.context = this;

        dbHelper = new DbHelper();
        if (Utils.MANUALSCAN)
            chkManual.setChecked(true);
        else
            chkManual.setChecked(false);

        chkManual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Utils.MANUALSCAN = true;
                } else {
                    Utils.MANUALSCAN = false;
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void mxConfirmAlert(String message, String isUpdateOrClear) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuActivity.this);
        alertDialog.setMessage(message);
        final AlertDialog dialog = alertDialog.create();

        if (isUpdateOrClear.equals("clear")) {
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    database.execSQL("DELETE FROM " + DbHelper.MXBARCODE);
                    database.execSQL("DELETE FROM " + DbHelper.MXATTENDANCE);

                    mxAlert("All the barcodes and attendance are deleted from device");

                }
            });
        }
        if (isUpdateOrClear.equals("update")) {
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
//                    dbHelper.clearBarcodes(database);

                    database.execSQL("DELETE FROM " + DbHelper.MXBARCODE);//+" WHERE attStatus = '0'");


                    HashMap<String, String> getBarcodeList = new HashMap<>();
                    getBarcodeList.put("xAction", "getBarcodeList");
                    getBarcodeList.put("deviceNo", Utils.DEVICEID);

                    getBarcodeList.put("maxBarcodeID", "0");

                    if (proDialog != null && proDialog.isShowing()) {
                        proDialog.setMessage("0" + " : Please Wait...");
                    }

                    new mxCallService(getBarcodeList, "0" + " : Please Wait...", 1).execute();

                }
            });
        }

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        dialog.show();
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setTextSize(25);
        textView.setPadding(50, 50, 50, 50);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        /*final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    timer.cancel();
                }
            }
        }, 2000);*/
    }

    public static void updateEventName() {
        try {
            tvZoneTitle.setText(Utils.getStringFromLocalDB("zoneTitle")); ////
            tvEventTitle.setText(Utils.getStringFromLocalDB("eventTitle")); ////
        } catch (Exception e) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateEventName();
        Utils.context = this;
        if (Utils.DEVICEID.equals("")) {
            Utils.DEVICEID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            Log.e("deviceid", Utils.DEVICEID);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnReset) {
            if (Utils.isConnected()) {
                //Utils.showProgress("Please wait...");
                mxConfirmAlert("Are you sure you want to delete all the barcodes and attendance from device? \n (Please sync data before deletion)", "clear");

            } else {
                mxAlert("Please Check Your Internet Connection");
            }

        } else if (v.getId() == R.id.btnUpdate) {
            if (Utils.isConnected()) {
                //Utils.showProgress("Please wait...");
                mxConfirmAlert("Are you sure you want to update the barcodes?", "update");

            } else {
                mxAlert("Please Check Your Internet Connection");
            }

        } else if (v.getId() == R.id.btnLoadBarcodes) {
            if (Utils.isConnected()) {
                //Utils.showProgress("Please wait...");
                getBarCodeData();
            } else {
                mxAlert("Please Check Your Internet Connection");
            }

        } else if (v.getId() == R.id.btnSyncData) {

            Intent intent = new Intent(MenuActivity.this, SyncActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.btnLoadAdminBarcodes) {

            if (Utils.isConnected()) {
                //Utils.showProgress("Please wait...");
                getAdminBarCodeData();
            } else {
                mxAlert("Please Check Your Internet Connection");
            }

        } else if (v.getId() == R.id.btnStartScanning) {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            if (dbHelper.checkAdminRecordsInDb(database)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(MenuActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MenuActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSIONS);
                        Log.e(TAG, "surfaceCreated: ");
                       /* Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();*/

                    } else {
                        Log.e(TAG, "camera opened: ");
                        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }

            } else {
                mxAlert("Please Load Admin Barcodes");
            }
        } else {
            Intent intent = new Intent(MenuActivity.this, DeviceReportActivity.class);
            startActivity(intent);
        }
    }

    private void getAdminBarCodeData() {
        HashMap<String, String> getAdminBarcodeList = new HashMap<>();
        getAdminBarcodeList.put("xAction", "getAdminBarcodeList");
        getAdminBarcodeList.put("deviceNo", Utils.DEVICEID);

        new mxCallService(getAdminBarcodeList, "Please Wait...", 1).execute();
    }

    public void getBarCodeData() {
        HashMap<String, String> getBarcodeList = new HashMap<>();
        getBarcodeList.put("xAction", "getBarcodeList");
        getBarcodeList.put("deviceNo", Utils.DEVICEID);

        int maxBarcodeID = getMaxId();
        Log.e(TAG, "onClick: " + maxBarcodeID);
        getBarcodeList.put("maxBarcodeID", String.valueOf(maxBarcodeID));

        if (proDialog != null && proDialog.isShowing()) {
            proDialog.setMessage(maxBarcodeID + " : Please Wait...");
        }

        new mxCallService(getBarcodeList, maxBarcodeID + " : Please Wait...", 1).execute();
    }

    private int getMaxId() {
        int maxID = 0;
        dbHelper = new DbHelper();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(barcodeID) FROM " + DbHelper.MXBARCODE, null);
        if (cursor.moveToFirst()) {
            maxID = cursor.getInt(0);
        }
        cursor.close();
        return maxID;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}