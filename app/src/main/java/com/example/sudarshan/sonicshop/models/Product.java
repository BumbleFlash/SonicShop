package com.example.sudarshan.sonicshop.models;

/**
 * Created by admin on 3/18/2017.
 */

public class Product {
    private String ProductName;
    private String Price;
//    private static int mQuantity;
    private String item;
    private String Picurl;

    public Product(String productName, String price, String item,String picurl) {
        ProductName = productName;
        Price = price;
        this.item = item;
        this.Picurl = picurl;
    }

    public String getPicurl() {
        return Picurl;
    }

    public void setPicurl(String picurl) {
        Picurl = picurl;
    }

    public Product() {
    }


    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String  getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
