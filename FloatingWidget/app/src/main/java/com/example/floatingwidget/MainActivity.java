package com.example.floatingwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this))
        {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        }
        else
        {
            initializView();
        }
    }

    private void initializView()
    {
        findViewById(R.id.notify_me).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startService(new Intent(MainActivity.this, FloatingViewService.class));
                finish();
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION)
        {
            if (resultCode == RESULT_OK)
            {
                initializView();
            }
            else
            {
                Toast.makeText(this, "DRAW OVER THE APP PERMISSION NOT AVAILABLE.  CLOSING THE APPLICATION", Toast.LENGTH_SHORT).show();

                finish();
            }

        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}