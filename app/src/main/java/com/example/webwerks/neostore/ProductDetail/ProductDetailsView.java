package com.example.webwerks.neostore.ProductDetail;

import com.example.webwerks.neostore.ProductListing.ProductData;

/**
 * Created by webwerks on 4/2/18.
 */

public interface ProductDetailsView {
    ProductData getIntentData();
    void setTitle(String Title);
    void setData();
    void shareIntent();
}
