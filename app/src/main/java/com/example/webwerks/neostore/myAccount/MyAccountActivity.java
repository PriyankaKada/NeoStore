package com.example.webwerks.neostore.myAccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.webwerks.neostore.myAccount.model.UserDetails;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.resetPassword.ResetPasswordActivity;
import com.example.webwerks.neostore.utils.SPManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAccountActivity extends AppCompatActivity implements MyAccountView {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.last_name)
    EditText last_name;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.phone_number)
    EditText phone_number;
    @BindView(R.id.birthday)
    EditText birthday;
    @BindView(R.id.profile_image)
    ImageView profile_image;
    @BindView(R.id.edit_profile)
    Button edit_profile;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.btnReset)
    Button btnReset;
    MyAccountPresenter myAccountPresenter;
    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SPManager.getInstance(this).saveString("Mode", "display");

        if (SPManager.getInstance(this).retriveString("Mode").equals("display")) {
            name.setEnabled(false);
            last_name.setEnabled(false);
            email.setEnabled(false);
            phone_number.setEnabled(false);
            birthday.setEnabled(false);
            toolbar_title.setText("My Account");

        } else {


        }

        myAccountPresenter = new MyAccountPresenterImplementation(MyAccountActivity.this);

        myAccountPresenter.getDataFromAPI(SPManager.getInstance(getApplicationContext()).retriveString("access_token"));

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.edit_profile)
    public void profile_pic_Click() {

    }

    @OnClick(R.id.btnReset)
    public void resetPasswordClick() {
        Intent intent = new Intent(MyAccountActivity.this, ResetPasswordActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.edit_profile)
    public void edit_profileClick() {
        SPManager.getInstance(this).saveString("Mode", "edit");
        toolbar_title.setText("Edit Profile");
        name.setEnabled(true);
        last_name.setEnabled(true);
        email.setEnabled(true);
        phone_number.setEnabled(true);
        birthday.setEnabled(true);
        btn_submit.setVisibility(View.VISIBLE);
        edit_profile.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_submit)
    public void submit_profileClick() {
        myAccountPresenter.submitProfileData(SPManager.getInstance(getApplicationContext()).retriveString("access_token").trim().toString(),
                name.getText().toString().trim(),
                last_name.getText().toString().trim()
                , email.getText().toString().trim(),
                "18-08-1989",
                phone_number.getText().toString().trim(),
                "http://www.beingmarathi.in/wp-content/uploads/2017/03/Priya.jpg");

    }


    @Override
    public void setdata(UserDetails userDetails) {
        name.setText(userDetails.getData().getUserData().getFirstName());
        last_name.setText(userDetails.getData().getUserData().getLastName());
        email.setText(userDetails.getData().getUserData().getEmail());
        phone_number.setText(userDetails.getData().getUserData().getPhoneNo());
        birthday.setText(userDetails.getData().getUserData().getCreated());

        String pro_pic_url = "http://www.beingmarathi.in/wp-content/uploads/2017/03/Priya.jpg";

        Log.e("pro_pic_url", "setdata: " + pro_pic_url);

        Glide.with(MyAccountActivity.this)
                .load(pro_pic_url)
                .into(profile_image);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
                            }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
