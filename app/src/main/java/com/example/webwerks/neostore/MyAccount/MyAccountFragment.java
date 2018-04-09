package com.example.webwerks.neostore.MyAccount;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.webwerks.neostore.MyAccount.Model.UserDetails;
import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.SPManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment implements MyAccountView {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.last_name)
    TextView last_name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.phone_number)
    TextView phone_number;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.profile_image)
    ImageView profile_image;
    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBar;
    MyAccountPresenter myAccountPresenter;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_my_account, container, false);
        ButterKnife.bind(this, view);


        myAccountPresenter=new MyAccountPresenterImplementation(MyAccountFragment.this);

        myAccountPresenter.getDataFromAPI(SPManager.getInstance(getActivity().getApplicationContext()).retriveString("access_token"));
        return view;
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
    public void setdata(UserDetails userDetails) {
        name.setText(userDetails.getData().getUserData().getFirstName());
        last_name.setText(userDetails.getData().getUserData().getLastName());
        email.setText(userDetails.getData().getUserData().getEmail());
        phone_number.setText(userDetails.getData().getUserData().getPhoneNo());
        birthday.setText(userDetails.getData().getUserData().getCreated());
        String pro_pic_url= (String) userDetails.getData().getUserData().getProfilePic();

    }

    @Override
    public void showMessage(String message) {

    }
}
