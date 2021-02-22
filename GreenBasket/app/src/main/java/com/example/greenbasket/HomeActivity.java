package com.example.greenbasket;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;

import com.example.greenbasket.adapters.FruitAdpater;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.FruitModel;
import Model.Products;
import ViewHolder.ProductViewHolder;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private ArrayList<FruitModel> imageModelArrayList;
    private FruitAdpater adapter;

    private int[] myImageList = new int[]{R.drawable.apple, R.drawable.mango,R.drawable.straw, R.drawable.pineapple,R.drawable.orange,R.drawable.blue,R.drawable.water};
    private String[] myImageNameList = new String[]{"Apple","Mango" ,"Strawberry","Pineapple","Orange","Blueberry","Watermelon"};


    private DatabaseReference ProductsRef;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        Paper.init(this);

        adapter=new FruitAdpater(this,eatFruits());

        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }


    @Override
    protected void onStart() {
        super.onStart();

       /* FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(ProductsRef, Products.class)
                        .build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull Products products) {
                        productViewHolder.txtProductName.setText(products.getPname());
                        productViewHolder.txtProductDescription.setText(products.getPrice());
                        productViewHolder.txtProductPrice.setText("Price = " + products.getPname() + "$");
                        Picasso.get().load(products.getImage()).into(productViewHolder.imageView);


                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_add_new_product, parent, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };
*/
//        recyclerView.setAdapter(adapter);
//        adapter.startListening();
    }

    private  ArrayList<FruitModel> eatFruits()
    {
        ArrayList<FruitModel> list = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            FruitModel fruitModel = new FruitModel();
            fruitModel.setName(myImageNameList[i]);
            fruitModel.setImage_drawable(myImageList[i]);
            list.add(fruitModel);
        }

        return list;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings)
//        {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            Paper.book().destroy();

            Intent intent = new Intent(HomeActivity.this, LoginorRegisterActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }


        return false;
    }
}

