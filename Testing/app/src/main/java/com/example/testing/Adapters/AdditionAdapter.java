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

public class AdditionAdapter extends RecyclerView.Adapter<AdditionAdapter.RecyclerViewHolder>
{
    Context mcontext;
    private List<ProjectAssModel.ExtraWork> ExtraworkList;
    CommonInterface commonInterface;

    public AdditionAdapter(Context mcontext, List<ProjectAssModel.ExtraWork> ExtraworkList, CommonInterface commonInterface)
    {
        this.mcontext = mcontext;
        this.ExtraworkList = ExtraworkList;
        this.commonInterface = commonInterface;
    }

    @NonNull
    @Override
    public AdditionAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addition_work,parent,false);
        return new AdditionAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdditionAdapter.RecyclerViewHolder holder, int position) {
      holder.tvadditionSelect.setText(ExtraworkList.get(position).getExtraworkName());
    }

    @Override
    public int getItemCount() {
        return ExtraworkList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvadditionSelect;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvadditionSelect = itemView.findViewById(R.id.additionWork);

            tvadditionSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  commonInterface.onListSelected(ExtraworkList.get(getAdapterPosition()),"Addition");
                }
            });
        }
    }
}
