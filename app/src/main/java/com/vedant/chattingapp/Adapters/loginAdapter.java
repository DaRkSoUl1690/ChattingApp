package com.vedant.chattingapp.Adapters;

import android.content.Context;

import com.vedant.chattingapp.loginFragment;
import com.vedant.chattingapp.signUpFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class loginAdapter extends FragmentPagerAdapter {

    public Context context1;
    int totalTabs1;

    public loginAdapter(@NonNull FragmentManager fm,Context context,int totalTabs) {
        super(fm);
        this.context1 = context;
        this.totalTabs1 = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new loginFragment();

            case 1:
                return new signUpFragment();

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return totalTabs1;
    }
}
