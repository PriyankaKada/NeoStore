package com.example.webwerks.neostore.Login;

import android.util.Log;

import com.example.webwerks.neostore.Remote.APIClient;
import com.example.webwerks.neostore.SignUp.Example;
import com.example.webwerks.neostore.SignUp.SignupView;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 16/3/18.
 */

public class LoginPresenterImplementation implements LoginPresenter {
    LoginView loginView;
    Example response1;


    public LoginPresenterImplementation(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void preformLogin(String username, String password) {
        sendNetwokRequest(username,password);
    }

    private void sendNetwokRequest(String username, String password) {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        okhttpClientBuilder.addInterceptor(loggingInterceptor);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(APIClient.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClientBuilder.build());

        Retrofit retrofit = builder.build();
        //get client and call object for the request
        APIClient apiClient = retrofit.create(APIClient.class);
        Call<Example> call = apiClient.signin(username, password);
        call.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                response1 = response.body();
                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse LoginView: "+response1.getMessage());
                    loginView.loginSuccess(response1.getMessage());
                }else {
                    Log.e(TAG, "onResponse: Response" + response1.getMessage());
                    loginView.loginSuccess(response1.getMessage());

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.getMessage());
                loginView.loginError(t.getMessage());

            }
        });

    }

}
