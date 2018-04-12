package com.example.webwerks.neostore.Address;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webwerks.neostore.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAddressActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address1);
        ButterKnife.bind(this);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar_title.setText("Add Address");
    }
}
