package com.example.givegiftdesign.request;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestDataApi {
    @POST("/generate_ideas")
    Call<RequestData> createPost(@Body RequestData requestData);
}
