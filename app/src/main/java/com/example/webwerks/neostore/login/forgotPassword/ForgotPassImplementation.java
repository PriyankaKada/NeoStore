package com.example.webwerks.neostore.login.forgotPassword;

import android.util.Log;

import com.example.webwerks.neostore.remote.ResponseListener;
import com.example.webwerks.neostore.remote.RetroHelper;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 3/28/18.
 */

public class ForgotPassImplementation implements ForgotPassPresenter {

    private ForgotPasswordModel res;
    forgotPassView forgotPasswordView;

    public ForgotPassImplementation(forgotPassView forgotPasswordView) {
        this.forgotPasswordView = forgotPasswordView;
    }


    @Override
    public void SendEmail(String email) {
        networkcall(email);
    }

    private void networkcall(String email) {
        RetroHelper.getInstance().passwordRequest(email, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (ForgotPasswordModel) baseResponse.body();
                if (res != null) {
                    if ((res.getMessage() != null)) {
                        forgotPasswordView.loginSuccess(res.getUser_msg());

                    } else {
                        forgotPasswordView.loginError(res.getUser_msg());
                    }

                } else {
                    forgotPasswordView.loginError(res.getUser_msg());
                }
            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
                forgotPasswordView.loginError(res.getUser_msg());

            }
        });

    }
}
