package com.example.greenbasket;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService1 extends FirebaseMessagingService {

    private static final String TAG = "FCM Service";




    public void onNewToken(String token)
    {
        Log.d("TAG", "Refreshed token: " + token);
        sendRegistrationToserver(token);
    }

    private void sendRegistrationToserver(String token) {
    }
}