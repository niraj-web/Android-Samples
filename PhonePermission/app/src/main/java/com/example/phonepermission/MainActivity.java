package com.example.phonepermission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    Button button1;

    private static final int REQUEST = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText1);
        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (Build.VERSION.SDK_INT >= 23) {
                    String[] PERMISSIONS = {android.Manifest.permission.CALL_PHONE};
                    if (!hasPermissions(MainActivity.this, PERMISSIONS)) {
                        ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, REQUEST );
                    } else {
                        makeCall();
                    }
                } else {
                    makeCall();
                }


            }
        });

    }

    private boolean hasPermissions(MainActivity mainActivity, String[] permissions)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && MainActivity.class != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    private void makeCall()
    {
        String number = editText1.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+number));//change the number
        startActivity(callIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
