package com.example.ratingassignment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ratingassignment.Model.ProjectAssModel;

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
                ACTIVE tab1 = new ACTIVE(projectAssModel.getData().getActive(),context);
                return tab1;
            case 1:
                EXPIRED tab2 = new EXPIRED(projectAssModel.getData().getExpired(),context);
                return tab2;
            case 2:
                REDEEMED tab3 = new REDEEMED(projectAssModel.getData().getRedeem(),context);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
