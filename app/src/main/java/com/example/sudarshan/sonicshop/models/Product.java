package com.example.sudarshan.sonicshop.models;

/**
 * Created by admin on 3/18/2017.
 */

public class Product {
    private String ProductName;
    private double Price;
//    private static int mQuantity;
    private int item;
    private String Picurl;

    public Product(String productName, double price, int item, String picurl) {
        ProductName = productName;
        Price = price;
        this.item = item;
        Picurl = picurl;
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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
}
