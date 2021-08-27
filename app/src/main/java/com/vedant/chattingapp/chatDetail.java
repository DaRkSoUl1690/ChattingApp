package com.vedant.chattingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vedant.chattingapp.Adapters.chatAdapter;
import com.vedant.chattingapp.Models.MessageModel;
import com.vedant.chattingapp.databinding.ActivityChatDetailBinding;

import java.util.ArrayList;
import java.util.Date;

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

      final   String senderId = auth.getUid();
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

        final ArrayList<MessageModel> messageModels = new ArrayList<>();

        final chatAdapter chatAdapter = new chatAdapter(messageModels , this);
        binding.recyclerView.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager  = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        final String sendeRoom = senderId + recieveid;
        final String recieverRoom = recieveid + senderId;

database.getReference().child("Chats")
        .child(sendeRoom)

       .addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

      messageModels.clear();
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
        {
            MessageModel model  = dataSnapshot1.getValue(MessageModel.class);

            messageModels.add(model);
        }
        chatAdapter.notifyDataSetChanged();

    }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }


       });



        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String message =   binding.messagebar.getText().toString();
              final MessageModel messageModel = new MessageModel(senderId,message);
              messageModel.setTimestamp(new Date().getTime());
              binding.messagebar.setText(" ");

              database.getReference().child("Chats")
                      .child(sendeRoom)
                      .push()
                      .setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void unused) {
                      database.getReference().child("Chats")
                              .child(recieverRoom)
                              .push()
                              .setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void unused) {

                          }
                      });
                  }
              });
            }
        });

    }
}