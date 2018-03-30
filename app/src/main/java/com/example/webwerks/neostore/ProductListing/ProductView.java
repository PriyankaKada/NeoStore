package com.example.webwerks.neostore.ProductListing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webwerks on 3/30/18.
 */

public interface ProductView {
     void successMessage(String message);
     void getDataFromURL(List<ProductData> list);

}
