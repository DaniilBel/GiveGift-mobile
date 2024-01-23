package com.example.givegiftdesign.request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PreferenceApi {
    @GET("messages1.json")
    Call<List<Preference>> prefs();
}
