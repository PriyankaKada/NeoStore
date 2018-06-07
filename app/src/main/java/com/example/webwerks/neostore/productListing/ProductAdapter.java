package com.example.webwerks.neostore.productListing;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.productDetail.ProductDetailActivity;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.utils.SPManager;

import java.util.List;

/**
 * Created by webwerks on 3/30/18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<ProductData> productlistItem;
    Context mContext;
    ProductData productData;


    ProductAdapter(Context mContext, List<ProductData> productlistItem) {
        this.mContext = mContext;
        this.productlistItem = productlistItem;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //elements to be displayed inside view holder are created here
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_listing, parent, false);

        return new ProductViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {

        holder.itemView.setTag(position);
        holder.txt_product_name.setText(productlistItem.get(position).getName());
        holder.txt_product_desc.setText(productlistItem.get(position).getDescription());
        holder.txt_product_price_view.setText("Rs: " + productlistItem.get(position).getCost().toString());
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setRating(productlistItem.get(position).getRating());

        Glide.with(mContext)
                .load(productlistItem.get(position).getProductImages())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                productData = productlistItem.get((Integer) v.getTag());
                int product_id = productData.getId();
                SPManager.getInstance(mContext).saveInt("product_id", product_id);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productlistItem.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {


        private TextView txt_product_name, txt_product_desc, txt_product_price_view;
        private RatingBar ratingBar;
        private ImageView imageView;

        ProductViewHolder(View view) {
            super(view);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            txt_product_name = (TextView) view.findViewById(R.id.txt_product_name);
            txt_product_desc = (TextView) view.findViewById(R.id.txt_product_desc);
            txt_product_price_view = (TextView) view.findViewById(R.id.txt_product_price);
            imageView = (ImageView) view.findViewById(R.id.img_product);


        }


    }
}
