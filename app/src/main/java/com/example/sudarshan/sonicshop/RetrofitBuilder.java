package com.example.sudarshan.sonicshop;

/**
 * Created by Sudarshan on 06-04-2017.
 */

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitBuilder {
    public static final  String API_BASE_URL = "http://192.168.10.13:8080/SonicShopWeb/";

    public static  OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor());

    public static   Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );

    public static   Retrofit retrofit =builder
            .client(httpClient.build())
            .build();
    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
   // RetrofitInterface nurse =  retrofit.create(RetrofitInterface.class);

}
