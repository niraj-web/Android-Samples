package com.example.zoneattendence.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zoneattendence.Model.UpdateDeviceModel;
import com.example.zoneattendence.Model.VerifyDeviceModel;
import com.example.zoneattendence.R;
import com.example.zoneattendence.utils.ApiRequestHelper;
import com.example.zoneattendence.utils.Utils;

import java.util.HashMap;

public class OtpActivity extends BaseActivity {

    UpdateDeviceModel updateDeviceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        getSupportActionBar().setTitle("OTP Verification");

        Button verify = (Button) findViewById(R.id.btnVerify);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtpActivity.this, MenuActivity.class));
                updateDeviceData();
            }
        });
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_otp;
    }

    private void updateDeviceData() {
        ProgressDialog dialog = new ProgressDialog(OtpActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","updateDeviceData");
        params.put("deviceEmail", "saraswatniraj99@gmail.com");
        params.put("otp","262514");
        params.put("deviceMobile", "9403723945");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign1(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    updateDeviceModel = (UpdateDeviceModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (updateDeviceModel != null) {
                        if (updateDeviceModel.getCount() != null && updateDeviceModel.getData() != null) {

                        } else {

                        }
                    } else {
                        Utils.showLongToast(OtpActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(OtpActivity.this, apiResponse);
                }
            });
        } else {
           // Utils.alert_dialog(OtpActivity.this);
        }
    }
}