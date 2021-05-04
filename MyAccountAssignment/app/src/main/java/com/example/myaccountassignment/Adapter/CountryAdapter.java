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

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.RecyclerViewHolder> {
    Context mcontext;
    private List<AccountModel.Nationality> countrylist;
    CommonInterface commonInterface;

    public CountryAdapter(Context mcontext, List<AccountModel.Nationality> countrylist, CommonInterface commonInterface)
    {
        this.mcontext = mcontext;
        this.countrylist = countrylist;
        this.commonInterface = commonInterface;
    }

    @NonNull
    @Override
    public CountryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list, parent,false);
        return new CountryAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.RecyclerViewHolder holder, int position) {
        holder.countryList.setText(countrylist.get(position).getCountryName());
    }

    @Override
    public int getItemCount() {
        return countrylist.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView countryList;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            countryList = itemView.findViewById(R.id.countryList);
            countryList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commonInterface.onListSelected(countrylist.get(getAdapterPosition()),"Addition");
                }
            });
        }
    }
}
