package com.example.hoteldetails.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hoteldetails.DetailActivity;
import com.example.hoteldetails.MainActivity;
import com.example.hoteldetails.Model.RoomDetailsModel;
import com.example.hoteldetails.R;

import java.util.List;

public class ImageSwiperAdapter extends RecyclerView.Adapter<ImageSwiperAdapter.ImageSwiper> {
    Context mcontext;
    private List<String> imageList;
    DetailActivity mainActivity;

    public ImageSwiperAdapter(List<String> imageList, Context mcontext, DetailActivity mainActivity) {
        this.mcontext = mcontext;
        this.imageList=imageList;
        this.mainActivity=mainActivity;
    }
    @NonNull
    @Override
    public ImageSwiperAdapter.ImageSwiper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list, parent,false);
        return new ImageSwiperAdapter.ImageSwiper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSwiperAdapter.ImageSwiper holder, int position) {
        Glide.with(mcontext).load(imageList.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


    public class ImageSwiper extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ImageSwiper(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
