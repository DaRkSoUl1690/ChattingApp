package com.vedant.chattingapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.vedant.chattingapp.Models.Users;
import com.vedant.chattingapp.R;
import com.vedant.chattingapp.databinding.ActivitySettingBinding;

import java.util.HashMap;
import java.util.Objects;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class settingActivity extends AppCompatActivity {

    ActivitySettingBinding binding;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;


    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri uri = data.getData();
                            binding.profileImage.setImageURI(uri);
                            final StorageReference reference = storage.getReference().child("profile pictures")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
                            reference.putFile(uri).addOnSuccessListener(taskSnapshot -> reference.getDownloadUrl().addOnSuccessListener(uri1 -> {
                                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                        .child("profilePic").setValue(uri1.toString());


                                Toast.makeText(settingActivity.this, "Profile Picture updated", Toast.LENGTH_SHORT).show();
                            }));

                        }
                    }
                }

            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.backArrow.setOnClickListener(v -> startActivity(new Intent(settingActivity.this, MainActivity.class)));

        database.getReference().child("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users users = snapshot.getValue(Users.class);
                        assert users != null;
                        Picasso.get()
                                .load(users.getProfilePic())
                                .placeholder(R.drawable.user)
                                .into(binding.profileImage);

                        binding.etUserName.setText(users.getUserName());
                        binding.etStatus.setText(users.getAbout());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        binding.plus.setOnClickListener(v -> openSomeActivityForResult());


        binding.save.setOnClickListener(v -> {
            String about = binding.etStatus.getText().toString();
            String userName = binding.etUserName.getText().toString();

            HashMap<String, Object> obj = new HashMap<>();
            obj.put("userName", userName);
            obj.put("about", about);

            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                    .updateChildren(obj);

            startActivity(new Intent(settingActivity.this, MainActivity.class));
            finish();

            Toast.makeText(settingActivity.this, "Details Updated", Toast.LENGTH_SHORT).show();

        });

    }

    public void openSomeActivityForResult() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);
    }
}
