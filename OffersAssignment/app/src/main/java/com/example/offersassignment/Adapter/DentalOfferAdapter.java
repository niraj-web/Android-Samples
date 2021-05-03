package com.example.offersassignment.Adapter;

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
import com.example.offersassignment.DentalOfferActivity;
import com.example.offersassignment.Model.DentalModel;
import com.example.offersassignment.Model.HospitalModel;
import com.example.offersassignment.OfferDetailActivity;
import com.example.offersassignment.R;
import com.example.offersassignment.utils.CommonInterface;

import java.util.List;

public class DentalOfferAdapter extends RecyclerView.Adapter<DentalOfferAdapter.RecyclerViewHolder> {

    Context mcontext;
    private List<DentalModel.Data> offerlist;
    CommonInterface commonInterface;

    public DentalOfferAdapter(Context mcontext, List<DentalModel.Data> offerlist)
    {
        this.mcontext = mcontext;
        this.offerlist = offerlist;
        this.commonInterface = commonInterface;

    }
    @NonNull
    @Override
    public DentalOfferAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dental_list, parent,false);
        return new DentalOfferAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DentalOfferAdapter.RecyclerViewHolder holder, int position) {
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
                    Intent intent = new Intent(mcontext, OfferDetailActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        }
    }
}
