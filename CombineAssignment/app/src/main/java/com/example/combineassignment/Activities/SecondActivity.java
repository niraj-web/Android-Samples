package com.example.combineassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.combineassignment.Adapter.OfferAdapter;
import com.example.combineassignment.Model.OffersModel;
import com.example.combineassignment.R;
import com.example.combineassignment.utils.ApiRequestHelper1;
import com.example.combineassignment.utils.Utils;

import java.util.HashMap;

public class SecondActivity extends BaseActivity {

    private OffersModel offersModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getMainCategory();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_second;
    }

    private void getMainCategory() {
        ProgressDialog dialog = new ProgressDialog(SecondActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","getMainCategory");
        params.put("userID", "3");
        params.put("langCode", "ar");
        params.put("categoryFor", "1");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper1().updateProjectAssign1(params, new ApiRequestHelper1.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    offersModel = (OffersModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (offersModel != null) {
                        if (offersModel.getStatus()  && offersModel.getData() != null) {

                            RecyclerView offerlist = (RecyclerView) findViewById(R.id.MainRecyclerView);
                            offerlist.setHasFixedSize(true);
                            offerlist.setLayoutManager(new GridLayoutManager(SecondActivity.this,2));
                            OfferAdapter offerAdapter = new OfferAdapter(SecondActivity.this,offersModel.getData());
                            offerlist.setAdapter(offerAdapter);

                        } else {

                        }
                    } else {
                        Utils.showLongToast(SecondActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(SecondActivity.this, apiResponse);
                }
            });
        } else {
            Utils.alert_dialog(SecondActivity.this);
        }
    }
}