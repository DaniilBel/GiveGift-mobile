package com.example.givegiftdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.givegiftdesign.profilescreen.adapter.CollectionAdapter;
import com.example.givegiftdesign.profilescreen.adapter.PostAdapter;
import com.example.givegiftdesign.profilescreen.api.MyretrofitClient;
import com.example.givegiftdesign.profilescreen.model.UserCollection;
import com.example.givegiftdesign.profilescreen.model.UserData;
import com.example.givegiftdesign.profilescreen.model.UserPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ImageView profileimage;
    List<UserCollection>userCollections;
    List<UserPost>userPosts;
    CollectionAdapter collectionAdapter;
    PostAdapter postAdapter;
    RecyclerView collection;
    TextView username,userinfo,userpost,userfollower,userfollowing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileimage=(ImageView)findViewById(R.id.profile_image);
        username=(TextView)findViewById(R.id.username);
        userpost=(TextView)findViewById(R.id.posts);
        userinfo=(TextView)findViewById(R.id.about);
        userfollower=(TextView)findViewById(R.id.follower);
        userfollowing=(TextView)findViewById(R.id.following);

        getuserdata();

        collection=(RecyclerView)findViewById(R.id.rec_collection);
        collection.setHasFixedSize(true);
        collection.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        getcollectiondata();

        collection.setHasFixedSize(true);
        collection.setLayoutManager(new GridLayoutManager(this,3));
        getpostdata();
    }

    private void getuserdata() {
        Call<UserData>call= MyretrofitClient.getInstance().getMyApi().geuserdata();
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(@NonNull Call<UserData> call, @NonNull Response<UserData> response) {
                assert response.body() != null;
                String name=response.body().getUsername();
                String profileimg=response.body().getProfile_image();
                String about=response.body().getUserinfo();
                String post=response.body().getPosts();
                String followers=response.body().getFollowers();
                String following=response.body().getFollowing();
                username.setText(name);
                userinfo.setText(about);
                userfollower.setText(followers);
                userfollowing.setText(following);
                userpost.setText(post);
                Glide.with(getApplicationContext())
                        .load(profileimg)
                        .apply(RequestOptions.circleCropTransform())
                        .into(profileimage);
            }

            @Override
            public void onFailure(@NonNull Call<UserData> call, @NonNull Throwable t) {

            }
        });
    }
    private void getcollectiondata() {
        Call<List<UserCollection>>call=MyretrofitClient.getInstance().getMyApi().getcolldata();
        call.enqueue(new Callback<List<UserCollection>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserCollection>> call, @NonNull Response<List<UserCollection>> response) {
                userCollections = response.body();
                collectionAdapter = new CollectionAdapter(userCollections, getApplicationContext());
                collection.setAdapter(collectionAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<UserCollection>> call, @NonNull Throwable t) {

            }
        });
    }

    private void getpostdata() {
        Call<List<UserPost>>call=MyretrofitClient.getInstance().getMyApi().getpostdata();
        call.enqueue(new Callback<List<UserPost>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserPost>> call, @NonNull Response<List<UserPost>> response) {
                userPosts = response.body();
                postAdapter = new PostAdapter(userPosts,getApplicationContext());
                collection.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<UserPost>> call, @NonNull Throwable t) {

            }
        });
    }
}
