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
import android.widget.Toast;

import com.example.zoneattendence.DbHelper;
import com.example.zoneattendence.Model.AdminBarcodeModel;
import com.example.zoneattendence.Model.LoadBarcodeModel;
import com.example.zoneattendence.Model.UpdateDeviceModel;
import com.example.zoneattendence.R;
import com.example.zoneattendence.utils.ApiRequestHelper;
import com.example.zoneattendence.utils.Utils;

import java.util.HashMap;

import static com.example.zoneattendence.utils.Utils.context;

public class  MenuActivity extends BaseActivity {

    Button update, synData, deviceReport, reset, scanning, btnLoadAdminBarcodes, btnLoadBarcodes;
    public static TextView tvEventTitle, tvZoneTitle;

    private static final String TAG = MenuActivity.class.getSimpleName();
    DbHelper dbHelper;
    private static final int REQUESTPERMISSIONS = 100;

    private Dialog dialog;
    private ProgressDialog proDialog;
    private AdminBarcodeModel adminBarcodeModel;
    private LoadBarcodeModel loadBarcodeModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setTitle("Menu");

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

                SQLiteDatabase database = dbHelper.getWritableDatabase();
                if (dbHelper.checkAdminRecordsInDb(database)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ActivityCompat.checkSelfPermission(MenuActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MenuActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSIONS);
                            Log.e(TAG, "surfaceCreated: ");

                            Intent intent = new Intent(MenuActivity.this, ScannedBarcodeAcivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        } else {
                            Log.e(TAG, "camera opened: ");
                            Intent intent = new Intent(MenuActivity.this, ScannedBarcodeAcivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Intent intent = new Intent(MenuActivity.this, ScannedBarcodeAcivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    Toast.makeText(context, "Please Load Admin Barcodes", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLoadAdminBarcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAdminBarCodeData();
                Toast.makeText(context, "Admin Barcodes Loaded Successfully", Toast.LENGTH_LONG).show();

            }
        });

        btnLoadBarcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBarCodeData();
                Toast.makeText(context, "Barcodes Loaded", Toast.LENGTH_LONG).show();

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

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_menu;
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

    private void getAdminBarCodeData() {
        ProgressDialog dialog = new ProgressDialog(MenuActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "getAdminBarcodeList");
        params.put("deviceNo", Utils.DEVICEID);

        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign2(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    adminBarcodeModel = (AdminBarcodeModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (adminBarcodeModel != null) {
                        if (adminBarcodeModel.getCount() != null && adminBarcodeModel.getAdminBarcodeList() != null) {

                        } else {

                        }
                    } else {
                        Utils.showLongToast(MenuActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }

                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(MenuActivity.this, apiResponse);
                }
            });
        } else {
           // Utils.alert_dialog(MenuActivity.this);
        }
    }

    public void getBarCodeData() {

        ProgressDialog dialog = new ProgressDialog(MenuActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "getBarcodeList");
        params.put("deviceNo", Utils.DEVICEID);

        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign3(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    loadBarcodeModel = (LoadBarcodeModel) object;
                    if (loadBarcodeModel != null) {
                        if (loadBarcodeModel.getCount() != null && loadBarcodeModel.getMsg() != null) {

                        } else {

                        }
                    } else {
                        Utils.showLongToast(MenuActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(MenuActivity.this, apiResponse);
                }
            });
        } else {
          //  Utils.alert_dialog(MenuActivity.this);
        }

        int maxBarcodeID = getMaxId();
        Log.e(TAG, "onClick: " + maxBarcodeID);
        params.put("maxBarcodeID", String.valueOf(maxBarcodeID));

        if (proDialog != null && proDialog.isShowing()) {
            proDialog.setMessage(maxBarcodeID + " : Please Wait...");
        }
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