package com.example.sudarshan.sonicshop;

/**
 * Created by Sudarshan on 20-03-2017.
 */

public class users {
    public String uname;
    public double uprice;
    public String uid;
    public int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public users(){}

    public users(String uname, double uprice,String uid,int quantity) {
        this.uname = uname;
        this.uprice = uprice;
        this.uid= uid;
        this.quantity=quantity;
    }

    public double getUprice() {
        return uprice;
    }

    public void setUprice(double uprice) {
        this.uprice = uprice;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
