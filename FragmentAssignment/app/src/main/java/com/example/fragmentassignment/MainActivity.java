package com.example.fragmentassignment;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance("", ""));
    }

    public static void replaceFragment(Activity activity, Fragment frag, boolean flagsAddToBackStack) {

        FragmentManager fm = ((AppCompatActivity) activity).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
// ft.setCustomAnimations(android.R.anim_out.fade_in,android.R.anim_out.fade_out,android.R.anim_out.fade_in,android.R.anim_out.fade_out);
// ft.setCustomAnimations(R.anim_out.enter_from_right, R.anim_out.exit_to_left, R.anim_out.enter_from_left, R.anim_out.exit_to_right);
//ft.setCustomAnimations(R.anim_out.activity_open_enter, R.anim_out.activity_open_exit, R.anim_out.activity_close_enter, R.anim_out.activity_close_exit);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.container, frag, "");
        if (flagsAddToBackStack) {
            if (ft.isAddToBackStackAllowed()) {
                ft.addToBackStack(null);
            }
        }
        ft.commit();
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            openFragment(HomeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_sms:
                            openFragment(SmsFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_notifications:
                            openFragment(NotificationFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

}