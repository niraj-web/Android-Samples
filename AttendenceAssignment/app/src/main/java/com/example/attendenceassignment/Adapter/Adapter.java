package com.example.attendenceassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendenceassignment.Model.ProjectAssModel;
import com.example.attendenceassignment.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.RecyclerViewHolder>{
    Context mContext;
    List<ProjectAssModel.Datum> data;

    public Adapter(Context mContext, List<ProjectAssModel.Datum> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public Adapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
        return new Adapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.RecyclerViewHolder holder, int position) {
       holder.tvdate.setText(data.get(position).getDayDate());
        holder.in.setText(data.get(position).getInTime());
        holder.outt.setText(data.get(position).getMinutes());
        holder.out.setText(data.get(position).getOutTime());
        holder.worked.setText(data.get(position).getWrkedHrs());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvdate;
        TextView tvDay;
        TextView inTime;
        TextView actualHrs;
        TextView in;
        TextView outt;
        TextView out;
        TextView worked;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvdate = itemView.findViewById(R.id.tvdate);
            tvDay = itemView.findViewById(R.id.tvDay);
            inTime = itemView.findViewById(R.id.intime);
            actualHrs = itemView.findViewById(R.id.actualHrs);
            in = itemView.findViewById(R.id.in);
            outt = itemView.findViewById(R.id.outt);
            out = itemView.findViewById(R.id.out);
            worked = itemView.findViewById(R.id.worked);

        }
    }
}
