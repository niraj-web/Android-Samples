package com.example.combineassignment.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.combineassignment.Fragment.DETAILS1;
import com.example.combineassignment.Fragment.LOCATION1;
import com.example.combineassignment.Fragment.TC1;
import com.example.combineassignment.Model.OfferDetailsModel;

public class PageAdapter1 extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    private OfferDetailsModel offerDetailsModel;
    Context context;

    public PageAdapter1(@NonNull FragmentManager fm, int NumOfTabs, OfferDetailsModel offerDetailsModel, Context context) {
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
                DETAILS1 tab1 = new DETAILS1(offerDetailsModel.getData().getOfferDetails().getOfferBrandDesc(),context);
                return tab1;
            case 1:
                LOCATION1 tab2 = new LOCATION1();
                return tab2;
            case 2:
                TC1 tab3 = new TC1(offerDetailsModel.getData().getOfferDetails().getTncDescription(), context);
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
