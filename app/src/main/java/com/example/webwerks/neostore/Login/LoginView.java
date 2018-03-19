package com.example.webwerks.neostore.Login;

/**
 * Created by webwerks on 14/3/18.
 */

public interface LoginView {
    void loginSuccess(String userMsg);
    void loginValidations();
    void performSignUp();
    void loginError(String message);
}
