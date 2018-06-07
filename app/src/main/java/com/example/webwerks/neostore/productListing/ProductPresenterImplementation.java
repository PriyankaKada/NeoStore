package com.example.webwerks.neostore.productListing;

import android.util.Log;

import com.example.webwerks.neostore.remote.ResponseListener;
import com.example.webwerks.neostore.remote.RetroHelper;
import com.example.webwerks.neostore.utils.SPManager;

import java.util.List;

import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 3/30/18.
 */

public class ProductPresenterImplementation implements ProductListPresenter {
    SPManager instance;

    ProductView productView;
    private ProductDatail res;
    List<ProductData> productDataList;

    public ProductPresenterImplementation(ProductView productView, SPManager instance) {
        this.productView = productView;
        this.instance = instance;
    }

    @Override
    public void getProductData() {
        productView.showProgressBar();
        int category_id = instance.retriveInt("Product_category_id", 1);
        networkcall(category_id);


    }

    private void networkcall(int category_id) {
        RetroHelper.getInstance().ProductsRequest(category_id, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (ProductDatail) baseResponse.body();
                if (res != null) {
                    if (res.getStatus() != null) {
                        Log.e(TAG, "onResponse LoginView Success: " + "Success");
                        productDataList= res.getData();
                        productView.getDataFromURL(productDataList);
                        productView.hideProgressBar();

                    } else {
                        productView.successMessage("Failed status null");
                    }

                } else {
                    productView.successMessage("Failed res null ");
                }
            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + throwable.getMessage());
                productView.successMessage("Failed Throwable");

            }
        });
    }
}
