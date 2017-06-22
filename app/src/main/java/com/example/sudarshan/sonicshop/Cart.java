package com.example.sudarshan.sonicshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sudarshan on 20-03-2017.
 */

public class Cart {
    @SerializedName("uemail")
    @Expose
    private String uemail;
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
    private double sum;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @SerializedName("Quantity")
    @Expose
    private int quantity;

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

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
