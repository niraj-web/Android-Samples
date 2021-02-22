package com.example.smspermission;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mobileno,message;
    Button sendsms;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileno=(EditText)findViewById(R.id.editText1);
        message=(EditText)findViewById(R.id.editText2);
        sendsms=(Button)findViewById(R.id.button1);

        sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String no = mobileno.getText().toString();
                String msg = message.getText().toString();

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.SEND_SMS)
                            == PackageManager.PERMISSION_DENIED) {

                        Log.d("permission", "permission denied to SEND_SMS - requesting it");
                        String[] permissions = {Manifest.permission.SEND_SMS};

                        requestPermissions(permissions, PERMISSION_REQUEST_CODE);

                    }
                }

                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(no, null, msg, pi,null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}