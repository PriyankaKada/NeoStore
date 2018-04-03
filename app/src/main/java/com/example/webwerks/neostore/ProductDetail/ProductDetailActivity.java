package com.example.webwerks.neostore.ProductDetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.ProductListing.ProductData;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.SPManager;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailsView, Serializable {
    ProductData productdata;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.txt_product_name)
    TextView txt_product_name;
    @BindView(R.id.txt_category)
    TextView txt_category;
    @BindView(R.id.txt_make)
    TextView txt_make;

    @BindView(R.id.txt_product_price)
    TextView txt_product_price;

    @BindView(R.id.img_product)
    ImageView img_product;
    @BindView(R.id.img_1)
    ImageView img_1;
    @BindView(R.id.img_2)
    ImageView img_2;
    @BindView(R.id.img_3)
    ImageView img_3;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.txt_description)
    TextView txt_description;

    @BindView(R.id.btn_buy)
    Button buy;

    @BindView(R.id.btn_rate)
    Button btn_rate;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        productdata = getIntentData();
        setSupportActionBar(toolbar);
        setTitle(productdata.getName());
        setData();

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @OnClick(R.id.share)
    public void shareClick(){
        shareIntent();
    }
    @OnClick(R.id.btn_rate)
    public void rateClick(){

    }

    @OnClick(R.id.btn_buy)
    public void buyClick(){

    }
    @Override
    public ProductData getIntentData() {
        // To retrieve object in second Activity
        return (ProductData) getIntent().getSerializableExtra("MyClass");

    }

    @Override
    public void setTitle(String Title) {
        toolbar_title.setText(Title);
    }

    @Override
    public void setData() {
        txt_product_name.setText(productdata.getName());
        txt_category.setText("Category - "+SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));
        txt_make.setText(productdata.getProducer());
        txt_product_price.setText("Rs " + productdata.getCost().toString());
        txt_description.setText(productdata.getDescription());
        Glide.with(getApplicationContext())
                .load(productdata.getProductImages())
                .placeholder(R.drawable.placeholder)
                .into(img_product);

        ratingBar.setNumStars(5);
        ratingBar.setRating(productdata.getRating());
        Glide.with(getApplicationContext())
                .load(productdata.getProductImages())
                .placeholder(R.drawable.placeholder)
                .into(img_1);
        Glide.with(getApplicationContext())
                .load(productdata.getProductImages())
                .placeholder(R.drawable.placeholder)
                .into(img_2);
        Glide.with(getApplicationContext())
                .load(productdata.getProductImages())
                .placeholder(R.drawable.placeholder)
                .into(img_3);

    }

    @Override
    public void shareIntent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Share This Product");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
