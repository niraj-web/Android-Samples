package com.example.reetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.reetrofit.model.Message;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Message message;
        message= (Message) getIntent().getSerializableExtra("Message_key");
        Log.e("Main", "onCreate: "+ message.getMessage());
        TextView t = findViewById(R.id.textView);
        t.setText(message.getMessage());

    }
}