package com.example.zoneattendence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckInternet extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("NETWORK", "STATUS");
        Log.e("INTENT", "" + intent.getAction());

        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (conMan != null) {
            netInfo = conMan.getActiveNetworkInfo();
        }
        boolean isWiFiON = netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI;
        if (isWiFiON) {
            if (isConnectingToInternet(context)) {
                new CheckNetConnection(context).execute();
            }
        }
    }

    public static boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = new Network[0];
            if (connectivityManager != null) {
                networks = connectivityManager.getAllNetworks();
            }
            NetworkInfo networkInfo = null;
            for (Network mNetwork : networks) {
                if (connectivityManager != null) {
                    networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                }
                if (networkInfo != null && networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    class CheckNetConnection extends AsyncTask<Void, Void, Boolean> {
        Context context;

        public CheckNetConnection(Context context) {
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(Void... strings) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();

                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {

            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean s) {
            LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);

            manager.sendBroadcast(new Intent("android.net.wifi.WIFI_STATE_CHANGED").putExtra("isNetConnected", s).putExtra("isWiFiON", true));
        }
    }
}
