package com.example.webwerks.neostore.myCart;

import android.util.Log;

import com.example.webwerks.neostore.myCart.model.CartModel;
import com.example.webwerks.neostore.myCart.model.Datum;
import com.example.webwerks.neostore.rating.AddToCart;
import com.example.webwerks.neostore.remote.ResponseListener;
import com.example.webwerks.neostore.remote.RetroHelper;

import java.util.List;

import retrofit2.Response;

/**
 * Created by webwerks on 4/9/18.
 */

public class CartPresenterImplementation implements CartPresenter {
    CartView cartView;
    private CartModel res;
    List<Datum> cart_product_list;
    private AddToCart res_delete;

    public CartPresenterImplementation(CartView cartView) {
        this.cartView = cartView;
    }

    @Override
    public void getCartDetails(String access_token) {
        cartView.showProgressBar();
        networkcall(access_token);
    }

    @Override
    public void deleteCartItem(String access_token, int productId) {
        cartView.showProgressBar();

        delete_networkcall(access_token, productId);

    }

    @Override
    public void editCart(String access_token, int productId, int selected_value) {
        cartView.showProgressBar();
        edit_networkcall(access_token, productId, selected_value);
    }

    private void edit_networkcall(final String access_token, int productId, int selected_value) {
        RetroHelper.getInstance().editcartrequest(access_token, productId, selected_value, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res_delete = (AddToCart) baseResponse.body();
                if (res_delete != null) {

                    cartView.hideProgressBar();


                } else {
                    cartView.showMessage("Something Went Wrong");
                    cartView.hideProgressBar();
                }
            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                cartView.showMessage("Something Went Wrong");
                cartView.hideProgressBar();

            }
        });


    }

    private void delete_networkcall(final String access_token, int productId) {
        RetroHelper.getInstance().deleterequest(access_token, productId, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res_delete = (AddToCart) baseResponse.body();
                if (res_delete != null) {
                    cartView.showMessage(res_delete.getUserMsg());
                    cartView.hideProgressBar();
                    getCartDetails(access_token);
                    Log.e("", "deleteCartItem: "+res.getTotal().toString() );


                } else {
                    cartView.showMessage("Something Went Wrong");
                    cartView.hideProgressBar();
                }
            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                cartView.showMessage("Something Went Wrong");
                cartView.hideProgressBar();

            }
        });
    }

    private void networkcall(String access_token) {
        RetroHelper.getInstance().requestcartItems(access_token, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (CartModel) baseResponse.body();
                if (res != null) {


                    cart_product_list = res.getData();
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
