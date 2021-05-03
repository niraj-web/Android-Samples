package com.example.offersassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.offersassignment.Adapter.OfferAdapter;
import com.example.offersassignment.Model.OffersModel;
import com.example.offersassignment.utils.ApiRequestHelper;
import com.example.offersassignment.utils.CommonInterface;
import com.example.offersassignment.utils.Utils;

import java.util.HashMap;

public class MainActivity extends BaseActivity {

    private OffersModel offersModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMainCategory();
    }


    private void getMainCategory() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","getMainCategory");
        params.put("userID", "3");
        params.put("langCode", "en");
        params.put("categoryFor", "1");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    offersModel = (OffersModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (offersModel != null) {
                        if (offersModel.getStatus()  && offersModel.getData() != null) {

                            RecyclerView offerlist = (RecyclerView) findViewById(R.id.MainRecyclerView);
                            offerlist.setHasFixedSize(true);
                            offerlist.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                            OfferAdapter offerAdapter = new OfferAdapter(MainActivity.this,offersModel.getData());
                            offerlist.setAdapter(offerAdapter);

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

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }
}