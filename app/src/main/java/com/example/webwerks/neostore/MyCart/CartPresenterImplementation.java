package com.example.webwerks.neostore.MyCart;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.webwerks.neostore.MyCart.Model.CartModel;
import com.example.webwerks.neostore.MyCart.Model.Datum;
import com.example.webwerks.neostore.Remote.ResponseListener;
import com.example.webwerks.neostore.Remote.RetroHelper;
import com.example.webwerks.neostore.SignUp.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 4/9/18.
 */

public class CartPresenterImplementation implements CartPresenter {
    CartView cartView;
    private CartModel res;
    List<Datum> cart_product_list;

    public CartPresenterImplementation(CartView cartView) {
        this.cartView = cartView;
    }

    @Override
    public void getCartDetails(String access_token) {
        cartView.showProgressBar();
        networkcall(access_token);
    }

    private void networkcall(String access_token) {
        RetroHelper.getInstance().requestcartItems(access_token, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (CartModel) baseResponse.body();
                if (res != null) {
                  cart_product_list=res.getData();
                    cartView.setData(res);
                    cartView.hideProgressBar();
                } else {
                    cartView.showMessage("Something Went Wrong");
                }
            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                cartView.showMessage("Something Went Wrong");

            }
        });
    }

}
