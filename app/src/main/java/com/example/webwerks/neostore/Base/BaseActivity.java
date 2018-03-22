package com.example.webwerks.neostore.Base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.Utils.PreferenceHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by webwerks on 19/3/18.
 */

public abstract class BaseActivity extends AppCompatActivity {
ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        onViewReady(savedInstanceState, getIntent());
    }

    protected abstract int getContentView();

    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

        //To be used by child activities
    }

    private void initializeDialog() {
    }
    protected void showDialog(String message){

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }
    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


}
