package com.example.webwerks.neostore.myAccount;

/**
 * Created by webwerks on 4/4/18.
 */

public interface MyAccountPresenter {
    void getDataFromAPI(String access_token);
    void submitProfileData(String access_token,String first_name,String last_name,String email,String dob,String phone_no,String profile_pic);
    void onDestroy();
    void onback();
}
