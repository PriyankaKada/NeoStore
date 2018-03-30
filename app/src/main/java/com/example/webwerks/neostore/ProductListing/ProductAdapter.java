package com.example.webwerks.neostore.ProductListing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.webwerks.neostore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webwerks on 3/30/18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<ProductData> productlistItem;

    ProductAdapter(List<ProductData> productlistItem) {

        this.productlistItem = productlistItem;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //elements to be displayed inside view holder are created here
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_listing, parent, false);

        return new ProductViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.txt_product_name.setText(productlistItem.get(position).getName());
        holder.txt_product_desc.setText(productlistItem.get(position).getDescription());
        holder.txt_product_price_view.setText("Rs: "+productlistItem.get(position).getCost().toString());
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setRating(productlistItem.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return productlistItem.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_product_name, txt_product_desc,txt_product_price_view;
        private RatingBar ratingBar;

        ProductViewHolder(View view) {
            super(view);
            ratingBar=(RatingBar)view.findViewById(R.id.ratingBar);
            txt_product_name = (TextView) view.findViewById(R.id.txt_product_name);
            txt_product_desc = (TextView) view.findViewById(R.id.txt_product_desc);
            txt_product_price_view = (TextView) view.findViewById(R.id.txt_product_price);

        }

    }
}
