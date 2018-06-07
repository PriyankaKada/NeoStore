package com.example.webwerks.neostore.productListing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.utils.SPManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListingActivity extends AppCompatActivity implements ProductView {
    @BindView(R.id.rec_product_listing)
    RecyclerView rec_product_listing;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBar;

    private ProductAdapter adapter;
    private ProductListPresenter productListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setTitle();
        initViews();
        productListPresenter = new ProductPresenterImplementation(ProductListingActivity.this, SPManager.getInstance(getApplicationContext()));
        productListPresenter.getProductData();
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void initViews() {
        rec_product_listing = (RecyclerView) findViewById(R.id.rec_product_listing);
        rec_product_listing.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rec_product_listing.setLayoutManager(layoutManager);
    }
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void successMessage(String meaage) {
        Toast.makeText(getApplicationContext(), meaage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getDataFromURL(List<ProductData> list) {
        adapter = new ProductAdapter(getApplicationContext(), list);
        rec_product_listing.setAdapter(adapter);
    }

    @Override
    public void setTitle() {
        toolbar_title.setText(getIntent().getStringExtra("Title"));
    }



}
