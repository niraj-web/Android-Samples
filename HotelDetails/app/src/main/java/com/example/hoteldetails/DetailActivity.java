package com.example.hoteldetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hoteldetails.Adapter.AmenitiesAdapter;
import com.example.hoteldetails.Adapter.ImageSwiperAdapter;
import com.example.hoteldetails.Adapter.PageAdapter;
import com.example.hoteldetails.Model.RoomDetailsModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPager2 viewPager2;
    RoomDetailsModel.Data roomDetailsModel;
    private List<RoomDetailsModel.Data> imagelist;
    ImageSwiperAdapter imageSwiperAdapter;
    TextView number;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        roomDetailsModel = (RoomDetailsModel.Data) getIntent().getSerializableExtra("someData");

        TextView details = (TextView) findViewById(R.id.details);
        details.setText(roomDetailsModel.getDescription());

        TextView availableRoom = (TextView) findViewById(R.id.availableRoom);
        availableRoom.setText(roomDetailsModel.getTotalRooms() + " Rooms ");

        TextView AdultQ = (TextView) findViewById(R.id.AdultQ);
        AdultQ.setText(roomDetailsModel.getTotalGuest().getAdultMax());

        viewPager2 = findViewById(R.id.view_pager);
        imageSwiperAdapter = new ImageSwiperAdapter(roomDetailsModel.getGalleryData(),DetailActivity.this,DetailActivity.this);
        viewPager2.setAdapter(imageSwiperAdapter);

        TextView ChildQ = (TextView) findViewById(R.id.ChildQ);
        ChildQ.setText(roomDetailsModel.getTotalGuest().getAdultMin());

        RecyclerView amenitiesList = (RecyclerView) findViewById(R.id.RecyclerView);
        amenitiesList.setLayoutManager(new GridLayoutManager(DetailActivity.this,2));
        AmenitiesAdapter amenitiesAdapter = new AmenitiesAdapter(DetailActivity.this,roomDetailsModel.getAmenitiesData(),DetailActivity.this);
        amenitiesList.setAdapter(amenitiesAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("DETAILS"));
        tabLayout.addTab(tabLayout.newTab().setText("LOCATION"));
        tabLayout.addTab(tabLayout.newTab().setText("T&C's"));
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

        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(),3,roomDetailsModel,DetailActivity.this);
        viewPager.setAdapter(adapter);

        number = (TextView) findViewById(R.id.number);
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
}