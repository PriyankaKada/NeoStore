package com.example.webwerks.neostore.login.forgotPassword;

/**
 * Created by webwerks on 3/28/18.
 */

public interface ForgotPassPresenter {
    void SendEmail(String email);
    void onDestroy();
    void onBackPRessed();


}
