package com.example.givegiftdesign.profilescreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.givegiftdesign.R;
import com.example.givegiftdesign.profilescreen.model.UserCollection;
import com.example.givegiftdesign.profilescreen.model.UserPost;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<UserPost>userPosts;
    private Context context;

    public PostAdapter(List<UserPost> userPosts, Context context) {
            this.userPosts = userPosts;
            this.context = context;
            }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout,parent,false);
            return new ViewHolder(view);
            }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            UserPost up=userPosts.get(position);

            Glide.with(context)
            .load(up.getPost_data())
            .into(holder.postimage);
            }

    @Override
    public int getItemCount() {
            return userPosts.size();
            }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView postimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postimage=(ImageView)itemView.findViewById(R.id.post_image);

        }
    }
}
