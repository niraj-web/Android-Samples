package com.example.hotelbookingassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbookingassignment.MainActivity;
import com.example.hotelbookingassignment.Model.ProjectAssModel;
import com.example.hotelbookingassignment.R;
import com.example.hotelbookingassignment.utils.CommonInterface;

import java.util.List;

public class AEDVoucherAdapter extends RecyclerView.Adapter<AEDVoucherAdapter.RecyclerViewHolder>
{
    Context mcontext;
    private List<ProjectAssModel.Denomination> voucherList;
    CommonInterface commonInterface;

    public AEDVoucherAdapter(Context mcontext, List<ProjectAssModel.Denomination> voucherList, CommonInterface commonInterface)
    {
        this.mcontext = mcontext;
        this.voucherList = voucherList;
        this.commonInterface = commonInterface;

    }
    @NonNull
    @Override
    public AEDVoucherAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_list, parent,false);
        return new AEDVoucherAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AEDVoucherAdapter.RecyclerViewHolder holder, int position) {
        holder.points.setText(voucherList.get(position).getDenomPoints() + "PTS");
        holder.voucherName.setText("AED" + voucherList.get(position).getDenomPrice() + "Voucher");

    }

    @Override
    public int getItemCount() {
        return voucherList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView points;
        TextView voucherName;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            points = itemView.findViewById(R.id.points);
            voucherName = itemView.findViewById(R.id.voucherName);

        }
    }
}
