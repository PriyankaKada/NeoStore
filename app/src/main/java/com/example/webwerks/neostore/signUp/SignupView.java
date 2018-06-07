package com.example.webwerks.neostore.signUp;

/**
 * Created by webwerks on 14/3/18.
 */

public interface SignupView {
    void validateData();
    void performSignUp();
    void success(String message);
    void showProgressBar();
    void hideProgressBar();
}
