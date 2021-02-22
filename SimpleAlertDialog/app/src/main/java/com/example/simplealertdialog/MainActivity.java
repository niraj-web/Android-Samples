package com.example.simplealertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity

{
    Button closeButton;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        closeButton = (Button) findViewById(R.id.button);
        builder = new AlertDialog.Builder(this);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                builder.setMessage("Do you want to close this application ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        finish();
                        Toast.makeText(getApplicationContext(),"you choose yes action for alertbox", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.cancel();
                        Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();

                alert.setTitle("AlertDialogExample");
                alert.show();
            }
        });
    }
}