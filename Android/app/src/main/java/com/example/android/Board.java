package com.example.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Board extends AppCompatActivity {

    TextView txtWelcome;
    EditText et_firstName,et_lastName,et_dob,et_userName,et_password,et_sequrityAnswer,et_address;
    Button bt_register;
    AutoCompleteTextView et_sequrityQue;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        getSupportActionBar().setTitle("Profile");

        txtWelcome = findViewById(R.id.txtWelcome);
        et_firstName = findViewById(R.id.et_firstNameUpdate);
        et_lastName = findViewById(R.id.et_lastNameUpdate);
        et_dob = findViewById(R.id.et_dobUpdate);
        et_userName = findViewById(R.id.et_userNameUpdate);
        et_password = findViewById(R.id.et_passwordUpdate);
        et_sequrityQue = findViewById(R.id.et_sequrityQueUpdate);
        et_sequrityAnswer = findViewById(R.id.et_sequrityAnswerUpdate);
        et_address = findViewById(R.id.et_addressUpdate);
        bt_register = findViewById(R.id.bt_registerUpdate);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(view);
            }
        });



        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        txtWelcome.setText(getString(R.string.Welcome) + " " + account.getUsername());
        et_firstName.setText(account.getFirstname());
        et_lastName.setText(account.getLastname());
        et_dob.setText(account.getDOB());
        et_userName.setText(account.getUsername());
        et_password.setText(account.getPassword());
        et_sequrityQue.setText(account.getSecurityQuestion());
        et_sequrityAnswer.setText(account.getSecurityAnswer());
        et_address.setText(account.getAddress());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.location)
        {
            Intent intent = new Intent(Board.this,LocationActivity.class);
            startActivity(intent);
        }
//        if (id == R.id.action_settings)
//        {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private void update(View view) {
        try {
            AccountDB accountDB = new AccountDB(getApplicationContext());
            Account currentAccount = accountDB.find(account.getId());
            Account temp = accountDB.checkUsername(et_userName.getText().toString());
            if (temp == null) {
                currentAccount.setUsername(et_userName.getText().toString());
                currentAccount.setFirstname(et_firstName.getText().toString());
                currentAccount.setLastname(et_lastName.getText().toString());
                currentAccount.setDOB(et_dob.getText().toString());
                currentAccount.setPassword(et_password.getText().toString());
                currentAccount.setSecurityQuestion(et_sequrityQue.getText().toString());
                currentAccount.setSecurityAnswer(et_sequrityAnswer.getText().toString());
                currentAccount.setAddress(et_address.getText().toString());
                if (accountDB.update(currentAccount))
                {
                    Toast.makeText(Board.this, "Updated Succssfully", Toast.LENGTH_SHORT).show();
                }
                else
                {

                }

            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Username Exists");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setMessage("Can not create Account");

                builder.show();
            }

        }catch (Exception e)
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
    }

}