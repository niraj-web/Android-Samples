package com.example.combineassignment.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.combineassignment.Adapter.RoomTypeAdapter;
import com.example.combineassignment.LocaleHelper;
import com.example.combineassignment.Model.RoomDetailsModel;
import com.example.combineassignment.R;
import com.example.combineassignment.utils.ApiRequestHelper;
import com.example.combineassignment.utils.Utils;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends BaseActivity {
    private RoomDetailsModel roomDetailsModel;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button english = (Button) findViewById(R.id.english);
        Button arebic = (Button) findViewById(R.id.arebic);

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                LocaleHelper.setLocale(MainActivity.this,"en");
            }
        });

        arebic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Locale locale = new Locale("ar");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            }
        });

        getData();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    private void getData() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "getAvailableRooms");
        params.put("userID","3");
        params.put("langCode","ar");
        params.put("hotelSlug","pullman");
        params.put("room_selection","1");
        params.put("checkout","2021-04-21");
        params.put("checkin","2021-04-20");
        params.put("roomsguest","1");
        params.put("adultID","15");
        params.put("childID","16");
        params.put("adultQTY","1");
        params.put("childQTY","0");
        Log.e("Tag", String.valueOf(15));
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    roomDetailsModel = (RoomDetailsModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (roomDetailsModel != null) {
                        if (roomDetailsModel.getStatus()  && roomDetailsModel.getData() != null) {

                            RecyclerView roomlist = (RecyclerView) findViewById(R.id.MainRecycleView);
                            roomlist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            RoomTypeAdapter roomTypeAdapter = new RoomTypeAdapter(MainActivity.this,roomDetailsModel.getData(),MainActivity.this);
                            roomlist.setAdapter(roomTypeAdapter);

                            ImageView image = (ImageView) findViewById(R.id.MainImageView);
                            Glide.with(MainActivity.this)
                                    .load(roomDetailsModel.getData().get(0).getGalleryData())
                                    .into(image);

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
}