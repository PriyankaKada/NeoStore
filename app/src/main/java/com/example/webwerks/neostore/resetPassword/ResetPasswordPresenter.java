package com.example.webwerks.neostore.resetPassword;

/**
 * Created by webwerks on 4/6/18.
 */

public interface ResetPasswordPresenter {
    void changePassword(String access_token,String old_password,String password,String confirm_password);
}
