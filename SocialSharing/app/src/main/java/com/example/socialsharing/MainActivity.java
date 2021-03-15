package com.example.socialsharing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void share(View v) {

        Intent S = new Intent(Intent.ACTION_SEND);
        Uri SU = Uri.parse("android.resource://com.example.hp.myapplication/*");
        S.setType("image/jpeg");
        S.putExtra(Intent.EXTRA_STREAM, SU);
        startActivity(Intent.createChooser(S, "Share image using"));
    }
}