package com.example.givegiftdesign.profilescreen.api;

import com.example.givegiftdesign.profilescreen.model.UserCollection;
import com.example.givegiftdesign.profilescreen.model.UserData;
import com.example.givegiftdesign.profilescreen.model.UserPost;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApi {

    @GET("getuserdata.php")
    Call<UserData> geuserdata();

    @GET("getcollectiondata.php")
    Call<List<UserCollection>> getcolldata();

    @GET("getpostdata.php")
    Call<List<UserPost>> getpostdata();
}
