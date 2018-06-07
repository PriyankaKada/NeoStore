package com.example.webwerks.neostore.myAccount;

import com.example.webwerks.neostore.myAccount.model.UserDetails;

/**
 * Created by webwerks on 4/4/18.
 */

public interface MyAccountView {
    void setdata(UserDetails userDetails);
    void showMessage(String message);
    void showProgressBar();
    void hideProgressBar();

}
