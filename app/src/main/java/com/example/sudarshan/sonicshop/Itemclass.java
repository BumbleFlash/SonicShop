package com.example.sudarshan.sonicshop;

/**
 * Created by Sudarshan on 19-03-2017.
 */

public class Itemclass {
    public int num;
    public String name;
    public double price;
    public Itemclass()
    {}
    public Itemclass(int num,String name,double price)
    {
        this.num=num;
        this.name=name;
        this.price=price;
    }
    public int getNumber(){return num;}
    public String getName(){return name;}
    public double getPrice(){return price;}
    public void setNumber(int num){this.num=num;}
    public void setName(String name){this.name=name;}
    public void setPrice(double price){this.price=price;}



}
