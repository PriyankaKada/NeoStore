package com.example.webwerks.neostore.Rating;

import android.util.Log;

import com.example.webwerks.neostore.Remote.ResponseListener;
import com.example.webwerks.neostore.Remote.RetroHelper;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 4/4/18.
 */

public class RatingPresenterImplmentation implements RatingPresenter {
    RatingView ratingView;
    private Rating res;
    private AddToCart res_cart;

    public RatingPresenterImplmentation(RatingView ratingView) {
        this.ratingView=ratingView;
    }

    @Override
    public void submitRatings(String product_id,int ratings) {
    ratingView.showProgressBar();
    sendNetwokRequest(product_id,ratings);
    }

    @Override
    public void addProductToCart(String access_token, int product_id, int quantity) {
        ratingView.showProgressBar();
        addProductRequest(access_token,product_id,quantity);
    }

    private void addProductRequest(String access_token, int product_id, int quantity) {
        RetroHelper.getInstance().addtocart(access_token,product_id, quantity, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res_cart = (AddToCart) baseResponse.body();

                if(res_cart != null){
                    ratingView.showSuccess(res_cart.getUserMsg());
                    ratingView.hideProgressBar();

                }

            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
            }
        });

    }

    private void sendNetwokRequest(String product_id, int rating) {
        RetroHelper.getInstance().setRating(product_id, rating, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (Rating) baseResponse.body();

                if(res != null){
                    ratingView.showSuccess(res.getUserMsg());
                    ratingView.hideProgressBar();

                }

            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
            }
        });


    }
}
