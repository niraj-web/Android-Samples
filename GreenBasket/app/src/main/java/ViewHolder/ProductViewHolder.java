package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenbasket.R;

import Interface.ItemClickListner;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;


    public ProductViewHolder(@NonNull View itemView)
    {
        super(itemView);

        imageView = (com.rey.material.widget.ImageView) itemView.findViewById(R.id.select_product_image);
        txtProductName = (com.rey.material.widget.TextView) itemView.findViewById(R.id.product_name);
        txtProductDescription = (com.rey.material.widget.TextView) itemView.findViewById(R.id.product_description);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price);
    }

    public void  setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
