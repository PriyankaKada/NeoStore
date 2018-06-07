package com.example.webwerks.neostore.productDetail;

/**
 * Created by webwerks on 4/2/18.
 */

public interface ProductDetailsView {
    void shareIntent();
    void setData(SingleProductDetails res);

    void getImageString(String image_url);
    void showProgressBar();
    void hideProgressBar();
}
