package com.example.webwerks.neostore.MyCart;

/**
 * Created by webwerks on 4/9/18.
 */

public interface CartPresenter {
    void getCartDetails(String access_token);

    void deleteCartItem(String access_token, int productId);

    void editCart(String access_token, int productId, int selection);
}
