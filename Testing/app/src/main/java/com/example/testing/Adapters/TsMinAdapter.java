package com.example.testing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.R;
import com.example.testing.utils.CommonInterface;

import java.util.List;

public class TsMinAdapter extends RecyclerView.Adapter<TsMinAdapter.RecyclerViewHolder>
{
    Context mContext;
    private List<String> tsMinuteList;
    CommonInterface commonInterface;
    private String tsMin;

    public TsMinAdapter( Context mContext,List<String> tsMinuteList,CommonInterface commonInterface,String tsMin)
    {
        this.mContext = mContext;
        this.tsMinuteList = tsMinuteList;
        this.commonInterface = commonInterface;
        this.tsMin = tsMin;
    }

    @NonNull
    @Override
    public TsMinAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tsminute, parent, false);
        return new TsMinAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TsMinAdapter.RecyclerViewHolder holder, int position) {
       holder.tvMin.setText(tsMinuteList.get(position));
    }

    @Override
    public int getItemCount() {
        return tsMinuteList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvMin;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMin = itemView.findViewById(R.id.tsMinute);

            tvMin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 commonInterface.onListSelected(tsMinuteList.get(getAdapterPosition()),tsMin);
                }
            });
        }
    }
}
