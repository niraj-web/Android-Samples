package com.example.timesheetassignment.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timesheetassignment.Model.ProjectAssModel;
import com.example.timesheetassignment.R;
import com.example.timesheetassignment.utils.CommonInterface;

import java.util.ArrayList;
import java.util.List;


public class ProjectNameAdapter extends RecyclerView.Adapter<ProjectNameAdapter.RecyclerViewHolder> {
    Context mContext;
    private List<ProjectAssModel.Project> projectList;
    CommonInterface commonInterface;


    public ProjectNameAdapter(Context mContext, List<ProjectAssModel.Project> projectList, CommonInterface commonInterface) {
        this.mContext = mContext;
        this.projectList = projectList;
        this.commonInterface = commonInterface;
    }

    @NonNull
    @Override
    public ProjectNameAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectNameAdapter.RecyclerViewHolder holder, int position) {
        holder.tvProjectName.setText(projectList.get(position).getProjectName());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvProjectName;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProjectName = itemView.findViewById(R.id.projectname);

            tvProjectName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    commonInterface.onListSelected(projectList.get(getAdapterPosition()),"");

                }
            });
        }
    }
}


