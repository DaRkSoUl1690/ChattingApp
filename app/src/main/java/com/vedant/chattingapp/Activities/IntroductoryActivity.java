package com.vedant.chattingapp.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.vedant.chattingapp.Adapters.SlidePagerAdapter;
import com.vedant.chattingapp.R;
import com.vedant.chattingapp.databinding.ActivityIntroductoryBinding;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class IntroductoryActivity extends AppCompatActivity {
    ActivityIntroductoryBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_introductory);
        mBinding = ActivityIntroductoryBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        SlidePagerAdapter pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        mBinding.pager.setAdapter(pagerAdapter);


        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
        }


    }


}
