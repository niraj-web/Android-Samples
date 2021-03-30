package com.example.timesheetassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timesheetassignment.Model.ProjectAssModel;
import com.example.timesheetassignment.R;
import com.example.timesheetassignment.utils.CommonInterface;

import java.util.List;

public class WorkTypeAdapter extends RecyclerView.Adapter<WorkTypeAdapter.RecyclerViewHolder>
{
    Context mContext;
    private List<ProjectAssModel.WorkType> workTypeList;
    CommonInterface commonInterface;


    public WorkTypeAdapter(Context mContext, List<ProjectAssModel.WorkType> workTypeList,CommonInterface commonInterface) {
        this.mContext = mContext;
        this.workTypeList = workTypeList;
        this.commonInterface = commonInterface;
    }

    @NonNull
    @Override
    public WorkTypeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worktype, parent, false);
        return new WorkTypeAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkTypeAdapter.RecyclerViewHolder holder, int position) {
        holder.tvWorkTypeSelect.setText(workTypeList.get(position).getWorkTypeName());
    }

    @Override
    public int getItemCount() {
        return workTypeList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvWorkTypeSelect;
        public RecyclerViewHolder(@NonNull View itemView) {

            super(itemView);
            tvWorkTypeSelect = itemView.findViewById(R.id.workType);

            tvWorkTypeSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commonInterface.onListSelected(workTypeList.get(getAdapterPosition()),"");
                }
            });
        }
    }
}
