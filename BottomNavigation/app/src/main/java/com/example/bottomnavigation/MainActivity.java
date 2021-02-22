package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomnavigation.Fragment.HomeFragment;
import com.example.bottomnavigation.Fragment.NotificationFragment;
import com.example.bottomnavigation.Fragment.SmsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance("", ""));
    }

    private void openFragment(Fragment newInstance)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newInstance);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openFragment(HomeFragment.newInstance("", ""));
                    return true;
                case R.id.navigation_sms:
                    openFragment(NotificationFragment.newInstance("", ""));
                    return true;
                case R.id.navigation_notifications:
                    openFragment(SmsFragment.newInstance("", ""));
                    return true;
            }
            return false;
        }
    };




}