package com.example.ratingassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.ratingassignment.Model.ProjectAssModel;
import com.example.ratingassignment.utils.ApiRequestHelper;
import com.example.ratingassignment.utils.CommonInterface;
import com.example.ratingassignment.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements CommonInterface  {

    private ProjectAssModel projectAssModel;
    private ViewPager viewPager;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("ACTIVE"));
        tabLayout.addTab(tabLayout.newTab().setText("EXPIRED"));
        tabLayout.addTab(tabLayout.newTab().setText("REDEEMED"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        getData();
    }

    private void getData() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "myVoucherDetails");
        params.put("userID", "3");
        params.put("deviceToken","djXOV4saQg-4WGUpLKt14N%3AAPA91bGTwoRgpxV0yZkySFFxj3_5Ps5m3RYIiqZ56IM5EVdpFrt9CtzX3PmIJ2rakboZQt2tg6VOY0Qg_HbRHzXiqq-QtaY5UyW2Fs5Q0MsPquJkYlV-QS2SdBmD_87wNDQNUQOIO3eb");
        params.put("langCode","en");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    projectAssModel = (ProjectAssModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (projectAssModel != null) {
                        if (projectAssModel.getStatus() && projectAssModel.getData() != null) {
                            final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(),3,projectAssModel,MainActivity.this);
                            viewPager.setAdapter(adapter);
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

    public void getUpdateStatus(float rating, Integer productID, Integer offerCouponCodeID, Integer rateFor) {
        Map<String, String> params = new HashMap<> ();
        params.put("xAction", "addRating");
        params.put("productID", "productID");
        params.put("deviceToken","djXOV4saQg-4WGUpLKt14N%3AAPA91bGTwoRgpxV0yZkySFFxj3_5Ps5m3RYIiqZ56IM5EVdpFrt9CtzX3PmIJ2rakboZQt2tg6VOY0Qg_HbRHzXiqq-QtaY5UyW2Fs5Q0MsPquJkYlV-QS2SdBmD_87wNDQNUQOIO3eb");
        params.put("rateFor","rateFor");
        params.put("rating","rating");
        params.put("offerCouponCodeID","offerCouponCodeID");

        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    ProjectAssModel projectAssModel = (ProjectAssModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if ( projectAssModel != null) {
                        if (projectAssModel.getStatus() && projectAssModel.getData() != null) {

                        } else {
                        }
                    } else {
                        Utils.showLongToast(mContext, Utils.UNPROPER_RESPONSE);
                    }
                }

                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(mContext, apiResponse);
                }
            });

        } else {
            Utils.alert_dialog(mContext);
        }
    }
    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onListSelected(Object object, String string)
    {

    }
}