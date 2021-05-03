package com.example.fragmentassignment.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.fragmentassignment.Model.OfferDetailsModel;


public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private OfferDetailsModel offerDetailsModel;
    Context context;

    public PageAdapter(@NonNull FragmentManager fm,int NumOfTabs, OfferDetailsModel offerDetailsModel, Context context) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.offerDetailsModel = offerDetailsModel;
        this.context = context;
    }

    @NonNull
    @Override

 public Fragment getItem(int position) {
        switch (position) {

            case 0:

             DETAILS tab1 = new DETAILS(offerDetailsModel.getData().getOfferDetails().getOfferBrandDesc(),context);
              return tab1;
            case 1:
                LOCATION tab2 = new LOCATION();
                return tab2;
            case 2:
               TC tab3 = new TC(offerDetailsModel.getData().getOfferDetails().getTncDescription(), context);
                return tab3;
            default:
                return null;


        }
    }
        @Override
        public int getCount () {
            return mNumOfTabs;
        }
    }
