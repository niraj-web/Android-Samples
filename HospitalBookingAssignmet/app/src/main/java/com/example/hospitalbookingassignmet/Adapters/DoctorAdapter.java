package com.example.hospitalbookingassignmet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalbookingassignmet.MainActivity;
import com.example.hospitalbookingassignmet.Model.ModelClass;
import com.example.hospitalbookingassignmet.R;
import com.example.hospitalbookingassignmet.utils.CommonInterface;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.RecyclerViewHolder> {
    Context mcontext;
    private List<ModelClass.Data> doctorList;
    CommonInterface commonInterface;

    public DoctorAdapter(Context mcontext, List<ModelClass.Data> doctorList, CommonInterface commonInterface)
    {
        this.mcontext = mcontext;
        this.doctorList = doctorList;
        this.commonInterface = commonInterface;
    }



    @NonNull
    @Override
    public DoctorAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list, parent,false);
        return new DoctorAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.RecyclerViewHolder holder, int position) {
        holder.etChooseDoctor.setText(doctorList.get(position).getDocName());
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView etChooseDoctor;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            etChooseDoctor = itemView.findViewById(R.id.doctorList);

            etChooseDoctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    commonInterface.onListSelected(doctorList.get(getAdapterPosition()),"");
                }
            });
        }
    }
}
