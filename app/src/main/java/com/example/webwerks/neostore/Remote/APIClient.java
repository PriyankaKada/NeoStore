package com.example.webwerks.neostore.Remote;


import com.example.webwerks.neostore.SignUp.Example;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
