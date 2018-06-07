package com.example.webwerks.neostore.login.forgotPassword;

/**
 * Created by webwerks on 3/28/18.
 */

public interface forgotPassView {
    void loginSuccess(String userMsg);
    void loginValidations();

    void loginError(String message);
    void startNewActivity();

}
