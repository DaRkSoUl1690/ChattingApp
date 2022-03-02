package com.vedant.chattingapp.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.vedant.chattingapp.Activities.MainActivity;
import com.vedant.chattingapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class loginFragment extends Fragment {

    Context mContext;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String emailAddress = "tiwarivedant.2690@gmail.com";
    EditText email, password;
    TextView textView;
    Button login;
    ProgressDialog proDialog;


    public loginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            mContext = context;
        }
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_login2, container, false);

        mAuth = FirebaseAuth.getInstance();
        // progress dialog with message and title

//        progressDialog = new ProgressDialog(mContext);
//        progressDialog.setTitle("Login");
//        progressDialog.setMessage("Login to your account");


        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        login = root.findViewById(R.id.button2);
        textView = root.findViewById(R.id.textView3);


        textView.setOnClickListener(v -> mAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "email send", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), "email failed",
                                Toast.LENGTH_SHORT).show();

                    }
                }));

        login.setOnClickListener(v -> {
            try {
                if (email != null && password != null) {
//                    progressDialog.show();
                    proDialog = ProgressDialog.show(mContext, null, null, false, true);
                    proDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    proDialog.setContentView(R.layout.progress_bar);

                    mAuth.signInWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnCompleteListener(task -> {
                        // used with message and title
//                        progressDialog.dismiss();
//                        proDialog.setCancelable(true);
                        proDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {

                            Toast.makeText(mContext, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            } catch (Exception e) {
//              progressDialog.dismiss();
//                proDialog.setCancelable(true);


                Toast.makeText(getActivity(), "please fill your Email and Password",
                        Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });


        return root;
    }


}
