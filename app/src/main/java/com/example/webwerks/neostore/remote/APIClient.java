package com.example.webwerks.neostore.remote;


import com.example.webwerks.neostore.address.AddressListingModel;
import com.example.webwerks.neostore.login.forgotPassword.ForgotPasswordModel;
import com.example.webwerks.neostore.myAccount.model.UserDetails;
import com.example.webwerks.neostore.myAccount.model.UserDetailsUpdates;
import com.example.webwerks.neostore.myCart.model.CartModel;
import com.example.webwerks.neostore.productDetail.SingleProductDetails;
import com.example.webwerks.neostore.productListing.ProductDatail;
import com.example.webwerks.neostore.rating.AddToCart;
import com.example.webwerks.neostore.rating.Rating;
import com.example.webwerks.neostore.resetPassword.ResetPassword;
import com.example.webwerks.neostore.signUp.Example;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @FormUrlEncoded
    @POST("products/setRating")
    Call<Rating> setRatings(@Field("product_id") String product_id,
                            @Field("rating") int rating);

    @GET("users/getUserData")
    Call<UserDetails> getUserProfileDetails(@Header("access_token") String access_token);


    @FormUrlEncoded
    @POST("users/update")
    Call<UserDetailsUpdates> submitUserProfileDetails(@Header("access_token") String access_token,
                                                      @Field("first_name") String first_name,
                                                      @Field("last_name") String last_name,
                                                      @Field("email") String email,
                                                      @Field("dob") String dob,
                                                      @Field("phone_no") String phone_no,
                                                      @Field("profile_pic") String profile_pic
    );

    @FormUrlEncoded
    @POST("users/change")
    Call<ResetPassword> resetPassword(@Header("access_token") String access_token,
                                      @Field("old_password") String old_password,
                                      @Field("password") String password,
                                      @Field("confirm_password") String confirm_password);


    @FormUrlEncoded
    @POST("addToCart")
    Call<AddToCart> addtocart(@Header("access_token") String access_token,
                              @Field("product_id") int product_id,
                              @Field("quantity") int quantity
    );

    @GET("cart")
    Call<CartModel> getCartItems(@Header("access_token") String access_token);

    @FormUrlEncoded
    @POST("deleteCart")
    Call<AddToCart> deletefromcart(@Header("access_token") String access_token,
                                   @Field("product_id") Integer productId);

    @FormUrlEncoded
    @POST("editCart")
    Call<AddToCart> editcart(@Header("access_token") String access_token,
                             @Field("product_id") int product_id,
                             @Field("quantity") int selected_value);

    @FormUrlEncoded
    @POST("order")
    Call<AddressListingModel> placeOrder(@Header("access_token") String access_token,
                                         @Field("address") String address);
}
