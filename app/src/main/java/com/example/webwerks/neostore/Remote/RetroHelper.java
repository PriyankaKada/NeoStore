package com.example.webwerks.neostore.Remote;

import android.util.Log;

import com.example.webwerks.neostore.AppConstants;
import com.example.webwerks.neostore.SignUp.Example;
import com.google.gson.Gson;
import java.security.cert.CertificateException;
import java.util.Observable;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetroHelper {

    public static APIClient apiService;
    private static RetroHelper mInstance;

    public RetroHelper() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();

        apiService = retrofit.create(APIClient.class);
    }
    private OkHttpClient.Builder getInterceptor() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        okhttpClientBuilder.addInterceptor(loggingInterceptor);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return okhttpClientBuilder;
    }


    public static RetroHelper getInstance() {
        if(mInstance == null) {
            return new RetroHelper();
        }
        return mInstance;
    }



  public void loginRequest(String username,String password, ResponseListener responseListener) {
        Call<Example> call = apiService.signin(username,password);
        networkCall(call, responseListener);
    }
    public void signupRequest(String name, String lastname, String email, String password, String confirm_password, String gender, int phone_number, ResponseListener responseListener) {
        Call<Example> call = apiService.createAccount(name, lastname, email, password, confirm_password, gender, phone_number);
        networkCall(call, responseListener);
    }


    private <T> void networkCall(Call<T> call, final ResponseListener responseListener) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                responseListener.onResponseSuccess(response);
            }
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                responseListener.onResponseFailure(t);
            }
        });
    }


}