package com.example.sudarshan.sonicshop;

import com.example.sudarshan.sonicshop.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sudarshan on 06-04-2017.
 */

public interface RetrofitInterface {
    @GET("product.jsp")
    Call<List<Product>> getallproducts(
            @Query("cat") String category);
}
