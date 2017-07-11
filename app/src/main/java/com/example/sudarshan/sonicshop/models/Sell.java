package com.example.sudarshan.sonicshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sudarshan on 11-07-2017.
 */

public class Sell {
    @SerializedName("ProductName")
    @Expose
    public String name;
    @SerializedName("Price")
    @Expose
    public Double price;
    @SerializedName("Category")
    @Expose
    public String category;
    @SerializedName("Email")
    @Expose
    public String email;

    public Sell(String name, Double price, String category, String email) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
