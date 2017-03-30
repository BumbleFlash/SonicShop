package com.example.sudarshan.sonicshop;

/**
 * Created by Sudarshan on 20-03-2017.
 */

public class Cart {
    public String uname;

    public double uprice;
    public int cartitem;
    public int quantity;
    public String Picurl;


    public String getPic() {
        return Picurl;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartitem() {
        return cartitem;
    }

    public void setCartitem(int cartitem) {
        this.cartitem = cartitem;
    }

    public Cart(String uname, double uprice, int cartitem,int quantity) {
        this.uname = uname;
        this.uprice = uprice;
        this.cartitem=cartitem;
        this.quantity=quantity;

    }
    public Cart(String uname, double uprice,int quantity) {
        this.uname = uname;
        this.uprice = uprice;

        this.quantity=quantity;
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
