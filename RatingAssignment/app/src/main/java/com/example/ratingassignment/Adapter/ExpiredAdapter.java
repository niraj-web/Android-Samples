package com.example.ratingassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratingassignment.EXPIRED;
import com.example.ratingassignment.Model.ProjectAssModel;
import com.example.ratingassignment.R;

import java.util.List;


public class ExpiredAdapter  extends RecyclerView.Adapter<ExpiredAdapter.RecyclerViewHolder> {
    Context mContext;
    private List<ProjectAssModel.Expired> expiredList;

    public ExpiredAdapter(List<ProjectAssModel.Expired> expiredList, Context mContext) {
        this.mContext = mContext;
        this.expiredList = expiredList;
    }
    @NonNull
    @Override
    public ExpiredAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expired, parent, false);
        return new ExpiredAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpiredAdapter.RecyclerViewHolder holder, int position) {
        holder.expired.setText(expiredList.get(position).getOfferTitle());
       holder.expired1.setText(expiredList.get(position).getOfferToDate());

    }

    @Override
    public int getItemCount() {
        return expiredList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
         TextView expired;
        TextView expired1;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            expired = itemView.findViewById(R.id.expired);
            expired1 = itemView.findViewById(R.id.expired1);
        }
    }
}

