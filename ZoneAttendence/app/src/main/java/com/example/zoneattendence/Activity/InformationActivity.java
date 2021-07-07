package com.example.zoneattendence.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zoneattendence.R;
import com.example.zoneattendence.utils.Utils;

public class InformationActivity extends AppCompatActivity {
    RelativeLayout container;
    Button btnNewScan;
    TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        getSupportActionBar().setTitle("Information");

        container = findViewById(R.id.container);
        btnNewScan = findViewById(R.id.btnNewScan);
        txtStatus = findViewById(R.id.txtStatus);

        String intentString = getIntent().getStringExtra("scanStatus");
        String customerName = getIntent().getStringExtra("customerName");
        String attCount = getIntent().getStringExtra("attCount");
        if (attCount==null)
            attCount="1";

        switch (intentString) {
            case "RED":
                container.setBackgroundColor(Color.RED);
                txtStatus.setText("You don't have access to this zone!!!");
                break;
            case "GREEN":
                container.setBackgroundColor(Color.GREEN);
                txtStatus.setText("Welcome..."+"\n"+customerName+"\n("+attCount+")");
                break;
            case "ORANGE":
                container.setBackgroundColor(Color.parseColor("#ea6218"));
                txtStatus.setText("Welcome Again..."+"\n"+customerName+"\n("+attCount+")");
                break;
        }

        if (Utils.MANUALSCAN){
            btnNewScan.setVisibility(View.VISIBLE);
        }else {
            btnNewScan.setVisibility(View.GONE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(InformationActivity.this, ScannedBarcodeAcivity.class);
                    startActivity(intent);
                    finish();
                }
            },2000);
        }

        btnNewScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, ScannedBarcodeAcivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}