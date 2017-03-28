package com.example.sudarshan.sonicshop;

/**
 * Created by Sudarshan on 20-03-2017.
 */

public class users {
    public String uname;
    public Double uprice;
    public String uid;
    public int quantity;
//    public String Picurl;

//    public String getPicurl() {
//        return Picurl;
//    }
//
//    public void setPictureurl(String picurl) {
//        Picurl = picurl;
//    }

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

    public users(String uname, Double uprice,String uid,int quantity) {
        this.uname = uname;
        this.uprice = uprice;
        this.uid= uid;
        this.quantity=quantity;
//        this.Picurl=Picurl;
    }

    public Double getUprice() {
        return uprice;
    }

    public void setUprice(Double uprice) {
        this.uprice = uprice;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
