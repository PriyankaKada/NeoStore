package com.example.webwerks.neostore.signUp;

import android.util.Log;

import com.example.webwerks.neostore.remote.ResponseListener;
import com.example.webwerks.neostore.remote.RetroHelper;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 15/3/18.
 */

public class SignUpPresenterImplementation implements SignupPresenter {
    SignupView signUpView;
    private Example res;

    public SignUpPresenterImplementation(SignupView signUpView) {
        this.signUpView = signUpView;
    }


    @Override
    public void preformSignUp(String name, String lastname, String email, String password, String confirm_password, String gender, int phone_number) {
      signUpView.showProgressBar();
       sendNetwokRequest(name, lastname, email, password, confirm_password, gender, phone_number);

    }

    private void sendNetwokRequest(String name, String lastname, String email, String password, String confirm_password, String gender, int phone_number) {
        RetroHelper.getInstance().signupRequest(name, lastname, email, password, confirm_password, gender, phone_number, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
             res = (Example) baseResponse.body();

                if(res != null){
                    Log.e(TAG, "onResponse LoginView: "+res.getMessage());
                    signUpView.success(res.getMessage());
                    signUpView.hideProgressBar();

                }

            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
                signUpView.success(res.getMessage());
            }
        });


    }

}

