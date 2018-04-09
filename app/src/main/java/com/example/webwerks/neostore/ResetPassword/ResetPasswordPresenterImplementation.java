package com.example.webwerks.neostore.ResetPassword;

import android.util.Log;

import com.example.webwerks.neostore.Rating.Rating;
import com.example.webwerks.neostore.Remote.ResponseListener;
import com.example.webwerks.neostore.Remote.RetroHelper;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 4/6/18.
 */

public class ResetPasswordPresenterImplementation implements ResetPasswordPresenter {


    private ResetPassword res;
    ResetPasswordView resetPasswordView;

    public ResetPasswordPresenterImplementation(ResetPasswordView resetPasswordView) {
        this.resetPasswordView = resetPasswordView;
    }

    @Override
    public void changePassword(String access_token, String old_password, String password, String confirm_password) {
        resetPasswordView.showProgressBar();
        sendNetwokRequest(access_token, old_password, password, confirm_password);

    }

    private void sendNetwokRequest(String access_token, String old_password, String password, String confirm_password) {
        RetroHelper.getInstance().resetPassword(access_token, old_password, password, confirm_password, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (ResetPassword) baseResponse.body();

                if (res != null) {
                    resetPasswordView.showSuccess(res.getUserMsg());
                    resetPasswordView.hideProgressBar();

                }

            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
            }
        });


    }
}
