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

public class ClientHourAdapter extends RecyclerView.Adapter<ClientHourAdapter.RecyclerViewHolder>
{
    Context mcontext;
    private List<String> ClientHourList;
    CommonInterface commonInterface;
    private String ClientHour;

    public ClientHourAdapter(Context mcontext,List<String> ClientHourList, CommonInterface commonInterface,String ClientHour)
    {
        this.mcontext = mcontext;
        this.ClientHourList = ClientHourList;
        this.commonInterface = commonInterface;
        this.ClientHour = ClientHour;

    }
    @NonNull
    @Override
    public ClientHourAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clienthour,parent,false);
        return new ClientHourAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHourAdapter.RecyclerViewHolder holder, int position) {
        holder.tvClientHr.setText(ClientHourList.get(position));

    }

    @Override
    public int getItemCount() {
        return ClientHourList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvClientHr;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClientHr = itemView.findViewById(R.id.clientHour);

            tvClientHr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  commonInterface.onListSelected(ClientHourList.get(getAdapterPosition()),ClientHour);
                }
            });
        }
    }
}
