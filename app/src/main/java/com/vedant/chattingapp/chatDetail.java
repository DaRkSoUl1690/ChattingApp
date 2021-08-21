package com.vedant.chattingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.vedant.chattingapp.databinding.ActivityChatDetailBinding;

public class chatDetail extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);
        binding  = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
  getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
               auth = FirebaseAuth.getInstance();

        String senderId = auth.getUid();
        String recieveid = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("username");
        String profilepic = getIntent().getStringExtra("profilepic");

        binding.textView4.setText(userName);
        Picasso.get().load(profilepic).placeholder(R.drawable.userimg).into(binding.profileImage);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chatDetail.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}