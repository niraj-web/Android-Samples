package com.example.testing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Model.ProjectAssModel;
import com.example.testing.R;
import com.example.testing.utils.CommonInterface;

import java.util.List;

public class ProjectNameAdapter extends RecyclerView.Adapter<ProjectNameAdapter.RecyclerViewHolder>
{
    Context mcontext;
    private List<ProjectAssModel.Project> projectList;
    CommonInterface commonInterface;

    public ProjectNameAdapter(Context mcontext,List<ProjectAssModel.Project> projectList,CommonInterface commonInterface)
    {
        this.mcontext = mcontext;
        this.projectList = projectList;
        this.commonInterface = commonInterface;
    }
    @NonNull
    @Override
    public ProjectNameAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list, parent,false);
        return new ProjectNameAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectNameAdapter.RecyclerViewHolder holder, int position) {
         holder.tvProjectName.setText(projectList.get(position).getProjectName());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvProjectName;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProjectName = itemView.findViewById(R.id.projectname);

            tvProjectName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  commonInterface.onListSelected(projectList.get(getAdapterPosition()),"");
                }
            });
        }
    }
}
