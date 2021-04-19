package com.example.hotelbookingassignment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.hotelbookingassignment.Model.ProjectAssModel;

import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private ProjectAssModel projectAssModel;
    Context context;

    public PageAdapter(@NonNull FragmentManager fm, int NumOfTabs, ProjectAssModel projectAssModel, Context context) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.projectAssModel = projectAssModel;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
              DETAILS tab1 = new DETAILS(projectAssModel.getData().getRewardDetails().getVoucherDescription(),context);
              return tab1;
            case 1:
                LOCATION tab2 = new LOCATION(projectAssModel.getData().getLocation(), context);
                return tab2;
            case 2:
               TC tab3 = new TC(projectAssModel.getData().getRewardDetails().getTncDescription(), context);
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
