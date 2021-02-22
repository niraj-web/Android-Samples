package com.example.chrometab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://www.journaldev.com";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                builder.addDefaultShareMenuItem();

                CustomTabsIntent anotherCustomTab = new CustomTabsIntent.Builder().build();


                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
                int requestCode = 100;
                Intent intent = anotherCustomTab.intent;
                intent.setData(Uri.parse("https://www.journaldev.com/author/anupam"));

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                        requestCode,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setActionButton(bitmap, "Android", pendingIntent, true);
                builder.setShowTitle(true);


                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));
            }
        });
    }
}