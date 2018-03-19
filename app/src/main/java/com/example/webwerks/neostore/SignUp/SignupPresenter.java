package com.example.webwerks.neostore.SignUp;

/**
 * Created by webwerks on 15/3/18.
 */

public interface SignupPresenter {
    void preformSignUp(String name, String lastname,
                       String email,
                       String password, String conf_password,
                       String gender, int phone_number);

}

