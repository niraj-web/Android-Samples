package com.example.myaccountassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaccountassignment.Model.AccountModel;
import com.example.myaccountassignment.R;
import com.example.myaccountassignment.utils.CommonInterface;

import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.RecyclerViewHolder>{
    Context mcontext;
    private List<AccountModel.Emirate> nameList;
    CommonInterface commonInterface;

    public NameAdapter(Context mcontext, List<AccountModel.Emirate> nameList, CommonInterface commonInterface)
    {
        this.mcontext = mcontext;
        this.nameList = nameList;
        this.commonInterface = commonInterface;
    }
    @NonNull
    @Override
    public NameAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name, parent,false);
        return new NameAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameAdapter.RecyclerViewHolder holder, int position) {

        holder.nameeList.setText(nameList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView nameeList;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            nameeList = (TextView) itemView.findViewById(R.id.namee_list);

            nameeList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commonInterface.onListSelected(nameList.get(getAdapterPosition()),"Addition");
                }
            });
        }
    }
}
