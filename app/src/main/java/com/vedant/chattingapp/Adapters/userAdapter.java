package com.vedant.chattingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vedant.chattingapp.Models.Users;
import com.vedant.chattingapp.R;
import com.vedant.chattingapp.chatDetail;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder> {

    ArrayList<Users>  list;
    Context context;

    public userAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_user,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          Users users = list.get(position);
        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.userimg).into(holder.img);
          holder.userName.setText(users.getUserName());
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(context, chatDetail.class);
                  intent.putExtra("userId",users.getUserId());
                  intent.putExtra("profilepic",users.getProfilepic());
                  intent.putExtra("username",users.getUserName());
                  context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView userName, lastMessage;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.userName);
            lastMessage = itemView.findViewById(R.id.lastMessage);
        }
    }
}




