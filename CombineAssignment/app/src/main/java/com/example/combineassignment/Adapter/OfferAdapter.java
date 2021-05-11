package com.example.combineassignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.combineassignment.Activities.HospitalActivity;
import com.example.combineassignment.Model.OffersModel;
import com.example.combineassignment.R;
import com.example.combineassignment.utils.CommonInterface;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<com.example.combineassignment.Adapter.OfferAdapter.RecyclerViewHolder> {
    Context mcontext;
    private List<OffersModel.Data> imageList;
    CommonInterface commonInterface;

    public OfferAdapter(Context mcontext, List<OffersModel.Data> imageList)
    {
        this.mcontext = mcontext;
        this.imageList = imageList;
        this.commonInterface = commonInterface;

    }
    @NonNull
    @Override
    public com.example.combineassignment.Adapter.OfferAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_list, parent,false);
        return new com.example.combineassignment.Adapter.OfferAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.combineassignment.Adapter.OfferAdapter.RecyclerViewHolder holder, int position) {
        holder.offerName.setText(imageList.get(position).getCategoryTitle());
        Glide.with(mcontext)
                .load(imageList.get(position).getImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.offersImage);

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView offersImage;
        TextView offerName;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            offersImage = itemView.findViewById(R.id.offersImage);
            offerName = itemView.findViewById(R.id.offerName);

            offerName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent intent = new Intent(mcontext, HospitalActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        }
    }
}
