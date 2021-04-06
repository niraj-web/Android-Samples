package com.example.attendenceassignment.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendenceassignment.MainActivity;
import com.example.attendenceassignment.Model.ProjectAssModel;
import com.example.attendenceassignment.R;
import com.example.attendenceassignment.utils.CommonInterface;

import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.RecyclerViewHolder>
{
    Context mContext;
  //  private List<String> monthList;
    CommonInterface commonInterface;
    Dialog dialog;


    String monthList[] = {"january", "february", "march", "april",
            "may", "june", "july", "august", "september", "october", "November", "December"};
   // private String month;

    public MonthAdapter(Context mContext, CommonInterface commonInterface) {
        this.mContext = mContext;
        this.commonInterface = commonInterface;
      //  this.month = month;

    }

    @NonNull
    @Override
    public MonthAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.month, parent, false);
        return new MonthAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthAdapter.RecyclerViewHolder holder, int position) {
        holder.tvMonth.setText(monthList[position]);
    }

    @Override
    public int getItemCount() {
        return monthList.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvMonth;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
           tvMonth = itemView.findViewById(R.id.month);

           tvMonth.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   commonInterface.onListSelected(monthList[getAdapterPosition()],"month");


               }
           });
        }
    }
}
