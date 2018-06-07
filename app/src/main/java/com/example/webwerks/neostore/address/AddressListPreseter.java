package com.example.webwerks.neostore.address;

/**
 * Created by webwerks on 4/16/18.
 */

public interface AddressListPreseter {

    void placeOrder(String access_token, String address);
    void onBackPressed();
    void onActivityDestroyed();

}
