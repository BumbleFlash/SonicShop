package com.example.sudarshan.sonicshop.models;

/**
 * Created by admin on 3/18/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("ProductID")
    @Expose
    private String productID;
    @SerializedName("Picurl")
    @Expose
    private String picurl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

}



//public class Product {
//    private String ProductName;
//    private String Price;
////    private static int mQuantity;
//    private String item;
//    private String Picurl;
//
//    public Product(String productName, String price, String item,String picurl) {
//        ProductName = productName;
//        Price = price;
//        this.item = item;
//        this.Picurl = picurl;
//    }
//
//    public String getPicurl() {
//        return Picurl;
//    }
//
//    public void setPicurl(String picurl) {
//        Picurl = picurl;
//    }
//
//    public Product() {
//    }
//
//
//    public String getProductName() {
//        return ProductName;
//    }
//
//    public void setProductName(String productName) {
//        ProductName = productName;
//    }
//
//    public String  getPrice() {
//        return Price;
//    }
//
//    public void setPrice(String price) {
//        Price = price;
//    }
//
//    public String getItem() {
//        return item;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }
