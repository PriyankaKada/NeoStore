package com.example.webwerks.neostore.address;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.neostore.address.roomDb.Address;
import com.example.webwerks.neostore.address.roomDb.MyAddressDatabase;
import com.example.webwerks.neostore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.address)
    EditText et_address;
    @BindView(R.id.street_name)
    EditText street_name;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.country)
    EditText country;
    @BindView(R.id.zip_code)
    EditText zip_code;
    @BindView(R.id.state)
    EditText state;
    @BindView(R.id.btn_saveAddress)
    Button btn_saveAddress;
    public static MyAddressDatabase myAddressDatabase;
    Address address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address1);
        ButterKnife.bind(this);
        myAddressDatabase= Room.databaseBuilder(getApplicationContext(),MyAddressDatabase.class,"address_information").allowMainThreadQueries().build();
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar_title.setText("Add Address");
    }
    @OnClick(R.id.btn_saveAddress)
    public void ClickBtn_saveAddress(){
        address=new Address();
        setDataforDB();
        myAddressDatabase.myDao().addAddress(address);
        Toast.makeText(this, "Address added", Toast.LENGTH_SHORT).show();
        clearData();
        Intent intent=new Intent(AddAddressActivity.this,AddressListingActivity.class);
        startActivity(intent);
    }

    private void clearData() {
        et_address.setText("");
        city.setText("");
        country.setText("");
        street_name.setText("");
        zip_code.setText("");
        state.setText("");
    }

    private void setDataforDB() {
        address.setAddress(et_address.getText().toString().trim());
        address.setCity(city.getText().toString().trim());
        address.setState(state.getText().toString().trim());
        address.setCountry(country.getText().toString().trim());
        address.setZip_code(Integer.parseInt(zip_code.getText().toString().trim()));
        address.setStreet_name(street_name.getText().toString().trim());
    }

}
