package com.vedant.chattingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vedant.chattingapp.Activities.MainActivity;
import com.vedant.chattingapp.Adapters.chatAdapter;
import com.vedant.chattingapp.Models.MessageModel;
import com.vedant.chattingapp.databinding.ActivityChatDetailBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class chatDetail extends AppCompatActivity {

    final ArrayList<MessageModel> messageModels = new ArrayList<>();
    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_chat_detail);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderId = auth.getUid();
        final String ReceiveId = getIntent().getStringExtra("userId");
        final String userName = getIntent().getStringExtra("username");
        final String ProfilePic = getIntent().getStringExtra("profilePic");


        binding.textView4.setText(userName);
        Picasso.get().load(ProfilePic).placeholder(R.drawable.user).into(binding.profileImage);

        //BACK BUTTON
        binding.back.setOnClickListener(v -> {
            Intent intent = new Intent(chatDetail.this, MainActivity.class);
            startActivity(intent);
        });


        final chatAdapter chatAdapter = new chatAdapter(messageModels, this);
        binding.recyclerView.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        final String senderRoom = senderId + ReceiveId;
        final String receiverRoom = ReceiveId + senderId;

        binding.send.setOnClickListener(v -> {

            String message = binding.messageBar.getText().toString();
            if (!message.equals("")) {
                final MessageModel messageModel = new MessageModel(senderId, message);
                messageModel.setTimeStamp(new Date().getTime());
                binding.messageBar.setText("");

                database.getReference().child("Chats")
                        .child(senderRoom)
                        .push()
                        .setValue(messageModel).addOnSuccessListener(unused -> database.getReference().child("Chats")
                        .child(receiverRoom)
                        .push()
                        .setValue(messageModel).addOnSuccessListener(unused1 -> {
                        }));
            } else {
                Toast.makeText(chatDetail.this, "Please write something", Toast.LENGTH_SHORT).show();
            }
        });

        database.getReference().child("Chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //**UNDERSTOOD*/
                messageModels.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    MessageModel model = dataSnapshot1.getValue(MessageModel.class);
                    messageModels.add(model);
                }
                chatAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }


        });


    }
}
