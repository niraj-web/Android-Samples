package com.example.fragmentassignment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fragmentassignment.MainActivity;
import com.example.fragmentassignment.Model.HospitalModel;
import com.example.fragmentassignment.R;
import com.example.fragmentassignment.SecondFragment;
import com.example.fragmentassignment.ThirdFragment;
import com.example.fragmentassignment.utils.CommonInterface;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.RecyclerViewHolder> {
    Context mcontext;
    private List<HospitalModel.Data> hospitalList;
    CommonInterface commonInterface;
    private Activity getActivity;

    public HospitalAdapter(Context mcontext, List<HospitalModel.Data> hospitalList,Activity getActivity)
    {
        this.mcontext = mcontext;
        this.hospitalList = hospitalList;
        this.commonInterface = commonInterface;
        this.getActivity = getActivity;

    }
    @NonNull
    @Override
    public com.example.fragmentassignment.Adapter.HospitalAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_list, parent,false);
        return new com.example.fragmentassignment.Adapter.HospitalAdapter.RecyclerViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull com.example.fragmentassignment.Adapter.HospitalAdapter.RecyclerViewHolder holder, int position) {
        holder.hospitalName.setText(hospitalList.get(position).getSubcategoryTitle());
        Glide.with(mcontext)
                .load(hospitalList.get(position).getImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.hospitalImage);

    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView hospitalImage;
        TextView hospitalName;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            hospitalImage = itemView.findViewById(R.id.hospitalImage);
            hospitalName = itemView.findViewById(R.id.hospitalName);

            hospitalImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.replaceFragment(getActivity,new ThirdFragment(),true);

                }
            });
        }
    }
}
