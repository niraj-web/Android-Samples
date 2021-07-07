package com.example.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements Serializable {

    Button login;
    EditText loginusername,loginuserpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        login = findViewById(R.id.login);
        loginusername = findViewById(R.id.login_userName);
        loginuserpassword = findViewById(R.id.login_userPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                startActivity(intent);
*/
                login_onClick(view);
            }
        });
    }

    private void login_onClick(View view) {

        AccountDB accountDB = new AccountDB(getApplicationContext());
        String username = loginusername.getText().toString();
        String password = loginuserpassword.getText().toString();
        Account account = accountDB.login(username,password);
        if (account==null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Error");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.setMessage("Invalid Account");

            builder.show();
        }
        else {
            Intent intent = new Intent(LoginActivity.this,Board.class);
            intent.putExtra("account",account);
            startActivity(intent);

            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}