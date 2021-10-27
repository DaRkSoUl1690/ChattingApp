package com.vedant.chattingapp.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.vedant.chattingapp.Activities.MainActivity;
import com.vedant.chattingapp.Models.Users;
import com.vedant.chattingapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class signUpFragment extends Fragment {

    private static final String TAG = "EmailPassword";
    Context mContext;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    EditText email, password, name;
    Button login;
//    float v = 0;


    public signUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_login_frag1, container, false);

        // context=container.getContext();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("creating account");
        progressDialog.setMessage("we are creating your account");

        email = root.findViewById(R.id.email1);
        password = root.findViewById(R.id.password1);
        login = root.findViewById(R.id.signup);
        name = root.findViewById(R.id.Name);
//
//        email.setTranslationY(500);
//        password.setTranslationY(600);
//        login.setTranslationY(800);
//
//        name.setTranslationY(700);
//
//        email.setAlpha(v);
//        password.setAlpha(v);
//        login.setAlpha(v);
//     //  cPassword.setAlpha(v);
//       name.setAlpha(v);
//
//        name.animate().translationY(0).alpha(1).setDuration(700).setStartDelay(500).start();
//        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
//        password.animate().translationY(0).alpha(1).setDuration(900).setStartDelay(700).start();
//        login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
//     //   cPassword.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();


        login.setOnClickListener(v -> {
            try {
                if (email != null && password != null && name != null) {
                    progressDialog.show();

                    if (password.length() < 6)
                        progressDialog.dismiss();
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Users users = new Users(name.getText().toString(), email.getText().toString(), password.getText().toString());

                            String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                            database.getReference().child("Users").child(id).setValue(users);


                            Log.d(TAG, "createUserWithEmail:success");

                            Toast.makeText(mContext, "User created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, MainActivity.class));
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(mContext, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "please fill your Email and Password",
                        Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        });

        return root;
    }

}
