package com.example.givegiftdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    //Hooks
    ImageView profile_image;
    TextView user_name, age, about, email, password, username;
    Button update_button;
    FirebaseDatabase firebaseDatabase;
    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_image = findViewById(R.id.profile_image);
        user_name = findViewById(R.id.user_name);
        age = findViewById(R.id.age);
        about = findViewById(R.id.about_user);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        username = findViewById(R.id.login_username);
        update_button = findViewById(R.id.update_profile);

        update_button.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, UpdateActivity.class);
            startActivity(intent);
        });

        firebaseDatabase = FirebaseDatabase.getInstance("https://givegift-241db-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("users");

        /*Intent intent = getIntent();
        String userUsername = intent.getStringExtra("username");
        getData(userUsername);

        if (userUsername == null) {
            Toast.makeText(ProfileActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
        }*/
    }

    private void getData(String userUsername) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nameFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                String usernameFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                user_name.setText(nameFromDB);
                username.setText(usernameFromDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
                    /* intent = getIntent();
                    String user_username = intent.getStringExtra("username");
                    String user_name_db = intent.getStringExtra("user_name");
                    String user_email = intent.getStringExtra("email");
                    String user_password = intent.getStringExtra("password");

                    user_name.setText(nameFromDB);
                    username.setText(usernameFromDB);*/

/*
public class ProfileActivity extends AppCompatActivity {

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

        profile_image = findViewById(R.id.profile_image);
        username= findViewById(R.id.username);
        userpost= findViewById(R.id.posts);
        userinfo= findViewById(R.id.about);
        userfollower= findViewById(R.id.follower);
        userfollowing= findViewById(R.id.following);

        getUserData();

        collection= findViewById(R.id.rec_collection);
        collection.setHasFixedSize(true);
        collection.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        getcollectiondata();

        collection.setHasFixedSize(true);
        collection.setLayoutManager(new GridLayoutManager(this,3));
        getpostdata();
    }

    private void getUserData() {
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
                        .into(profile_image);
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
*/
