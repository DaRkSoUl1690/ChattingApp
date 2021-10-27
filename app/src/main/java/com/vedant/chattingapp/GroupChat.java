package com.vedant.chattingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vedant.chattingapp.Activities.MainActivity;
import com.vedant.chattingapp.Adapters.chatAdapter;
import com.vedant.chattingapp.Models.MessageModel;
import com.vedant.chattingapp.databinding.ActivityGroupChatBinding;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class GroupChat extends AppCompatActivity {

    ActivityGroupChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
   getSupportActionBar().hide();
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupChat.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
         final ArrayList<MessageModel> messageModels = new ArrayList<>();

         final String senderId=FirebaseAuth.getInstance().getUid();
         binding.textView4.setText("FriendsGroup");


        final chatAdapter  adapter = new chatAdapter(messageModels,this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(manager);


           database.getReference().child("Group chat")
                   .addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           messageModels.clear();
                           for (DataSnapshot dataSnapshot: snapshot.getChildren())
                           {
                               MessageModel model = dataSnapshot.getValue(MessageModel.class);
                               messageModels.add(model);
                           }
                           adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });



         binding.send.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final String message = binding.messagebar.getText().toString();

                 final MessageModel model = new MessageModel(senderId,message);
                 model.setTimestamp(new Date().getTime());
                 binding.messagebar.setText(" ");

                 database.getReference().child("Group chat")
                                        .push()
                         .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void unused) {

                     }
                 });
             }
         });

    }
}
