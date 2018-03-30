package com.example.webwerks.neostore.ProductListing;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.webwerks.neostore.Login.LoginActivity;
import com.example.webwerks.neostore.Login.LoginPresenterImplementation;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.SPManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class ProductListingActivity extends AppCompatActivity implements ProductView {
    @BindView(R.id.rec_product_listing)
    RecyclerView rec_product_listing;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<ProductData> data;
    private ProductAdapter adapter;
    private ProductListPresenter productListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);
        setSupportActionBar(toolbar);
        initViews();
        productListPresenter = new ProductPresenterImplementation(ProductListingActivity.this,SPManager.getInstance(getApplicationContext()));
        productListPresenter.getProductData();
        data = new ArrayList<>();
     }

    private void initViews() {

        rec_product_listing = (RecyclerView)findViewById(R.id.rec_product_listing);
        rec_product_listing.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rec_product_listing.setLayoutManager(layoutManager);
    }


    @Override
    public void successMessage(String meaage) {
        Toast.makeText(getApplicationContext(),meaage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void getDataFromURL(List<ProductData> list) {

        adapter = new ProductAdapter(list);
        rec_product_listing.setAdapter(adapter);

    }


}
