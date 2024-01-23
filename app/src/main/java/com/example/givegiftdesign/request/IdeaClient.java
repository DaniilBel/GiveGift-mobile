package com.example.givegiftdesign.request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IdeaClient {
    @GET("/generate_ideas")
    Call<List<Idea>> ideasForUser();
}
