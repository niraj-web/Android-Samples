package com.example.hospitalbookingassignmet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalbookingassignmet.Model.ModelClass;
import com.example.hospitalbookingassignmet.R;
import com.example.hospitalbookingassignmet.utils.CommonInterface;

import java.util.List;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.RecyclerViewHolder> {
    Context mcontext;
    private List<ModelClass.Data> clinicList;
    CommonInterface commonInterface;

    public ClinicAdapter(Context mcontext,List<ModelClass.Data> clinicList,CommonInterface commonInterface)
    {
        this.mcontext = mcontext;
        this.clinicList = clinicList;
        this.commonInterface = commonInterface;
    }
    @NonNull
    @Override
    public ClinicAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_list, parent,false);
        return new ClinicAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicAdapter.RecyclerViewHolder holder, int position) {
        holder.etChooseClinic.setText(clinicList.get(position).getHosName());

    }

    @Override
    public int getItemCount() {
        return clinicList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
            TextView etChooseClinic;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            etChooseClinic = itemView.findViewById(R.id.clinicList);

            etChooseClinic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    commonInterface.onListSelected(clinicList.get(getAdapterPosition()),"");
                }
            });
        }
    }
}
