package com.example.webwerks.neostore.MyCart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.MyCart.Model.CartModel;
import com.example.webwerks.neostore.MyCart.Model.Datum;
import com.example.webwerks.neostore.ProductListing.ProductData;
import com.example.webwerks.neostore.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webwerks on 4/10/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Datum> cart_product_list;
    Context context;
    CartView cartView;
    Datum productData;
    private int VIEW_TYPE_FOOTER=1;
    private int VIEW_TYPE_CELL=0;
CartModel res;

    public CartAdapter(Context context, List<Datum> cart_product_list, CartModel res, CartView cartView) {
        this.cart_product_list = cart_product_list;
        this.context = context;
        this.cartView = cartView;
        this.res=res;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_CELL) {

            //Create viewholder for your default cell
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_listing, parent, false);

        }
        else {

            //Create viewholder for your footer view
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_listing_footer, parent, false);

        }
        return new CartViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return (position == cart_product_list.size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {

        if(position == cart_product_list.size()) {
            holder.total_price.setText("Rs "+res.getTotal());

        }else {
            holder.itemView.setTag(position);
            productData = cart_product_list.get(position);
            String image_url = productData.getProduct().getProductImages();
            String product_name = productData.getProduct().getName();
            String category = productData.getProduct().getProductCategory();
            int quantity = productData.getQuantity();
            int price = productData.getProduct().getCost();
            holder.product_name.setText(product_name);
            holder.price.setText("Rs " + price);
            holder.category.setText("(" + category + ")");
            Glide.with(context)
                    .load(image_url)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.img_product);
            setupSpinner(holder, quantity);
        }
    }

    private void setupSpinner(CartViewHolder holder, int quantity) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(""+i);
        }
        ArrayAdapter<String> dataAdapter =new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.quantity.setAdapter(dataAdapter);
        holder.quantity.setSelection(quantity);
    }

    @Override
    public int getItemCount() {
        return cart_product_list.size()+1;

    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product;

        private TextView product_name, price, category,total_price;
        Spinner quantity;

        public CartViewHolder(View view) {
            super(view);

            product_name = (TextView) view.findViewById(R.id.txt_product_name);
            quantity = (Spinner) view.findViewById(R.id.spi_quantity);
            price = (TextView) view.findViewById(R.id.txt_price);
            img_product = (ImageView) view.findViewById(R.id.img_product);
            category = (TextView) view.findViewById(R.id.txt_category);
            total_price=(TextView)view.findViewById(R.id.total_price);

        }
    }
}
