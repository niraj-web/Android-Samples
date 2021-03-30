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

public class EmployeeNameAdapter extends RecyclerView.Adapter<EmployeeNameAdapter.RecyclerViewHolder>
{
    Context mContext;
    private List<ProjectAssModel.UsersList> userList;
    CommonInterface commonInterface;

    public EmployeeNameAdapter(Context mContext, List<ProjectAssModel.UsersList> userList, CommonInterface commonInterface) {
        this.mContext = mContext;
        this.userList = userList;
        this.commonInterface = commonInterface;
    }

    @NonNull
    @Override
    public EmployeeNameAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list, parent, false);
        return new EmployeeNameAdapter.RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeNameAdapter.RecyclerViewHolder holder, int position) {
        holder.tvEmployeeSelect.setText(userList.get(position).getUserID());
    }

    @Override
    public int getItemCount() {

        return userList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvEmployeeSelect;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmployeeSelect = itemView.findViewById(R.id.employeename);

            tvEmployeeSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commonInterface.onListSelected(userList.get(getAdapterPosition()),"");
                }
            });
        }
    }
}
