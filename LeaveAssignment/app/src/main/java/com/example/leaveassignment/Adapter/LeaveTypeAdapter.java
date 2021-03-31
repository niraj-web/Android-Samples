package com.example.leaveassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaveassignment.Model.ProjectAssModel;
import com.example.leaveassignment.R;
import com.example.leaveassignment.utils.CommonInterface;

import java.util.List;

public class LeaveTypeAdapter extends RecyclerView.Adapter<LeaveTypeAdapter.RecyclerViewHolder>
{
    Context mcontext;
    private List<ProjectAssModel.LeaveType> leaveList;
    CommonInterface commonInterface;

    public LeaveTypeAdapter(Context mcontext,List<ProjectAssModel.LeaveType> leaveList, CommonInterface commonInterface)
    {
        this.mcontext = mcontext;
        this.leaveList = leaveList;
        this.commonInterface = commonInterface;
    }

    @NonNull
    @Override
    public LeaveTypeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leavetype, parent,false);
        return new LeaveTypeAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveTypeAdapter.RecyclerViewHolder holder, int position) {
        holder.tvLeaveTypeSelect.setText(leaveList.get(position).getLeaveTypeName());
    }

    @Override
    public int getItemCount() {
        return leaveList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
         TextView tvLeaveTypeSelect;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLeaveTypeSelect = itemView.findViewById(R.id.leavetype);

            tvLeaveTypeSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commonInterface.onListSelected(leaveList.get(getAdapterPosition()),"Leave");
                }
            });
        }
    }
}
