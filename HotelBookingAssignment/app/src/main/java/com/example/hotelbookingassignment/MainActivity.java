package com.example.hotelbookingassignment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hotelbookingassignment.Adapter.AEDVoucherAdapter;
import com.example.hotelbookingassignment.Model.ProjectAssModel;
import com.example.hotelbookingassignment.utils.ApiRequestHelper;
import com.example.hotelbookingassignment.utils.CommonInterface;
import com.example.hotelbookingassignment.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements CommonInterface {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;




    private ProjectAssModel projectAssModel;
    private ViewPager viewPager;
    private AEDVoucherAdapter aedVoucherAdapter;
    private TextView number;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind (this);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("DETAILS"));
        tabLayout.addTab(tabLayout.newTab().setText("LOCATION"));
        tabLayout.addTab(tabLayout.newTab().setText("T&C's"));
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

        TextView plus = (TextView) findViewById(R.id.plus);
        TextView minus = (TextView) findViewById(R.id.minus);
        number = (TextView) findViewById(R.id.number);

        getData();
    }



    private void getData() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "getRewardsDetails");
        params.put("userID", "533");
        params.put("langCode", "en");
        params.put("voucherID", "4");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    projectAssModel = (ProjectAssModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (projectAssModel != null) {
                        if (projectAssModel.getStatus()  && projectAssModel.getData() != null) {
                            final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(),3,projectAssModel,MainActivity.this);
                            viewPager.setAdapter(adapter);

                            RecyclerView voucheList = (RecyclerView) findViewById(R.id.recyclerView);
                            voucheList.setHasFixedSize(true);
                            voucheList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            AEDVoucherAdapter aedVoucherAdapter = new AEDVoucherAdapter(MainActivity.this,projectAssModel.getData().getDenomination(), MainActivity.this);
                            voucheList.setAdapter(aedVoucherAdapter);

                            TextView details = (TextView) findViewById(R.id.details);
                            details.setText(projectAssModel.getData().getRewardDetails().getVoucherDescription());

                            TextView valid = (TextView) findViewById(R.id.valid);
                            valid.setText("Valid till " + projectAssModel.getData().getRewardDetails().getVoucherToDate());

                            ImageView image = (ImageView) findViewById(R.id.image);
                            Glide.with(MainActivity.this)
                                    .load(projectAssModel.getData().getRewardDetails().getImageUrl())
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

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onListSelected(Object object, String string) {

    }
}