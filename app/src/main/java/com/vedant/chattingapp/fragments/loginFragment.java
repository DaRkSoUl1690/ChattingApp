package com.vedant.chattingapp.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

    private static final String TAG = "EmailPassword";
    Context mContext;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    EditText email, password;
    TextView textView;
    Button login;
    float v = 0;

    public loginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_login2, container, false);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account");


        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        login = root.findViewById(R.id.button2);
        textView = root.findViewById(R.id.textView3);


//       email.setTranslationY(500);
//        password.setTranslationY(600);
//        login.setTranslationY(700);
//        textView.setTranslationY(700);
//
//        email.setAlpha(v);
//        password.setAlpha(v);
//        login.setAlpha(v);
//        textView.setAlpha(v);
//
//        email.animate().translationY(0).alpha(1).setDuration(700).setStartDelay(500).start();
//        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
//        textView.animate().translationY(0).alpha(1).setDuration(900).setStartDelay(700).start();
//        login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        login.setOnClickListener(v -> {
            try {
                if (email != null && password != null) {
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnCompleteListener(task -> {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        } else {
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

//         if(mAuth.getCurrentUser()!=null)
//         {
//             Intent intent = new Intent(getActivity(),MainActivity.class);
//             startActivity(intent);
//         }


        return root;
    }


}
