package com.example.webwerks.neostore.MyAccount;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webwerks.neostore.R;

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
    @BindView(R.id.profile_image)
    ImageView profile_image;
    MyAccountPresenter navigationPresenter;
    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_my_account, container, false);
        ButterKnife.bind(this, view);
//        myAccountPresenter=new MyAccountPresenterImplementation(getActivity().MyAccountFragment.this);
        return view;
    }


    @Override
    public void atachPresenter(MyAccountPresenter presenter) {
        navigationPresenter = presenter;

    }
}
