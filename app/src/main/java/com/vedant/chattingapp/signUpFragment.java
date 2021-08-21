package com.vedant.chattingapp;


import android.app.Activity;
import android.app.FragmentManager;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.vedant.chattingapp.Models.Users;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public  class signUpFragment extends Fragment {

    private static final String TAG = "EmailPassword";
 Context mContext;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    EditText email,password,cPassword,name;
    Button login;
    float v=0;


    public signUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        mContext=activity;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_login_frag1,container,false);


       // context=container.getContext();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("creating account");
        progressDialog.setMessage("we are creating your account");

        email=root.findViewById(R.id.email1);
        password= root.findViewById(R.id.password1);
        login=root.findViewById(R.id.signup);
        cPassword=root.findViewById(R.id.password2);
       name=root.findViewById(R.id.Name);

        email.setTranslationY(500);
        password.setTranslationY(600);
        login.setTranslationY(800);
        cPassword.setTranslationY(700);
        name.setTranslationY(700);

        email.setAlpha(v);
        password.setAlpha(v);
        login.setAlpha(v);
       cPassword.setAlpha(v);
       name.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        password.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        cPassword.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        name.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();


                mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                        password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        
                        if(task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Users users = new Users(name.getText().toString(),email.getText().toString(),password.getText().toString());

                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(users);


                            Log.d(TAG, "createUserWithEmail:success");

                                Toast.makeText(mContext, "User created Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                           Toast.makeText(mContext, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }


                });


            }
        });

        return root;
    }

    }

