package com.example.webwerks.neostore.address;

import android.util.Log;

import com.example.webwerks.neostore.remote.ResponseListener;
import com.example.webwerks.neostore.remote.RetroHelper;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 4/16/18.
 */

public class AddressListPresenterImplementaion implements AddressListPreseter {
    AddressListingView addressListingView;
    private AddressListingModel res;
    RetroHelper mInstance;


    public AddressListPresenterImplementaion(AddressListingView addressListingView) {
    this.addressListingView=addressListingView;
    }

    @Override
    public void placeOrder(String access_token, String address) {
        addressListingView.showProgressBar();
        networkcall(access_token, address);
    }

    @Override
    public void onBackPressed() {
        addressListingView=null;
        mInstance=null;
    }

    @Override
    public void onDestroyed() {
        addressListingView=null;
        mInstance=null;
    }

    private void networkcall(String access_token, String address) {

       mInstance.placeOrder(access_token, address, new ResponseListener() {
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
