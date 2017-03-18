package com.example.sudarshan.sonicshop.models;

/**
 * Created by admin on 3/18/2017.
 */

public class Product {
    private static String mProductName;
    private static double mPrice;
    private static int mQuantity;
    public Product(){
        //empty constructor
    }
    public Product(String name, double price, int quantity){
        mProductName = name;
        mPrice = price;
        mQuantity = quantity;
    }
    public String getProductName(){
        return mProductName;
    }
    public double getProductPrice(){
        return mPrice;
    }
    public int getProductQuntity(){
        return mQuantity;
    }

}
