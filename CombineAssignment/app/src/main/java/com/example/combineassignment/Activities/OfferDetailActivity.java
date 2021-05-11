package com.example.combineassignment.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.combineassignment.Adapter.PageAdapter1;
import com.example.combineassignment.Model.OfferDetailsModel;
import com.example.combineassignment.R;
import com.example.combineassignment.utils.ApiRequestHelper1;
import com.example.combineassignment.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

public class OfferDetailActivity extends BaseActivity {

    private OfferDetailsModel offerDetailsModel;
    private ViewPager viewPager;
    TextView number;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("تفاصيل"));
        tabLayout.addTab(tabLayout.newTab().setText("موقعك"));
        tabLayout.addTab(tabLayout.newTab().setText("الشروط والأحكام"));
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

        Button buyNow = (Button) findViewById(R.id.buyNow);

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OfferDetailActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        number = (TextView) findViewById(R.id.number);

        getOffersDetails();

    }

    public void increment(View v)
    {
        count++;
        number.setText("" + count);
    }
    public void decrement(View v)
    {
        if (count <= 0) count = 0;
        else count--;
        number.setText("" + count );

    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_offer_detail;
    }

    private void  getOffersDetails() {
        ProgressDialog dialog = new ProgressDialog(OfferDetailActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","getOffersDetails");
        params.put("userID", "3");
        params.put("langCode", "ar");
        params.put("offerID","119");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper1().updateProjectAssign4(params, new ApiRequestHelper1.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    offerDetailsModel = (OfferDetailsModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (offerDetailsModel != null) {
                        if (offerDetailsModel.getStatus()  && offerDetailsModel.getIsUserAuthTokenValid() != null) {

                            ImageView image = (ImageView) findViewById(R.id.dentalofferImage);
                            Glide.with(OfferDetailActivity.this)
                                    .load(offerDetailsModel.getData().getOfferDetails().getImageUrl())
                                    .into(image);

                            TextView tagline = (TextView) findViewById(R.id.tagline);
                            tagline.setText(offerDetailsModel.getData().getOfferDetails().getOfferTagline());

                            TextView description = (TextView) findViewById(R.id.description);
                            description.setText(offerDetailsModel.getData().getOfferDetails().getOfferDescription());

                            TextView offerPriceText = (TextView) findViewById(R.id.offerPriceText);
                            offerPriceText.setText(offerDetailsModel.getData().getOfferDetails().getPrice() + "AED OR " + offerDetailsModel.getData().getOfferDetails().getPoint() + " PTS " + "Excluding" + offerDetailsModel.getData().getOfferDetails().getOfferDiscount() + "% VAT");

                            TextView saving = (TextView) findViewById(R.id.saving);
                            saving.setText("Estimated Savings " + offerDetailsModel.getData().getOfferDetails().getEstimatedSaving() + "AED");

                            TextView validDate = (TextView) findViewById(R.id.validDate);
                            validDate.setText(offerDetailsModel.getData().getOfferDetails().getOfferToDate());

                            final PageAdapter1 adapter = new PageAdapter1(getSupportFragmentManager(),3,offerDetailsModel, OfferDetailActivity.this);
                            viewPager.setAdapter(adapter);


                        } else {

                        }
                    } else {
                        Utils.showLongToast(OfferDetailActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(OfferDetailActivity.this, apiResponse);
                }
            });
        } else {
            Utils.alert_dialog(OfferDetailActivity.this);
        }
    }
}