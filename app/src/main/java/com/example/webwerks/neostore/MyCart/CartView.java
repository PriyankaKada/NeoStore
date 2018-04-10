package com.example.webwerks.neostore.MyCart;

import com.example.webwerks.neostore.MyCart.Model.CartModel;

/**
 * Created by webwerks on 4/9/18.
 */

public interface CartView {
    void showProgressBar();
    void hideProgressBar();

    void showMessage(String s);

    void setData(CartModel res);
}
