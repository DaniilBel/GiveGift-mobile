package com.example.givegiftdesign.profilescreen.model;

import com.google.gson.annotations.SerializedName;

public class UserCollection {
    @SerializedName("id")
    public int id;
    @SerializedName("collname")
    public String coll_name;
    @SerializedName("collimage")
    public String coll_image;
    public int getId() {
        return id;
    }
    public String getColl_name() {
        return coll_name;
    }
    public String getColl_image() {
        return coll_image;
    }
}
