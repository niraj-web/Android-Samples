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
import com.example.attendenceassignment.R;
import com.example.attendenceassignment.utils.CommonInterface;

import java.util.List;

public class YearAdapter extends RecyclerView.Adapter<YearAdapter.RecyclerViewHolder>
{
    Context mContext;
   // private List<String> yearList;
    CommonInterface commonInterface;
    Dialog dialog;

    String yearList[] = {"2015", "2016", "2017", "2018",
            "2019", "2020", "2021"};

    public YearAdapter(Context mContext, CommonInterface commonInterface) {
        this.mContext = mContext;
        this.commonInterface = commonInterface;
    }
            @NonNull
            @Override
            public YearAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.year, parent, false);
                return new YearAdapter.RecyclerViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull YearAdapter.RecyclerViewHolder holder, int position) {
                holder.tvYear.setText(yearList[position]);
            }

            @Override
            public int getItemCount() {
                return yearList.length;
            }

            public class RecyclerViewHolder extends RecyclerView.ViewHolder {
                TextView tvYear;
                public RecyclerViewHolder(@NonNull View itemView) {
                    super(itemView);
                    tvYear = itemView.findViewById(R.id.year);

                    tvYear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            commonInterface.onListSelected(yearList[getAdapterPosition()],"year");


                        }
                    });
                }
            }
        }
