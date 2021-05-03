package com.example.fragmentassignment.Adapter;

import android.app.Activity;
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
import com.example.fragmentassignment.FourFragment;
import com.example.fragmentassignment.MainActivity;
import com.example.fragmentassignment.Model.DentalModel;
import com.example.fragmentassignment.R;
import com.example.fragmentassignment.SecondFragment;
import com.example.fragmentassignment.utils.CommonInterface;

import java.util.List;

public class DentalOfferAdapter extends RecyclerView.Adapter<DentalOfferAdapter.RecyclerViewHolder> {

    Context mcontext;
    private List<DentalModel.Data> offerlist;
    CommonInterface commonInterface;
    private Activity getActivity;

    public DentalOfferAdapter(Context mcontext, List<DentalModel.Data> offerlist, Activity getActivity)
    {
        this.mcontext = mcontext;
        this.offerlist = offerlist;
        this.getActivity = getActivity;
        this.commonInterface = commonInterface;

    }
    @NonNull
    @Override
    public com.example.fragmentassignment.Adapter.DentalOfferAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dental_list, parent,false);
        return new com.example.fragmentassignment.Adapter.DentalOfferAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.fragmentassignment.Adapter.DentalOfferAdapter.RecyclerViewHolder holder, int position) {
        Glide.with(mcontext)
                .load(offerlist.get(position).getImageUrl())
                .into(holder.dentalImage);

        holder.flatoff.setText(offerlist.get(position).getOfferTitle());
        holder.AED.setText(offerlist.get(position).getPrice() + " AED OR " + offerlist.get(position).getPoint() + " PTS" );

    }

    @Override
    public int getItemCount() {
        return offerlist.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView dentalImage;
        TextView flatoff;
        TextView AED;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            dentalImage = itemView.findViewById(R.id.dentalImage);
            flatoff = itemView.findViewById(R.id.flatoff);
            AED = itemView.findViewById(R.id.AED);

            dentalImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MainActivity.replaceFragment(getActivity,new FourFragment(),true);
                }
            });
        }
    }
}
