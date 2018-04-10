package com.example.webwerks.neostore.Remote;

import com.example.webwerks.neostore.Login.forgotPassword.ForgotPasswordModel;
import com.example.webwerks.neostore.MyAccount.Model.UserDetails;
import com.example.webwerks.neostore.MyAccount.Model.UserDetailsUpdates;
import com.example.webwerks.neostore.MyCart.Model.CartModel;
import com.example.webwerks.neostore.ProductDetail.SingleProductDetails;
import com.example.webwerks.neostore.ProductListing.ProductDatail;

import com.example.webwerks.neostore.Rating.AddToCart;
import com.example.webwerks.neostore.Rating.Rating;
import com.example.webwerks.neostore.ResetPassword.ResetPassword;
import com.example.webwerks.neostore.Utils.AppConstants;
import com.example.webwerks.neostore.SignUp.Example;

import java.util.concurrent.TimeUnit;

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
    public void passwordRequest(String email, ResponseListener responseListener) {
        Call<ForgotPasswordModel> call = apiService.forgotPassword(email);
        networkCall(call, responseListener);
    }
    public void ProductsRequest(int category_id, ResponseListener responseListener) {
        Call<ProductDatail> call = apiService.getProductDatails(category_id);
        networkCall(call, responseListener);
    }
    public void getSingleProductDetail(int product_id, ResponseListener responseListener) {
        Call<SingleProductDetails> call = apiService.getSingleProductDetails(product_id);
        networkCall(call, responseListener);
    }
    public void setRating(String product_id,int ratings, ResponseListener responseListener) {
        Call<Rating> call = apiService.setRatings(product_id,ratings);
        networkCall(call, responseListener);
    }
    public void getUserProfileData(String access_token, ResponseListener responseListener) {
        Call<UserDetails> call = apiService.getUserProfileDetails(access_token);
        networkCall(call, responseListener);
    }
    public void submitUserProfileData(String access_token,String first_name,String last_name,String email,String dob,String phone_no,String profile_pic, ResponseListener responseListener) {
        Call<UserDetailsUpdates> call = apiService.submitUserProfileDetails(access_token,first_name,last_name,email,dob,phone_no,profile_pic);
        networkCall(call, responseListener);
    }
    public void requestcartItems(String access_token, ResponseListener responseListener) {
        Call<CartModel> call = apiService.getCartItems(access_token);
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


    public void resetPassword(String access_token, String old_password, String password, String confirm_password, ResponseListener responseListener) {
        Call<ResetPassword> call = apiService.resetPassword(access_token,old_password,password,confirm_password);
        networkCall(call, responseListener);
    }

    public void addtocart(String access_token, int product_id, int quantity, ResponseListener responseListener) {
        Call<AddToCart> call = apiService.addtocart(access_token,product_id,quantity);
        networkCall(call, responseListener);

    }
}