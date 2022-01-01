package com.vedant.chattingapp.Adapters;

import android.content.Context;

import com.vedant.chattingapp.tab_fragments.Status_Fragment;
import com.vedant.chattingapp.tab_fragments.call_Fragment;
import com.vedant.chattingapp.tab_fragments.chat_Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class fragmentAdapter extends FragmentStateAdapter {

    public static final int NUM_PAGES = 3;
    public Context context1;
    int totalTabs1;

    public fragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle
            , Context context, int totalTabs) {
        super(fragmentManager, lifecycle);
        this.context1 = context;
        this.totalTabs1 = totalTabs;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {

            case 0:
                return new chat_Fragment();

            case 1:
                return new Status_Fragment();

            case 2:
                return new call_Fragment();

            default:
                return null;


        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }


}
