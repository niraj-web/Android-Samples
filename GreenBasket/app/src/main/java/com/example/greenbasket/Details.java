package com.example.greenbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity
{
    String name;
    int image;
    TextView t ;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i =getIntent();
        image = i.getIntExtra("image",0);
        name = i.getStringExtra("name");

        t = findViewById(R.id.textView);
        t.setText(name);

        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(image);


    }
}