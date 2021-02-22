package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    FruitAdapter adapter;

    List<Fruits> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_fruits);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] fruits = getResources().getStringArray(R.array.item_fruits);
        String[] price = getResources().getStringArray(R.array.item_price);


        for (int i=0;i<fruits.length;i++){

            Fruits fruits1 = new Fruits(fruits[i],price[i],false);
            list.add(fruits1);
        }


        adapter = new FruitAdapter(MainActivity.this,list);
        recyclerView.setAdapter(adapter);

    }
}