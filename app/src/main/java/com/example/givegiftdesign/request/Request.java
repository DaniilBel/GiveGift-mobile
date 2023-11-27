package com.example.givegiftdesign.request;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Request {
    public void req() {
        // Работающий запрос на сторонний сервер
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        PreferenceApi prefApi = retrofit.create(PreferenceApi.class);
        ServerApi serverApi = retrofit.create(ServerApi.class);

//        Call<List<Preference>> prefs = prefApi.prefs();
        Call<Account> acc = serverApi.getAccount();

//        prefs.enqueue(new Callback<List<Preference>>() {
//            @Override
//            public void onResponse(Call<List<Preference>> call, Response<List<Preference>> response) {
//                if (response.isSuccessful())
//                    Log.d("Callback", "response: " + response.body().size());
//                else
//                    Log.d("Response code", "response code " + response.code());
//            }
//
//            @Override
//            public void onFailure(Call<List<Preference>> call, Throwable t) {
//                Log.w("Failure", "failure: " + t);
//            }
//        });

        acc.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful())
                    Log.d("Callback", "response: " + response.body());
                else
                    Log.d("Response code", "response code " + response.code());
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.w("Failure", "failure: " + t);
            }
        });

        // Временная заглушка на предпочтения
        Account.tempInterests();

    }
}