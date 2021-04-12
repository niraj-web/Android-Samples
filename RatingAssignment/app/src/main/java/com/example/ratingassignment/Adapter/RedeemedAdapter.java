package com.example.ratingassignment.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratingassignment.MainActivity;
import com.example.ratingassignment.Model.ProjectAssModel;
import com.example.ratingassignment.R;
import com.example.ratingassignment.REDEEMED;

import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class RedeemedAdapter  extends RecyclerView.Adapter<RedeemedAdapter.RecyclerViewHolder> {
    Context mContext;
    private List<ProjectAssModel.Redeem> redeemList;
    private Dialog dialog;



    public RedeemedAdapter(Context mContext, List<ProjectAssModel.Redeem> redeemList) {
        this.mContext = mContext;
        this.redeemList = redeemList;
    }
    @NonNull
    @Override
    public RedeemedAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.redeem, parent, false);
        return new RedeemedAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RedeemedAdapter.RecyclerViewHolder holder, int position) {
           holder.redeem.setText(redeemList.get(position).getOfferTitle());
         //  holder.redeem1.setText(redeemList.get(position).getRating());


    }

    @Override
    public int getItemCount() {
        return redeemList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView redeem;
        TextView redeem1;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

             redeem = itemView.findViewById(R.id.redeem);
           // redeem1 = itemView.findViewById(R.id.redeem1);

            redeem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog = new Dialog(mContext);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.rating);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.setCancelable(true);
                    dialog.show();

                   Button rateNow = (Button) dialog.findViewById(R.id.rateNow);

                    Button rateLater = (Button) dialog.findViewById(R.id.rateLater);

                    RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);

                    rateNow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((MainActivity)mContext).getUpdateStatus(ratingBar.getRating(),redeemList.get(getAdapterPosition()).getProductID(),redeemList.get(getAdapterPosition()).getOfferCouponCodeID(),redeemList.get(getAdapterPosition()).getRateFor());
                        }
                    });

                    rateLater.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            });
        }
    }
}
