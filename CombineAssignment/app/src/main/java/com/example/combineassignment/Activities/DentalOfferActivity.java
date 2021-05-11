package com.example.combineassignment.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.combineassignment.Adapter.DentalOfferAdapter;
import com.example.combineassignment.Model.DentalModel;
import com.example.combineassignment.R;
import com.example.combineassignment.utils.ApiRequestHelper1;
import com.example.combineassignment.utils.Utils;

import java.util.HashMap;

public class DentalOfferActivity extends BaseActivity {

    private DentalModel dentalModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental_offer);

        getoffers();
    }


    private void getoffers() {
        ProgressDialog dialog = new ProgressDialog(DentalOfferActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","getoffers");
        params.put("userID", "3");
        params.put("langCode", "ar");
        params.put("categoryID", "7");
        params.put("latitude","20.5377023");
        params.put("longitude","74.8751956");
        params.put("subcategoryID","24");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper1().updateProjectAssign3(params, new ApiRequestHelper1.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    dentalModel = (DentalModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (dentalModel != null) {
                        if (dentalModel.getStatus()  && dentalModel.getIsUserAuthTokenValid() != null) {


                            RecyclerView offerlist = (RecyclerView) findViewById(R.id.DentalRecyclerView);
                            offerlist.setHasFixedSize(true);
                            offerlist.setLayoutManager(new GridLayoutManager(DentalOfferActivity.this,2));
                            DentalOfferAdapter dentalOfferAdapter = new DentalOfferAdapter(DentalOfferActivity.this,dentalModel.getData());
                            offerlist.setAdapter(dentalOfferAdapter);


                        } else {

                        }
                    } else {
                        Utils.showLongToast(DentalOfferActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(DentalOfferActivity.this, apiResponse);
                }
            });
        } else {
            Utils.alert_dialog(DentalOfferActivity.this);
        }
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_dental_offer;
    }
}