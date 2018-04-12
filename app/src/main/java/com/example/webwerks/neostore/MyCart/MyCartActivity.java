package com.example.webwerks.neostore.MyCart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.neostore.Login.LoginActivity;
import com.example.webwerks.neostore.MyCart.Model.CartModel;
import com.example.webwerks.neostore.MyCart.Model.Datum;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.SPManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCartActivity extends AppCompatActivity implements CartView {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.rec_cart)
    RecyclerView rec_cart;
    CartPresenter cartPresenter;
    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBar;
    List<Datum> product_data;
    CartAdapter cartAdapter;
    @BindView(R.id.empty_textview)
    TextView empty_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        ButterKnife.bind(this);
        product_data = new ArrayList<>();
        toolbar_title.setText("My Cart");
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cartPresenter = new CartPresenterImplementation(MyCartActivity.this);
        cartPresenter.getCartDetails(SPManager.getInstance(getApplicationContext()).retriveString("access_token"));

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
    public void showMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setData(CartModel res) {
         product_data = res.getData();
            if(product_data!=null){
                SPManager.getInstance(getApplicationContext()).saveInt("cart_count", res.getCount());
                Log.e("My Cart", "setData: "+""+SPManager.getInstance(getApplicationContext()).retriveInt("cart_count",1));
                setRecyclerView(product_data, res);
            }else {
                rec_cart.setVisibility(View.GONE);
                empty_textview.setVisibility(View.VISIBLE);
            }

    }

    @Override
    public void refreshAdapter(int position) {
        product_data.remove(position);
        cartAdapter.notifyItemRemoved(position);
    }

    @Override
    public void refreshAdapter() {
        cartAdapter.notifyDataSetChanged();

    }

    private void setRecyclerView(List<Datum> product_data, CartModel res) {
        cartAdapter = new CartAdapter(getApplicationContext(), product_data, res, this, cartPresenter);
        rec_cart.setHasFixedSize(true);
        rec_cart.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rec_cart.setLayoutManager(layoutManager);
        rec_cart.setAdapter(cartAdapter);

    }
}
