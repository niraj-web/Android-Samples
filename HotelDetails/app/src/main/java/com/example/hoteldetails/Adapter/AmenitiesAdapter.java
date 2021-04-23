package com.example.hoteldetails.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hoteldetails.DetailActivity;
import com.example.hoteldetails.MainActivity;
import com.example.hoteldetails.Model.RoomDetailsModel;
import com.example.hoteldetails.R;

import java.util.List;

public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.RecyclerViewHolder> {
    Context mcontext;
    private List<RoomDetailsModel.AmenitiesDatum> amenitiesDatumList;
    DetailActivity mainActivity;

    public AmenitiesAdapter(DetailActivity mainActivity, List<RoomDetailsModel.AmenitiesDatum> amenitiesDatumList, Context mcontext) {
        this.mcontext = mcontext;
        this.amenitiesDatumList=amenitiesDatumList;
        this.mainActivity=mainActivity;
    }
    @NonNull
    @Override
    public AmenitiesAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.amenities_list, parent,false);
        return new AmenitiesAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmenitiesAdapter.RecyclerViewHolder holder, int position) {
        holder.planeText.setText(amenitiesDatumList.get(position).getType());
       // holder.plane.setImageURI(Uri.parse(amenitiesDatumList.get(position).getIcon()));
        Glide.with(mcontext)
                .load(amenitiesDatumList.get(position).getIcon())
                .into(holder.plane);
    }

    @Override
    public int getItemCount() {
        return amenitiesDatumList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView plane;
        TextView planeText;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            plane = itemView.findViewById(R.id.plane);
            planeText = itemView.findViewById(R.id.planeText);
            plane = (ImageView) itemView.findViewById(R.id.plane);

        }
    }
}
