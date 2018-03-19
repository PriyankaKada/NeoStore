package com.example.webwerks.neostore.Utils;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.webwerks.neostore.R;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by webwerks on 19/3/18.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener, Observer {
    ActionBar actionBar;
    PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg_gradient));
        preferenceHelper = new PreferenceHelper(this);


    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
