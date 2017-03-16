package com.example.sudarshan.sonicshop;

/**
 * Created by Sudarshan on 16-03-2017.
 */

public class itemClass {
    private String itemName;
    private double itemPrice;

    public itemClass(String name, double price) {
        itemName = name;
        itemPrice = price;
    }

    public String getName() {
        return itemName;
    }
    public double getPrice()
    {
        return itemPrice;
    }

}
