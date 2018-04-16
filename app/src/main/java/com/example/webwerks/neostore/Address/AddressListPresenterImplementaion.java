package com.example.webwerks.neostore.Address;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.webwerks.neostore.Remote.ResponseListener;
import com.example.webwerks.neostore.Remote.RetroHelper;
import com.example.webwerks.neostore.SignUp.Example;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 4/16/18.
 */

public class AddressListPresenterImplementaion implements AddressListPreseter {
    AddressListingView addressListingView;
    private AddressListingModel res;


    public AddressListPresenterImplementaion(AddressListingView addressListingView) {
    this.addressListingView=addressListingView;
    }

    @Override
    public void placeOrder(String access_token, String address) {
        addressListingView.showProgressBar();
        networkcall(access_token, address);
    }

    private void networkcall(String access_token, String address) {

        RetroHelper.getInstance().placeOrder(access_token, address, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (AddressListingModel) baseResponse.body();
                if (res != null) {
                    addressListingView.hideProgressBar();
                    addressListingView.showMessage(res.getUserMsg());


                } else {
                    addressListingView.showMessage("Unable to login");
                }
            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
                addressListingView.showMessage("Unable to login");

            }
        });
    }
}
