package com.example.webwerks.neostore.Rating;

import android.util.Log;

import com.example.webwerks.neostore.Remote.ResponseListener;
import com.example.webwerks.neostore.Remote.RetroHelper;
import com.example.webwerks.neostore.SignUp.Example;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 4/4/18.
 */

public class RatingPresenterImplmentation implements RatingPresenter {
    RatingView ratingView;
    private Rating res;

    public RatingPresenterImplmentation(RatingView ratingView) {
        this.ratingView=ratingView;
    }

    @Override
    public void submitRatings(String product_id,int ratings) {
    ratingView.showProgressBar();
    sendNetwokRequest(product_id,ratings);
    }

    private void sendNetwokRequest(String product_id, int rating) {
        RetroHelper.getInstance().setRating(product_id, rating, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (Rating) baseResponse.body();

                if(res != null){
                    ratingView.showSuccess(res.getUserMsg());
                    ratingView.showProgressBar();

                }

            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
            }
        });


    }
}
