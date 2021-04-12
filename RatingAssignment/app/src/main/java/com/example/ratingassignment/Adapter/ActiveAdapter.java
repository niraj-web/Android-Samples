package com.example.ratingassignment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratingassignment.ACTIVE;
import com.example.ratingassignment.Model.ProjectAssModel;
import com.example.ratingassignment.R;

import java.util.List;

public class ActiveAdapter extends RecyclerView.Adapter<ActiveAdapter.RecyclerViewHolder> {
    Context mContext;
    private List<ProjectAssModel.Active> ActiveList;

   public ActiveAdapter(List<ProjectAssModel.Active> activeList, Context mContext) {
        this.mContext = mContext;
        this.ActiveList = activeList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.active.setText(ActiveList.get(position).getOfferTitle() );
        holder.active1.setText(ActiveList.get(position).getOfferToDate());

    }

    @Override
    public int getItemCount() {
        return ActiveList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
       TextView active;
       TextView active1;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            active = itemView.findViewById(R.id.active);
            active1 = itemView.findViewById(R.id.active1);
        }
    }
}
