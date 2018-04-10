package com.example.webwerks.neostore.Rating;

/**
 * Created by webwerks on 4/4/18.
 */

public interface RatingPresenter {

    void submitRatings(String product_id,int rating);
    void addProductToCart(String access_token,int product_id,int quantity);

}
