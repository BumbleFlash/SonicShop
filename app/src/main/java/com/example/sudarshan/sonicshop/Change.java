package com.example.sudarshan.sonicshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sudarshan on 23-06-2017.
 */

public class Change {
    @SerializedName("uemail")
    @Expose
    private String uemail;
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("quantity")
    @Expose
    public int quantity;
    @SerializedName("choice")
    @Expose
    public int choice;   //update =1 and delete =2

    public Change(String itemName, int quantity, int choice,String uemail) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.choice = choice;
        this.uemail=uemail;
    }
    public Change(){}
}
