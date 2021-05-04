package com.example.myaccountassignment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaccountassignment.Adapter.CountryAdapter;
import com.example.myaccountassignment.Adapter.NameAdapter;
import com.example.myaccountassignment.Model.AccountModel;
import com.example.myaccountassignment.utils.ApiRequestHelper;
import com.example.myaccountassignment.utils.CommonInterface;
import com.example.myaccountassignment.utils.Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import javax.sql.RowSet;

import okhttp3.MultipartBody;

public class MainActivity extends BaseActivity implements CommonInterface {

    private static final int SELECT_IMAGE = 1;
    private static final int CAMERA_PIC_REQUEST = 1;
    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }
    };

    private AccountModel accountModel;
    private EditText etBirth;
    private EditText etCountry;
    private Dialog dialog;
    private EditText name;
    private ImageView image;
    private Button Update;
    private MultipartBody.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBirth = (EditText) findViewById(R.id.etBirth);
        etCountry = (EditText) findViewById(R.id.etCountry);
        name = (EditText) findViewById(R.id.name);
        image = (ImageView) findViewById(R.id.image);
        Update = (Button) findViewById(R.id.Update);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*uploadImage();*/
            }
        });

        etBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                updateLabel();
            }
            private void updateLabel()
            {
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                etBirth.setText(sdf.format(myCalendar.getTime()));
            }
        });

        etCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.country_name_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView countryNameList = (RecyclerView) dialog.findViewById(R.id.country_name_list);
                countryNameList.setHasFixedSize(true);
                countryNameList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                CountryAdapter countryAdapter = new CountryAdapter(MainActivity.this,accountModel.getData().getNationality(),MainActivity.this);
                countryNameList.setAdapter(countryAdapter);
                dialog.show();
            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.name_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView nameList = (RecyclerView) dialog.findViewById(R.id.name_list);
                nameList.setHasFixedSize(true);
                nameList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                NameAdapter nameAdapter = new NameAdapter(MainActivity.this,accountModel.getData().getEmirates(),MainActivity.this);
                nameList.setAdapter(nameAdapter);
                dialog.show();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.image_option);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                dialog.show();

                TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
                TextView gallery = (TextView) dialog.findViewById(R.id.gallery);
                TextView camera = (TextView) dialog.findViewById(R.id.camera);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
                    }
                });

                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                    }
                });
            }
        });



        getNationality();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && null != data) {

            dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) dialog.findViewById(R.id.imageview);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            dialog.show();
           /* if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
            }*/
        }
    }


    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    private void getNationality() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","getNationality");
        params.put("userID", "609");
        params.put("langCode", "en");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    accountModel = (AccountModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (accountModel != null) {
                        if (accountModel.getStatus()  && accountModel.getIsUserAuthTokenValid() != null) {

                        } else {

                        }
                    } else {
                        Utils.showLongToast(MainActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(MainActivity.this, apiResponse);
                }
            });
        } else {
            Utils.alert_dialog(MainActivity.this);
        }
    }

   /* private void uploadImage()
    {
        new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("xAction", "uploadProfileImage");
        builder.addFormDataPart("userID", "609");
        builder.addFormDataPart("deviceToken", "dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        builder.addFormDataPart("userImage", selectedfile);
        builder.addFormDataPart("langCode", "en");
        MultipartBody multipartBody = builder.build();
    }*/
    @Override
    public void onListSelected(Object object, String string) {

        if (object instanceof AccountModel.Nationality)
        {
            AccountModel.Nationality user = (AccountModel.Nationality) object;
            etCountry.setText(user.getCountryName());
            dialog.dismiss();
        }
        if (object instanceof AccountModel.Emirate)
        {
            AccountModel.Emirate user = (AccountModel.Emirate) object;
            name.setText(user.getName());
            dialog.dismiss();
        }
    }
}