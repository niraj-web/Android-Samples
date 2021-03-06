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

public class EmployeeNameAdapter extends RecyclerView.Adapter<EmployeeNameAdapter.RecyclerViewHolder>
{
    Context mcontext;
    private List<ProjectAssModel.UsersList> userList;
    CommonInterface commonInterface;

    public EmployeeNameAdapter(Context mcontext, List<ProjectAssModel.UsersList> userList, CommonInterface commonInterface)
    {
        this.mcontext = mcontext;
        this.userList = userList;
        this.commonInterface = commonInterface;
    }
    @NonNull
    @Override
    public EmployeeNameAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list, parent,false);
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
