package com.example.webwerks.neostore.ProductDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.ProductListing.ProductData;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.SPManager;

import java.util.List;

/**
 * Created by webwerks on 4/3/18.
 */

public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ProductImageViewHolder> {
    private List<ProductImage> productImageList;
    Context mContext;
    ProductImage productImage;
    ProductDetailsView productDetailsView;

    public ProductImagesAdapter(Context mContext, List<ProductImage> productImageList, ProductDetailsView productDetailsView) {
        this.mContext = mContext;
        this.productImageList = productImageList;
        this.productDetailsView = productDetailsView;
    }

    @Override
    public ProductImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recview_product_iamges, parent, false);

        return new ProductImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductImageViewHolder holder, int position) {
        holder.itemView.setTag(position);
        productImage = productImageList.get(position);
        String image_url = productImage.getImage();

        Glide.with(mContext)
                .load(image_url)
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productImage = productImageList.get((Integer) v.getTag());
                String image_url = productImage.getImage();
                productDetailsView.getImageString(image_url);
//                v. setBackgroundResource(R.drawable.border_clicked);

            }
        });


    }

    @Override
    public int getItemCount() {
        return productImageList.size();
    }


    public static class ProductImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ProductImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image_recview);

        }
    }
}
