package com.example.webwerks.neostore.myCart;

import com.example.webwerks.neostore.myCart.model.CartModel;

/**
 * Created by webwerks on 4/9/18.
 */

public interface CartView {
    void showProgressBar();
    void hideProgressBar();

    void showMessage(String s);

    void setData(CartModel res);

    void refreshAdapter(int position);

    void refreshAdapter();
}
