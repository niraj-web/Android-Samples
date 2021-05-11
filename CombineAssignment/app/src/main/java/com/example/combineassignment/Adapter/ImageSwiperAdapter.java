package com.example.combineassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.combineassignment.Activities.DetailActivity;
import com.example.combineassignment.R;

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
    public ImageSwiper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list, parent,false);
        return new ImageSwiper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSwiper holder, int position) {
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
