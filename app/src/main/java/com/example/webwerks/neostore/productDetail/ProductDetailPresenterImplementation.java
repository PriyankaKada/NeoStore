package com.example.webwerks.neostore.productDetail;

import android.util.Log;
import com.example.webwerks.neostore.remote.ResponseListener;
import com.example.webwerks.neostore.remote.RetroHelper;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks on 4/2/18.
 */

public class ProductDetailPresenterImplementation implements ProductDetailPresenter {
    ProductDetailsView productDetailsView;
    private SingleProductDetails res;

    public ProductDetailPresenterImplementation(ProductDetailsView productDetailsView) {
        this. productDetailsView=productDetailsView;
    }


    @Override
    public void getDatafromAPI(int product_id) {
        productDetailsView.showProgressBar();
        networkcall(product_id);
    }

    private void networkcall(int product_id) {
        RetroHelper.getInstance().getSingleProductDetail(product_id, new ResponseListener() {
            @Override
            public void onResponseSuccess(Response baseResponse) {
                res = (SingleProductDetails) baseResponse.body();
                if (res != null) {
                    Log.e(TAG, "onResponseSuccess: "+"Res not null");
                    productDetailsView.setData(res);
                    productDetailsView.hideProgressBar();


                } else {
//                    loginView.loginError("Unable to login");
                }
            }

            @Override
            public void onResponseFailure(Throwable throwable) {
                Log.e(TAG, "onResponse: Response" + throwable.getMessage());
//                loginView.loginError("Unable to login");

            }
        });

    }
}
