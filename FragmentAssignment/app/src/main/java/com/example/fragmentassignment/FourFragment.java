package com.example.fragmentassignment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.fragmentassignment.Adapter.DentalOfferAdapter;
import com.example.fragmentassignment.Adapter.PageAdapter;
import com.example.fragmentassignment.Model.DentalModel;
import com.example.fragmentassignment.Model.OfferDetailsModel;
import com.example.fragmentassignment.Model.OffersModel;
import com.example.fragmentassignment.utils.ApiRequestHelper;
import com.example.fragmentassignment.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

public class FourFragment extends BaseFragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OffersModel offersModel;
    Context context;
    private OfferDetailsModel offerDetailsModel;
    private TextView tagline;
    private ImageView image;
    private TextView description;
    private TextView offerPriceText;
    private TextView saving;
    private TextView validDate;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView buyNow;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourFragment newInstance(String param1, String param2) {
        FourFragment fragment = new FourFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




        getOffersDetails();
    }

    private void  getOffersDetails() {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","getOffersDetails");
        params.put("userID", "3");
        params.put("langCode", "en");
        params.put("offerID","119");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign3(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    offerDetailsModel = (OfferDetailsModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (offerDetailsModel != null) {
                        if (offerDetailsModel.getStatus()  && offerDetailsModel.getIsUserAuthTokenValid() != null) {

                           
                            Glide.with(getContext())
                                    .load(offerDetailsModel.getData().getOfferDetails().getImageUrl())
                                    .into(image);

                           
                            tagline.setText(offerDetailsModel.getData().getOfferDetails().getOfferTagline());
                            
                            description.setText(offerDetailsModel.getData().getOfferDetails().getOfferDescription());

                           
                            offerPriceText.setText(offerDetailsModel.getData().getOfferDetails().getPrice() + "AED OR " + offerDetailsModel.getData().getOfferDetails().getPoint() + " PTS " + "Excluding" + offerDetailsModel.getData().getOfferDetails().getOfferDiscount() + "% VAT");
                            
                            saving.setText("Estimated Savings " + offerDetailsModel.getData().getOfferDetails().getEstimatedSaving() + "AED");
                            
                            validDate.setText(offerDetailsModel.getData().getOfferDetails().getOfferToDate());

                            final PageAdapter adapter = new PageAdapter(getFragmentManager(),3,offerDetailsModel,getContext());
                            viewPager.setAdapter(adapter);

                        } else {

                        }
                    } else {
                        Utils.showLongToast(getContext(), Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(getContext(), apiResponse);
                }
            });
        } else {
            Utils.alert_dialog(getContext());
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        image = (ImageView) view.findViewById(R.id.dentalofferImage);
        tagline = (TextView) view.findViewById(R.id.tagline);
        description = (TextView) view.findViewById(R.id.description);
        offerPriceText = (TextView) view.findViewById(R.id.offerPriceText);
         saving = (TextView) view.findViewById(R.id.saving);
         validDate = (TextView) view.findViewById(R.id.validDate);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        buyNow = (TextView) view.findViewById(R.id.number);

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                       MainActivity.replaceFragment(getActivity(),new FifthFragment(), true);
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("DETAILS"));
        tabLayout.addTab(tabLayout.newTab().setText("LOCATION"));
        tabLayout.addTab(tabLayout.newTab().setText("T&C's"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


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
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.fragment_four;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_four, container, false);
        return rootView;

        // return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
