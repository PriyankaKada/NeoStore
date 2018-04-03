package com.example.webwerks.neostore.Remote;


import com.example.webwerks.neostore.Login.forgotPassword.ForgotPasswordModel;
import com.example.webwerks.neostore.ProductDetail.SingleProductDetails;
import com.example.webwerks.neostore.ProductListing.ProductDatail;
import com.example.webwerks.neostore.SignUp.Example;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by webwerks on 9/3/18.
 */

public interface APIClient {


    //put data to server
    @FormUrlEncoded
    @POST("users/register")
    Call<Example> createAccount(@Field("first_name") String name,
                                @Field("last_name") String last_name,
                                @Field("email") String email,
                                @Field("password") String password,
                                @Field("confirm_password") String confirm_password,
                                @Field("gender") String gender,
                                @Field("phone_no") int phone_no);

    @FormUrlEncoded
    @POST("users/login")
    Call<Example> signin(@Field("email") String email,
                         @Field("password") String password);
    @FormUrlEncoded
    @POST("users/forgot")
    Call<ForgotPasswordModel> forgotPassword(@Field("email") String email);

    @GET("products/getList")
    Call<ProductDatail> getProductDatails(@Query("product_category_id") int category_id);

    @GET("products/getDetail")
    Call<SingleProductDetails> getSingleProductDetails(@Query("product_id") int product_id);

}
