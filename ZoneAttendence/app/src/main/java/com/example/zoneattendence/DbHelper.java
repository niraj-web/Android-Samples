package com.example.zoneattendence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.zoneattendence.Activity.MenuActivity;
import com.example.zoneattendence.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.example.zoneattendence.utils.Utils.SYNCTYPE;
import static com.example.zoneattendence.utils.Utils.getStringFromLocalDB;
import static com.example.zoneattendence.utils.Utils.saveToLocalDB;

/**
 * Created by mxmacmini on 29/05/18.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "barcodes";
    private static final int DBVERSION = 1;
    public static final String MXBARCODE = "mx_barcode";
    public static final String MXATTENDANCE = "mx_attendance";
    public static final String MXADMINBARCODE = "mx_admin_barcode";

    public DbHelper() {
        super(Utils.context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE " + MXBARCODE + " (\n" +
                "  `barcodeID` INTEGER PRIMARY KEY ,\n" +
                "  `barcodeNumber` varchar(50),\n" +
                "  `customerName` varchar(50),\n" +
                "  `syncStatus` TINYINT NOT NULL DEFAULT 0,\n" +
                "  `attStatus` TINYINT NOT NULL DEFAULT 0);";

        String sql2 = "CREATE TABLE " + MXATTENDANCE + " (\n" +
                "  `attID` INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  `barcodeID` varchar(50),\n" +
                "  `eventID` varchar(50),\n" +
                "  `barcodeNumber` varchar(50),\n" +
                "  `deviceNo` varchar(50),\n" +
                "  `zoneID` varchar(50),\n" +
                "  `syncStatus` TINYINT NOT NULL DEFAULT 0,\n" +
                "  `attendanceDateTime` DATETIME DEFAULT (datetime('now','localtime')),\n" +
                "  `attStatus` TINYINT NOT NULL DEFAULT 0);";

        String sql3 = "CREATE TABLE " + MXADMINBARCODE + " (\n" +
                "  `barcodeID` INTEGER PRIMARY KEY ,\n" +
                "  `barcodeNumber` varchar(50),\n" +
                "  `customerName` varchar(50),\n" +
                "  `attStatus` TINYINT NOT NULL DEFAULT 0);";

        createTable(sql1, db);
        createTable(sql2, db);
        createTable(sql3, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + MXBARCODE + ";");
        } catch (Exception e) {
            Log.e("SQL ERR:", e.getMessage());
        }

    }

    private void createTable(String sql, SQLiteDatabase db) {
        try {
            Log.e("SQL QUERY:", sql);
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.e("SQL ERR:", e.getMessage());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void updateBarcodeData(SQLiteDatabase database, JSONObject jsonObject) throws JSONException {

        saveToLocalDB("zoneID", jsonObject.getString("zoneID"));
        saveToLocalDB("eventID", jsonObject.getString("eventID"));
        saveToLocalDB("zoneTitle", jsonObject.getString("zoneTitle"));
        saveToLocalDB("eventTitle", jsonObject.getString("eventName"));

        if (MenuActivity.tvEventTitle != null) {
            MenuActivity.updateEventName();
        }
        JSONArray data = jsonObject.getJSONArray("barcode_list");
        database.beginTransaction();
        for (int i = 0; i < data.length(); i++) {
            JSONObject c = data.getJSONObject(i);

            String barcodeID = c.getString("barcodeID");
            String barcodeNumber = c.getString("barcodeNumber");
            String customerName = c.getString("customerName");
            String attStatus = c.getString("attStatus");

            ContentValues values = new ContentValues();
            values.put("barcodeID", barcodeID);
            values.put("barcodeNumber", barcodeNumber);
            values.put("customerName", customerName);
            values.put("attStatus", attStatus);

            long id = database.insert(MXBARCODE, null, values);

            if (id < 0) {
                Log.v("ERROR INSERT:", "CANNOT INSERT MXBARCODE");
            } else {

            }
        }
        database.setTransactionSuccessful();
        database.endTransaction();

        Cursor cursor1 = database.rawQuery("SELECT * FROM " + DbHelper.MXBARCODE, null);
        Log.e("DBHELPER", "MXBARCODE COUNT: " + cursor1.getCount());

        if (Utils.isConnected()) {
            if (cursor1.getCount() < jsonObject.getInt("totalRecords") || jsonObject.getInt("count") > 0) {
                if (jsonObject.getInt("count") > 0) {
                    new MenuActivity().getBarCodeData();
                } else if (cursor1.getCount() == jsonObject.getInt("totalRecords")) {
                    Utils.mxAlert(jsonObject.getString("msg"));
                } else {
                    Utils.mxAlert("Internet Connection Error. Please try again...");
                }
            }
        } else {
            Utils.hideProgress();
            Utils.mxAlert("Internet Connection Error. Please try again...");
        }
        cursor1.close();
    }

    public void updateAdminBarcodeData(SQLiteDatabase database, JSONObject jsonObject) throws JSONException {
        database.execSQL("DELETE FROM " + MXADMINBARCODE);
        JSONArray data = jsonObject.getJSONArray("admin_barcode_list");
        for (int i = 0; i < data.length(); i++) {
            JSONObject c = data.getJSONObject(i);

            String barcodeID = c.getString("barcodeID");
            String barcodeNumber = c.getString("barcodeNumber");
            String customerName = c.getString("customerName");
            String attStatus = c.getString("attStatus");

            ContentValues values = new ContentValues();
            values.put("barcodeID", barcodeID);
            values.put("barcodeNumber", barcodeNumber);
            values.put("customerName", customerName);
            values.put("attStatus", attStatus);

            long id = database.insert(MXADMINBARCODE, null, values);
            if (id < 0) {
                Log.v("ERROR INSERT:", "CANNOT INSERT MXADMINBARCODE");
            } else {
                // Log.v("INSERT SETTING ID:", String.valueOf(id));
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void syncAttendanceToServer(SQLiteDatabase db, int cnt, int syncType) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + MXATTENDANCE + " WHERE syncStatus=" + SYNCTYPE + " ORDER BY attID ASC LIMIT 20", null);
        if (cursor.moveToFirst()) {

            String sepM = "";
            String strMaster = "";
            do {
                String attID = cursor.getString(cursor.getColumnIndex("attID"));
                strMaster += sepM + "oAttID=" + attID + "~~" +
                        "barcodeID=" + cursor.getString(cursor.getColumnIndex("barcodeID")) + "~~" +
                        "syncStatus=" + cursor.getString(cursor.getColumnIndex("syncStatus")) + "~~" +
                        "attendanceDateTime=" + cursor.getString(cursor.getColumnIndex("attendanceDateTime")) + "~~" +
                        "zoneID=" + getStringFromLocalDB("zoneID") + "~~" +
                        "eventID=" + getStringFromLocalDB("eventID") + "~~" +  //////
                        "attStatus=" + cursor.getString(cursor.getColumnIndex("attStatus"));

                sepM = "~!~";
            } while (cursor.moveToNext());

            HashMap<String, String> params = new HashMap<>();
            params.put("xAction", "syncBarcodeAttToServer");
            //   params.put("zoneID", getStringFromLocalDB("zoneID")); ////// added by Hari 17 dec
            params.put("data", strMaster);
            params.put("deviceNo", Utils.DEVICEID);

            Log.e("zone", "syncAttendanceToServer: " + params);
            mxCallService sr = new mxCallService(params, "Please Wait...", syncType);
            sr.execute();

        } else {
            Utils.hideProgress();
            if (syncType == 1) {
                if (cnt == 0) {
                    if (SYNCTYPE == 1)
                        Utils.mxAlert("Sorry! No data to ReSync...");
                    else
                        Utils.mxAlert("Sorry! No data to Sync...");
                } else {
                    Utils.mxAlert("Data Sync Successful...");
                }
            }


        }
        cursor.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void syncBarcodeMasterToServer(SQLiteDatabase db, int cnt, int syncType) {
        Cursor cursor = db.rawQuery("SELECT barcodeID,syncStatus,attStatus FROM " + MXBARCODE + " WHERE syncStatus= 0 AND attStatus= 1 ORDER BY barcodeID ASC LIMIT 20", null);
        String sepM = "";
        String strMaster = "";
        if (cursor.moveToFirst()) {

            do {
                strMaster += sepM +
                        "barcodeID=" + cursor.getString(cursor.getColumnIndex("barcodeID")) + "~~" +
                        "syncStatus=" + cursor.getString(cursor.getColumnIndex("syncStatus")) + "~~" +
                        "attStatus=" + cursor.getString(cursor.getColumnIndex("attStatus"));

                sepM = "~!~";
            } while (cursor.moveToNext());

        } else {
            Utils.hideProgress();
            if (syncType == 1) {
                if (cnt == 0) {
                    Utils.mxAlert("Sorry! No data to sync...");
                } else {
                    Utils.mxAlert("Data sync successful...");
                }
            }
        }

        cursor.close();
        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "syncBarcodeMasterToServer");
        params.put("data", strMaster);
        params.put("deviceNo", Utils.DEVICEID);
        params.put("lastSyncDate", Utils.LASTSYNCDATETIME == null ? "" : Utils.LASTSYNCDATETIME); // added on 14 may 19  by hari
        //Log.e("LASTSYNCDATETIME ", "dbhelper: "+Utils.LASTSYNCDATETIME );

        mxCallService sr = new mxCallService(params, "Please Wait...", syncType);
        sr.execute();
    }

    public boolean checkAdminRecordsInDb(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + MXADMINBARCODE, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean checkAttendanceInDb(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + MXATTENDANCE, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        return false;
    }

    public int getAttendanceCount(SQLiteDatabase database, String barcodeNumber) {
        //  Log.e("att", "getAttendanceCount: "+"SELECT barcodeNumber FROM " + DbHelper.MXATTENDANCE + " WHERE barcodeNumber=" + barcodeNumber );
        Cursor cursor = database.rawQuery("SELECT barcodeNumber FROM " + DbHelper.MXATTENDANCE + " WHERE barcodeNumber='" + barcodeNumber + "'", null);
        if (cursor.getCount() > 0) {
            cursor.close();
            return cursor.getCount();
        }
        return 0;

    }

}
