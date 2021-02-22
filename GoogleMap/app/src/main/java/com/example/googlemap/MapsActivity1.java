package com.example.googlemap;

import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.google.android.gms.common.ConnectionResult;

public interface MapsActivity1 {
    void onConnected(Bundle bundle);

    @RequiresApi(api = Build.VERSION_CODES.M)
    void onLocationChanged(Location location);

    void onConnectionFailed(ConnectionResult connectionResult);
}
