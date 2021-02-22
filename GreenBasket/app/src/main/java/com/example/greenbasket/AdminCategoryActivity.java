package com.example.greenbasket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView flower, fruit, ayurveda, bonsai;
    private ImageView cactus, climbers, hanging, palm;
    private ImageView seeds, vegetable, tools, tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);


        flower = (ImageView) findViewById(R.id.Flower);
        fruit = (ImageView) findViewById(R.id.Fruit);
        ayurveda = (ImageView) findViewById(R.id.Ayurveda);
        bonsai = (ImageView) findViewById(R.id.Bonsai);
        cactus = (ImageView) findViewById(R.id.Cactus);
        climbers = (ImageView) findViewById(R.id.Climbers);
        hanging = (ImageView) findViewById(R.id.Hanging);
        palm = (ImageView) findViewById(R.id.Palm);
        seeds = (ImageView) findViewById(R.id.Seeds);
        vegetable = (ImageView) findViewById(R.id.Vegetable);
        tools = (ImageView) findViewById(R.id.Tools);
        tutorial = (ImageView) findViewById(R.id.Tutorial);



        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Flower");
                startActivity(intent);
            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Fruit");
                startActivity(intent);
            }
        });

        ayurveda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Ayurveda");
                startActivity(intent);
            }
        });

        bonsai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Bonsai");
                startActivity(intent);
            }
        });
        cactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Cactus");
                startActivity(intent);
            }
        });
        climbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Climbers");
                startActivity(intent);
            }
        });
        hanging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Hanging");
                startActivity(intent);
            }
        });

        palm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Palm");
                startActivity(intent);
            }
        });
        seeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Seeds");
                startActivity(intent);
            }
        });
        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Vegetable");
                startActivity(intent);
            }
        });

        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Tools");
                startActivity(intent);
            }
        });
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Tutorial");
                startActivity(intent);
            }
        });
    }
}