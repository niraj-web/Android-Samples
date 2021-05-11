package com.example.combineassignment.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.combineassignment.Adapter.HospitalAdapter;
import com.example.combineassignment.Model.HospitalModel;
import com.example.combineassignment.R;
import com.example.combineassignment.utils.ApiRequestHelper1;
import com.example.combineassignment.utils.Utils;

import java.util.HashMap;

public class HospitalActivity extends BaseActivity {

    private HospitalModel hospitalModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        getSubCategory();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_hospital;
    }

    private void getSubCategory() {
        ProgressDialog dialog = new ProgressDialog(HospitalActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","getSubCategory");
        params.put("userID", "3");
        params.put("langCode", "ar");
        params.put("categoryID", "7");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper1().updateProjectAssign2(params, new ApiRequestHelper1.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    hospitalModel = (HospitalModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (hospitalModel != null) {
                        if (hospitalModel.getStatus()  && hospitalModel.getIsUserAuthTokenValid() != null) {

                            RecyclerView hospitalList = (RecyclerView) findViewById(R.id.HospitalRecyclerView);
                            hospitalList.setHasFixedSize(true);
                            hospitalList.setLayoutManager(new GridLayoutManager(HospitalActivity.this,2));
                            HospitalAdapter hospitalAdapter = new HospitalAdapter(HospitalActivity.this,hospitalModel.getData());
                            hospitalList.setAdapter(hospitalAdapter);

                        } else {

                        }
                    } else {
                        Utils.showLongToast(HospitalActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(HospitalActivity.this, apiResponse);
                }
            });
        } else {
            Utils.alert_dialog(HospitalActivity.this);
        }
    }
}