package com.example.zoneattendence;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.zoneattendence.Activity.SyncActivity;
import com.example.zoneattendence.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by mustafa on 9/5/16.
 */

public class mxCallService extends AsyncTask<Void, Void, Void> {
    DbHelper mxDb;
    String jsonStr;
    int syncType;

    HashMap<String, String> params = new HashMap<>();
    String xAction, message;

    public mxCallService(HashMap<String, String> params, String message, int syncType) {
        this.params = params;
        this.xAction = params.get("xAction");
        this.message = message;
        this.syncType = syncType;
    }

    public String makeWebServiceCall() {
        URL url;
        String response = "";
        try {
            url = new URL(Utils.SITEURL + "/services/");
            Log.e("SERVICE URL", url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(0);
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            //conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            if (params != null) {
                OutputStream ostream = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ostream, "UTF-8"));
                StringBuilder requestresult = new StringBuilder();
                boolean first = true;

                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (first)
                        first = false;
                    else
                        requestresult.append("&");
                    requestresult.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    requestresult.append("=");
                    requestresult.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
                Log.e("REQUEST RESULT", requestresult.toString());
                writer.write(requestresult.toString());
                writer.flush();
                writer.close();
                ostream.close();
            }
            int reqresponseCode = conn.getResponseCode();

            if (reqresponseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (syncType == 1) {
            Utils.showProgress(message);
        }

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        jsonStr = makeWebServiceCall();
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onPostExecute(Void requestresult) {
        super.onPostExecute(requestresult);
        if (jsonStr != null) {
            JSONObject jsonObj = null;
            try {
                //Utils.hideProgress();
                Log.e("JSONSTR", jsonStr);
                jsonObj = new JSONObject(jsonStr);
                Integer count = jsonObj.getInt("count");

                if (count > 0) {
                    //JSONObject data = jsonObj.getJSONObject("data");
                    switch (this.xAction) {
                        case "getBarcodeList": {
                            mxDb = new DbHelper();
                            SQLiteDatabase db = mxDb.getWritableDatabase();
                            mxDb.updateBarcodeData(db, jsonObj);
                            db.close();
                            break;
                        }
                        case "syncBarcodeAttToServer": {
                            JSONObject jsonObject = jsonObj.getJSONObject("data");

                            if (!jsonObject.getString("oAttIDs").equals("")) {
                                mxDb = new DbHelper();
                                SQLiteDatabase db = mxDb.getWritableDatabase();
                                String oAttIDs = jsonObject.getString("oAttIDs");
                                int sType = 1;
                                if (Utils.SYNCTYPE == 1) {
                                    sType = 0;
                                }
                                db.execSQL("UPDATE " + DbHelper.MXATTENDANCE + " SET syncStatus='" + sType + "' WHERE attID IN(" + oAttIDs + ")");
                                mxDb.syncAttendanceToServer(db, 1, syncType);
                                if (SyncActivity.syncedRec != null)
                                    SyncActivity.setSyncCount();
                            }
                            break;
                        }
                        case "syncBarcodeMasterToServer": {
                            JSONObject jsonObject = jsonObj.getJSONObject("data");
                            //if (!jsonObject.getStringFromLocalDB("barcodeIDs").equals("")) {

                            Utils.LASTSYNCDATETIME = jsonObject.getString("lastSyncDateTime"); // added on 14 may 19  by hari
                            //Log.e("LASTSYNCDATETIME ", "onPostExecute: "+Utils.LASTSYNCDATETIME );
                            mxDb = new DbHelper();
                            SQLiteDatabase db = mxDb.getWritableDatabase();
                            String barcodeIDs = jsonObject.getString("barcodeIDs");
                            String syncIDs = jsonObject.getString("syncIDs");
                            db.execSQL("UPDATE " + DbHelper.MXBARCODE + " SET syncStatus = '1' WHERE barcodeID IN(" + barcodeIDs + ")");
                            db.execSQL("UPDATE " + DbHelper.MXBARCODE + " SET attStatus = '1',syncStatus = '1' WHERE barcodeID IN(" + syncIDs + ")");

                            //mxDb.syncBarcodeMasterToServer(db, 1,syncType);
                            //}

                            // added on 14 may 19  by hari
                            JSONArray jsonArray=jsonObject.getJSONArray("barcodeCustomerData");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //Log.e("jsonarray ", "onPostExecute: "+jsonArray.getStringFromLocalDB(i) );
                                JSONObject jsonObject1= (JSONObject) jsonArray.get(i);
                                String barcodeID=jsonObject1.getString("barcodeID");
                                String customerName=jsonObject1.getString("customerName");
                                db.execSQL("UPDATE " + DbHelper.MXBARCODE + " SET customerName = '"+customerName.replace("'","''")+"' WHERE barcodeID ='"+barcodeID+"'");
                            }

                            break;
                        }
                        case "getAdminBarcodeList": {
                            mxDb = new DbHelper();
                            SQLiteDatabase db = mxDb.getWritableDatabase();
                            mxDb.updateAdminBarcodeData(db, jsonObj);
                            Utils.mxAlert(jsonObj.getString("msg"));
                            Utils.hideProgress();
                            break;
                        }

                    }
                } else {
                    Utils.hideProgress();
                    String msg;
                    /*if (xAction.equals("getBarcodeList")) {
                        msg = "Barcodes Loaded Successfully";
                    } else {*/
                        msg = jsonObj.getString("msg");
                    //}
                    Utils.mxAlert(msg);
                }
            } catch (JSONException e) {
                Utils.hideProgress();
                e.printStackTrace();
            }
        } else {
            Utils.hideProgress();
        }
    }
}

