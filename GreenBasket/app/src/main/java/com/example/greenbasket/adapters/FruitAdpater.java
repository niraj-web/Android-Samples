package com.example.greenbasket.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.greenbasket.Details;
import com.example.greenbasket.HomeActivity;
import com.example.greenbasket.R;

import java.util.ArrayList;

import Model.FruitModel;

public class FruitAdpater extends RecyclerView.Adapter<FruitAdpater.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<FruitModel> imageModelArrayList;

    private HomeActivity homeActivity;

    public FruitAdpater(HomeActivity homeActivity, ArrayList<FruitModel> imageModelArrayList) {
        inflater = LayoutInflater.from(homeActivity);
        this.imageModelArrayList = imageModelArrayList;
        this.homeActivity = homeActivity;
    }


    @Override
    public FruitAdpater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(FruitAdpater.MyViewHolder holder, int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        holder.time.setText(imageModelArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.tv);
            iv = (ImageView) itemView.findViewById(R.id.iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    Intent i = new Intent(homeActivity, Details.class);
                    i.putExtra("image",imageModelArrayList.get(getAdapterPosition()).getImage_drawable());
                    i.putExtra("name",imageModelArrayList.get(getAdapterPosition()).getName());
                    homeActivity.startActivity(i);

                }
            });
        }

    }
}



