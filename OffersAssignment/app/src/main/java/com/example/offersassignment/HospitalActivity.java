package com.example.offersassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.offersassignment.Adapter.HospitalAdapter;
import com.example.offersassignment.Adapter.OfferAdapter;
import com.example.offersassignment.Model.HospitalModel;
import com.example.offersassignment.Model.OffersModel;
import com.example.offersassignment.utils.ApiRequestHelper;
import com.example.offersassignment.utils.Utils;

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
        params.put("langCode", "en");
        params.put("categoryID", "7");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign1(params, new ApiRequestHelper.OnRequestComplete() {
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