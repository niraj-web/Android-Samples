package com.example.zoneattendence.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {


    public static final String BASE_URL = "http://dev.maxdigi.co/";
    public static final String SITEURL = "http://dev.maxdigi.co/zone-attendence/xadmin";
    public static String NO_INTERNET_MSG = "You don't have internet connection.Please connect to internet";
    public static int SYNCTYPE = 0;
    public static String LASTSYNCDATETIME = "";
    public static String UNPROPER_RESPONSE = "Unable to process your request. Please, Try again later.";
    public static Context context;
    private static ProgressDialog proDialog;
    public static String DEVICEID = "515868fe3aac73cb";
    public static boolean MANUALSCAN = false;

    public Utils(Context context) {
        this.context = context;
    }

    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = connectivityManager != null ? connectivityManager.getAllNetworkInfo() : new NetworkInfo[0];
        for (NetworkInfo anInfo : info) {
            if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public static void hideProgress() {
        if (proDialog != null && proDialog.isShowing()) {
            proDialog.dismiss();
        }
    }

    public static void showProgress(String message) {

        if (proDialog == null || !proDialog.isShowing()) {
            proDialog = new ProgressDialog(context);
            proDialog.setMessage(message);
            proDialog.setCancelable(false);
            proDialog.show();
        }
    }

    public static String getStringFromLocalDB(String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_DEFAULT, 0);
        return preferences.getString(key, "");
    }

    private static final String PREF_DEFAULT = "user_data";

    public static void saveToLocalDB(String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_DEFAULT, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
