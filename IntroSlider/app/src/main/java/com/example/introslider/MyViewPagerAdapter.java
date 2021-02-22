package com.example.introslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class MyViewPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private int[] layouts;
    private WelcomeActivity welcomeActivity;

    public MyViewPagerAdapter() {

    }
    public MyViewPagerAdapter(int[] layouts, WelcomeActivity welcomeActivity) {

        this.layouts = layouts;
        this.welcomeActivity = welcomeActivity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater = (LayoutInflater) welcomeActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(layouts[position],container,false);
        container.addView(view);
        return view;
    }


    @Override
    public int getCount()
    {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        View view = (View) object;
        container.removeView(view);
    }
}
