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



    @SerializedName("Quantity")
    @Expose
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
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




