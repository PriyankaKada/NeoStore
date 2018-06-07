package com.example.webwerks.neostore.address;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.neostore.address.roomDb.Address;
import com.example.webwerks.neostore.dashboard.DashboardActivity;
import com.example.webwerks.neostore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressListingActivity extends AppCompatActivity implements AddressListingView {

    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBar;
    @BindView(R.id.rec_address)
    RecyclerView rec_address;
    AddressAdapter addressAdapter;
    List<Address> addresses;
    AddressListPreseter addressListPreseter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_listing);
        ButterKnife.bind(this);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar_title.setText("Address List");
        add.setVisibility(View.VISIBLE);
        search.setVisibility(View.GONE);
        addresses= DashboardActivity.myAddressDatabase.myDao().readAddress();
        addressListPreseter=new AddressListPresenterImplementaion(AddressListingActivity.this);

        addressAdapter = new AddressAdapter(addresses,getApplicationContext(),addressListPreseter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rec_address.setHasFixedSize(true);
        rec_address.setLayoutManager(mLayoutManager);
        rec_address.setAdapter(addressAdapter);

    }
    @OnClick(R.id.add)
    public void clickAdd(){
        Intent intent=new Intent(AddressListingActivity.this,AddAddressActivity.class);
        startActivity(intent);
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
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        addressListPreseter.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addressListPreseter.onActivityDestroyed();

    }
}
