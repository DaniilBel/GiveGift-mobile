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

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

private final List<UserCollection>userCollections;
private final Context context;

public CollectionAdapter(List<UserCollection> userCollections, Context context) {
        this.userCollections = userCollections;
        this.context = context;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_layout,parent,false);
            return new ViewHolder(view);
            }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            UserCollection uc=userCollections.get(position);

            Glide.with(context)
            .load(uc.getColl_image())
            .into(holder.collimage);

            holder.collname.setText(uc.getColl_name());
            }

    @Override
    public int getItemCount() {
            return userCollections.size();
            }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView collname;
        ImageView collimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            collname=(TextView)itemView.findViewById(R.id.collname);
            collimage=(ImageView)itemView.findViewById(R.id.coll_image);
        }
    }
}
