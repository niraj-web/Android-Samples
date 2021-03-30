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

public class TsHourAdapter extends RecyclerView.Adapter<TsHourAdapter.RecyclerViewHolder>
{
    Context mContext;
    private List<String> tsHourList;
    CommonInterface commonInterface;
    private String tsHour;

    public TsHourAdapter( Context mContext,List<String> tsHourList,  CommonInterface commonInterface,String tsHour)
    {
       this.mContext = mContext;
       this.tsHourList = tsHourList;
       this.commonInterface = commonInterface;
       this.tsHour = tsHour;
    }
    @NonNull
    @Override
    public TsHourAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tshour, parent, false);
        return new TsHourAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TsHourAdapter.RecyclerViewHolder holder, int position) {
        holder.tvHour.setText(tsHourList.get(position));
    }

    @Override
    public int getItemCount() {
        return tsHourList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvHour;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHour = itemView.findViewById(R.id.tshour);

            tvHour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    commonInterface.onListSelected(tsHourList.get(getAdapterPosition()),tsHour);
                }
            });
        }
    }
}
