package com.example.combineassignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.combineassignment.Activities.DetailActivity;
import com.example.combineassignment.Activities.MainActivity;
import com.example.combineassignment.Model.RoomDetailsModel;
import com.example.combineassignment.R;

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
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hoteltype_list, parent,false);
        return new RecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvRoomType.setText(roomtypelist.get(position).getTitle() +" - " + " بدء " + roomtypelist.get(position).getPrice() + " درهم أو " +  roomtypelist.get(position).getBuyPoints() + " نقاط ");
        holder.tvOccupancy.setText("شغل : " + roomtypelist.get(position).getTotalGuest().getAdultMax() + " الكبار + " + roomtypelist.get(position).getTotalGuest().getChildMax() + " طفل");
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
