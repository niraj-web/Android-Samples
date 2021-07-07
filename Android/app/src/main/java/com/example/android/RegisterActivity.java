package com.example.android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    EditText et_firstName,et_lastName,et_dob,et_userName,et_password,et_sequrityAnswer,et_address;
    Button bt_register;
    ImageView profile,add;
    AutoCompleteTextView et_sequrityQue;
    private static final int GALLERY_REQUEST_CODE = 123;

    String[] questions = { "Who is your favourite hero ?","What is your favourite colour","Name of the city where you born" };

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_dob.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_dob = findViewById(R.id.et_dob);
        et_userName = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        et_sequrityQue = findViewById(R.id.et_sequrityQue);
        et_sequrityAnswer = findViewById(R.id.et_sequrityAnswer);
        et_address = findViewById(R.id.et_address);
        bt_register = findViewById(R.id.bt_register);
        profile = findViewById(R.id.profile);
        add = findViewById(R.id.add);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   Intent intent = new Intent();
                   intent.setType("image/*");
                   intent.setAction(Intent.ACTION_GET_CONTENT);
                   startActivityForResult(Intent.createChooser(intent,"Pick an image"), GALLERY_REQUEST_CODE);
            }
        });

         bt_register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 bt_register_onClick(view);
             }
         });

         et_dob.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 new DatePickerDialog(RegisterActivity.this, date, myCalendar
                         .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                         myCalendar.get(Calendar.DAY_OF_MONTH)).show();
             }
         });

      
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode ==RESULT_OK && data!=null)
        {
            Uri imageData = data.getData();
            profile.setImageURI(imageData);
        }
    }

    private void bt_register_onClick(View view) {
        try {

            AccountDB accountDB = new AccountDB(getApplicationContext());
            Account account = new Account();
            account.setFirstname(et_firstName.getText().toString());
            account.setLastname(et_lastName.getText().toString());
            account.setDOB(et_dob.getText().toString());
            account.setUsername(et_userName.getText().toString());
            account.setPassword(et_password.getText().toString());
            account.setSecurityQuestion(et_sequrityQue.getText().toString());
            account.setSecurityAnswer(et_sequrityAnswer.getText().toString());
            account.setAddress(et_address.getText().toString());
            Account temp = accountDB.checkUsername(et_userName.getText().toString());
            if (temp == null) {

                if (accountDB.create(account)) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                    Toast.makeText(RegisterActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Error");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.setMessage("Can not create Account");

                    builder.show();
                }
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("UserName Exist");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setMessage("Can not create Account");

                builder.show();
            }
        }
        catch (Exception e)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Error");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.setMessage(e.getMessage());

            builder.show();
        }
    }
}