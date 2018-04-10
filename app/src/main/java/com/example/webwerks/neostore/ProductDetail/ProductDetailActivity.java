package com.example.webwerks.neostore.ProductDetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Rating.RatingBarActivity;
import com.example.webwerks.neostore.Utils.SPManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailsView, Serializable {

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
    @BindView(R.id.rec_pro_images)
    RecyclerView rec_pro_images;
    List<ProductImage> images;
    ProductDetailPresenter productDetailPresenter;
    private ProductImagesAdapter productImagesAdapter;
    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBar;
    String main_image_url,title;
        int product_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        productDetailPresenter = new ProductDetailPresenterImplementation(ProductDetailActivity.this);
        productDetailPresenter.getDatafromAPI(SPManager.getInstance(getApplicationContext()).retriveInt("product_id", 1));
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void setRecyclerView() {
        productImagesAdapter = new ProductImagesAdapter(getApplicationContext(), images, this);
        rec_pro_images.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rec_pro_images.setItemAnimator(new DefaultItemAnimator());
        rec_pro_images.setAdapter(productImagesAdapter);
    }

    @OnClick(R.id.share)
    public void shareClick() {
        shareIntent();
    }

    @OnClick(R.id.btn_rate)
    public void rateClick() {

        Intent intent=new Intent(ProductDetailActivity.this,RatingBarActivity.class);
        intent.putExtra("Mode","rate");
        intent.putExtra("Title",title);
        intent.putExtra("main_image_url",main_image_url);
        startActivity(intent);
    }

    @OnClick(R.id.btn_buy)
    public void buyClick() {

        Intent intent=new Intent(ProductDetailActivity.this,RatingBarActivity.class);
        intent.putExtra("Mode","buy");
        intent.putExtra("Title",title);
        intent.putExtra("main_image_url",main_image_url);

        startActivity(intent);
    }


    @Override
    public void setData(SingleProductDetails res) {
        product_id=res.getData().getId();
        title=res.getData().getName();
        toolbar_title.setText(title);
        txt_product_name.setText(res.getData().getName());
        txt_category.setText("Category - " + SPManager.getInstance(getApplicationContext()).retriveString("Product_category"));
        txt_make.setText(res.getData().getProducer());
        txt_product_price.setText("Rs " + res.getData().getCost().toString());
        txt_description.setText(res.getData().getDescription());
        images = new ArrayList<>();
        images = res.getData().getProductImages();
        setRecyclerView();
        ratingBar.setNumStars(5);
        ratingBar.setRating(res.getData().getRating());
        main_image_url=res.getData().getProductImages().get(0).getImage();
        Glide.with(getApplicationContext())
                .load(main_image_url)
                .placeholder(R.drawable.placeholder)
                .into(img_product);
    }

    @Override
    public void getImageString(String image_url) {
        Glide.with(ProductDetailActivity.this)
                .load(image_url)
                .placeholder(R.drawable.placeholder)
                .into(img_product);

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
