package com.example.sudarshan.sonicshop;

import com.example.sudarshan.sonicshop.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Sudarshan on 06-04-2017.
 */

public interface RetrofitInterface {
    @GET("product.jsp")
    Call<List<Product>> getallproducts(

            @Query("cat") String category);
    @FormUrlEncoded
    @POST("signup.jsp")

    Call<Void> setUsers(
            @Field("Json") String users
    );
    @GET("loginand.jsp")
    Call<users> auth(
            @Query("uemail") String uemail, @Query("upass") String upass
    );
    @FormUrlEncoded
    @POST("cart.jsp")
    Call<Void> setProducts(@Field("cart") String Cart);
    @GET("getcart.jsp")
    Call<List<Cart>> getCart(@Query("email") String email);
    @FormUrlEncoded
    @POST("updateCart.jsp")
    Call<Void> sendUpdate(@Field("Update") String update);


}
