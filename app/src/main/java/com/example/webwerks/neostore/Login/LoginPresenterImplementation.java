package com.example.webwerks.neostore.Login;

import android.content.Intent;
import android.util.Log;

import com.example.webwerks.neostore.Remote.ResponseListener;
import com.example.webwerks.neostore.Remote.RetroHelper;
import com.example.webwerks.neostore.SignUp.Data;
import com.example.webwerks.neostore.SignUp.Example;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 16/3/18.
 */

public class LoginPresenterImplementation implements LoginPresenter {
    LoginView loginView;

    Example res;


    public LoginPresenterImplementation(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void preformLogin(String username, String password) {
        networkcall(username, password);
    }

    private void networkcall(String username, String password) {
        RetroHelper.getInstance().loginRequest(username, password, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {


                if (res != null) {
                    res = (Example) baseResponse.body();
                    if ((res.getMessage() != null) && (res.getData() != null)) {
                        Log.e(TAG, "onResponse LoginView Success: " + res.getMessage());
                        loginView.loginSuccess(res.getMessage());


                    } else {
                        loginView.loginSuccess(res.getMessage());
                    }

                } else {


                }
            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
                loginView.loginSuccess(res.getMessage());
            }
        });
    }


}
