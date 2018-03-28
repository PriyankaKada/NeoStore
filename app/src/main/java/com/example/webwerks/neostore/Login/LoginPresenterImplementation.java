package com.example.webwerks.neostore.Login;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.webwerks.neostore.Remote.ResponseListener;
import com.example.webwerks.neostore.Remote.RetroHelper;

import com.example.webwerks.neostore.SignUp.Example;
import com.example.webwerks.neostore.Utils.SPManager;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 16/3/18.
 */

public class LoginPresenterImplementation implements LoginPresenter {
    LoginView loginView;
    SPManager instance;
    Example res;



    public LoginPresenterImplementation(LoginView loginView, SPManager instance) {
        this.loginView = loginView;
        this.instance = instance;

    }

    @Override
    public void preformLogin(String username, String password) {
        networkcall(username, password);
    }

    private void networkcall(String username, String password) {
        RetroHelper.getInstance().loginRequest(username, password, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (Example) baseResponse.body();
                if (res != null) {
                    if ((res.getMessage() != null) && (res.getData() != null)) {
                        Log.e(TAG, "onResponse LoginView Success: " + res.getMessage());
                        loginView.loginSuccess(res.getMessage());
                        getDatafromResponse(res);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                loginView.startNewActivity();
                                // this will run in the main thread
                            }
                        });

                    } else {
                        loginView.loginSuccess(res.getMessage());
                    }

                } else {
                    loginView.loginError("Unable to login");
                }
            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
                loginView.loginError("Unable to login");

            }
        });
    }

    private void getDatafromResponse(Example res) {
        int id = res.getData().getId();
        int role_id = res.getData().getRoleId();
        String first_name = res.getData().getFirstName();
        String last_name = res.getData().getLastName();
        String email = res.getData().getEmail();
        String username = res.getData().getUsername();
        Object profile_pic = res.getData().getProfilePic();
        Object country_id = res.getData().getCountryId();
        String gender = res.getData().getGender();
        String phone_no = res.getData().getPhoneNo();
        Object dob = res.getData().getDob();
        boolean is_active = res.getData().getIsActive();
        String created = res.getData().getCreated();
        String modified = res.getData().getModified();
        String access_token = res.getData().getAccessToken();
        String message = res.getMessage();
        String user_msg = res.getUserMsg();

        SavetoSP(id, role_id, first_name, last_name, email,
                username, profile_pic, country_id, gender, phone_no, dob, is_active,
                created, modified, access_token, message, user_msg);

    }

    private void SavetoSP(int id, int role_id, String first_name, String last_name,
                          String email, String username, Object profile_pic,
                          Object country_id, String gender, String phone_no, Object dob,
                          boolean is_active, String created, String modified, String access_token,
                          String message, String user_msg) {
        instance.saveInt("ID", id);
        instance.saveInt("role_id", role_id);
        instance.saveString("first_name", first_name);
        instance.saveString("last_name", last_name);
        instance.saveString("email", email);
        instance.saveString("username", username);
        instance.saveObject("profile_pic", profile_pic);
        instance.saveObject("country_id", country_id);
        instance.saveString("gender", gender);
        instance.saveString("phone_no", phone_no);
        instance.saveObject("dob", dob);
        instance.saveBoolean("is_active", is_active);
        instance.saveString("modified", modified);
        instance.saveString("access_token", access_token);
        instance.saveString("message", message);
        instance.saveString("user_msg", user_msg);

    }


}
