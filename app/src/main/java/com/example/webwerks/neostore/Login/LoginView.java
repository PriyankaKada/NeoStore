package com.example.webwerks.neostore.Login;

import android.content.Context;

/**
 * Created by webwerks on 14/3/18.
 */

public interface LoginView {
    void loginSuccess(String userMsg);
    void loginValidations();
    void performSignUp();
    void loginError(String message);
    void openDashboard();
}
