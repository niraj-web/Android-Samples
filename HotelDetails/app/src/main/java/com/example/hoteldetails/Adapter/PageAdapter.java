package com.example.hoteldetails.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.hoteldetails.DETAILS;
import com.example.hoteldetails.LOCATION;
import com.example.hoteldetails.Model.RoomDetailsModel;
import com.example.hoteldetails.TC;


public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private RoomDetailsModel.Data roomDetailsModel;
    Context context;

    public PageAdapter(@NonNull FragmentManager fm, int NumOfTabs, RoomDetailsModel.Data roomDetailsModel, Context context) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.roomDetailsModel = roomDetailsModel;
        this.context = context;
    }

    @NonNull
    @Override

 public Fragment getItem(int position) {
        switch (position) {

            case 0:

             DETAILS tab1 = new DETAILS(roomDetailsModel.getDescription(),context);
              return tab1;
            case 1:
                LOCATION tab2 = new LOCATION();
                return tab2;
            case 2:
               TC tab3 = new TC(roomDetailsModel.getTermConditions(), context);
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
