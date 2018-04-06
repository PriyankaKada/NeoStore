package com.example.webwerks.neostore.MyAccount;

import android.util.Log;

import com.example.webwerks.neostore.MyAccount.Model.UserDetails;
import com.example.webwerks.neostore.MyAccount.Model.UserDetailsUpdates;
import com.example.webwerks.neostore.Rating.Rating;
import com.example.webwerks.neostore.Remote.ResponseListener;
import com.example.webwerks.neostore.Remote.RetroHelper;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 4/4/18.
 */

public class MyAccountPresenterImplementation implements MyAccountPresenter {

MyAccountView myAccountView;
    private UserDetails res;
    private UserDetailsUpdates res_updates;

    public MyAccountPresenterImplementation(MyAccountView myAccountView) {
        this.myAccountView=myAccountView;
    }

    @Override
    public void getDataFromAPI(String access_token) {
        sendNetwokRequest(access_token);

    }

    @Override
    public void submitProfileData(String access_token,String first_name, String last_name, String email, String dob, String phone_no, String profile_pic) {
        sentDatatoApi(access_token, first_name,last_name, email,  dob,  phone_no,  profile_pic);
    }

    private void sentDatatoApi(String access_token,String first_name, String last_name, String email, String dob, String phone_no, String profile_pic) {
        RetroHelper.getInstance().submitUserProfileData(access_token,first_name,last_name,email,dob,phone_no,profile_pic, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response updateResponse) {
                res_updates = (UserDetailsUpdates) updateResponse.body();

                if(res_updates != null){
                    myAccountView.showMessage(res_updates.getUserMsg());
//                    myAccountView.setdata(res);


                }

            }

            @Override
            public void onResponseFailure(Throwable throwable) {

                Log.e(TAG, "onResponse: Response Updates" + res_updates.getStatus());
            }
        });


    }

    private void sendNetwokRequest(String access_token) {
        RetroHelper.getInstance().getUserProfileData(access_token, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (UserDetails) baseResponse.body();

                if(res != null){
                myAccountView.setdata(res);

                }

            }

            @Override
            public void onResponseFailure(Throwable throwable) {

                Log.e(TAG, "onResponse: Response" + res.getStatus());
            }
        });


    }
}
