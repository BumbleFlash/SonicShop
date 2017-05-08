package com.example.sudarshan.sonicshop;

/**
 * Created by Sudarshan on 20-03-2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class users {
    @SerializedName("uname")
    @Expose
    public String uname;
    @SerializedName("upassword")
    @Expose
    public String upassword;

    @SerializedName("uemail")
    @Expose
    public String uemail;

    public users() {

    }

    public users(String uname, String upassword, String uemail) {
        this.uname = uname;
        this.upassword = upassword;
        this.uemail = uemail;
    }

//    public users(String uname, String upassword, String uid, String uemail) {
//
//        this.uname = uname;
//        this.upassword = upassword;
//
//        this.umail = umail;
//    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upassword;
    }

    public void setUpass(String upassword) {
        this.upassword = upassword;
    }

//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }

    public String getUmail() {
        return uemail;
    }

    public void setUmail(String umail) {
        this.uemail = umail;
    }
}


