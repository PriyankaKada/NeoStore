package com.example.webwerks.neostore.SignUp;

import android.util.Log;
import android.widget.Toast;

import com.example.webwerks.neostore.Remote.APIClient;
import com.example.webwerks.neostore.Remote.ResponseListener;
import com.example.webwerks.neostore.Remote.RetroHelper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 15/3/18.
 */

public class SignUpPresenterImplementation implements SignupPresenter {
    SignupView signUpView;
    private Example res;

    public SignUpPresenterImplementation(SignupView signUpView) {
        this.signUpView = signUpView;
    }

//    private void sendNetwokRequest(String name, String lastname, String email, String password, String confirm_password, String gender, int phone_number) {
//
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl(APIClient.BaseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(getInterceptor().build());
//
//        Retrofit retrofit = builder.build();
//        //get client and call object for the request
//        APIClient apiClient = retrofit.create(APIClient.class);
//        Call<Example> call = apiClient.createAccount(name, lastname, email, password, confirm_password, gender, phone_number);
//        call.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//                Example response1 = response.body();
//                if (response.isSuccessful()) {
//                    signUpView.success(response1.getMessage());
//                    Log.e(TAG, "onResponse: "+response1.getMessage());
//                } else {
//                    signUpView.error(response1.getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.getMessage());
//                signUpView.error(t.getMessage());
//
//            }
//        });
//
//    }


    @Override
    public void preformSignUp(String name, String lastname, String email, String password, String confirm_password, String gender, int phone_number) {
       sendNetwokRequest(name, lastname, email, password, confirm_password, gender, phone_number);

    }

    private void sendNetwokRequest(String name, String lastname, String email, String password, String confirm_password, String gender, int phone_number) {
        RetroHelper.getInstance().signupRequest(name, lastname, email, password, confirm_password, gender, phone_number, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
             res = (Example) baseResponse.body();

                if(res != null){
                    Log.e(TAG, "onResponse LoginView: "+res.getMessage());
                    signUpView.success(res.getMessage());
                }

            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + res.getMessage());
                signUpView.success(res.getMessage());
            }
        });


    }

}

