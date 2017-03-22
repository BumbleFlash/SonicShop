package com.example.sudarshan.sonicshop;

/**
 * Created by Sudarshan on 20-03-2017.
 */

public class Cart {
    public String uname;

    public double uprice;
    public int cartitem;

    public int getCartitem() {
        return cartitem;
    }

    public void setCartitem(int cartitem) {
        this.cartitem = cartitem;
    }

    public Cart(String uname, double uprice, int cartitem) {
        this.uname = uname;
        this.uprice = uprice;
        this.cartitem=cartitem;
    }
    public Cart(){}

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public double getUprice() {
        return uprice;
    }

    public void setUprice(double uprice) {
        this.uprice = uprice;
    }
}
