package com.example.hotelbookingassignment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbookingassignment.Model.ProjectAssModel;
import com.example.hotelbookingassignment.R;
import com.example.hotelbookingassignment.utils.CommonInterface;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.RecyclerViewHolder> {
    Context mcontext;
    private List<ProjectAssModel.Location> locationList;


   public LocationAdapter(List<ProjectAssModel.Location> locationList, Context mcontext) {
        this.mcontext = mcontext;
        this.locationList=locationList;
    }

    @NonNull
    @Override
    public LocationAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list, parent,false);
        return new LocationAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.RecyclerViewHolder holder, int position) {
        holder.address.setText(locationList.get(position).getOutletAddress());
        Log.e("Tag",locationList.get(position).getOutletAddress());

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView address;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
        }
    }
}
