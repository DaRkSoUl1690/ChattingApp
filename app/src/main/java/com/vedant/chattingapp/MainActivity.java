package com.vedant.chattingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.vedant.chattingapp.Adapters.fragmentAdapter;
import com.vedant.chattingapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

     ViewPager viewPager;
     TabLayout tabLayout;
    ActivityMainBinding binding;
     FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);

        viewPager.setAdapter(new fragmentAdapter(getSupportFragmentManager()));
           tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.setting:
                Intent intent = new Intent(this,settingActivity.class);
                startActivity(intent);
                break;


            case R.id.logout:
                mAuth.signOut();
                Intent intent2 = new Intent(this,login_Activity.class);
                startActivity(intent2);
                break;

            case R.id.groupChat:
                Intent intent1 = new Intent(this,GroupChat.class);
                startActivity(intent1);
                break;

        }

        return true;
    }
}