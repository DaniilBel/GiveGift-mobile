package com.example.givegiftdesign.profilescreen.model;

import com.google.gson.annotations.SerializedName;

public class UserPost {

    @SerializedName("id")
    public int id;

    @SerializedName("post_data")
    public String post_data;

    public int getId() {
        return id;
    }

    public String getPost_data() {
        return post_data;
    }
}
