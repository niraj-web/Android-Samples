package com.example.hoteldetails.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoteldetails.DetailActivity;
import com.example.hoteldetails.MainActivity;
import com.example.hoteldetails.Model.RoomDetailsModel;
import com.example.hoteldetails.R;

import java.io.Serializable;
import java.util.List;

public class RoomTypeAdapter extends RecyclerView.Adapter<RoomTypeAdapter.RecyclerViewHolder>
{
    Context mcontext;
    private List<RoomDetailsModel.Data> roomtypelist;
    MainActivity mainActivity;

    public RoomTypeAdapter(MainActivity mainActivity, List<RoomDetailsModel.Data> roomtypelist, Context mcontext) {
        this.mcontext = mcontext;
        this.roomtypelist=roomtypelist;
        this.mainActivity=mainActivity;
    }
    @NonNull
    @Override
    public RoomTypeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hoteltype_list, parent,false);
        return new RoomTypeAdapter.RecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RoomTypeAdapter.RecyclerViewHolder holder, int position) {
        holder.tvRoomType.setText(roomtypelist.get(position).getTitle() +" - " + " Starting " + roomtypelist.get(position).getPrice() + " AED  OR " +  roomtypelist.get(position).getBuyPoints() + " PTS ");
        holder.tvOccupancy.setText("Occupancy : " + roomtypelist.get(position).getTotalGuest().getAdultMax() + " adults + " + roomtypelist.get(position).getTotalGuest().getChildMax() + " child");
    }

    @Override
    public int getItemCount() {
        return roomtypelist.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomType;
        TextView tvOccupancy;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRoomType = itemView.findViewById(R.id.tvRoomType);
            tvOccupancy = itemView.findViewById(R.id.tvOccupancy);

            tvRoomType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mcontext, DetailActivity.class);
                    intent.putExtra("someData", (Serializable) roomtypelist.get(getAdapterPosition()));
                    mcontext.startActivity(intent);
                }
            });
        }
    }
}
