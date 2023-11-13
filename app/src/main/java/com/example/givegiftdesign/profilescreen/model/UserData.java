package com.example.givegiftdesign.profilescreen.model;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("username")
    public String username;

    @SerializedName("userinfo")
    public String userinfo;

    @SerializedName("profile_image")
    public String profile_image;

    @SerializedName("posts")
    public String posts;

    @SerializedName("followers")
    public String followers;

    @SerializedName("following")
    public String following;

    public UserData(){

    }

    public UserData(String username, String userinfo, String profile_image, String posts, String followers, String following) {
        this.username = username;
        this.userinfo = userinfo;
        this.profile_image = profile_image;
        this.posts = posts;
        this.followers = followers;
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", userinfo='" + userinfo + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", posts='" + posts + '\'' +
                ", followers='" + followers + '\'' +
                ", following='" + following + '\'' +
                '}';
    }
}