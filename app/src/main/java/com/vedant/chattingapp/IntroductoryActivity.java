package com.vedant.chattingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.vedant.chattingapp.Boarding.Boarding_1;
import com.vedant.chattingapp.Boarding.Boarding_2;
import com.vedant.chattingapp.Boarding.Boarding_3;

import org.jetbrains.annotations.NotNull;

public class IntroductoryActivity extends AppCompatActivity {


    public static final int NUM_PAGES=3;
    private ViewPager viewPager;
  private SlidePagerAdapter pagerAdapter;
    ImageView backgr;
    LottieAnimationView chatani;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_introductory);

        textView=findViewById(R.id.textView);
        backgr=findViewById(R.id.imageView);
        chatani=findViewById(R.id.lottie);
        viewPager=findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        backgr.animate().translationY(-4000).setDuration(2000).setStartDelay(4000);
       textView.animate().translationY(4000).setDuration(2000).setStartDelay(4000);
       chatani.animate().translationY(4000).setDuration(2000).setStartDelay(4000);

    }

public class SlidePagerAdapter extends FragmentStatePagerAdapter {
    public SlidePagerAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0 :
                Boarding_1 tab1 = new Boarding_1();
                return tab1;
            case 1 :
                Boarding_2 tab2 = new Boarding_2();
                return tab2;
            case 2:
                Boarding_3 tab3 = new Boarding_3();
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }


}



}