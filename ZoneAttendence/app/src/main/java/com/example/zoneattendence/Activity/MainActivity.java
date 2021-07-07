package com.example.zoneattendence.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zoneattendence.Model.VerifyDeviceModel;
import com.example.zoneattendence.R;
import com.example.zoneattendence.utils.ApiRequestHelper;
import com.example.zoneattendence.utils.Utils;

import java.util.HashMap;

public class MainActivity extends BaseActivity {

    private VerifyDeviceModel verifyDeviceModel;
    EditText etCusName;
    EditText etMobileNumber;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Registration");

        Button submit = (Button) findViewById(R.id.btnSubmit);
        etCusName = (EditText) findViewById(R.id.etCusName);
        etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = etCusName.getText().toString();
                String str1 = etMobileNumber.getText().toString();
                String str2 = etEmail.getText().toString();

                Intent intent = new Intent(getApplicationContext(), OtpActivity.class);

                intent.putExtra("message_key",str);
                intent.putExtra("message_key1",str1);
                intent.putExtra("message_key2",str2);
                startActivity(intent);

                getVerifyDeviceEmail();
            }
        });
    }
    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    private void getVerifyDeviceEmail() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","verifyDeviceEmail");
        params.put("deviceEmail", "saraswatniraj99@gmail.com");
        params.put("deviceMobile", "9403723945");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    verifyDeviceModel = (VerifyDeviceModel) object;
                    if (verifyDeviceModel != null) {
                        if (verifyDeviceModel.getCount() != null && verifyDeviceModel.getData() != null) {

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

        }
    }
}
