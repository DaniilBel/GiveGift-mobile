package com.example.givegiftdesign.profilescreen.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyretrofitClient {
    private static final String BASE_URL="https://uniqueandrocode.000webhostapp.com/hiren/profileui/";
    private static MyretrofitClient myClient;
    private Retrofit retrofit;

    private MyretrofitClient(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized MyretrofitClient getInstance(){
        if (myClient==null){
            myClient=new MyretrofitClient();
        }
        return myClient;
    }

    public MyApi getMyApi(){
        return retrofit.create(MyApi.class);
    }
}

